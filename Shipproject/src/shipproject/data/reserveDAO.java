package shipproject.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import shipproject.model.reserve;
import shipproject.util.SQLConnection;

public class reserveDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	private static ArrayList<reserve> ReturnMatchingreservelist (String queryString){
		 ArrayList<reserve> reservelistDB=new  ArrayList<reserve>();
		 Statement stmt = null;
		 Connection conn = SQLConnection.getDBConnection();  
			try {
				stmt = conn.createStatement();
				ResultSet reserveList = stmt.executeQuery(queryString);
				while(reserveList.next()) {
					reserve reserve=new reserve();
					reserve.setIdreserve(reserveList.getInt("idreserve"));
					reserve.setId_user(reserveList.getInt("userid"));
					reserve.setIdcreate(reserveList.getInt("eventcreateid"));
					reservelistDB.add(reserve);
				}
			} catch (SQLException e) {}
			return reservelistDB;
	}
	public static void createreserve(reserve reserve) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String query="insert into ship.reserve(reserve.eventcreateid,reserve.userid) values("+reserve.getIdcreate()+","+reserve.getId_user()+")";
			stmt.executeUpdate(query);	
			conn.commit(); 
		} catch (SQLException e) {System.out.println("FAIL");}
	}
	public static ArrayList<reserve> typeDateSearch(String type,String Date,int userid){
		String query="SELECT * FROM ((ship.reserve join ship.user on reserve.userid=user.id_used) join ship.create on reserve.eventcreateid=ship.create.idcreate) join ship.events on ship.create.eventid=ship.events.idevents where userid="+userid+
				" and ship.create.DATE='"+Date+"' and ship.events.Type='"+type+"'";
		return ReturnMatchingreservelist(query);
	}
	public static ArrayList<reserve> capSearch(int createid){
		String query="SELECT * FROM ship.reserve where eventcreateid="+createid;
		return ReturnMatchingreservelist(query);
	}
	public static ArrayList<reserve> regSearch(int createid,int userid){
		String query="SELECT * FROM ship.reserve where eventcreateid="+createid+" and userid="+userid;
		return ReturnMatchingreservelist(query);
	}

}
