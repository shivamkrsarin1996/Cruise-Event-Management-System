package shipproject.model;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.text.*;

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
	
	//validate actions
	public void validateEvent(String action,Events event,EventsErrorMsgs errorMsg) {
		if(action.equalsIgnoreCase("eventSearch")) {
			errorMsg.setErrorMsg(validateDate(event.getDate(),event.getTime()));
		}
	}
	private String validateDate(String date,String time) {
		String result="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1=null,date2 = null;
		try {
			date1=sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			result="Date not in correct format";
		}
		String current = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		try {
			date2=sdf.parse(current);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			result="Date not in correct format";
		}
		if(result.equals("")) {
		if(date1.before(date2)){
            result="Cannot be past date";
        }
		else if(date1.equals(date2)) {
			result=validateTime(time);
		}
		}
		return result;
	}
	private String validateTime(String time) {
		String result="";
		SimpleDateFormat skf = new SimpleDateFormat("HH:mm:ss");
		Date time1=null,time2 = null;
		try {
			time1=skf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			result="time not in correct format";
		}
		String current = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		try {
			time2=skf.parse(current);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result.equals("")) {
		if(time1.before(time2)) {
			result="Cannot be past time";
		}
		}
		return result;
	}
}