package shipproject.model;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import shipproject.data.userDAO;
import shipproject.model.userErrorMsgs;

public class user implements Serializable {
	private static final long serialVersionUID = 3L;
	private int id_user;
	private String username;
	private String first_name;
	private String last_name;
	private String password;
	private String cpassword;
	private String role;
	private String phone;
	private String email;
	private String memtype;
	private String room_number;
	private String deck_number;
	
	public void setUser(String username,String first_name, String last_name, String password, String role, String phone,String email,String memtype,String room_number,String deck_number) {
		setUsername(username);
		setFirst_name(first_name);
		setLast_name(last_name);
		setPassword(password);
		setRole(role);
		setPhone(phone);
		setEmail(email);
		setMemtype(memtype);
		setRoom_number(room_number);
		setDeck_number(deck_number);
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user=id_user;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username=username;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name=first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name=last_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword=cpassword;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role=role;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone=phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public String getMemtype() {
		return memtype;
	}
	public void setMemtype(String memtype) {
		this.memtype=memtype;
	}
	public String getRoom_number() {
		return room_number;
	}
	public void setRoom_number(String room_number) {
		this.room_number=room_number;
	}
	public String getDeck_number() {
		return deck_number;
	}
	public void setDeck_number(String deck_number) {
		this.deck_number=deck_number;
	}
	// validate actions
	public void validateUser(String action,user user,userErrorMsgs errorMsgs) {
		if(action.equalsIgnoreCase("registerUser")) {
			errorMsgs.setUsernameError(validateUsername(user.getUsername()));
			errorMsgs.setFirst_nameError(validateFirst_name(user.getFirst_name()));
			errorMsgs.setLast_nameError(validateLast_name(user.getLast_name()));
			errorMsgs.setPasswordError(validatePassword(user.getPassword()));
			errorMsgs.setCpasswordError(validateCpassword(user.getCpassword(),user.getPassword()));
			errorMsgs.setPhoneError(validatePhone(user.getPhone()));
			errorMsgs.setEmailError(validateEmail(user.getEmail()));
			errorMsgs.setDeck_numberError(validateDeck_number(user.getDeck_number()));
			errorMsgs.setRoom_numberError(validateRoom_number(user.getRoom_number()));
		}
		errorMsgs.setErrorMsg();
	}
	// validations feature 
	private String validateDeck_number(String deck_number) {
		String result="";
		if (!isTextAnInteger(deck_number)) {
			result="Deck number must be a number";
		}
		else {
			int inum = Integer.parseInt(deck_number);
			if((inum<1)||(inum>15)) {
				result="Deck number must be between 1(inclusive) and 15(inclusive)";
			}
		}
		return result;
	}
	
	private String validateRoom_number(String room_number) {
		String result="";
		if (!isTextAnInteger(room_number)) {
			result="Room number must be a number";
		}
		else {
			int inum = Integer.parseInt(room_number);
			if((inum<100)||(inum>199)) {
				result="Room number must be between 100(inclusive) and 200(not inclusive)";
			}
		}
		return result;
	}
	private String validateUsername(String username) {
		String result="";
		if(!stringSize(username,5,20)) {
			result="Username must be between 4(not inclusive) and 21(not inclusive) characters long";
		}
		else {
			if(!username.matches("[a-zA-Z].*")) {
				result="Username must start with a letter";
			}
			else {
				if(!username.matches("[a-zA-Z0-9]*")) {
					result="Username must have only letters and numbers";
				}
				else {
					boolean usernameinDB=userDAO.checkusername(username);
					if(!usernameinDB) {
						result="Username already exsits";
					}
				}
			}
		}
		return result;
	}
	
	private String validateLast_name(String last_name) {
		String result="";
		if (!stringSize(last_name,3,29))
			result="Last Name must be between 2(not inclusive) and 30(not inclusive) characters long";
		else{
			//String[] list= {"/","!", "@", "#","$","%","%","^","&","*","(",")","_","+","=","-","`","~",";","<",">",".","?","[","]","{","}",","};
			if(!last_name.matches("[a-zA-Z]*")){
				result="Last Name cannot have numbers of special charectors";
			}
		}
		return result;
	}
	private String validateFirst_name(String first_name) {
		String result="";
		if (!stringSize(first_name,3,29))
			result="First Name must be between 2(not inclusive) and 30 characters long";
		else{
			//String[] list= {"/","!", "@", "#","$","%","%","^","&","*","(",")","_","+","=","-","`","~",";","<",">",".","?","[","]","{","}",","};
			if(!first_name.matches("[a-zA-Z]*")){
				result="First Name cannot have numbers of special charectors";
			}
		}
		return result;
	}
	private String validateCpassword(String cpassword,String password) {
		String result="";
			if(!cpassword.equals(password)) {
				result="Passwords not matching";
			}
		return result;
	}
	private String validatePassword(String password) {
		String result="";
		if(!stringSize(password,8,29))
			result="Password must be between 7(not inclusive) and 30 characters long";
		else {
			String regex = "^(?=.*[0-9])"+ "(?=.*[a-z])(?=.*[A-Z])"+ "(?=.*[@#$%^&+=])"+ "(?=\\S+$).{8,30}$";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(password);
			if(!m.matches()) {
				result="Password must have a number,upper case letter,lower case letter and special character";
			}	
		}
		return result;
	}
	
	private String validatePhone(String phone) {
		String result="";
		if (phone.length()!=10)
			result="Phone number must be 10 digits in length";
		else
			if (!isTextAnInteger(phone))
				result="Phone number must be a number";
		return result;		
	}
	
	private String validateEmail(String email) {
		String result="",extension="";
		if (!email.contains("@"))
			result = "Email address needs to contain @";
		else
			if (!stringSize(email,7,45))
				result="Email address must be between 7 and 45 characters long";
			else {
				extension = email.substring(email.length()-4, email.length());
				if (!extension.equals(".org") && !extension.equals(".edu") && !extension.equals(".com") 
						&& !extension.equals(".net") && !extension.equals(".gov") && !extension.equals(".mil"))
					result = "Invalid domain name";	
			}
		return result;
	}

//	This section is for general purpose methods used internally in this class
	
	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}
	private boolean isTextAnInteger (String string) {
        boolean result;
		try
        {
            Long.parseLong(string);
            result=true;
        } 
        catch (NumberFormatException e) 
        {
            result=false;
        }
		return result;
	}
}