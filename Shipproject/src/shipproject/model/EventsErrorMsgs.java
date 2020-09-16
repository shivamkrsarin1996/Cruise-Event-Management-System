package shipproject.model;

public class EventsErrorMsgs {
	private String errorMsg;
	private String date;
	private String time;
	
	public EventsErrorMsgs() {
		this.setErrorMsg("");
		this.date="";
		this.time="";
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

}
