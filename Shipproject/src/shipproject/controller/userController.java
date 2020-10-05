package shipproject.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shipproject.model.user;
import shipproject.model.userErrorMsgs;
import shipproject.data.userDAO;

/**
 * Servlet implementation class userController
 */
@WebServlet("/userController")
public class userController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private void getUserParam(HttpServletRequest request, user user) {
    	user.setUser(request.getParameter("username"), request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("password"), "passenger", request.getParameter("phone"),request.getParameter("email"), request.getParameter("memtype"), request.getParameter("roomNumber"), request.getParameter("deckNumber"));
    }
    

	private void getUserParam1(HttpServletRequest request, user user) {
		// TODO Auto-generated method stub
		user.setUser1(request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("phone"),
				request.getParameter("email"),  request.getParameter("roomNumber"), request.getParameter("deckNumber"));
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
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		
		userDAO dao=new userDAO();
		HttpSession session = request.getSession();
		user user=new user();
		userErrorMsgs UerrorMsgs=new userErrorMsgs();
		session.removeAttribute("errorMsgs");//
		session.removeAttribute("errorMs");
		if(action.equalsIgnoreCase("registerUser")) {
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
				UerrorMsgs.setCpasswordError("Registered Successfully");
				session.setAttribute("errorMs", UerrorMsgs);
				session.removeAttribute("user");
			}
//			boolean usernameinDB=userDAO.checkusername(user.getUsername());
//			if(usernameinDB) {
//				userDAO.insertuser(user);
//				url="/index.jsp";
//			}
//			else {
//				url="/fail.jsp";
//			}
		}
		else if(action.equalsIgnoreCase("login")) {
			String uname=request.getParameter("username");
			String psw=request.getParameter("password");
			user.setUsername(uname);
			user.setPassword(psw);
			boolean usernameinDB=userDAO.checkusername(uname);
			if(usernameinDB) {
				UerrorMsgs.setUsernameError("Username Does not exist");
				url="/index.jsp";
				session.setAttribute("user", user);//errorMs
				session.setAttribute("errorMs", UerrorMsgs);
			}
			else {
				ArrayList<user> UserinDB=new ArrayList<user>();
				UserinDB=userDAO.Searchusername(uname);
				user seluser=new user();
				seluser.setId_user(UserinDB.get(0).getId_user());
				seluser.setUser(UserinDB.get(0).getUsername(), UserinDB.get(0).getFirst_name(), UserinDB.get(0).getLast_name(), UserinDB.get(0).getPassword(), UserinDB.get(0).getRole(), UserinDB.get(0).getPhone(), UserinDB.get(0).getEmail(), UserinDB.get(0).getMemtype(), UserinDB.get(0).getRoom_number(), UserinDB.get(0).getDeck_number());
				if(seluser.getPassword().equals(psw)) {
					session.setAttribute("loginU", seluser);
					if(seluser.getRole().equals("passenger")) {
						System.out.println("LoginU---"+session.getAttribute("loginU"));
						url="/psg_homepage.jsp";
					//	psg_homepage.jsp
					}
					else if(seluser.getRole().equals("manager")) {
						url="/Eventmanagerhomepage.jsp";
						
					}
					else {
						url="/corodinorhomepage.jsp";
						
					}
				}
				else {
					UerrorMsgs.setUsernameError("Wrong Password");
					url="/index.jsp";
					session.setAttribute("user", user);
					session.setAttribute("errorMs", UerrorMsgs);
				}
			}
		}
		
		else if(action.equalsIgnoreCase("updateProfile"))
		{
				
				getUserParam1(request,user);
				//user.setCpassword(request.getParameter("cpassword"));
				//String rpwd=user.getCpassword();
				user.validateUpdateUser(action, user, UerrorMsgs);
				session.setAttribute("user", user);
			if(!UerrorMsgs.getErrorMsg().equals(""))
			  {
					getUserParam1(request,user);
					//user.setCpassword(rpwd);
					session.setAttribute("errorMsgs",UerrorMsgs);
					url="/psg_updateinfo.jsp";
				}
			else 
			{
			PrintWriter write=response.getWriter();
			user us = new user();
			//us.setUsername(request.getParameter("username"));
			us.setFirst_name(request.getParameter("first_name"));
			us.setLast_name(request.getParameter("last_name"));
			us.setPhone(request.getParameter("phone"));
			us.setEmail(request.getParameter("email"));
			us.setRoom_number(request.getParameter("room_number"));
			us.setDeck_number(request.getParameter("deck_number"));
			
			dao.updateProfile(us);
			request.setAttribute("username", username);
			RequestDispatcher rd=request.getRequestDispatcher("/psg_homepage.jsp");
			rd.include(request, response);
			write.print("<br><font color=green>profile update successfully</font>");
		}
		}
		else if(action.equalsIgnoreCase("logout")) {
			session.removeAttribute("loginU");
			UerrorMsgs.setCpasswordError("Logged Out Successfully");
			session.setAttribute("errorMs", UerrorMsgs);
			session.removeAttribute("user");
			url="/index.jsp";
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}



}
