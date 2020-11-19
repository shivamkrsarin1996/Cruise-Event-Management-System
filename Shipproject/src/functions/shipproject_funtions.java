package functions;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import shipproject.model.Events;
import shipproject.model.user;
import shipproject.util.SQLConnection;

public class shipproject_funtions {
	static SQLConnection DBMgr = SQLConnection.getInstance();
	public static WebDriver driver;
	public static Properties prop;
	public enum FunctionCoordinator {ListAllAssignedEvent,ViewEventDate,ViewAssignedDate}
	public enum FunctionPassenger {ViewAllEvents,Viewmyreservations,Viewprofile,ViewEventSummary,SearchEventBasedontypedateandtime}
	
	public void takeScreenshot(WebDriver driver, String screenshotname) {
		  try
		  {
			  File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);			
			  FileUtils.copyFile(source, new File("./ScreenShots/" + screenshotname +".png"));
		  }
		  catch(IOException e) {}
		  try {
//			  Change the delay value to 1_000 to insert a 1 second delay after 
//			  each screenshot
			  Thread.sleep(1_000);
		} catch (InterruptedException e) {}
	}
	public static ArrayList<Events> ReturnMatchingCompaniesList (String queryString) {
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
	}//String [][]
	public static ArrayList<user> returnMatcingusers(String queryString){
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
	
	public static String [][]  listcorevents(int id,int listSize) throws SQLException {  
		ArrayList<Events> UfromDB= ReturnMatchingCompaniesList(" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where managerid="+id+" order by DATE,time,eventName");
		String [][] arrayDB = new String [listSize-1][5];
		int i=0;
		for(Events p:UfromDB) {
			arrayDB[i][0]=p.getEventname();
			arrayDB[i][1]=p.getLocation();
			arrayDB[i][2]=p.getDate();
			arrayDB[i][3]=p.getTime();
			arrayDB[i][4]=p.getType();
			i++;
		}
		return arrayDB;
	}//listCoreventdate
	public static String [][] listCoreventdate(String date,String time,int listSize,int id) throws SQLException, ParseException{
		String OLD_FORMAT = "MM/dd/yyyy";
		String NEW_FORMAT = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		Date d = sdf.parse(date);
		sdf.applyPattern(NEW_FORMAT);
		date = sdf.format(d);
		SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
	    SimpleDateFormat parseFormat = new SimpleDateFormat("hhmma");
	    Date t = parseFormat.parse(time);
	    time=displayFormat.format(t);
		ArrayList<Events> UfromDB=ReturnMatchingCompaniesList("SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where DATE='"+date+"' and time>='"+time+"' and managerid="+id+" order by time,eventName");
		UfromDB.addAll(ReturnMatchingCompaniesList(" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where DATE>'"+date+"' and managerid="+id+" order by DATE,time,eventName"));
		String [][] arrayDB = new String [listSize-1][5];
		int i=0;
		for(Events p:UfromDB) {
			arrayDB[i][0]=p.getEventname();
			arrayDB[i][1]=p.getLocation();
			arrayDB[i][2]=p.getDate();
			arrayDB[i][3]=p.getTime();
			arrayDB[i][4]=p.getType();
			i++;
		}
		return arrayDB;
	}
	public static String [][] listeventdate(String date,String time,int listSize) throws SQLException, ParseException{
		String OLD_FORMAT = "MM/dd/yyyy";
		String NEW_FORMAT = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		Date d = sdf.parse(date);
		sdf.applyPattern(NEW_FORMAT);
		date = sdf.format(d);
		SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
	    SimpleDateFormat parseFormat = new SimpleDateFormat("hhmma");
	    Date t = parseFormat.parse(time);
	    time=displayFormat.format(t);
		ArrayList<Events> UfromDB=ReturnMatchingCompaniesList(" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where DATE='"+date+"' and time>='"+time+"' order by time,eventName");
		UfromDB.addAll(ReturnMatchingCompaniesList(" SELECT * FROM events join ship.create on events.idevents = ship.create.eventid where DATE>'"+date+"' order by DATE,time,eventName"));
		String [][] arrayDB = new String [listSize-1][5];
		int i=0;
		for(Events p:UfromDB) {
			arrayDB[i][0]=p.getEventname();
			arrayDB[i][1]=p.getLocation();
			arrayDB[i][2]=p.getDate();
			arrayDB[i][3]=p.getTime();
			arrayDB[i][4]=p.getType();
			i++;
		}
		return arrayDB;
	}
	public int userid(String name) {
		int i=0;
		ArrayList<user> fromDB=returnMatcingusers("SELECT * from user WHERE username='"+name+"'");
		i=fromDB.get(0).getId_user();
		return i;
	}
	public WebDriver invokeCorrectBrowser () {
		System.setProperty("webdriver.chrome.driver", "c:/ChromeDriver/chromedriver.exe");
		return new ChromeDriver();
	}
	public void verifyHeaders(WebDriver driver,String header1OnPage, String expectedHeader1Name,String header2OnPage, String expectedHeader2Name,
				String header3OnPage, String expectedHeader3Name,String header4OnPage, String expectedHeader4Name, 
				String header5OnPage, String expectedHeader5Name, String header6OnPage, String expectedHeader6Name, String snapShotName){
		assertTrue(driver.findElement(By.xpath(prop.getProperty(header1OnPage))).getText().equals(expectedHeader1Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header2OnPage))).getText().equals(expectedHeader2Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header3OnPage))).getText().equals(expectedHeader3Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header4OnPage))).getText().equals(expectedHeader4Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header5OnPage))).getText().equals(expectedHeader5Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header6OnPage))).getText().equals(expectedHeader6Name));
	    takeScreenshot(driver,snapShotName);
	}
	public void verifystrings10(WebDriver driver,String header1OnPage, String expectedHeader1Name,String header2OnPage, String expectedHeader2Name,
			String header3OnPage, String expectedHeader3Name,String header4OnPage, String expectedHeader4Name, 
			String header5OnPage, String expectedHeader5Name, String header6OnPage, String expectedHeader6Name, 
			String header7OnPage, String expectedHeader7Name,String header8OnPage, String expectedHeader8Name,String header9OnPage, String expectedHeader9Name,
			String header10OnPage, String expectedHeader10Name,String snapShotName){
		assertTrue(driver.findElement(By.xpath(prop.getProperty(header1OnPage))).getText().equals(expectedHeader1Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header2OnPage))).getText().equals(expectedHeader2Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header3OnPage))).getText().equals(expectedHeader3Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header4OnPage))).getText().equals(expectedHeader4Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header5OnPage))).getText().equals(expectedHeader5Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header6OnPage))).getText().equals(expectedHeader6Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header7OnPage))).getText().equals(expectedHeader7Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header8OnPage))).getText().equals(expectedHeader8Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header9OnPage))).getText().equals(expectedHeader9Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header10OnPage))).getText().equals(expectedHeader10Name));
	    takeScreenshot(driver,snapShotName);
		
	}
	public void Coordinator_function(WebDriver driver,FunctionCoordinator function) {
		switch (function) {
		case ListAllAssignedEvent:
			driver.findElement(By.xpath(prop.getProperty("coordinatorhmpg_AssignedeventlistLink"))).click();
			break;
		case ViewEventDate:
			driver.findElement(By.xpath(prop.getProperty("coordinatorhmpg_EventsummaryLink"))).click();
			break;
		case ViewAssignedDate:
			driver.findElement(By.xpath(prop.getProperty("coordinatorhmpg_AssignedeventsummaryLink"))).click();
		}
	}
	//public enum FunctionPassenger {ViewAllEvents,Viewmyreservations,Viewprofile,ViewEventSummary,SearchEventBasedontypedateandtime}
	
	public void Passenger_function(WebDriver driver,FunctionPassenger function) {
		switch (function) {
		case ViewAllEvents:
			driver.findElement(By.xpath(prop.getProperty("psg_homepg_viewAllEvent_link"))).click();
			break;
		case Viewmyreservations:
			driver.findElement(By.xpath(prop.getProperty("psg_homepg_viewMyRervation_link"))).click();
			break;
		case Viewprofile:
			driver.findElement(By.xpath(prop.getProperty("psg_homepg_viewProfile_link"))).click();
			break;
		case ViewEventSummary:
			driver.findElement(By.xpath(prop.getProperty("psg_homepg_viewEventSummary_link"))).click();
			break;
		case SearchEventBasedontypedateandtime:
			driver.findElement(By.xpath(prop.getProperty("psg_homepg_srchEventonDateTime"))).click();
		}
	}
	
	public void ship_login(WebDriver driver, String sUserName, String sPassword,String snapShotName) throws InterruptedException {
		driver.findElement(By.xpath(prop.getProperty("login_username"))).clear();
		driver.findElement(By.xpath(prop.getProperty("login_username"))).sendKeys(sUserName);
		driver.findElement(By.xpath(prop.getProperty("login_password"))).clear();
		driver.findElement(By.xpath(prop.getProperty("login_password"))).sendKeys(sPassword);
		Thread.sleep(1_000);
		driver.findElement(By.xpath(prop.getProperty("login_Submit_Btn"))).click();
		takeScreenshot(driver,snapShotName);
	}
	public void filldate(WebDriver driver,String date,String time,String snapShotName) throws InterruptedException {
		driver.findElement(By.xpath(prop.getProperty("coordinatorviewAsgneventsummary_Date_Val"))).clear();
		driver.findElement(By.xpath(prop.getProperty("coordinatorviewAsgneventsummary_Date_Val"))).sendKeys(date);
		driver.findElement(By.xpath(prop.getProperty("coordinatorviewAsgneventsummary_Time_Val"))).clear();
		driver.findElement(By.xpath(prop.getProperty("coordinatorviewAsgneventsummary_Time_Val"))).sendKeys(time);
		driver.findElement(By.xpath(prop.getProperty("coordinatorviewAsgneventsummary_Btn"))).click();
		takeScreenshot(driver,snapShotName);
	}
	public void verifyfilldate(WebDriver driver,String errorMsg) {
		assertTrue(driver.findElement(By.xpath(prop.getProperty("coordinatorviewAsgneventsummary_error"))).getAttribute("value").equals(errorMsg));
	}
	public void verifycorlistpage(WebDriver driver,String header) {
		assertTrue(driver.findElement(By.xpath(prop.getProperty("coordinatoreventpg_Title"))).getText().equals(header));
	}
	public void verifyLogin(WebDriver driver,String errorMsg) {
		assertTrue(driver.findElement(By.xpath(prop.getProperty("login_error"))).getAttribute("value").equals(errorMsg));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("login_error"))).getAttribute("value").equals(errorMsg));
	}
	public void ship_registration(WebDriver driver,String username,String first_name, String last_name, String password,String cpassword, String phone,String email,String memtype,String room_number,String deck_number,String snapShotName) throws InterruptedException {
		//driver.findElement(By.xpath(prop.getProperty("login_Registration_Btn"))).click();
		driver.findElement(By.xpath(prop.getProperty("registration_fname"))).clear();
		driver.findElement(By.xpath(prop.getProperty("registration_fname"))).sendKeys(first_name);
		driver.findElement(By.xpath(prop.getProperty("registration_lname"))).clear();
		driver.findElement(By.xpath(prop.getProperty("registration_lname"))).sendKeys(last_name);
		driver.findElement(By.xpath(prop.getProperty("registration_uname"))).clear();
		driver.findElement(By.xpath(prop.getProperty("registration_uname"))).sendKeys(username);
		driver.findElement(By.xpath(prop.getProperty("registration_pass"))).clear();
		driver.findElement(By.xpath(prop.getProperty("registration_pass"))).sendKeys(password);
		driver.findElement(By.xpath(prop.getProperty("registration_cpass"))).clear();
		driver.findElement(By.xpath(prop.getProperty("registration_cpass"))).sendKeys(cpassword);
		driver.findElement(By.xpath(prop.getProperty("registration_email"))).clear();
		driver.findElement(By.xpath(prop.getProperty("registration_email"))).sendKeys(email);
		driver.findElement(By.xpath(prop.getProperty("registration_phone"))).clear();
		driver.findElement(By.xpath(prop.getProperty("registration_phone"))).sendKeys(phone);
		driver.findElement(By.xpath(prop.getProperty("registration_rnum"))).clear();
		driver.findElement(By.xpath(prop.getProperty("registration_rnum"))).sendKeys(room_number);
		driver.findElement(By.xpath(prop.getProperty("registration_dnum"))).clear();
		driver.findElement(By.xpath(prop.getProperty("registration_dnum"))).sendKeys(deck_number);
		new Select(driver.findElement(By.xpath(prop.getProperty("registration_mem")))).selectByVisibleText(memtype);
		driver.findElement(By.xpath(prop.getProperty("registration_Btn"))).click();
		Thread.sleep(1_000);
		takeScreenshot(driver,snapShotName);
	}

	public void verifyRegistration(WebDriver driver,String errorMsg,String usernameError,String first_nameError,String last_nameError,String passwordError,String cpasswordError,String phoneError,String emailError,String room_numberError,String deck_numberError) {
		assertTrue(driver.findElement(By.xpath(prop.getProperty("registration_errorMsg"))).getAttribute("value").equals(errorMsg));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("registration_unameErr"))).getAttribute("value").equals(usernameError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("registration_fnameErr"))).getAttribute("value").equals(first_nameError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("registration_lnameErr"))).getAttribute("value").equals(last_nameError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("registration_passErr"))).getAttribute("value").equals(passwordError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("registration_cpassErr"))).getAttribute("value").equals(cpasswordError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("registration_phoneErr"))).getAttribute("value").equals(phoneError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("registration_emailErr"))).getAttribute("value").equals(emailError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("registration_rnumErr"))).getAttribute("value").equals(room_numberError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("registration_dnumErr"))).getAttribute("value").equals(deck_numberError));
	}
	public void corlogin(WebDriver driver,String header,String snapShotName) throws InterruptedException {
		Thread.sleep(1_000);
		assertTrue(driver.findElement(By.xpath(prop.getProperty("coordinatorhmpg_Title"))).getText().equals(header));
		takeScreenshot(driver,snapShotName);
	}
	public void logout(WebDriver driver,String logout,String snapShotName) throws InterruptedException {
		assertTrue(driver.findElement(By.xpath(prop.getProperty("login_regMsg"))).getAttribute("value").equals(logout));
		Thread.sleep(1_000);
		takeScreenshot(driver,snapShotName);
	}
}
