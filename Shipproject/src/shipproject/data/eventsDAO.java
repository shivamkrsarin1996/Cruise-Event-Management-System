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
    public static boolean checkbook2(int id,String date,String time,String time2,int cid) {
		
		String query=" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where idevents="+id+" and DATE='"+date+"' and time>='"+time+"' and time<='"+time2+"' and idcreate!="+cid;
		return emptycheck(query);
	}
    public static boolean checkMbook(String id,String date,String time,String time2) {
	
		String query=" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where managerid="+id+" and DATE='"+date+"' and time>='"+time+"' and time<='"+time2+"'";
		return emptycheck(query);
	}
    public static boolean checkMbook2(String id,String date,String time,String time2,int cid) {
	
	String query=" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where managerid="+id+" and DATE='"+date+"' and time>='"+time+"' and time<='"+time2+"' and idcreate!="+cid;
	return emptycheck(query);
}

	// For creating reservation
	public static void createReservation(int reserveID, int createdEventID,int userID){
		System.out.println("Inside create reservation method of EventDAO");
		System.out.println("rId="+ reserveID + " evntId=" + createdEventID + " uid="+ userID);
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			System.out.println("Inside try");
			System.out.println("con="+conn);
			stmt = conn.createStatement();
			System.out.println("stmt created="+ stmt);
			String registerevent ="INSERT INTO ship.reserve (idreserve, eventcreateid, userid) VALUES ('" +
					reserveID + "','" + createdEventID + "','" + userID+"')";
			System.out.println(registerevent);
			System.out.println("before update");
			stmt.executeUpdate(registerevent);
			System.out.println("after execute update");
			conn.commit();
			System.out.println("Reservation created");
			conn.close();
		}
		catch (SQLException e) {System.out.println("FAIL");}
		//return ReturnSimpleEventList("SELECT * FROM ship.events where idevents="+id); */
	}

//to check if there is an existing Reservation with the same reservation id
	public static int searchReservationId() {
	
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection(); 
		int reservationID= 0;
		try {
			
			stmt = conn.createStatement();
			reservationID = (int)(Math.random()*10000);
			String query = "SELECT * FROM ship.reserve where idreserve="+reservationID;
			ResultSet ReservedEventList = stmt.executeQuery(query);
			
			while (ReservedEventList == null) {
				reservationID = (int)(Math.random()*10000);
				query = "SELECT * FROM ship.reserve where idreserve="+reservationID;
				ReservedEventList = stmt.executeQuery(query);
			}
	
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return reservationID;
	}
	

	
	public static ArrayList<Events> searchEventbyUser(int userId){
		System.out.println("in DAO func-searchEventbyUser");
		String query = " SELECT * FROM events,ship.create,reserve,ship.user where ship.events.idevents = ship.create.eventid and reserve.eventcreateid = ship.create.idcreate and reserve.userid = user.id_used and user.id_used="+userId;
		System.out.println(query);
		return ReturnMatchingCompaniesList(query);
	}
	
	public static ArrayList<Events> countReservedEventsForUser(String eventType, String eventDate, int userId) {
		// TODO Auto-generated method stub
		System.out.println("in eDAO func-countReservedEventsForUser");
		String query ="SELECT * FROM events,ship.create,reserve,ship.user where ship.events.idevents = ship.create.eventid and reserve.eventcreateid = ship.create.idcreate and reserve.userid = user.id_used and user.id_used=" + userId + " and events.Type ='" + eventType +"' and ship.create.DATE ='" +eventDate + "'";

		System.out.println(query);
		return ReturnMatchingCompaniesList(query);
		
	}

}