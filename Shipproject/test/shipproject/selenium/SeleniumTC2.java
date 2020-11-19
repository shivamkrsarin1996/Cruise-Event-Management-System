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
public class SeleniumTC2 extends shipproject_funtions{
	private StringBuffer verificationErrors = new StringBuffer();
	public String sAppURL, sSharedUIMapPath, testDelay,username,password;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	    Logger.getLogger("org.openqa.selenium.remote").setLevel(Level.OFF);	  
	    }
	
	@Before
	public void setUp() throws Exception {
		driver = invokeCorrectBrowser();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    prop = new Properties();
	    prop.load(new FileInputStream("./Login/Login.properties"));
	    username = prop.getProperty("coordinator_username");
		password = prop.getProperty("coordinator_password");
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
					 !array1[i][4].equals(array2[i][4]);
		  }
		  return diff;
	  }
	 
	 private String [][] getTableContentsFromPage(int listSize) {
		 String [][] eventArray = new String[listSize-1][5];
		 for (int i=0; i<listSize-1; i++) {
			 eventArray[i][0]=driver.findElement(By.xpath(prop.getProperty("coordinatoreventpg_eventTableHeader")+(i+2)+
						prop.getProperty("coordinatoreventpg_EventnameCol"))).getText();
			 eventArray[i][1]=driver.findElement(By.xpath(prop.getProperty("coordinatoreventpg_eventTableHeader")+(i+2)+
						prop.getProperty("coordinatoreventpg_LocationCol"))).getText();
			 eventArray[i][2]=driver.findElement(By.xpath(prop.getProperty("coordinatoreventpg_eventTableHeader")+(i+2)+
						prop.getProperty("coordinatoreventpg_DateCol"))).getText();
			 eventArray[i][3]=driver.findElement(By.xpath(prop.getProperty("coordinatoreventpg_eventTableHeader")+(i+2)+
						prop.getProperty("coordinatoreventpg_TimeCol"))).getText();
			 eventArray[i][4]=driver.findElement(By.xpath(prop.getProperty("coordinatoreventpg_eventTableHeader")+(i+2)+
						prop.getProperty("coordinatoreventpg_TypeCol"))).getText();
		 }
		 return eventArray;
	 }
	
	
	@Test
	@FileParameters("test/shipproject/selenium/TC02a_test_cases.csv")
	public void TC02a(int testcaseNo,String col1,String col2,String col3,String col4,String col5,String col6) throws Exception{
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		ship_login(driver,username,password,methodName+" CoordinatorHomePage");
		Coordinator_function(driver,FunctionCoordinator.ListAllAssignedEvent);
		verifyHeaders(driver,"coordinatoreventpg_EventnameHeader",col1,"coordinatoreventpg_LocationHeader",col2,"coordinatoreventpg_DateHeader",col3,"coordinatoreventpg_TimeHeader",col4,"coordinatoreventpg_TypeHeader",col5,"coordinatoreventpg_ActionHeader",col6,methodName+" verifyHeaders test case "+testcaseNo);
		WebElement eventTable=driver.findElement(By.xpath(prop.getProperty("coordinatoreventpg_eventTable")));
		int rows = eventTable.findElements(By.tagName("tr")).size();
		int id=userid(username);
		assertFalse(arraysDiff(getTableContentsFromPage(rows),listcorevents(id,rows)));
		driver.findElement(By.xpath(prop.getProperty("coordinatoreventpg_Logout_Btn"))).click();
		Thread.sleep(1_000);
	}
	
	@Test
	@FileParameters("test/shipproject/selenium/TC02b_test_cases.csv")
	public void TC02b(int testcaseNo,String col1,String col2,String col3,String col4,String col5,String col6,String col7,String col8,String col9,String col10,String col11,String col12,String col13,String col14,String col15,String col16,String col17,String col18,String col19,String col20,String col21) throws InterruptedException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		ship_login(driver,username,password,methodName+" CoordinatorHomePage");
		Coordinator_function(driver,FunctionCoordinator.ListAllAssignedEvent);
		System.out.println(col1);
		Thread.sleep(2_000);
		driver.findElement(By.xpath(col1)).click();
		verifystrings10(driver,"coordinatorspecificlist_EventnameRow",col2,"coordinatorspecificlist_LocationRow",col3,"coordinatorspecificlist_CapacityRow",col4,"coordinatorspecificlist_EstimatedRow",col5,"coordinatorspecificlist_DurationRow",col6,"coordinatorspecificlist_TypeRow",col7,"coordinatorspecificlist_DateRow",col8,
				"coordinatorspecificlist_TimeRow",col9,"coordinatorspecificlist_CoordinatorFname",col10,"coordinatorspecificlist_CoordinatorLname",col11,methodName+" verifySpecific2 test case "+testcaseNo);
		verifystrings10(driver,"coordinatorspecificlist_EventnameVal",col12,"coordinatorspecificlist_LocationVal",col13,"coordinatorspecificlist_CapacityVal",col14,"coordinatorspecificlist_EstimatedVal",col15,"coordinatorspecificlist_DurationVal",col16,"coordinatorspecificlist_TypeVal",col17,"coordinatorspecificlist_DateVal",col18,
				"coordinatorspecificlist_TimeVal",col19,"coordinatorspecificlist_CoordinatorFnameVal",col20,"coordinatorspecificlist_CoordinatorLnameVal",col21,methodName+" verifySpecific2 test case "+testcaseNo);
		driver.findElement(By.xpath(prop.getProperty("coordinatoreventspecificlist_Logout_Btn"))).click();
		Thread.sleep(1_000);
	}
	
	@Test
	@FileParameters("test/shipproject/selenium/TC02c_test_cases.csv")
	public void TC02c(int testcaseNo,String date,String time,String error,String header) throws InterruptedException, SQLException, ParseException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		ship_login(driver,username,password,methodName+" CoordinatorHomePage");
		Coordinator_function(driver,FunctionCoordinator.ViewEventDate);
		filldate(driver,date,time,methodName+" datefill test case "+testcaseNo);
		if(!error.equals("")) {
		verifyfilldate(driver,error);
		Thread.sleep(1_000);
		driver.findElement(By.xpath(prop.getProperty("coordinatorviewAsgneventsummary_logout"))).click();
		Thread.sleep(1_000);
		}
		else {
			verifycorlistpage(driver,header);
			WebElement eventTable=driver.findElement(By.xpath(prop.getProperty("coordinatoreventpg_eventTable")));
			int rows = eventTable.findElements(By.tagName("tr")).size();
			Thread.sleep(2_000);
			assertFalse(arraysDiff(getTableContentsFromPage(rows),listeventdate(date,time,rows)));
			driver.findElement(By.xpath(prop.getProperty("coordinatoreventpg_Logout_Btn"))).click();
		}
	}
	
	@Test
	@FileParameters("test/shipproject/selenium/TC02c_test_cases.csv")
	public void TC02d(int testcaseNo,String date,String time,String error,String header) throws InterruptedException, SQLException, ParseException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		ship_login(driver,username,password,methodName+" CoordinatorHomePage");
		Coordinator_function(driver,FunctionCoordinator.ViewAssignedDate);
		filldate(driver,date,time,methodName+" datefill test case "+testcaseNo);
		if(!error.equals("")) {
		verifyfilldate(driver,error);
		Thread.sleep(1_000);
		driver.findElement(By.xpath(prop.getProperty("coordinatorviewAsgneventsummary_logout"))).click();
		Thread.sleep(1_000);
		}
		else {
			verifycorlistpage(driver,header);
			WebElement eventTable=driver.findElement(By.xpath(prop.getProperty("coordinatoreventpg_eventTable")));
			int rows = eventTable.findElements(By.tagName("tr")).size();
			Thread.sleep(2_000);
			int id=userid(username);
			assertFalse(arraysDiff(getTableContentsFromPage(rows),listCoreventdate(date,time,rows,id)));
			driver.findElement(By.xpath(prop.getProperty("coordinatoreventpg_Logout_Btn"))).click();
			Thread.sleep(1_000);
		}
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
