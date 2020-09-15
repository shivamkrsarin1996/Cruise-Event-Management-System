package shipproject.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import shipproject.model.Events;
import shipproject.model.user;
import shipproject.data.eventsDAO;
import shipproject.data.userDAO;


/**
 * Servlet implementation class eventController
 */
@WebServlet("/eventController")
public class eventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private void getEventParam (HttpServletRequest request, Events events) {
		events.setEvent(request.getParameter("eventname"),request.getParameter("location"),request.getParameter("capacity"),request.getParameter("duration"), request.getParameter("type"), request.getParameter("date"), request.getParameter("managerid"), request.getParameter("time"), Integer.parseInt(request.getParameter("id_event")),Integer.parseInt(request.getParameter("idcreate")),request.getParameter("estCap"));  
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		session.removeAttribute("cordinator");
//Event Manger - View List of events
		if (action.equalsIgnoreCase("eventmanagereventlist")) {
			ArrayList<Events> eventInDB = new ArrayList<Events>();
			eventInDB= eventsDAO.listevents();
			session.setAttribute("EVENTS", eventInDB);			
			getServletContext().getRequestDispatcher("/eventmanagereventlist.jsp").forward(request, response);
		}
		
//Passenger - List all events
		else if(action.equalsIgnoreCase("psg_view_all_events")) {
			System.out.println("found func to view all events");
			ArrayList<Events> eventsInDB = new ArrayList<Events>();
		//	System.out.println("events in DB="+eventsInDB);
			eventsInDB=eventsDAO.listevents();
		//	System.out.println("events in DB after query="+eventsInDB);
			session.setAttribute("EVENTS", eventsInDB);				
			getServletContext().getRequestDispatcher("/psg_view_all_events.jsp").forward(request, response);
		}
		else 		
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		int selectedeventIndex;
		Events events=new Events();
		if(action.equalsIgnoreCase("Eventmanagercreateevent")) {
		
			getEventParam(request,events);
		
			session.setAttribute("events", events);
		
			eventsDAO.insertevent(events);
			url="/index.jsp";	
		
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
			url="/listspecificevent.jsp";
			url="/psg_view_specific_event.jsp";					
		}
//     	else if(action.equalsIgnoreCase("listSpecificevent")){//request.getParameter("id")
//			ArrayList<Events> eventInDBs = new ArrayList<Events>();
//		    Events selectedevent = new Events();
//		    int ids=Integer.parseInt(request.getParameter("id"));
//		    eventInDBs=eventsDAO.searchevent(ids);
//		//selectedevent.setEvent(eventname, location, capacity, duration, type, date, managerid, time, id_event, idcreate);
//		    selectedevent.setEvent(eventInDBs.get(0).getEventname(), eventInDBs.get(0).getLocation(),eventInDBs.get(0).getCapacity(), eventInDBs.get(0).getDuration(),  eventInDBs.get(0).getType(),  eventInDBs.get(0).getDate(),  eventInDBs.get(0).getManagerid(),eventInDBs.get(0).getTime(), eventInDBs.get(0).getId_event(), eventInDBs.get(0).getIdcreate());
//	        session.setAttribute("EVENTS", selectedevent);
//		    url="/listspecificevent.jsp";	
//       	}
		
//	else {
//		    ArrayList<Events> eventInDB = new ArrayList<Events>();
//		    Events event = new Events();
//		    if (request.getParameter("radioCompany")!=null) {
//			    selectedeventIndex = Integer.parseInt(request.getParameter("radioCompany")) - 1;
//			    eventInDB=eventsDAO.listevents(); 
//			    event.setEvent(eventInDB.get(selectedeventIndex).getEventname(),  eventInDB.get(selectedeventIndex).getLocation(), 
//					 eventInDB.get(selectedeventIndex).getCapacity(),eventInDB.get(selectedeventIndex).getDuration(),eventInDB.get(selectedeventIndex).getType(),eventInDB.get(selectedeventIndex).getDate(), eventInDB.get(selectedeventIndex).getManagerid(),eventInDB.get(selectedeventIndex).getTime(),eventInDB.get(selectedeventIndex).getId_event(),eventInDB.get(selectedeventIndex).getIdcreate());
//				session.setAttribute("EVENTS", event);
////				ArrayList<user> userListInDBs=new ArrayList<user>();
////				userListInDBs=userDAO.username(event.getManagerid());
////				String first_name=userListInDBs.get()
//				url="/listspecificevent.jsp";	
//		
//	          }
//			
	//Event Manager- list specified event	
		else {
		ArrayList<Events> eventInDB = new ArrayList<Events>();
		Events event = new Events();
		if (request.getParameter("radioCompany")!=null) {
			selectedeventIndex = Integer.parseInt(request.getParameter("radioCompany")) - 1;
			 eventInDB=eventsDAO.listevents(); 
			 event.setEvent(eventInDB.get(selectedeventIndex).getEventname(),  eventInDB.get(selectedeventIndex).getLocation(), 
					 eventInDB.get(selectedeventIndex).getCapacity(),eventInDB.get(selectedeventIndex).getDuration(),eventInDB.get(selectedeventIndex).getType(),eventInDB.get(selectedeventIndex).getDate(), eventInDB.get(selectedeventIndex).getManagerid(),eventInDB.get(selectedeventIndex).getTime(),eventInDB.get(selectedeventIndex).getId_event(),eventInDB.get(selectedeventIndex).getIdcreate(),eventInDB.get(selectedeventIndex).getEstCap());
			session.setAttribute("EVENTS", event);
			ArrayList<user> UserinDB=new ArrayList<user>();
			UserinDB=userDAO.searchUserbyID(event.getManagerid());
			user cordinator=new user();
			cordinator.setUser(UserinDB.get(0).getUsername(), UserinDB.get(0).getFirst_name(), UserinDB.get(0).getLast_name(), UserinDB.get(0).getPassword(), UserinDB.get(0).getRole(), UserinDB.get(0).getPhone(), UserinDB.get(0).getEmail(), UserinDB.get(0).getMemtype(), UserinDB.get(0).getRoom_number(), UserinDB.get(0).getDeck_number());
			session.setAttribute("cordinator",cordinator);
			url="/listspecificevent.jsp";	
		
	      }
		else if (request.getParameter("ListSelectedCompanyButton")==null) {
			
			ArrayList<Events> eventInDBs = new ArrayList<Events>();
			Events selectedevent = new Events();
			int ids=Integer.parseInt(request.getParameter("id"));
			eventInDBs=eventsDAO.searchevent(ids);
			//selectedevent.setEvent(eventname, location, capacity, duration, type, date, managerid, time, id_event, idcreate);
			selectedevent.setEvent(eventInDBs.get(0).getEventname(), eventInDBs.get(0).getLocation(),eventInDBs.get(0).getCapacity(), eventInDBs.get(0).getDuration(),  eventInDBs.get(0).getType(),  eventInDBs.get(0).getDate(),  eventInDBs.get(0).getManagerid(),eventInDBs.get(0).getTime(), eventInDBs.get(0).getId_event(), eventInDBs.get(0).getIdcreate(),eventInDBs.get(0).getEstCap());
			session.setAttribute("EVENTS", selectedevent);
			ArrayList<user> UserinDB=new ArrayList<user>();
			UserinDB=userDAO.searchUserbyID(selectedevent.getManagerid());
			user cordinator=new user();
			cordinator.setUser(UserinDB.get(0).getUsername(), UserinDB.get(0).getFirst_name(), UserinDB.get(0).getLast_name(), UserinDB.get(0).getPassword(), UserinDB.get(0).getRole(), UserinDB.get(0).getPhone(), UserinDB.get(0).getEmail(), UserinDB.get(0).getMemtype(), UserinDB.get(0).getRoom_number(), UserinDB.get(0).getDeck_number());
			session.setAttribute("cordinator",cordinator);
			url="/listspecificevent.jsp";
			
			}
		
		}
		
		
		

		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	
	}

}
