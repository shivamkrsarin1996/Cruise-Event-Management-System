package shipproject.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shipproject.model.user;
import shipproject.data.userDAO;


/**
 * Servlet implementation class userController
 */
@WebServlet("/userController")
public class userController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private void getUserParam(HttpServletRequest request, user user) {
    	user.setUser(request.getParameter("username"), request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("password"), "passenger", request.getParameter("phone"),request.getParameter("email"), request.getParameter("memtype"), Integer.parseInt(request.getParameter("roomNumber")), Integer.parseInt(request.getParameter("deckNumber")));
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
		if(action.equalsIgnoreCase("registerUser")) {
			getUserParam(request,user);
			session.setAttribute("user", user);
			ArrayList<user> usernameinDB=new ArrayList<user>();
			ArrayList<user> emailinDB=new ArrayList<user>();
			usernameinDB=userDAO.checkusername(user.getUsername());
			emailinDB=userDAO.checkemail(user.getEmail());
			if(usernameinDB.isEmpty()&&emailinDB.isEmpty()) {
				userDAO.insertuser(user);
				url="/index.jsp";
			}
			else {
				url="/register.jsp";
			}
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
