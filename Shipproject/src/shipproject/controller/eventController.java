package shipproject.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import shipproject.model.Events;
import shipproject.model.EventsErrorMsgs;
import shipproject.model.reserve;
import shipproject.model.user;
import shipproject.data.eventsDAO;
import shipproject.data.reserveDAO;
import shipproject.data.userDAO;


/**
 * Servlet implementation class eventController
 */
@WebServlet("/eventController")
public class eventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
//	private void getEventParam (HttpServletRequest request, Events events) {
//		events.setEvent(request.getParameter("eventname"),request.getParameter("location"),request.getParameter("capacity"),request.getParameter("duration"), request.getParameter("type"), request.getParameter("date"), request.getParameter("managerid"), request.getParameter("time"), Integer.parseInt(request.getParameter("id_event")),Integer.parseInt(request.getParameter("idcreate")),request.getParameter("estCap"));  
//	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		session.removeAttribute("cordinator");//Msgs
		session.removeAttribute("dateevent");
		session.removeAttribute("andy3");
//Event Manger - View List of events
		if (action.equalsIgnoreCase("eventmanagereventlist")) {
			ArrayList<Events> eventInDB = new ArrayList<Events>();
			eventInDB= eventsDAO.listevents();
			session.setAttribute("andy0EVENTS", eventInDB);			
			getServletContext().getRequestDispatcher("/eventmanagereventlist.jsp").forward(request, response);
		}
		
//Passenger - List all events
		else if(action.equalsIgnoreCase("psg_view_all_events")) {
			session.removeAttribute("andyEVENTS");
			System.out.println("found func to view all events");
			ArrayList<Events> eventsInDB = new ArrayList<Events>();
		//	System.out.println("events in DB="+eventsInDB);
			eventsInDB=eventsDAO.listevents();
		//	System.out.println("events in DB after query="+eventsInDB);
			for(int i=0;i<eventsInDB.size();i++) {
				ArrayList<reserve> list=new ArrayList<reserve>();
				list=reserveDAO.capSearch(eventsInDB.get(i).getIdcreate());
				eventsInDB.get(i).setEstCap(String.valueOf(Integer.parseInt(eventsInDB.get(i).getCapacity())-list.size()));
			}
			session.setAttribute("andyEVENTS", eventsInDB);				
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
		//session.removeAttribute("cordinator");//Msgs
		session.removeAttribute("dateevent");
		session.removeAttribute("Msgs");//
		session.removeAttribute("CordinatorList");
		session.removeAttribute("simpleEventList");
		session.removeAttribute("create");//
		session.removeAttribute("createMsgs");//ModifyMsgs
		session.removeAttribute("ModifyMsgs");//success
		//session.removeAttribute("success");corMsgs
		session.removeAttribute("corMsgs");
		int selectedeventIndex;
		Events events=new Events();
//		if(action.equalsIgnoreCase("Eventmanagercreateevent")) {
//			session.removeAttribute("success");
//		
//			events.setEvent2(Integer.parseInt(request.getParameter("eventid")),request.getParameter("date"), request.getParameter("coordinatorid"),request.getParameter("time"), request.getParameter("estCap"));
//			ArrayList<Events> eventDB=new ArrayList<Events>();
//			eventDB=eventsDAO.simpleEventlistint(events.getId_event());
////			System.out.println(events.getId_event());
////			System.out.println(events.getDate());
////			System.out.println(events.getManagerid());
////			System.out.println(events.getTime());
////			System.out.println(events.getEstCap());
//			events.setEventname(eventDB.get(0).getEventname());
//			events.setLocation(eventDB.get(0).getLocation());
//			events.setCapacity(eventDB.get(0).getCapacity());
//			events.setDuration(eventDB.get(0).getDuration());
//			events.setType(eventDB.get(0).getType());
////			EventsErrorMsgs errorMsg=new EventsErrorMsgs();
////			events.validateEvent(action, events, errorMsg);
//			session.setAttribute("create",events);
//			ArrayList<user> CordinatorsinDB=new ArrayList<user>();
//			CordinatorsinDB=userDAO.searchCoordinator();
//			ArrayList<Events> eventsInDB = new ArrayList<Events>();
//			eventsInDB=eventsDAO.simpleEventlist();
//			session.setAttribute("CordinatorList",CordinatorsinDB);
//			session.setAttribute("simpleEventList", eventsInDB);
////			if(!errorMsg.getErrorMsg().equals("")) {
////				session.setAttribute("createMsgs",errorMsg);
////				url="/Eventmanagercreateevent.jsp";
////			}
////			else {
//				eventsDAO.createEvent(events);
//				url="/Eventmanagerhomepage.jsp";
//				session.removeAttribute("create");
////			}
//		}
//		else if(action.equalsIgnoreCase("Eventmanagermodifyevent")) {
		if(action.equalsIgnoreCase("Eventmanagermodifyevent")) {
			session.removeAttribute("success");
			int ids=Integer.parseInt(request.getParameter("id"));
			ArrayList<Events> eventInDBs = new ArrayList<Events>();
			Events selectedevent = new Events();
			eventInDBs=eventsDAO.searchevent(ids);
			//selectedevent.setEvent(eventname, location, capacity, duration, type, date, managerid, time, id_event, idcreate);
			selectedevent.setEvent(eventInDBs.get(0).getEventname(), eventInDBs.get(0).getLocation(),eventInDBs.get(0).getCapacity(), eventInDBs.get(0).getDuration(),  eventInDBs.get(0).getType(),  eventInDBs.get(0).getDate(),  eventInDBs.get(0).getManagerid(),eventInDBs.get(0).getTime(), eventInDBs.get(0).getId_event(), eventInDBs.get(0).getIdcreate(),eventInDBs.get(0).getEstCap());
		    String cdate=request.getParameter("date");
		    String ctime=request.getParameter("time");
		    String cestCap=request.getParameter("estCap");
		    EventsErrorMsgs errorMsg=new EventsErrorMsgs();
		    try {
				events.validateEventModify(action, selectedevent, errorMsg, cdate, ctime, cestCap);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    if(!errorMsg.getErrorMsg().equals("")) {
		    	session.setAttribute("EVENTS", selectedevent);
		    	session.setAttribute("ModifyMsgs", errorMsg);
		    	url="/EventModify.jsp";
		    }
		    else {
		    	//success message
		    	errorMsg.setErrorMsg("Modified Successfully");
		    	session.setAttribute("success",errorMsg);
		    	eventsDAO.modifyevent(selectedevent, cdate, cestCap, ctime);
			    url="/eventController?action=listSpecificevent&id="+selectedevent.getIdcreate();
		    }
		}
		else if(action.equalsIgnoreCase("Eventmanagerassignevent")) {
			session.removeAttribute("success");
			int ids=Integer.parseInt(request.getParameter("id"));
			ArrayList<Events> eventInDBs = new ArrayList<Events>();
			Events selectedevent = new Events();
			eventInDBs=eventsDAO.searchevent(ids);
			//selectedevent.setEvent(eventname, location, capacity, duration, type, date, managerid, time, id_event, idcreate);
			selectedevent.setEvent(eventInDBs.get(0).getEventname(), eventInDBs.get(0).getLocation(),eventInDBs.get(0).getCapacity(), eventInDBs.get(0).getDuration(),  eventInDBs.get(0).getType(),  eventInDBs.get(0).getDate(),  eventInDBs.get(0).getManagerid(),eventInDBs.get(0).getTime(), eventInDBs.get(0).getId_event(), eventInDBs.get(0).getIdcreate(),eventInDBs.get(0).getEstCap());
		    String id2=request.getParameter("coordinatorid");
		    EventsErrorMsgs errorMsg=new EventsErrorMsgs();
		    //errorMsg.setErrorMsg("Modified Successfully");
		    try {
				events.validateEventCor(action, selectedevent, errorMsg, id2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    if(!errorMsg.getErrorMsg().equals("")) {
		    	session.setAttribute("EVENTS", selectedevent);//corMsgs
		    	session.setAttribute("corMsgs", errorMsg);
		    	ArrayList<user> CordinatorsinDB=new ArrayList<user>();
				CordinatorsinDB=userDAO.searchCoordinator();
				session.setAttribute("CordinatorList",CordinatorsinDB);
				url="/ChangeCodinator.jsp";
		    }
		    else {
		    	errorMsg.setErrorMsg("Assigned New Coordinator Successfully");
		    	session.setAttribute("success",errorMsg);
		    	eventsDAO.assigncor(selectedevent, id2);
		    	url="/eventController?action=listSpecificevent&id="+selectedevent.getIdcreate();
		    	
		    }
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
				selectedEvent.setEstCap(String.valueOf(Integer.parseInt(selectedEvent.getCapacity())-list.size()));
			

			session.setAttribute("EVENTS", selectedEvent);
			
			ArrayList<user> UserinDB=new ArrayList<user>();
			UserinDB=userDAO.searchUserbyID(selectedEvent.getManagerid());
			user cordinator=new user();
			cordinator.setUser(UserinDB.get(0).getUsername(), UserinDB.get(0).getFirst_name(), UserinDB.get(0).getLast_name(), UserinDB.get(0).getPassword(), UserinDB.get(0).getRole(), UserinDB.get(0).getPhone(), UserinDB.get(0).getEmail(), UserinDB.get(0).getMemtype(), UserinDB.get(0).getRoom_number(), UserinDB.get(0).getDeck_number());
			session.setAttribute("cordinator",cordinator);
			url="/psg_view_specific_event.jsp";					
		}
		else if (action.equalsIgnoreCase("redirectPagedatetime") ){
			session.removeAttribute("success");
			String currentdate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			String currentTime =new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
			Events dateevent = new Events();
			dateevent.setDate(currentdate);
			dateevent.setTime(currentTime);
			session.setAttribute("dateevent",dateevent);
			url="/dateselect.jsp";
		}
		else if(action.equalsIgnoreCase("redirectPageTypedatetime")) {
			session.removeAttribute("success");
			session.removeAttribute("andyEVENTS");
			String currentdate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			String currentTime =new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
			Events dateevent = new Events();
			dateevent.setDate(currentdate);
			dateevent.setTime(currentTime);
			session.setAttribute("dateeventandy",dateevent);
			url="/datetypeselect.jsp";
		}
		else if(action.equalsIgnoreCase("redirectPagedatetimeAssigned")) {
			session.removeAttribute("success");
			String currentdate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			String currentTime =new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
			Events dateevent = new Events();
			dateevent.setDate(currentdate);
			dateevent.setTime(currentTime);
			session.setAttribute("dateeventandy2",dateevent);
			url="/datetypeselectassigned.jsp";
		}
		else if(action.equalsIgnoreCase("redirectAssignCor")) {
			session.removeAttribute("success");
			ArrayList<user> CordinatorsinDB=new ArrayList<user>();
			CordinatorsinDB=userDAO.searchCoordinator();
			session.setAttribute("CordinatorList",CordinatorsinDB);
			url="/ChangeCodinator.jsp";
		}

		else if(action.equalsIgnoreCase("eventAssignedSearch")) {
			session.removeAttribute("success");
			String date=request.getParameter("date");
			String time=request.getParameter("time");
			user loginU=(user) session.getAttribute("loginU");
			Events datecheck=new Events();
			EventsErrorMsgs errorMsg=new EventsErrorMsgs();
			datecheck.setDate(date);
			datecheck.setTime(time);
			datecheck.setManagerid(Integer.toString(loginU.getId_user()));
			try {
				datecheck.validateEvent(action, datecheck, errorMsg);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!errorMsg.getErrorMsg().equals("")) {
				url="/datetypeselectassigned.jsp";
				datecheck.setTime(time);
				session.setAttribute("dateeventandy2",datecheck);
				session.setAttribute("Msgs",errorMsg);
				
			}
			else {
				ArrayList<Events> eventInDB = new ArrayList<Events>();
				eventInDB=eventsDAO.searcheventbydateassigned(date, time,loginU.getId_user());
				eventInDB.addAll(eventsDAO.searchgreaterdateassigned(date,loginU.getId_user()));
				session.setAttribute("andyEVENTScor", eventInDB);
				url="/corodinatoreventlist.jsp";
			}
		}
		else if(action.equalsIgnoreCase("eventtypeSearch")) {
			session.removeAttribute("success");
			String date=request.getParameter("date");
			String time=request.getParameter("time");
			String type=request.getParameter("type");
			Events datecheck=new Events();
			EventsErrorMsgs errorMsg=new EventsErrorMsgs();
			datecheck.setDate(date);
			datecheck.setTime(time);
			datecheck.setType(type);
			try {
				datecheck.validateEvent(action, datecheck, errorMsg);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!errorMsg.getErrorMsg().equals("")) {
				url="/datetypeselect.jsp";
				datecheck.setTime(time);
				session.setAttribute("dateeventandy",datecheck);
				session.setAttribute("Msgs",errorMsg);
			}
			else {
				session.removeAttribute("andyEVENTS");
				ArrayList<Events> eventInDB = new ArrayList<Events>();
				eventInDB=eventsDAO.searcheventbydatetype(date,time,type);
				eventInDB.addAll(eventsDAO.searchgreaterdatetype(date,type));
				for(int i=0;i<eventInDB.size();i++) {
					ArrayList<reserve> list=new ArrayList<reserve>();
					list=reserveDAO.capSearch(eventInDB.get(i).getIdcreate());
					eventInDB.get(i).setEstCap(String.valueOf(Integer.parseInt(eventInDB.get(i).getCapacity())-list.size()));
				}
				session.setAttribute("andyEVENTS", eventInDB);
				url="/psg_view_all_events.jsp";
				
			}
		}
		else if(action.equalsIgnoreCase("eventSearch")) {
			session.removeAttribute("success");
			String date=request.getParameter("date");
			String time=request.getParameter("time");
			Events datecheck=new Events();
			EventsErrorMsgs errorMsg=new EventsErrorMsgs();
			datecheck.setDate(date);
			datecheck.setTime(time);
			try {
				datecheck.validateEvent(action, datecheck, errorMsg);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!errorMsg.getErrorMsg().equals("")) {
				url="/dateselect.jsp";
				datecheck.setTime(time);
				session.setAttribute("dateevent",datecheck);
				session.setAttribute("Msgs",errorMsg);
			}
			else {
			ArrayList<Events> eventInDB = new ArrayList<Events>();
			eventInDB=eventsDAO.searcheventbydate(date, time);
			eventInDB.addAll(eventsDAO.searchgreaterdate(date));
			session.setAttribute("EVENTS", eventInDB);
			session.setAttribute("andy0EVENTS", eventInDB);
			session.setAttribute("andyEVENTScor", eventInDB);
			user loginU=(user) session.getAttribute("loginU");
			if(loginU.getRole().equalsIgnoreCase("passenger")) {
				for(int i=0;i<eventInDB.size();i++) {
					ArrayList<reserve> list=new ArrayList<reserve>();
					list=reserveDAO.capSearch(eventInDB.get(i).getIdcreate());
					eventInDB.get(i).setEstCap(String.valueOf(Integer.parseInt(eventInDB.get(i).getCapacity())-list.size()));
				}
				session.setAttribute("andyEVENTS", eventInDB);
				url="/psg_view_all_events.jsp";
			}
			else if(loginU.getRole().equalsIgnoreCase("manager")) {
				url="/eventmanagereventlist.jsp";
			}
			else {
				url="/corodinatoreventlist.jsp";
			}
			}
		}

		else if(action.equalsIgnoreCase("eventassignedtlist")){
			user loginU=(user) session.getAttribute("loginU");
			int id=loginU.getId_user();
			ArrayList<Events> eventInDB = new ArrayList<Events>();
			eventInDB= eventsDAO.listcorevents(id);
			session.setAttribute("andyEVENTScor", eventInDB);			
			url="/corodinatoreventlist.jsp";
		}
		else if(action.equalsIgnoreCase("listcorSpecificevent")) {
			ArrayList<Events> eventInDBs = new ArrayList<Events>();
			Events selectedevent = new Events();
			int ids=Integer.parseInt(request.getParameter("id"));
			eventInDBs=eventsDAO.searchevent(ids);
			//selectedevent.setEvent(eventname, location, capacity, duration, type, date, managerid, time, id_event, idcreate);
			selectedevent.setEvent(eventInDBs.get(0).getEventname(), eventInDBs.get(0).getLocation(),eventInDBs.get(0).getCapacity(), eventInDBs.get(0).getDuration(),  eventInDBs.get(0).getType(),  eventInDBs.get(0).getDate(),  eventInDBs.get(0).getManagerid(),eventInDBs.get(0).getTime(), eventInDBs.get(0).getId_event(), eventInDBs.get(0).getIdcreate(),eventInDBs.get(0).getEstCap());
			session.setAttribute("EVENTScor", selectedevent);
			ArrayList<user> UserinDB=new ArrayList<user>();
			UserinDB=userDAO.searchUserbyID(selectedevent.getManagerid());
			user cordinator=new user();
			cordinator.setUser(UserinDB.get(0).getUsername(), UserinDB.get(0).getFirst_name(), UserinDB.get(0).getLast_name(), UserinDB.get(0).getPassword(), UserinDB.get(0).getRole(), UserinDB.get(0).getPhone(), UserinDB.get(0).getEmail(), UserinDB.get(0).getMemtype(), UserinDB.get(0).getRoom_number(), UserinDB.get(0).getDeck_number());
			session.setAttribute("cordinatorcor",cordinator);
			url="/coordinatorspecificlist.jsp";
		}		
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
