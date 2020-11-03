package shipproject.model;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import shipproject.data.eventsDAO;

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
	public void validateEvent(String action,Events event,EventsErrorMsgs errorMsg) throws ParseException {
		if(action.equalsIgnoreCase("eventSearch")) {
			errorMsg.setErrorMsg(validateDate(event.getDate(),event.getTime()));
		}
		else if(action.equalsIgnoreCase("eventtypeSearch")) {
			errorMsg.setErrorMsg(validateDate(event.getDate(),event.getTime()));
		}
		else {
			errorMsg.setErrorMsg(validateDate(event.getDate(),event.getTime()));
		}

	}
	public void validateEventModify(String action,Events event,EventsErrorMsgs errorMsg,String cdate,String ctime,String cEstCap) throws ParseException {
		errorMsg.setErrorMsg(validateSame(event,cdate,ctime,cEstCap));
		if(errorMsg.getErrorMsg().equals("")){
			errorMsg.setEstCap(validateEstCap(cEstCap,event.getCapacity()));
			errorMsg.setDate(validateDate(cdate,ctime));
			errorMsg.setTime(validateDate(cdate,ctime));
			if(errorMsg.getTime().equals("")) {
				errorMsg.setTime(validateRTime(ctime,event.getDuration()));
			}
			if(errorMsg.getTime().equals("")) {
				errorMsg.setTime(validateEvent2(event.getId_event(),cdate,ctime,event.getDuration(),event.getIdcreate()));		
			}
			if(errorMsg.getTime().equals("")) {
				errorMsg.setTime(validateManager2(event.getManagerid(),cdate,ctime,event.getDuration(),event.getIdcreate()));
				
			}
			errorMsg.setErrorMsg(validateErrorMsg2(errorMsg.getDate(),errorMsg.getTime(),errorMsg.getManager(),errorMsg.getEstCap()));
		}
	}
	public void validateEventCor(String action,Events event,EventsErrorMsgs errorMsg,String id) throws ParseException {
		errorMsg.setErrorMsg(validateSame2(event,id));
		if(errorMsg.getErrorMsg().equals("")){
			errorMsg.setManager(validateManager2(id,event.getDate(),event.getTime(),event.getDuration(),event.getIdcreate()));
			errorMsg.setErrorMsg(validateErrorMsg2(errorMsg.getDate(),errorMsg.getTime(),errorMsg.getManager(),errorMsg.getEstCap()));
		}
	}
	private String validateSame(Events event,String cdate,String ctime,String cEstCap) {
		String result="";
		if(cdate.equals(event.getDate())&&ctime.equals(event.getTime())&&cEstCap.equals(event.getEstCap())) {
			result="No modifications has been made";
		}
		return result;
	}
	private String validateSame2(Events event,String id) {
		String result="";
		if(id.equals(event.getManagerid())) {
			result="you have assigned same Coordinator";
		}
		return result;
	}
	private String validateErrorMsg2(String error1,String error2,String error3,String error4) {
		String result="";
		if(!error1.equals("")||!error2.equals("")||!error3.equals("")||!error4.equals("")) {
			result="Please correct the following errors";
		}
		return result;
	}
	private String validateDate(String date,String time) throws ParseException {
		String result="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1=null,date2 = null;
		date1=sdf.parse(date);
		String current = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		date2=sdf.parse(current);
		if(date1.before(date2)){
            result="Cannot be past date";
        }
		else if(date1.equals(date2)) {
			result=validateTime(time);
		}
		return result;
	}
	private String validateTime(String time) throws ParseException {
		String result="";
		SimpleDateFormat skf = new SimpleDateFormat("HH:mm");
		Date time1=null,time2 = null;
		time1=skf.parse(time);
		String current = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
		time2=skf.parse(current);
		if(time1.before(time2)) {
			result="Cannot be past time";
		}
		return result;
	}
	private String validateRTime(String time,String duration) throws ParseException {
		String result="";
		String Morning="07:00";
		String Night="22:00";
		SimpleDateFormat skf = new SimpleDateFormat("HH:mm");
		Date time1=null,time2 = null,time3 = null,time4=null;
	    time1=skf.parse(time);
		time2=skf.parse(Morning);
		time3=skf.parse(Night);
		
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(time1);
		 cal.add(Calendar.MINUTE, Integer.parseInt(duration));
		 String newTime = skf.format(cal.getTime());
		 time4=skf.parse(newTime);
		 if(time1.before(time2)) {
			 result="Event Cannot Start Before 7:00";
		 }
		 else if(time1.after(time3)) {
			 result="Event Start after 10:00PM";
		 }
		 else if(time1.equals(time3)) {
			 result="Event cannot End after 10:00PM";
		 }
		 
		 else if(time4.after(time3)) {
			 result="Event cannot End after 10:00PM";
		 }
		return result;
	}
	private String validateEvent2(int id,String date,String time,String duration,int cid) throws ParseException {
		String result="";
		SimpleDateFormat skf = new SimpleDateFormat("HH:mm");
		Date time1=null;
		time1=skf.parse(time);
		
		Calendar cal = Calendar.getInstance();
		 cal.setTime(time1);
		 cal.add(Calendar.MINUTE, Integer.parseInt(duration));
		 String newTime = skf.format(cal.getTime());
		 cal.setTime(time1);
		 cal.add(Calendar.MINUTE, -1*Integer.parseInt(duration));
		 String newTime2 = skf.format(cal.getTime());
		boolean booked=eventsDAO.checkbook2(id, date, newTime2, newTime,cid);
		if(!booked) {
			result="This event has prior booking during this time";
		}
		return result;
	}
	private String validateManager2(String id,String date,String time,String duration,int cid) throws ParseException {
		String result="";
		SimpleDateFormat skf = new SimpleDateFormat("HH:mm");
		Date time1=null;
		time1=skf.parse(time);
		
		Calendar cal = Calendar.getInstance();
		 cal.setTime(time1);
		 cal.add(Calendar.MINUTE, Integer.parseInt(duration));
		 String newTime = skf.format(cal.getTime());
		 cal.setTime(time1);
		 cal.add(Calendar.MINUTE, -1*Integer.parseInt(duration));
		 String newTime2 = skf.format(cal.getTime());
		 boolean busy=eventsDAO.checkMbook2(id, date, newTime2, newTime,cid);
		 if(!busy) {
				result="This Co-ordinator has prior booking during this time";
			}
		 return result;
	}
	private String validateEstCap(String estCap,String capacity) {
		String result="";
		if (!isTextAnInteger(estCap)) {
			result="Estimated Capacity must be a number";
		}
		else {
			int inum = Integer.parseInt(estCap);
			int dnum = Integer.parseInt(capacity);
			if(inum<1) {
				result="Estimated Capacity must be greater then 0";
			}
			else if(inum>dnum) {
				result="Estimated Capacity cannot be greated than Actual Capacity";
			}
		}
		return result;
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