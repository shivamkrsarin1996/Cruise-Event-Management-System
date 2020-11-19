package shipproject.selenium;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import functions.shipproject_funtions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class SeleniumTC1 extends shipproject_funtions{
	private StringBuffer verificationErrors = new StringBuffer();
	public String sAppURL, sSharedUIMapPath, testDelay;
	
	@Before
	public void setUp() throws Exception {
		driver = invokeCorrectBrowser();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    prop = new Properties();	  
	    prop.load(new FileInputStream("./Configuration/SP_Configuration.properties"));
	    sAppURL = prop.getProperty("sAppURL");
		sSharedUIMapPath = prop.getProperty("SharedUIMap");
		testDelay=prop.getProperty("testDelay");
		prop.load(new FileInputStream(sSharedUIMapPath));
	    }
	
	@Test
	@FileParameters("test/shipproject/selenium/TC01a_test_cases.csv")
	public void TC01a(int testcaseNo,String username,String first_name, String last_name, String password,String cpassword, String phone,String email,String memtype,String room_number,String deck_number,
			String errorMsg,String usernameError,String first_nameError,String last_nameError,String passwordError,String cpasswordError,String phoneError,String emailError,String room_numberError,String deck_numberError,String sucess) throws Exception {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
	    driver.get(sAppURL);
	    driver.findElement(By.xpath(prop.getProperty("login_Registration_Btn"))).click();
	    if(!errorMsg.equals("")) {
	    	ship_registration(driver,username,first_name,last_name,password,cpassword, phone,email,memtype,room_number,deck_number,methodName+" registationFuntion "+testcaseNo);
		    verifyRegistration(driver,errorMsg,usernameError,first_nameError,last_nameError,passwordError,cpasswordError,phoneError,emailError,room_numberError,deck_numberError);
		    Thread.sleep(1_000);
		    driver.findElement(By.xpath(prop.getProperty("registration_AppLink"))).click();
	        Thread.sleep(1_000);
	    }
	    else {
	    	Random rand = new Random();
	    	username=username+Integer.toString(rand.nextInt(900)+100)+Integer.toString(rand.nextInt(900)+100);
	    	ship_registration(driver,username,first_name,last_name,password,cpassword, phone,email,memtype,room_number,deck_number,methodName+" registationFuntion "+testcaseNo);
	    	Thread.sleep(1_000);
	    	assertTrue(driver.findElement(By.xpath(prop.getProperty("login_regMsg"))).getAttribute("value").equals(sucess));
	    	Thread.sleep(1_000);
	    }
	}
	
	@Test
	@FileParameters("test/shipproject/selenium/TC01b_test_cases.csv")
	public void TC01b(int testcaseNo,String username,String password,String errorMsg,String header,String logoutmsg) throws InterruptedException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
	    driver.get(sAppURL);
	    ship_login(driver,username,password,methodName+" loginFuntion "+testcaseNo);
	    if(!errorMsg.equals("")) {
	    	verifyLogin(driver,errorMsg);
	    }
	    else {
	    	corlogin(driver,header,methodName+" login Sucess "+testcaseNo);//coordinatorhmpg_Logout_Btn
	    	driver.findElement(By.xpath(prop.getProperty("coordinatorhmpg_Logout_Btn"))).click();
	    	logout(driver,logoutmsg,methodName+" logout Sucess "+testcaseNo);
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
