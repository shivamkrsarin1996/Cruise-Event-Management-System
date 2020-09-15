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
	
	public static void insertevent (Events events) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			
			String insertevent ="INSERT INTO events (idevents,eventName,location,capacity,duration,Type) VALUES('"
					+events.getId_event()+"','"
					+events.getEventname()+"','"
					+events.getLocation()+"','"
					+Integer.parseInt(events.getCapacity())+"','"
					+Integer.parseInt(events.getDuration())+"','"
					+events.getType()+"','"				
				    +"')";
			
			stmt.executeUpdate(insertevent);	
			conn.commit(); 
		} catch (SQLException e) {}
	}

	public static ArrayList<Events>  listevents() {  
			return ReturnMatchingCompaniesList(" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid");
	}
	public static ArrayList<Events> searchevent(int ids){
		return ReturnMatchingCompaniesList(" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where idcreate="+ids);
	}
	public static ArrayList<Events> psg_searchevent(int ids){
		return ReturnMatchingCompaniesList(" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where idcreate="+ids);
	}
	//searchevent(ids)
	

	

}