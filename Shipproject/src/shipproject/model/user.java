package shipproject.model;

import java.io.Serializable;

public class user implements Serializable {
	private static final long serialVersionUID = 3L;
	private int id_user;
	private String username;
	private String first_name;
	private String last_name;
	private String password;
	private String role;
	private String phone;
	private String email;
	private String memtype;
	private int room_number;
	private int deck_number;
	
	public void setUser(String username,String first_name, String last_name, String password, String role, String phone,String email,String memtype,int room_number,int deck_number) {
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
	public int getRoom_number() {
		return room_number;
	}
	public void setRoom_number(int room_number) {
		this.room_number=room_number;
	}
	public int getDeck_number() {
		return deck_number;
	}
	public void setDeck_number(int deck_number) {
		this.deck_number=deck_number;
	}

}
