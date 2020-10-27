package shipproject.model;

public class EventsErrorMsgs {
	private String errorMsg;
	private String date;
	private String time;
//	private String eventname;
	private String manager;
	private String estCap;
	
	public EventsErrorMsgs() {
		this.errorMsg="";
		this.date="";
		this.time="";
//		this.eventname="";
		this.manager="";
		this.estCap="";
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

//	public String getEventname() {
//		return eventname;
//	}

//	public void setEventname(String eventname) {
//		this.eventname = eventname;
//	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getEstCap() {
		return estCap;
	}

	public void setEstCap(String estCap) {
		this.estCap = estCap;
	}

}
