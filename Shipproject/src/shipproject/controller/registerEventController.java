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

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("errorMsgs");
					doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		user loginU=new user();
		session.removeAttribute("errorMsgs");
		session.removeAttribute("andy3");
//		session.removeAttribute("REG_EVENTS");
//		session.removeAttribute("EVENTS");
//		session.removeAttribute("cordinator");
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
			ArrayList<Events> res_eventInDB = new ArrayList<Events>();
			user luser=new user();
			luser= (user) session.getAttribute("loginU");
			int userId = luser.getId_user();
			res_eventInDB=eventsDAO.searchEventbyUser(userId);
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
					url="/psg_view_specific_event.jsp";	
					ArrayList<Events> eventsInDB = new ArrayList<Events>();
					Events selectedEvent = new Events();
					eventsInDB=eventsDAO.psg_searchevent(Integer.parseInt(request.getParameter("id")));
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
}
