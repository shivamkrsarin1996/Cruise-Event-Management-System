package shipproject.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
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
import org.openqa.selenium.support.ui.Select;

import functions.shipproject_funtions;
import functions.shipproject_funtions.FunctionManager;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class SeleniumTC7 extends shipproject_funtions{
	
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
	
	@Test
	@FileParameters("test/shipproject/selenium/TC07a_test_cases.csv")
	public void TC07a(int testcaseNo,String date,String time,String title,String xpathsel,String alert,String noModerror) throws InterruptedException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		ship_login(driver,username,password,methodName+" ManagerHomePage "+testcaseNo);
		manager_function(driver,FunctionManager.ViewEventSummary);
		filldate(driver,date,time,methodName+" datefill "+testcaseNo);
		Thread.sleep(2_000);
		driver.findElement(By.xpath(xpathsel)).click();
		Thread.sleep(2_000);
		driver.findElement(By.xpath(prop.getProperty("ManagerViewSelectedEvents_ModifyEvent_btn"))).click();
		assertTrue(driver.findElement(By.xpath(prop.getProperty("ManagerModifyEvent_ModifyEvent_label"))).getText().equals(title));
		takeScreenshot(driver,methodName+" preclick "+testcaseNo);
		/////
		try {
			driver.findElement(By.xpath(prop.getProperty("ManagerModifyEvent_Submit_btn"))).click();
			assertEquals(alert,driver.switchTo().alert().getText());

			driver.switchTo().alert().accept();
			}
			catch (org.openqa.selenium.UnhandledAlertException e) {
			}
			Thread.sleep(3_000);
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ManagerModifyEvent_errorMsg"))).getAttribute("value").equals(noModerror));
			takeScreenshot(driver,methodName+" postclick "+testcaseNo);//
			driver.findElement(By.xpath(prop.getProperty("ManagerModifyEvent_Logout_btn"))).click();
			Thread.sleep(1_000);
	}
	
	@Test
	@FileParameters("test/shipproject/selenium/TC07b_test_cases.csv")
	public void TC07b(int testcaseNo,String date,String time,String xpathsel,String cdate,String ctime,String cEst,String Error1,String Error2,String Error3,String Error4,String sucess) throws InterruptedException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		ship_login(driver,username,password,methodName+" ManagerHomePage "+testcaseNo);
		manager_function(driver,FunctionManager.ViewEventSummary);
		filldate(driver,date,time,methodName+" datefill "+testcaseNo);
		Thread.sleep(2_000);
		driver.findElement(By.xpath(xpathsel)).click();
		Thread.sleep(2_000);
		driver.findElement(By.xpath(prop.getProperty("ManagerViewSelectedEvents_ModifyEvent_btn"))).click();
		fillmodifyevent(driver,cdate,ctime,cEst);
			try {
				driver.findElement(By.xpath(prop.getProperty("ManagerModifyEvent_Submit_btn"))).click();

				driver.switchTo().alert().accept();
				}
				catch (org.openqa.selenium.UnhandledAlertException e) {
				}
			Thread.sleep(3_000);
			if(!Error1.equals("")) {
			verifyModifyEvent(driver,Error1,Error2,Error3,Error4);
			takeScreenshot(driver,methodName+" ModifyErrors "+testcaseNo);
			driver.findElement(By.xpath(prop.getProperty("ManagerModifyEvent_Logout_btn"))).click();
			takeScreenshot(driver,methodName+" postclick "+testcaseNo);
			}
			else {
				assertTrue(driver.findElement(By.xpath(prop.getProperty("ManagerViewSelectedEvents_errorMsg"))).getAttribute("value").equals(sucess));
				takeScreenshot(driver,methodName+" ModifyErrors "+testcaseNo);
				driver.findElement(By.xpath(prop.getProperty("ManagerViewSelectedEvents_Logout_btn"))).click();
			}
			Thread.sleep(1_000);
	}
	
	@Test
	@FileParameters("test/shipproject/selenium/TC07c_test_cases.csv")
	public void TC07c(int testcaseNo,String date,String time,String xpathsel,String manager,String error1,String error2,String sucess) throws InterruptedException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		ship_login(driver,username,password,methodName+" ManagerHomePage "+testcaseNo);
		manager_function(driver,FunctionManager.ViewEventSummary);
		filldate(driver,date,time,methodName+" datefill "+testcaseNo);
		Thread.sleep(2_000);
		driver.findElement(By.xpath(xpathsel)).click();
		Thread.sleep(2_000);
		takeScreenshot(driver,methodName+" preassign "+testcaseNo);
		driver.findElement(By.xpath(prop.getProperty("ManagerViewSelectedEvents_AssignNewCoord_btn"))).click();
		Thread.sleep(1_000);
		new Select(driver.findElement(By.xpath(prop.getProperty("ManagerAssignNewCoord_newCoordinator_dropdown")))).selectByVisibleText(manager);
		takeScreenshot(driver,methodName+" preassign "+testcaseNo);
		driver.findElement(By.xpath(prop.getProperty("ManagerAssignNewCoord_Submit_btn"))).click();
		if(!error1.equals("")) {
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ManagerAssignNewCoord_errorMsg"))).getAttribute("value").equals(error1));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ManagerAssignNewCoord_newCoordinator_errorMsg"))).getAttribute("value").equals(error2));
			takeScreenshot(driver,methodName+" postassinError "+testcaseNo);
			driver.findElement(By.xpath(prop.getProperty("ManagerAssignNewCoord_Logout_btn"))).click();
		}
		else {
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ManagerViewSelectedEvents_errorMsg"))).getAttribute("value").equals(sucess));
			takeScreenshot(driver,methodName+" postassinSuccess "+testcaseNo);
			driver.findElement(By.xpath(prop.getProperty("ManagerViewSelectedEvents_Logout_btn"))).click();
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
