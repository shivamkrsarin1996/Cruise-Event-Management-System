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
import shipproject.model.userErrorMsgs;
import shipproject.data.eventsDAO;
import shipproject.data.userDAO;


/**
 * Servlet implementation class eventController
 */
@WebServlet("/eventController")
public class eventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private void getEventParam (HttpServletRequest request, Events events) {
		events.setEvent(request.getParameter("eventname"),request.getParameter("location"),request.getParameter("capacity"),request.getParameter("duration"), request.getParameter("type"), request.getParameter("date"), request.getParameter("managerid"), request.getParameter("time"), Integer.parseInt(request.getParameter("id_event")),Integer.parseInt(request.getParameter("idcreate")));  
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("eventmanagereventlist")) {
			ArrayList<Events> eventInDB = new ArrayList<Events>();
			eventInDB= eventsDAO.listevents();
			session.setAttribute("EVENTS", eventInDB);			
			getServletContext().getRequestDispatcher("/eventmanagereventlist.jsp").forward(request, response);
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
		else if(action.equalsIgnoreCase("listSpecificevent")){//request.getParameter("id")
			ArrayList<Events> eventInDBs = new ArrayList<Events>();
			Events selectedevent = new Events();
			int ids=Integer.parseInt(request.getParameter("id"));
			eventInDBs=eventsDAO.searchevent(ids);
			//selectedevent.setEvent(eventname, location, capacity, duration, type, date, managerid, time, id_event, idcreate);
			selectedevent.setEvent(eventInDBs.get(0).getEventname(), eventInDBs.get(0).getLocation(),eventInDBs.get(0).getCapacity(), eventInDBs.get(0).getDuration(),  eventInDBs.get(0).getType(),  eventInDBs.get(0).getDate(),  eventInDBs.get(0).getManagerid(),eventInDBs.get(0).getTime(), eventInDBs.get(0).getId_event(), eventInDBs.get(0).getIdcreate());
			session.setAttribute("EVENTS", selectedevent);
			url="/listspecificevent.jsp";	
		}
//		else {
//			ArrayList<Events> eventInDB = new ArrayList<Events>();
//			Events event = new Events();
//			if (request.getParameter("radioCompany")!=null) {
//				selectedeventIndex = Integer.parseInt(request.getParameter("radioCompany")) - 1;
//				 eventInDB=eventsDAO.listevents(); 
//				 event.setEvent(eventInDB.get(selectedeventIndex).getEventname(),  eventInDB.get(selectedeventIndex).getLocation(), 
//						 eventInDB.get(selectedeventIndex).getCapacity(),eventInDB.get(selectedeventIndex).getDuration(),eventInDB.get(selectedeventIndex).getType(),eventInDB.get(selectedeventIndex).getDate(), eventInDB.get(selectedeventIndex).getManagerid(),eventInDB.get(selectedeventIndex).getTime(),eventInDB.get(selectedeventIndex).getId_event(),eventInDB.get(selectedeventIndex).getIdcreate());
//				session.setAttribute("EVENTS", events);
////				ArrayList<user> userListInDBs=new ArrayList<user>();
////				userListInDBs=userDAO.username(event.getManagerid());
////				String first_name=userListInDBs.get()
//				url="/listspecificevent.jsp";	
//			
//		}
//		}

		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	
	}

}
