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
		
//List specific Event
		if (action.equalsIgnoreCase("registerSpecifiedEvent") )  { 
			System.out.println("Registering event");
			url="/reservation_confirmation.jsp";	
	/*		ArrayList<Events> eventsInDB = new ArrayList<Events>();
			Events selectedEvent = new Events();
		/*	if (request.getParameter("radioCompany")!=null) {
				selectedCompanyIndex = Integer.parseInt(request.getParameter("radioCompany")) - 1;
				companyInDB=CompanyDAO.listCompanies(); 
				selectedCompany.setCompany(	companyInDB.get(selectedCompanyIndex).getIdcompany(), companyInDB.get(selectedCompanyIndex).getCompany_name(), 
						companyInDB.get(selectedCompanyIndex).getPhone(), companyInDB.get(selectedCompanyIndex).getEmail());
				session.setAttribute("COMPANIES", selectedCompany);
				url="/ListSpecificCompany.jsp";					
			}	
			else { // determine if Submit button was clicked without selecting a company
				if (request.getParameter("ListSelectedCompanyButton")!=null) {
					String errorMsgs =  "Please select a company";
					session.setAttribute("errorMsgs",errorMsgs);
					url="/listCompany.jsp";					
				}
				else { //view button was used instead of radio button
				
					eventsInDB=eventsDAO.searchEvents(request.getParameter("id"));
					System.out.println("View button clicked");
					System.out.println("eventsInDb= "+eventsInDB);
					System.out.println("eventsInDb= "+	eventsInDB.get(0).getIdevents());
					
					selectedEvent.setEvent(	eventsInDB.get(0).getIdevents(), eventsInDB.get(0).getEventName(), 
							eventsInDB.get(0).getLocation(), eventsInDB.get(0).getCapacity(), 
							eventsInDB.get(0).getDuration(), eventsInDB.get(0).getType());
					session.setAttribute("EVENTS", selectedEvent);
					url="/psg_view_specific_event.jsp";		*/			
				}
				
	//	}
	
	
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}


}
