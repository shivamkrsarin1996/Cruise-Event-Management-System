package shipproject.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shipproject.data.eventsDAO;
import shipproject.data.userDAO;
import shipproject.model.Events;
import shipproject.model.user;
import shipproject.model.userErrorMsgs;

@WebServlet("/registerEventController")
public class registerEventController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	   
	private void getEventParam(HttpServletRequest request, Events events) {
		events.setEvent(request.getParameter("eventname"),request.getParameter("location"),request.getParameter("capacity"),request.getParameter("duration"), request.getParameter("type"),request.getParameter("date"),request.getParameter("managerid"),request.getParameter("time"),Integer.parseInt(request.getParameter("id_event")),Integer.parseInt(request.getParameter("idcreate")),request.getParameter("estCap"));  
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action"), url="";;
		session.removeAttribute("errorMsgs");
		
		//List all events
				if(action.equalsIgnoreCase("psg_view_all_events")) {
					System.out.println("found func to view all events");
					ArrayList<Events> eventsInDB = new ArrayList<Events>();
				//	System.out.println("events in DB="+eventsInDB);
					eventsInDB=eventsDAO.listevents();
				//	System.out.println("events in DB after query="+eventsInDB);
					session.setAttribute("EVENTS", eventsInDB);	
					url="/psg_view_specific_event.jsp";	
					getServletContext().getRequestDispatcher(url).forward(request, response);
				}
				else // redirect all other gets to post
					doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		user user=new user();
		userErrorMsgs UerrorMsgs=new userErrorMsgs();
		session.removeAttribute("errorMsgs");
		
//Register specific Event
		if (action.equalsIgnoreCase("registerSpecifiedEvent") )  { 
	
	//checking for validation for no. of events		
			boolean checkCount = checkforEventValidation(request);
		if (checkCount == true) {
			System.out.println("Registering event in controller");
			
			int reservationID = eventsDAO.searchReservationId();
			ArrayList<Events> eventsInDB = new ArrayList<Events>();
			Events reservingEvent = new Events();
			//eventsInDB=eventsDAO.searchEvents(request.getParameter("id"));
			System.out.println("Reserve button clicked");
			
			
			System.out.println("Reservation ID=" +reservationID);
			int createdEventId = Integer.parseInt(request.getParameter("id"));
				System.out.println("Event ID ="+ createdEventId);
			int userId = Integer.parseInt(request.getParameter("userId"));
				System.out.println("User ID ="+ userId);
				
				
		//		insert into reservation
				eventsDAO.createReservation(reservationID, createdEventId, userId);

			url="/reservation_confirmation.jsp";	
			}
		else 
		{
			System.out.println("Limit exceeded");
			url="/psg_view_specific_event.jsp";	
		}
		}

// view registerd events		
		if (action.equalsIgnoreCase("psg_viewRegisteredEvent") )  { 
			System.out.println("Controller-Viewing regsiterd event of the user");
			ArrayList<Events> res_eventInDB = new ArrayList<Events>();
			int userId = Integer.parseInt(request.getParameter("userId"));
			System.out.println("User ID ="+ userId);
			res_eventInDB=eventsDAO.searchEventbyUser(userId);
			System.out.println(res_eventInDB);
			session.setAttribute("REG_EVENTS", res_eventInDB);	

			url="/psg_reserved_events.jsp";	
		
		}
		
		
//Passenger - List specific Event 
				else if (action.equalsIgnoreCase("psg_listSpecificEvent") )  { 
					System.out.println("List specific company");
					url="/psg_view_specific_event.jsp";	
					ArrayList<Events> eventsInDB = new ArrayList<Events>();
					Events selectedEvent = new Events();
					eventsInDB=eventsDAO.psg_searchevent(Integer.parseInt(request.getParameter("id")));
					System.out.println("View button clicked");
					System.out.println("eventsInDb= "+eventsInDB);
					System.out.println("eventsInDb= "+	eventsInDB.get(0).getId_event());
					selectedEvent.setEvent(eventsInDB.get(0).getEventname(), eventsInDB.get(0).getLocation(),
							eventsInDB.get(0).getCapacity(), eventsInDB.get(0).getDuration(),  eventsInDB.get(0).getType(), 
							eventsInDB.get(0).getDate(),  eventsInDB.get(0).getManagerid(),eventsInDB.get(0).getTime(),
							eventsInDB.get(0).getId_event(), eventsInDB.get(0).getIdcreate(),eventsInDB.get(0).getEstCap());

					session.setAttribute("EVENTS", selectedEvent);
					
					ArrayList<user> UserinDB=new ArrayList<user>();
					UserinDB=userDAO.searchUserbyID(selectedEvent.getManagerid());
					user cordinator=new user();
					cordinator.setUser(UserinDB.get(0).getUsername(), UserinDB.get(0).getFirst_name(), UserinDB.get(0).getLast_name(), UserinDB.get(0).getPassword(), UserinDB.get(0).getRole(), UserinDB.get(0).getPhone(), UserinDB.get(0).getEmail(), UserinDB.get(0).getMemtype(), UserinDB.get(0).getRoom_number(), UserinDB.get(0).getDeck_number());
					session.setAttribute("cordinator",cordinator);
					url="/psg_view_specific_reg_event.jsp";					
				}
				
	//	}
	
	
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}


	private boolean checkforEventValidation(HttpServletRequest request) {
		// TODO Auto-generated method stub
		boolean status = true;
		System.out.println("In checkforEventValidation");
		String eventType = request.getParameter("event_type");
		System.out.println("Event type ="+ eventType);
		String eventDate = request.getParameter("event_date");
		System.out.println("Event Date ="+ eventDate);
		int userId = Integer.parseInt(request.getParameter("userId"));
		System.out.println("User ID ="+ userId);
		
		ArrayList<Events> val_eventsInDB = new ArrayList<Events>();
		val_eventsInDB=eventsDAO.countReservedEventsForUser(eventType, eventDate, userId);
		System.out.println("events result size="+ val_eventsInDB.size());
		if (eventType.equalsIgnoreCase("Athletic")) {
			if(val_eventsInDB.size() > 2)
			{ status = false;}
		}
		else if (eventType.equalsIgnoreCase("Show")) {
			if(val_eventsInDB.size() > 1)
			{ status = false;}
		}
		return status;
	}


}
