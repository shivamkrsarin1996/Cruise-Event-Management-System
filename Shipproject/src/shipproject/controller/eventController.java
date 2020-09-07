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
		events.setCompany(request.getParameter("idcompany"),request.getParameter("company_name"),request.getParameter("phone"),request.getParameter("email"));  
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		user user=new user();
		userErrorMsgs UerrorMsgs=new userErrorMsgs();
		session.removeAttribute("errorMsgs");
		
	/*	if(action.equalsIgnoreCase("registerUser")) {
			getUserParam(request,user);
			user.setCpassword(request.getParameter("cpassword"));
			String rpwd=user.getCpassword();
			user.validateUser(action, user, UerrorMsgs);
			session.setAttribute("user", user);
			if(!UerrorMsgs.getErrorMsg().equals("")) {
				getUserParam(request,user);
				user.setCpassword(rpwd);
				session.setAttribute("errorMsgs",UerrorMsgs);
				url="/register.jsp";
			}
			else {
				userDAO.insertuser(user);
				url="/index.jsp";
			}
	
//			boolean usernameinDB=userDAO.checkusername(user.getUsername());
//			if(usernameinDB) {
//				userDAO.insertuser(user);
//				url="/index.jsp";
//			}
//			else {
//				url="/fail.jsp";
//			}
			
//		List companies
		if (action.equalsIgnoreCase("psg_view_all_events")) {
			ArrayList<Events> companyInDB = new ArrayList<Events>();
			companyInDB=eventsDAO.listCompanies();
			session.setAttribute("COMPANIES", companyInDB);				
			getServletContext().getRequestDispatcher("/listCompany.jsp").forward(request, response);
		}
		else // redirect all other gets to post
			doPost(request,response);
			
		}
		*/
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
