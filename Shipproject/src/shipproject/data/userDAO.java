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
				user.setId_user(userList.getInt("id_used"));
				user.setUsername(userList.getString("username"));
				user.setFirst_name(userList.getString("first_name"));
				user.setLast_name(userList.getString("last_name"));
				user.setRole(userList.getString("role"));
				user.setPassword(userList.getString("password"));
				user.setRoom_number(String.valueOf(userList.getInt("room_number")));
				user.setPhone(userList.getString("phone"));
				user.setDeck_number(String.valueOf(userList.getInt("decknumber")));
				user.setMemtype(userList.getString("memtype"));
				user.setEmail(userList.getString("email"));
				userListInDB.add(user);
			}
		} catch (SQLException e) {}
		return userListInDB;
	}
	private static boolean emptycheck(String queryString){
		boolean ans=false;
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet userList=stmt.executeQuery(queryString);
			if(userList.next()) {
				ans=false;
			}
			else {
				ans=true;
			}
		} catch (SQLException e) {}
		return ans;
	}
	public static boolean checkusername(String username){
		return emptycheck("SELECT * from user WHERE username='"+username+"'");
	}
	public static ArrayList<user> Searchusername(String username){
		return returnMatcingusers("SELECT * from user WHERE username='"+username+"'");
	}
	
	public static ArrayList<user> searchUserbyID(String id) {
		int ids=Integer.parseInt(id);
		String query="Select * from user WHERE id_used="+ids;
		return returnMatcingusers(query);
	}
	public static ArrayList<user> searchCoordinator(){
		String query="SELECT * FROM ship.user where role='coordinator'";
		return returnMatcingusers(query);
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
					+Integer.parseInt(user.getDeck_number())+"','"
					+user.getMemtype()+"','"
					+user.getEmail()+"','"
					+Integer.parseInt(user.getRoom_number())+"')";
			stmt.executeUpdate(insert);
			conn.commit();
		}catch(SQLException e) {}
	}
	public static void updateuser(user user,String first,String last, String pass,String email,String mem,String phone,String deck,String room) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			String update="update ship.user set first_name='"+first+"',last_name='"+last+"',password='"+pass+"',email='"+email+
					"',memtype='"+mem+"',phone='"+phone+"',decknumber="+deck+", room_number="+room+" where id_used="+user.getId_user();
			stmt.executeUpdate(update);
			conn.commit();
		}catch(SQLException e) {System.out.println("FAIL");}
	}
}
