package shipproject.data;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import shipproject.util.SQLConnection;
import shipproject.model.Events;

public class eventsDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	private static ArrayList<Events> ReturnMatchingCompaniesList (String queryString) {
		ArrayList<Events> eventListInDB = new ArrayList<Events>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet eventList = stmt.executeQuery(queryString);
			while (eventList.next()) {
				Events events = new Events(); 
			    events.setId_event(eventList.getInt("idevents"));
			    events.setEventname(eventList.getString("eventName"));
			    events.setLocation(eventList.getString("location"));
			    events.setCapacity(eventList.getString("capacity"));
			    events.setDuration(eventList.getString("duration"));
			    events.setType(eventList.getString("Type"));
			    events.setManagerid(eventList.getString("managerid"));
			    events.setDate(eventList.getString("DATE"));
			    events.setTime(eventList.getString("time"));
			    events.setIdcreate(eventList.getInt("idcreate"));
			    events.setEstCap(eventList.getString("estimated"));
			    events.setTime(events.getTime().substring(0, events.getTime().length()-3));
			    eventListInDB.add(events);
			}
		} catch (SQLException e) {}
		return eventListInDB;
	}
	//Use above one
	
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
	
	
	public static void modifyevent(Events events,String cdate,String cestCap, String ctime) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			String updateevent="update ship.create set DATE='"+cdate+"',estimated='"+cestCap+"',time='"+ctime+"' where idcreate="+events.getIdcreate();
			stmt.executeUpdate(updateevent);
			conn.commit(); 
		} catch (SQLException e) {System.out.println("FAIL");}
			
	}
	public static void assigncor(Events events,String id) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			String updateevent="update ship.create set managerid="+id+" where idcreate="+events.getIdcreate();
			stmt.executeUpdate(updateevent);
			conn.commit(); 
		} catch (SQLException e) {System.out.println("FAIL");}
	}

	public static ArrayList<Events>  listevents() {  
			return ReturnMatchingCompaniesList(" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid order by DATE,time,eventName");
	}
	public static ArrayList<Events>  listcorevents(int id) {  
		return ReturnMatchingCompaniesList(" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where managerid="+id+" order by DATE,time,eventName");
	}
	public static ArrayList<Events> searchevent(int ids){
		return ReturnMatchingCompaniesList(" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where idcreate="+ids);
	}
	public static ArrayList<Events> psg_searchevent(int ids){
		return ReturnMatchingCompaniesList(" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where idcreate="+ids);
	}
	//searchevent(ids)
	public static ArrayList<Events> searcheventbydate(String date,String time){
		return ReturnMatchingCompaniesList(" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where DATE='"+date+"' and time>='"+time+"' order by time,eventName");
	}
	public static ArrayList<Events> searcheventbydatetype(String date,String time,String type){
		return ReturnMatchingCompaniesList("SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where DATE='"+date+"' and time>='"+time+"' and Type='"+type+"' order by time,eventName");
	}
	public static ArrayList<Events> searchgreaterdate(String date){
		return ReturnMatchingCompaniesList(" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where DATE>'"+date+"' order by DATE,time,eventName");
	}
	public static ArrayList<Events> searchgreaterdatetype(String date,String type){
		return ReturnMatchingCompaniesList(" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where DATE>'"+date+"' and Type='"+type+"' order by DATE,time,eventName");
	}
	public static ArrayList<Events> searcheventbydateassigned(String date,String time,int id){
		return ReturnMatchingCompaniesList("SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where DATE='"+date+"' and time>='"+time+"' and managerid="+id+" order by time,eventName");
	}
	public static ArrayList<Events> searchgreaterdateassigned(String date,int id){
		return ReturnMatchingCompaniesList(" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where DATE>'"+date+"' and managerid="+id+" order by DATE,time,eventName");
	}
	
	
    public static boolean checkbook2(int id,String date,String time,String time2,int cid) {
		
		String query=" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where idevents="+id+" and DATE='"+date+"' and time>='"+time+"' and time<='"+time2+"' and idcreate!="+cid;
		return emptycheck(query);
	}
    
    public static boolean checkMbook2(String id,String date,String time,String time2,int cid) {
	
	String query=" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where managerid="+id+" and DATE='"+date+"' and time>='"+time+"' and time<='"+time2+"' and idcreate!="+cid;
	return emptycheck(query);
}
	
	public static ArrayList<Events> searchEventbyUser(int userId){
		System.out.println("in DAO func-searchEventbyUser");
		String query = " SELECT * FROM events,ship.create,reserve,ship.user where ship.events.idevents = ship.create.eventid and reserve.eventcreateid = ship.create.idcreate and reserve.userid = user.id_used and user.id_used="+userId;
		System.out.println(query);
		return ReturnMatchingCompaniesList(query);
	}

}