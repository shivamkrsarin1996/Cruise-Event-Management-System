package shipproject.data;

import shipproject.util.SQLConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import shipproject.model.user;
public class userDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();
	private static ArrayList<user> returnMatcingusers(String queryString){
		ArrayList<user> userListInDB=new ArrayList<user>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet userList=stmt.executeQuery(queryString);
			while(userList.next()) {
				user user=new user();
				user.setId_user(userList.getInt("id_user"));
				user.setUsername(userList.getString("username"));
				user.setFirst_name(userList.getString("first_name"));
				user.setLast_name(userList.getString("last_name"));
				user.setRole(userList.getString("role"));
				user.setPassword(userList.getString("password"));
				user.setRoom_number(userList.getInt("room_number"));
				user.setPhone(userList.getString("phone"));
				user.setDeck_number(userList.getInt("decknumber"));
				user.setMemtype(userList.getString("memtype"));
				user.setEmail(userList.getString("email"));
				userListInDB.add(user);
			}
		} catch (SQLException e) {}
		return userListInDB;
	}
	public static ArrayList<user> checkusername(String username){
		return returnMatcingusers("SELECT * from user WHERE username='"+username+"'");
	}
	public static ArrayList<user> checkemail(String email){
		return returnMatcingusers("SELECT * from user WHERE email='"+email+"'");
	}
	public static void insertuser(user user) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			String insert="INSERT INTO user(username,first_name,last_name,role,password,phone,decknumber,memtype,email,room_number) VALUES('"
					+user.getUsername()+"','"
					+user.getFirst_name()+"','"
					+user.getLast_name()+"','"
					+user.getRole()+"','"
					+user.getPassword()+"','"
					+user.getPhone()+"','"
					+user.getDeck_number()+"','"
					+user.getMemtype()+"','"
					+user.getEmail()+"','"
					+user.getRoom_number()+"')";
			stmt.executeUpdate(insert);
			conn.commit();
		}catch(SQLException e) {}
	}
}
