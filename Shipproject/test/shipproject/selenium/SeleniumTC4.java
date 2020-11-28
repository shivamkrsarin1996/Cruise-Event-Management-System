package shipproject.selenium;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import functions.shipproject_funtions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class SeleniumTC4 extends shipproject_funtions{
	private StringBuffer verificationErrors = new StringBuffer();
	public String sAppURL, sSharedUIMapPath, testDelay,username,password;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	    Logger.getLogger("org.openqa.selenium.remote").setLevel(Level.OFF);	  
	    }
	
	@Before
	public void setUp() throws Exception {
		driver = invokeCorrectBrowser ();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    prop = new Properties();
	    prop.load(new FileInputStream("./Login/Login.properties"));
	    username = prop.getProperty("passenger_username");
		password = prop.getProperty("passenger_password");
	    prop.load(new FileInputStream("./Configuration/SP_Configuration.properties"));
	    sAppURL = prop.getProperty("sAppURL");
		sSharedUIMapPath = prop.getProperty("SharedUIMap");
		testDelay=prop.getProperty("testDelay");
		prop.load(new FileInputStream(sSharedUIMapPath));
	    }
	private Boolean arraysDiff (String [][] array1, String [][] array2) { // this method compares the contents of the two tables
	  Boolean diff=false || (array1.length!=array2.length);
	  for (int i=0;i<array1.length && !diff;i++) {
		 diff  = !array1[i][0].equals(array2[i][0]) || !array1[i][1].equals(array2[i][1]) || 
				 !array1[i][2].equals(array2[i][2]) || !array1[i][3].equals(array2[i][3]) ||
				 !array1[i][4].equals(array2[i][4]) || !array1[i][5].equals(array2[i][5]);
	  }
	  return diff;
}

private String [][] getTableContentsFromPage(int listSize) {
	 String [][] eventArray = new String[listSize-1][6];
	 for (int i=0; i<listSize-1; i++) {
		 eventArray[i][0]=driver.findElement(By.xpath(prop.getProperty("psgViewAllEvents_EventsTable_Partial")+(i+2)+
					prop.getProperty("psgViewAllEvents_EventNameCol"))).getText();
		 eventArray[i][1]=driver.findElement(By.xpath(prop.getProperty("psgViewAllEvents_EventsTable_Partial")+(i+2)+
					prop.getProperty("psgViewAllEvents_EventDateCol"))).getText();
		 eventArray[i][2]=driver.findElement(By.xpath(prop.getProperty("psgViewAllEvents_EventsTable_Partial")+(i+2)+
					prop.getProperty("psgViewAllEvents_StartTimeCol"))).getText();
		 eventArray[i][3]=driver.findElement(By.xpath(prop.getProperty("psgViewAllEvents_EventsTable_Partial")+(i+2)+
					prop.getProperty("psgViewAllEvents_DurationCol"))).getText();
		 eventArray[i][4]=driver.findElement(By.xpath(prop.getProperty("psgViewAllEvents_EventsTable_Partial")+(i+2)+
					prop.getProperty("psgViewAllEvents_LocationCol"))).getText();
		 eventArray[i][5]=driver.findElement(By.xpath(prop.getProperty("psgViewAllEvents_EventsTable_Partial")+(i+2)+
					prop.getProperty("psgViewAllEvents_AvlSeatsCol"))).getText();
	 }
	 return eventArray;
}

	@Test
	@FileParameters("test/shipproject/selenium/TC04a_test_cases.csv")
	public void TC04a(int testcaseNo,String date,String time,String error,String header) throws Exception{
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		ship_login(driver,username,password,methodName+" PassengerHomePage "+testcaseNo);
		Passenger_function(driver,FunctionPassenger.ViewEventSummary);
		filldate(driver,date,time,methodName+" datefill test case "+testcaseNo);
		if(!error.equals("")) {
		verifyfilldate(driver,error);
		Thread.sleep(1_000);
		driver.findElement(By.xpath(prop.getProperty("psgDateTimePg_logout_btn"))).click();
		Thread.sleep(1_000);
		}
		else {
			verifypaslistpage(driver,header);
			WebElement eventTable=driver.findElement(By.xpath(prop.getProperty("psgViewAllEvents_EventsTable")));
			int rows = eventTable.findElements(By.tagName("tr")).size();
			Thread.sleep(2_000);
			assertFalse(arraysDiff(getTableContentsFromPage(rows),listeventdatepas(date,time,rows)));
			driver.findElement(By.xpath(prop.getProperty("psgViewAllEvents_Logout_btn"))).click();
		}
	}
	
	@Test
	@FileParameters("test/shipproject/selenium/TC04b_test_cases.csv")
	public void TC04b(int testcaseNo,String date,String time,String type,String error,String header) throws Exception{
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		ship_login(driver,username,password,methodName+" PassengerHomePage "+testcaseNo);
		Passenger_function(driver,FunctionPassenger.SearchEventBasedontypedateandtime);
		System.out.println("1");
		filldatetype(driver,date,time,type,methodName+" datefill test case "+testcaseNo);
		if(!error.equals("")) {
		verifyfilldate(driver,error);
		Thread.sleep(1_000);
		driver.findElement(By.xpath(prop.getProperty("psgDateTimePg_logout_btn"))).click();
		Thread.sleep(1_000);
		}
		else {
			verifypaslistpage(driver,header);
			WebElement eventTable=driver.findElement(By.xpath(prop.getProperty("psgViewAllEvents_EventsTable")));
			int rows = eventTable.findElements(By.tagName("tr")).size();
			Thread.sleep(2_000);
			assertFalse(arraysDiff(getTableContentsFromPage(rows),listeventdatepastype(date,time,type,rows)));
			driver.findElement(By.xpath(prop.getProperty("psgViewAllEvents_Logout_btn"))).click();
		}
	}
	
	@Test
	@FileParameters("test/shipproject/selenium/TC04c_test_cases.csv")
	public void TC04c(int testcaseNo,String date,String time,String col1,String col2,String col3,String col4,String col5,String col6,String col7,String col8,String col9,String col10,String col11,String col12,String col13,String col14,String col15,String col16,String col17,String col18,String col19,String col20,String col21) throws InterruptedException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		ship_login(driver,username,password,methodName+" PassengerHomePage "+testcaseNo);
		Passenger_function(driver,FunctionPassenger.ViewEventSummary);
		filldate(driver,date,time,methodName+" datefill test case "+testcaseNo);
		driver.findElement(By.xpath(col1)).click();
		verifystrings10(driver,"psgViewSpecificEvents_EventName_label",col2,"psgViewSpecificEvents_EventDate_label",col3,"psgViewSpecificEvents_StartTime_label",col4,"psgViewSpecificEvents_Duration_label",col5,"psgViewSpecificEvents_Location_label",col6,"psgViewSpecificEvents_Capacity_label",col7,"psgViewSpecificEvents_AvlSeats_label",col8,
				"psgViewSpecificEvents_ShowType_label",col9,"psgViewSpecificEvents_CoordinatorFName_label",col10,"psgViewSpecificEvents_CoordinatorLName_label",col11,methodName+" verifySpecific2 test case "+testcaseNo);
		verifystrings10(driver,"psgViewSpecificEvents_EventName_value",col12,"psgViewSpecificEvents_EventDate_value",col13,"psgViewSpecificEvents_StartTime_value",col14,"psgViewSpecificEvents_Duration_value",col15,"psgViewSpecificEvents_Location_value",col16,"psgViewSpecificEvents_Capacity_value",col17,"psgViewSpecificEvents_AvlSeats_value",col18,
				"psgViewSpecificEvents_ShowType_value",col19,"psgViewSpecificEvents_CoordinatorFName_value",col20,"psgViewSpecificEvents_CoordinatorLName_value",col21,methodName+" verifySpecific2 test case "+testcaseNo);
		driver.findElement(By.xpath(prop.getProperty("psgViewSpecificEvents_Logout_btn"))).click();
		Thread.sleep(1_000);
		
	}
	
	@Test
	@FileParameters("test/shipproject/selenium/TC04d_test_cases.csv")
	public void TC04d(int testcaseNo,String date,String time,String type,String col1,String error) throws InterruptedException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		ship_login(driver,username,password,methodName+" PassengerHomePage "+testcaseNo);
		Passenger_function(driver,FunctionPassenger.SearchEventBasedontypedateandtime);
		filldatetype(driver,date,time,type,methodName+" datefill test case "+testcaseNo);
		Thread.sleep(1_000);
		driver.findElement(By.xpath(col1)).click();
		driver.findElement(By.xpath(prop.getProperty("psgViewSpecificEvents_ReserveEvent_btn"))).click();
		takeScreenshot(driver,methodName+" reservationerror "+testcaseNo);
		assertTrue(driver.findElement(By.xpath(prop.getProperty("psgViewSpecificEvents_errorMsg"))).getAttribute("value").equals(error));
		driver.findElement(By.xpath(prop.getProperty("psgViewSpecificEvents_Logout_btn"))).click();
		Thread.sleep(1_000);
	}
	
	@After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	}
	

}
