package shipproject.model;


import java.io.Serializable;

public class Events implements Serializable{

	private static final long serialVersionUID = 3L;
	private int id_event;
	private String eventname;
	private String location;
	private String capacity;
	private String duration;
	private String type;
	private String date;
	private String managerid;
	private int idcreate;
	private String time;
	private String estCap;
	
	public void setEvent(String eventname, String location, String capacity, String duration,String type, String date, String managerid, String time, int id_event,int idcreate,String estCap) 
	{
	   
		
	     setEventname(eventname);
	     setLocation(location);
	     setCapacity(capacity);
	     setDuration(duration);
	     setType(type);
	     setId_event(id_event);
	     setDate(date);
	     setManagerid(managerid);
	     setTime(time);
	     setIdcreate(idcreate);
	     setEstCap(estCap);
	}
	
	public int getId_event() {
		return id_event;
	}
	public void setId_event(int id_event) {
		this.id_event = id_event;
	}
	public String getEventname() {
		return eventname;
	}
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getManagerid() {
		return managerid;
	}

	public void setManagerid(String managerid) {
		this.managerid = managerid;
	}

	public int getIdcreate() {
		return idcreate;
	}

	public void setIdcreate(int idcreate) {
		this.idcreate = idcreate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getEstCap() {
		return estCap;
	}

	public void setEstCap(String estCap) {
		this.estCap = estCap;
	}
	
	
	
	
	
  
//
//  NOTE: 	The following code is not representative of how this would be coded in an industrial application.
//			We are using this code to maximize the ability of Pit to mutate the code to determine how
//			good the developed test cases are. This course is using this Java backend code as an application
//			of the principles learned in CSE 5321 only.
//	
/*	
	public void validateCompany (String action, Company company, CompanyErrorMsgs errorMsgs) {
		if (action.equals("saveCompany")) {
			errorMsgs.setCompanyIDerror(validateIdcompany(action,company.getIdcompany()));
			errorMsgs.setCompanyNameError(validateCompany_name(company.getCompany_name()));
			errorMsgs.setPhoneError(validatePhone(company.getPhone()));
			errorMsgs.setEmailError(validateEmail(company.getEmail()));
		}
		else
			if (action.equals("searchCompany")) {
				if (company_name.equals("") && idcompany.equals("")) 
					errorMsgs.setCompanyNameError("Both Company Name and Company ID cannot be blank");
				else
					if (!idcompany.equals(""))
						errorMsgs.setCompanyIDerror(validateIdcompany(action, idcompany));
			}
			else 
				if (idcompany.equals("")) 
					errorMsgs.setCompanyIDerror("Company ID cannot be blank");
				else
					errorMsgs.setCompanyIDerror(validateIdcompany(action,idcompany));
		errorMsgs.setErrorMsg();
	}

	private String validateIdcompany(String action, String idcompany) {
		String result="";
		if (!isTextAnInteger(idcompany))
			result="Your company ID must be a number";
		else
			if (action.equals("saveCompany")) {
				if (!stringSize(idcompany,3,16))
					result= "Your Company Id must between 3 and 16 digits";
				else
					if (!CompanyDAO.CompanyIDunique(idcompany))
						result="Company ID already in database";
			}
		return result;
	}
	
	private String validateCompany_name(String company_name) {
		String result="";
		if (!stringSize(company_name,3,45))
			result= "Your Company Name must between 3 and 45 digits";
		else
			if (Character.isLowerCase(company_name.charAt(0)))
				result="Your company name must start with a capital letter";
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
*/
}