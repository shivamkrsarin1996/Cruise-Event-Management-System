package shipproject.model;

public class reserveErrorMsgs {
	private String errorMsg;
	private String usererror;
	private String evernterror;
	
	public reserveErrorMsgs() {
		this.errorMsg="";
		this.usererror="";
		this.evernterror="";
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getUsererror() {
		return usererror;
	}
	public void setUsererror(String usererror) {
		this.usererror = usererror;
	}
	public String getEvernterror() {
		return evernterror;
	}
	public void setEvernterror(String evernterror) {
		this.evernterror = evernterror;
	}

}
