package shipproject.model;

import java.io.Serializable;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
import java.util.ArrayList;

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
		if(action.equals("registerUser")) {
			errorMsgs.setUsernameError(validateUsername(user.getUsername()));
			errorMsgs.setFirst_nameError(validateFirst_name(user.getFirst_name()));
			errorMsgs.setLast_nameError(validateLast_name(user.getLast_name()));
			errorMsgs.setPasswordError(validatePassword(user.getPassword()));
			errorMsgs.setCpasswordError(validateCpassword(user.getCpassword(),user.getPassword()));
			errorMsgs.setPhoneError(validatePhone(user.getPhone()));
			errorMsgs.setEmailError(validateEmail(user.getEmail()));
			errorMsgs.setDeck_numberError(validateDeck_number(user.getDeck_number()));
			errorMsgs.setRoom_numberError(validateRoom_number(user.getRoom_number()));
			errorMsgs.setErrorMsg();
		}
		else {
			boolean usernameinDB=userDAO.checkusername(user.getUsername());
			if(usernameinDB) {
				errorMsgs.setUsernameError("Username Does not exist");
			}
			else {
				ArrayList<user> UserinDB=new ArrayList<user>();
				UserinDB=userDAO.Searchusername(user.getUsername());
				user seluser=new user();
				seluser.setId_user(UserinDB.get(0).getId_user());
				seluser.setUser(UserinDB.get(0).getUsername(), UserinDB.get(0).getFirst_name(), UserinDB.get(0).getLast_name(), UserinDB.get(0).getPassword(), UserinDB.get(0).getRole(), UserinDB.get(0).getPhone(), UserinDB.get(0).getEmail(), UserinDB.get(0).getMemtype(), UserinDB.get(0).getRoom_number(), UserinDB.get(0).getDeck_number());
				if(!(user.getPassword().equals(seluser.getPassword()))) {
				 errorMsgs.setUsernameError("Wrong Password");
				}
				else {
					user.setId_user(UserinDB.get(0).getId_user());
					user.setUser(UserinDB.get(0).getUsername(), UserinDB.get(0).getFirst_name(), UserinDB.get(0).getLast_name(), UserinDB.get(0).getPassword(), UserinDB.get(0).getRole(), UserinDB.get(0).getPhone(), UserinDB.get(0).getEmail(), UserinDB.get(0).getMemtype(), UserinDB.get(0).getRoom_number(), UserinDB.get(0).getDeck_number());
					
				}
			}
		}
	}
	public void validateChange(String action,user user,userErrorMsgs errorMsgs,String first,String last, String pass,String email,String mem,String phone,String deck,String room) {
		errorMsgs.setCpasswordError(validateSame(user,first,last,pass,email,mem,phone,deck,room));
		if(errorMsgs.getCpasswordError().equals("")) {
			errorMsgs.setFirst_nameError(validateFirst_name(first));
			errorMsgs.setLast_nameError(validateLast_name(last));
			errorMsgs.setPasswordError(validatePassword(pass));
			errorMsgs.setPhoneError(validatePhone(phone));
			errorMsgs.setEmailError(validateEmail(email));
			errorMsgs.setDeck_numberError(validateDeck_number(deck));
			errorMsgs.setRoom_numberError(validateRoom_number(room));
		}
		errorMsgs.setErrorMsg();
	}
	private String validateSame(user user,String first,String last, String pass,String email,String mem,String phone,String deck,String room) {
		String result="";
		if(first.equals(user.getFirst_name())&&last.equals(user.getLast_name())&&pass.equals(user.getPassword())&&email.equals(user.getEmail())&&mem.equals(user.getMemtype())&&phone.equals(user.getPhone())&&deck.equals(user.getDeck_number())&&room.equals(user.getRoom_number())) {
			result="No modifications has been made";
		}
		return result;
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
			if(Character.isDigit(username.charAt(0))) {
				result="Username must start with a letter";
			}
			else {
				int m=username.length();
				for(int i=0;i<m;i++) {
					if(!Character.isLetterOrDigit(username.charAt(i))) {
						result="Username must have only letters and numbers";
					}
				}
				if(result.equals("")) {
					boolean usernameinDB=userDAO.checkusername(username);
					if(!usernameinDB) {
						result="Username already exsits";
					}
				}
				else {
					System.out.println("Anirudh");
				}
			}
		}
		return result;
	}
	
	private String validateLast_name(String last_name) {
		String result="";
		if (!stringSize(last_name,3,29))
			result="Last Name must be between 2(not inclusive) and 30(not inclusive) characters long";
		else if(!Character.isUpperCase(last_name.charAt(0))) {
			result="First Letter must be Capital";
		}
		else{
			int m=last_name.length();
			int i=0;
			while(i<m) {
				if(!Character.isLetter(last_name.charAt(i))) {
					result="Last Name cannot have numbers or special charectors";
				}
				i++;
			}
		}
		return result;
	}
	private String validateFirst_name(String first_name) {
		String result="";
		if (!stringSize(first_name,3,29))
			result="First Name must be between 2(not inclusive) and 30(not inclusive) characters long";
		else if(!Character.isUpperCase(first_name.charAt(0))) {
			result="First Letter must be Capital";
		}
		else{
			int m=first_name.length();
			int i=0;
			while(i<m) {
				if(!Character.isLetter(first_name.charAt(i))) {
					result="First name cannot have numbers or special charectors";
				}
				i++;
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
			String uppercase="(.*[A-Z].*)";
			if(!password.matches(uppercase)) {
				result="Password should contain atleast one upper case alphabet";
			}
			else {
				String lowercase="(.*[a-z].*)";
				if(!password.matches(lowercase)) {
					result="Password should contain atleast one lower case alphabet";
				}
				else {
					String numbers="(.*[0-9].*)";
					if(!password.matches(numbers)) {
						result="Password should contain atleast one number.";
					}
					else {
						String specialchar="(.*[.,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,?].*)";
						if(!password.matches(specialchar)) {
							result="Password should contain atleast one special character";
						}
					}
				}
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
