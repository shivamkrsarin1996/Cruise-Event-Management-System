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
			    eventListInDB.add(events);
			}
		} catch (SQLException e) {}
		return eventListInDB;
	}
	//Use above one
	private static ArrayList<Events> ReturnSimpleEventList (String queryString) {
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
			    eventListInDB.add(events);
			}
		} catch (SQLException e) {}
		return eventListInDB;
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
	
	public static void createEvent (Events events) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			
			String insertevent ="INSERT INTO ship.create (eventid, managerid, time, DATE, estimated) VALUES('"
					+events.getId_event()+"','"
					+events.getManagerid()+"','"
					+events.getTime()+"','"
					+events.getDate()+"','"
					+Integer.parseInt(events.getEstCap())+"')";
			
			stmt.executeUpdate(insertevent);	
			conn.commit(); 
		} catch (SQLException e) {System.out.println("FAIL");}
	}

	public static ArrayList<Events>  listevents() {  
			return ReturnMatchingCompaniesList(" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid order by DATE,time,eventName");
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
	public static ArrayList<Events> searchgreaterdate(String date){
		return ReturnMatchingCompaniesList(" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where DATE>'"+date+"' order by DATE,time,eventName");
	}
	public static ArrayList<Events> simpleEventlist(){
		return ReturnSimpleEventList("SELECT * FROM ship.events");
	}
	public static ArrayList<Events> simpleEventlistint(int id){
		return ReturnSimpleEventList("SELECT * FROM ship.events where idevents="+id);
	}
	public static boolean checkbook(int id,String date,String time,String time2) {
		
		String query=" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where idevents="+id+" and DATE='"+date+"' and time>='"+time+"' and time<='"+time2+"'";
		return emptycheck(query);
	}
public static boolean checkMbook(String id,String date,String time,String time2) {
		
		String query=" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where managerid="+id+" and DATE='"+date+"' and time>='"+time+"' and time<='"+time2+"'";
		return emptycheck(query);
	}

	

}