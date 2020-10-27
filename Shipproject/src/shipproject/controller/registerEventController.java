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
import shipproject.data.reserveDAO;
import shipproject.data.userDAO;
import shipproject.model.Events;
import shipproject.model.reserve;
import shipproject.model.reserveErrorMsgs;
import shipproject.model.user;

@WebServlet("/registerEventController")
public class registerEventController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	   
//	private void getEventParam(HttpServletRequest request, Events events) {
//		events.setEvent(request.getParameter("eventname"),request.getParameter("location"),request.getParameter("capacity"),request.getParameter("duration"), request.getParameter("type"),request.getParameter("date"),request.getParameter("managerid"),request.getParameter("time"),Integer.parseInt(request.getParameter("id_event")),Integer.parseInt(request.getParameter("idcreate")),request.getParameter("estCap"));  
//	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
//		String action = request.getParameter("action"), url="";;
		session.removeAttribute("errorMsgs");
		
		//List all events
//				if(action.equalsIgnoreCase("psg_view_all_events")) {
//					System.out.println("found func to view all events");
//					ArrayList<Events> eventsInDB = new ArrayList<Events>();
//				//	System.out.println("events in DB="+eventsInDB);
//					eventsInDB=eventsDAO.listevents();
//				//	System.out.println("events in DB after query="+eventsInDB);
//					session.setAttribute("EVENTS", eventsInDB);	
//					url="/psg_view_specific_event.jsp";	
//					getServletContext().getRequestDispatcher(url).forward(request, response);
//				}
//				else // redirect all other gets to post
					doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		user loginU=new user();
		session.removeAttribute("errorMsgs");
		session.removeAttribute("andy3");
//Register specific Event
		if (action.equalsIgnoreCase("registerSpecifiedEvent") )  { 
			reserve reserve=new reserve();
			reserve.setIdcreate(Integer.parseInt(request.getParameter("id")));
			loginU=(user) session.getAttribute("loginU");
			reserve.setId_user(loginU.getId_user());
			reserveErrorMsgs errormsg=new reserveErrorMsgs();
			reserve.validatereservation(action, reserve, errormsg);
			if(!errormsg.getErrorMsg().equals("")){
				session.setAttribute("andy3",errormsg);
				url="/psg_view_specific_event.jsp";
			}
			else {
				reserveDAO.createreserve(reserve);
				url="/reservation_confirmation.jsp";
			}
			    
		}

// view registerd events		
		if (action.equalsIgnoreCase("psg_viewRegisteredEvent") )  { 
			session.removeAttribute("REG_EVENTS");
			System.out.println("Controller-Viewing regsiterd event of the user");
			ArrayList<Events> res_eventInDB = new ArrayList<Events>();
			user luser=new user();
			luser= (user) session.getAttribute("loginU");
			int userId = luser.getId_user();
			System.out.println("User ID ="+ userId);
			res_eventInDB=eventsDAO.searchEventbyUser(userId);
			System.out.println(res_eventInDB);
			for(int i=0;i<res_eventInDB.size();i++) {
				ArrayList<reserve> list=new ArrayList<reserve>();
				list=reserveDAO.capSearch(res_eventInDB.get(i).getIdcreate());
				res_eventInDB.get(i).setEstCap(String.valueOf(list.size()));
			}
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
					ArrayList<reserve> list=new ArrayList<reserve>();
					list=reserveDAO.capSearch(selectedEvent.getIdcreate());
					selectedEvent.setEstCap(String.valueOf(list.size()));
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


//	private boolean checkforEventValidation(HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		System.out.println("In checkforEventValidation");
//		boolean status = true;
//		int createdEventId = Integer.parseInt(request.getParameter("id"));
//		System.out.println("Event ID ="+ createdEventId);
//		
//		String eventType = request.getParameter("event_type");
//		System.out.println("Event type ="+ eventType);
//		String eventDate = request.getParameter("event_date");
//		System.out.println("Event Date ="+ eventDate);
//		int userId = Integer.parseInt(request.getParameter("userId"));
//		System.out.println("User ID ="+ userId);
//		int eventCapacity = Integer.parseInt(request.getParameter("evnt_capacity"));
//		System.out.println("Event capacity ="+ eventCapacity);
//		String eventName = request.getParameter("evnt_Name");
//		System.out.println("Event name ="+ eventName);
//		
//		
//		
//	//for checking estimated < capacity
//		ArrayList<Events> attendees_eventsInDB = new ArrayList<Events>();
//		attendees_eventsInDB = eventsDAO.fetchCapacity(eventDate, eventName);
//		System.out.println("capacity of events result size="+ attendees_eventsInDB.size());
//		if (attendees_eventsInDB.size() >= eventCapacity)
//		{
//			return false;
//		}
//		
//		
//	// for checking count of Athletic and show event
//		ArrayList<Events> val_eventsInDB = new ArrayList<Events>();
//		val_eventsInDB=eventsDAO.countReservedEventsForUser(eventType, eventDate, userId);
//		System.out.println("events result size="+ val_eventsInDB.size());
//		if (eventType.equalsIgnoreCase("Athletic")) {
//			if(val_eventsInDB.size() > 2)
//			{ status = false;}
//		}
//		else if (eventType.equalsIgnoreCase("Show")) {
//			if(val_eventsInDB.size() > 1)
//			{ status = false;}
//		}
//		return status;
//	}


}
