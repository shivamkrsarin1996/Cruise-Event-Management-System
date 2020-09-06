package shipproject.model;

public class userErrorMsgs {
	
	private String errorMsg;
	private String usernameError;
	private String first_nameError;
	private String last_nameError;
	private String passwordError;
	private String cpasswordError;
	private String phoneError;
	private String emailError;
	private String room_numberError;
	private String deck_numberError;
	
	public userErrorMsgs() {
		this.errorMsg="";
		this.usernameError="";
		this.first_nameError="";
		this.last_nameError="";
		this.passwordError="";
		this.cpasswordError="";
		this.phoneError="";
		this.emailError="";
		this.room_numberError="";
		this.deck_numberError="";
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg() {
		if(!usernameError.equals("")||!first_nameError.equals("")||!last_nameError.equals("")||!passwordError.equals("")||!cpasswordError.equals("")||!phoneError.equals("")||!emailError.equals("")||!room_numberError.equals("")||!deck_numberError.equals("")) {
			this.errorMsg = "Please correct the following errors";
		}
	}
	public String getUsernameError() {
		return usernameError;
	}
	public void setUsernameError(String usernameError) {
		this.usernameError=usernameError;
	}
	public String getFirst_nameError() {
		return first_nameError;
	}
	public void setFirst_nameError(String first_nameError) {
		this.first_nameError=first_nameError;
	}
	public String getLast_nameError() {
		return last_nameError;
	}
	public void setLast_nameError(String last_nameError) {
		this.last_nameError=last_nameError;
	}
	public String getPasswordError() {
		return passwordError;
	}
	public void setPasswordError(String passwordError) {
		this.passwordError=passwordError;
	}
	public String getCpasswordError() {
		return cpasswordError;
	}
	public void setCpasswordError(String cpasswordError) {
		this.cpasswordError=cpasswordError;
	}
	public String getPhoneError() {
		return phoneError;
	}
	public void setPhoneError(String phoneError) {
		this.phoneError=phoneError;
	}
	public String getEmailError() {
		return emailError;
	}
	public void setEmailError(String emailError) {
		this.emailError = emailError;
	}
	public String getRoom_numberError() {
		return room_numberError;
	}
	public void setRoom_numberError(String room_numberError) {
		this.room_numberError = room_numberError;
	}
	public String getDeck_numberError() {
		return deck_numberError;
	}
	public void setDeck_numberError(String deck_numberError) {
		this.deck_numberError = deck_numberError;
	}
}
