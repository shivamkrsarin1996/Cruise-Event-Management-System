package shipproject.selenium;

import static org.junit.Assert.assertFalse;
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
public class SeleniumTC6 extends shipproject_funtions{
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
	    username = prop.getProperty("manager_username");
		password = prop.getProperty("manager_password");
	    prop.load(new FileInputStream("./Configuration/SP_Configuration.properties"));
	    sAppURL = prop.getProperty("sAppURL");
		sSharedUIMapPath = prop.getProperty("SharedUIMap");
		testDelay=prop.getProperty("testDelay");
		prop.load(new FileInputStream(sSharedUIMapPath));
	    }
	private String [][] getTableContentsFromPage(int listSize) {
		 String [][] eventArray = new String[listSize-1][6];
		 for (int i=0; i<listSize-1; i++) {
			 eventArray[i][0]=driver.findElement(By.xpath(prop.getProperty("ManagerEventPage_EventTable_Partial")+(i+2)+
						prop.getProperty("ManagerEventPage_EventNameCol"))).getText();
			 eventArray[i][1]=driver.findElement(By.xpath(prop.getProperty("ManagerEventPage_EventTable_Partial")+(i+2)+
						prop.getProperty("ManagerEventPage_EventLocationCol"))).getText();
			 eventArray[i][2]=driver.findElement(By.xpath(prop.getProperty("ManagerEventPage_EventTable_Partial")+(i+2)+
						prop.getProperty("ManagerEventPage_EventDateCol"))).getText();
			 eventArray[i][3]=driver.findElement(By.xpath(prop.getProperty("ManagerEventPage_EventTable_Partial")+(i+2)+
						prop.getProperty("ManagerEventPage_EventTimeCol"))).getText();
			 eventArray[i][4]=driver.findElement(By.xpath(prop.getProperty("ManagerEventPage_EventTable_Partial")+(i+2)+
						prop.getProperty("ManagerEventPage_EventTypeCol"))).getText();
		 }
		 return eventArray;
	}
	private Boolean arraysDiff (String [][] array1, String [][] array2) { // this method compares the contents of the two tables
		  Boolean diff=false || (array1.length!=array2.length);
		  for (int i=0;i<array1.length && !diff;i++) {
			 diff  = !array1[i][0].equals(array2[i][0]) || !array1[i][1].equals(array2[i][1]) || 
					 !array1[i][2].equals(array2[i][2]) || !array1[i][3].equals(array2[i][3]) ||
					 !array1[i][4].equals(array2[i][4]);
		  }
		  return diff;
	}
	
	@Test
	@FileParameters("test/shipproject/selenium/TC06a_test_cases.csv")
	public void TC06a(int testcaseNo,String header1,String header2,String header3) throws InterruptedException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		ship_login(driver,username,password,methodName+" ManagerHomePage "+testcaseNo);
		verifymanagerHomepage(driver,header1,header2,header3);
		Thread.sleep(1_000);
		driver.findElement(By.xpath(prop.getProperty("mngr_hompg_logout_btn"))).click();
		Thread.sleep(1_000);
	}
	
	@Test
	@FileParameters("test/shipproject/selenium/TC02a_test_cases.csv")
	public void TC06b(int testcaseNo,String col1,String col2,String col3,String col4,String col5,String col6) throws InterruptedException, SQLException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		ship_login(driver,username,password,methodName+" ManagerHomePage "+testcaseNo);
		manager_function(driver,FunctionManager.ViewAllEvents);
		verifyHeaders(driver,"ManagerEventPage_EventName_header",col1,"ManagerEventPage_Location_header",col2,"ManagerEventPage_Date_header",col3,"ManagerEventPage_Time_header",col4,"ManagerEventPage_Type_header",col5,"ManagerEventPage_Action_header",col6,methodName+" verifyHeaders test case "+testcaseNo);
		WebElement eventTable=driver.findElement(By.xpath(prop.getProperty("ManagerEventPage_EventTable")));
		int rows = eventTable.findElements(By.tagName("tr")).size();
		assertFalse(arraysDiff(getTableContentsFromPage(rows),listmanevents(rows)));
		driver.findElement(By.xpath(prop.getProperty("ManagerEventPage_logout_btn"))).click();
		Thread.sleep(1_000);
	}
	
	@Test
	@FileParameters("test/shipproject/selenium/TC02c_test_cases.csv")
	public void TC06c(int testcaseNo,String date,String time,String error,String header) throws InterruptedException, SQLException, ParseException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		ship_login(driver,username,password,methodName+" ManagerHomePage "+testcaseNo);
		manager_function(driver,FunctionManager.ViewEventSummary);
		filldate(driver,date,time,methodName+" datefill test case "+testcaseNo);
		if(!error.equals("")) {
			verifyfilldate(driver,error);
			Thread.sleep(1_000);
			driver.findElement(By.xpath(prop.getProperty("coordinatorviewAsgneventsummary_logout"))).click();
			Thread.sleep(1_000);
		}
		else {
			verifymanagerlistpage(driver,header);
			WebElement eventTable=driver.findElement(By.xpath(prop.getProperty("ManagerEventPage_EventTable")));
			int rows = eventTable.findElements(By.tagName("tr")).size();
			Thread.sleep(2_000);
			assertFalse(arraysDiff(getTableContentsFromPage(rows),listeventdate(date,time,rows)));
			driver.findElement(By.xpath(prop.getProperty("ManagerEventPage_logout_btn"))).click();
		}
		
	}
	
	@Test
	@FileParameters("test/shipproject/selenium/TC06d_test_cases.csv")
	public void TC06d(int testcaseNo,String date,String time,String xpathsel,String col1,String col2,String col3,String col4,String col5,String col6,String col7,String col8,String col9,String col10,String col11,String col12,String col13,String col14,String col15,String col16,String col17,String col18,String col19,String col20) throws InterruptedException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		ship_login(driver,username,password,methodName+" ManagerHomePage "+testcaseNo);
		manager_function(driver,FunctionManager.ViewEventSummary);
		filldate(driver,date,time,methodName+" datefill "+testcaseNo);
		Thread.sleep(2_000);
		driver.findElement(By.xpath(xpathsel)).click();
		verifystrings10(driver,"ManagerViewSelectedEvents_EventName_label",col1,"ManagerViewSelectedEvents_Location_label",col2,"ManagerViewSelectedEvents_Capacity_label",col3,"ManagerViewSelectedEvents_EstAttendees_label",col4,"ManagerViewSelectedEvents_Duration_label",col5,"ManagerViewSelectedEvents_Type_label",col6,"ManagerViewSelectedEvents_Date_label",col7,
				"ManagerViewSelectedEvents_Time_label",col8,"ManagerViewSelectedEvents_CoordinatorFName_label",col9,"ManagerViewSelectedEvents_CoordinatorLName_label",col10,methodName+" verifySpecific2 test case "+testcaseNo);
		verifystrings10(driver,"ManagerViewSelectedEvents_EventName_value",col11,"ManagerViewSelectedEvents_Location_value",col12,"ManagerViewSelectedEvents_Capacity_value",col13,"ManagerViewSelectedEvents_EstAttendees_value",col14,"ManagerViewSelectedEvents_Duration_value",col15,"ManagerViewSelectedEvents_Type_value",col16,"ManagerViewSelectedEvents_Date_value",col17,
				"ManagerViewSelectedEvents_Time_value",col18,"ManagerViewSelectedEvents_CoordinatorFName_value",col19,"ManagerViewSelectedEvents_CoordinatorLName_value",col20,methodName+" verifySpecific2 test case "+testcaseNo);
		driver.findElement(By.xpath(prop.getProperty("ManagerViewSelectedEvents_Logout_btn"))).click();
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
