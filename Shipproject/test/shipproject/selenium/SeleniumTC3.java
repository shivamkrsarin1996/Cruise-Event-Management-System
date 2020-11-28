package shipproject.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;
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

import functions.shipproject_funtions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import shipproject.model.user;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class SeleniumTC3 extends shipproject_funtions{
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

	@Test
	@FileParameters("test/shipproject/selenium/TC03a_test_cases.csv")
	public void TC03a(int testcaseNo,String header1,String header2,String header3,String header4,String header5) throws Exception{
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		ship_login(driver,username,password,methodName+" PassengerHomePage "+testcaseNo);
		verifypassengerHomepage(driver,header1,header2,header3,header4,header5);
		Thread.sleep(1_000);
		driver.findElement(By.xpath(prop.getProperty("psg_homepg_logout_btn"))).click();
		Thread.sleep(1_000);
	}
	
	@Test
	@FileParameters("test/shipproject/selenium/TC03b_test_cases.csv")
	public void TC03b(int testcaseNo,String col1,String col2,String col3,String col4,String col5,String col6,String col7,String col8) throws InterruptedException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		ship_login(driver,username,password,methodName+" PassengerHomePage "+testcaseNo);
		Passenger_function(driver,FunctionPassenger.Viewprofile);
		verifystrings8(driver,"psgInfo_psgID_label",col1,"psgInfo_psgFname_label",col2,"psgInfo_psgLname_label",col3,"psgInfo_psgPhone_label",col4,"psgInfo_psgEmail_label",col5,"psgInfo_psgRoom_label",col6,"psgInfo_psgDeck_label",col7,"psgInfo_psgMemType_label",col8,methodName+" verifyprofilepage test case "+testcaseNo);
		user User=userinformation(username);
		verifystrings8(driver,"psgInfo_psgID_value",Integer.toString(User.getId_user()),"psgInfo_psgFname_value",User.getFirst_name(),"psgInfo_psgLname_value",User.getLast_name(),"psgInfo_psgPhone_value",User.getPhone(),"psgInfo_psgEmail_value",User.getEmail(),"psgInfo_psgRoom_value",User.getRoom_number(),"psgInfo_psgDeck_value",User.getDeck_number(),"psgInfo_psgMemType_value",User.getMemtype(),methodName+" verifyprofilepage test case "+testcaseNo);
		driver.findElement(By.xpath(prop.getProperty("psgInfo_logout_btn"))).click();
		Thread.sleep(1_000);
	}
	
	@Test
	@FileParameters("test/shipproject/selenium/TC03c_test_cases.csv")
	public void TC03c(int testcaseNo,String title,String alert,String noModerror) throws InterruptedException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		ship_login(driver,username,password,methodName+" PassengerHomePage "+testcaseNo);
		Passenger_function(driver,FunctionPassenger.Viewprofile);
		Thread.sleep(1_000);
		driver.findElement(By.xpath(prop.getProperty("psgInfo_updateProfile_btn"))).click();
		assertTrue(driver.findElement(By.xpath(prop.getProperty("psgUpdateInfo_Title"))).getText().equals(title));
		takeScreenshot(driver,methodName+" preclick "+testcaseNo);
		try {
		driver.findElement(By.xpath(prop.getProperty("psgUpdateInfo_saveProfile_btn"))).click();
		assertEquals(alert,driver.switchTo().alert().getText());

		driver.switchTo().alert().accept();
		}
		catch (org.openqa.selenium.UnhandledAlertException e) {
		}
		Thread.sleep(3_000);
		assertTrue(driver.findElement(By.xpath(prop.getProperty("psgUpdateInfo_nomoderror"))).getAttribute("value").equals(noModerror));
		takeScreenshot(driver,methodName+" postclick "+testcaseNo);//
		driver.findElement(By.xpath(prop.getProperty("psgUpdateInfo_logout_btn"))).click();
		Thread.sleep(1_000);
	}
	
	@Test
	@FileParameters("test/shipproject/selenium/TC03d_test_cases.csv")
	public void TC03d(int testcaseNo,String psw,String fname,String lname,String phone,String email,String rnum,String dnum,String pswError,String fnameError,String lnameError,String phoneError,String emailError,String rnumError,String dnumError,String sucess) throws InterruptedException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		ship_login(driver,username,password,methodName+" PassengerHomePage "+testcaseNo);
		Passenger_function(driver,FunctionPassenger.Viewprofile);
		Thread.sleep(1_000);
		driver.findElement(By.xpath(prop.getProperty("psgInfo_updateProfile_btn"))).click();
		takeScreenshot(driver,methodName+" preclick "+testcaseNo);
		if(!pswError.equals("")) {
			updateprofileFill(driver,psw,fname,lname,phone,email,rnum,dnum,methodName+" updateFuntionpreclick "+testcaseNo);
			try {
				driver.findElement(By.xpath(prop.getProperty("psgUpdateInfo_saveProfile_btn"))).click();
				driver.switchTo().alert().accept();
			}
			catch (org.openqa.selenium.UnhandledAlertException e) {
			}
			takeScreenshot(driver,methodName+" updateFuntionpostclick "+testcaseNo);
			verifyupdate(driver,pswError,fnameError,lnameError,phoneError,emailError,rnumError,dnumError);
			driver.findElement(By.xpath(prop.getProperty("psgUpdateInfo_logout_btn"))).click();
			Thread.sleep(1_000);
		}
		else {
			int leftLimit = 97; // letter 'a'
		    int rightLimit = 122; // letter 'z'
		    int targetStringLength = 6;
		    Random random = new Random();
		    StringBuilder buffer = new StringBuilder(targetStringLength);
		    for (int i = 0; i < targetStringLength; i++) {
		        int randomLimitedInt = leftLimit + (int) 
		          (random.nextFloat() * (rightLimit - leftLimit + 1));
		        buffer.append((char) randomLimitedInt);
		    }
		    String generatedString = buffer.toString();
		    fname=fname+generatedString;
			updateprofileFill(driver,psw,fname,lname,phone,email,rnum,dnum,methodName+" updateFuntionpreclick "+testcaseNo);
			try {
				driver.findElement(By.xpath(prop.getProperty("psgUpdateInfo_saveProfile_btn"))).click();
				driver.switchTo().alert().accept();
			}
			catch (org.openqa.selenium.UnhandledAlertException e) {
			}
			takeScreenshot(driver,methodName+" updateFuntionpostclick "+testcaseNo);
			assertTrue(driver.findElement(By.xpath(prop.getProperty("psgInfo_errorMsg"))).getAttribute("value").equals(sucess));
	    	Thread.sleep(1_000);
	    	driver.findElement(By.xpath(prop.getProperty("psgInfo_logout_btn"))).click();
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
