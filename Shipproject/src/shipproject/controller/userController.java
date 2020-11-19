package shipproject.controller;

import java.io.IOException;

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
		session.removeAttribute("errorMsgs");//
		session.removeAttribute("errorMs");
		session.removeAttribute("andy");
		session.removeAttribute("andy2");
		session.removeAttribute("andy3");
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
			user.validateUser(action, user, UerrorMsgs);
			if(UerrorMsgs.getUsernameError().equals("")) {
				session.setAttribute("loginU", user);
				if(user.getRole().equals("passenger")) {
					System.out.println("LoginU---"+session.getAttribute("loginU"));
					url="/psg_homepage.jsp";
					//	psg_homepage.jsp
					}
				else if(user.getRole().equals("manager")) {
					url="/Eventmanagerhomepage.jsp";
					}
				else {
					url="/corodinorhomepage.jsp";
					}
				}
			else {
				url="/index.jsp";
				session.setAttribute("user", user);
				session.setAttribute("errorMs", UerrorMsgs);
				}
			}
		else if(action.equalsIgnoreCase("updateProfile")) {
			user loginU=(user) session.getAttribute("loginU");
			String pass=request.getParameter("password");
			String first=request.getParameter("firstname");
			String last=request.getParameter("lastname");
			String phone=request.getParameter("phone");
			String email=request.getParameter("email");
			String room=request.getParameter("roomNumber");
			String deck=request.getParameter("deckNumber");
			String mem=request.getParameter("memtype");
			user.validateChange(action, loginU, UerrorMsgs, first, last, pass, email, mem, phone, deck, room);
			if(!UerrorMsgs.getErrorMsg().equals("")) {
				session.setAttribute("andy",UerrorMsgs);
				url="/psg_updateinfo.jsp";
			}
			else {
				loginU.setFirst_name(first);
				loginU.setLast_name(last);
				loginU.setPassword(pass);
				loginU.setPhone(phone);
				loginU.setEmail(email);
				loginU.setRoom_number(room);
				loginU.setDeck_number(deck);
				loginU.setMemtype(mem);
				session.setAttribute("loginU", loginU);
				userDAO.updateuser(loginU, first, last, pass, email, mem, phone, deck, room);
				UerrorMsgs.setCpasswordError("Updated Successfully");
				session.setAttribute("andy2",UerrorMsgs);
				url="/psg_info.jsp";
			}
			
			
		}
		//else if(action.equalsIgnoreCase("logout")) {
		else {
			session.removeAttribute("loginU");
			UerrorMsgs.setCpasswordError("Logged Out Successfully");
			session.setAttribute("errorMs", UerrorMsgs);
			session.removeAttribute("user");
			url="/index.jsp";
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
