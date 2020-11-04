package functions;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class shipproject_funtions {
	public static WebDriver driver;
	public static Properties prop;
	
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
			  Thread.sleep(0);
		} catch (InterruptedException e) {}
	}
	
	public void ship_login(WebDriver driver, String sUserName, String sPassword ) {
		driver.findElement(By.xpath(prop.getProperty("login_username"))).clear();
		driver.findElement(By.xpath(prop.getProperty("login_username"))).sendKeys(sUserName);
		driver.findElement(By.xpath(prop.getProperty("login_password"))).clear();
		driver.findElement(By.xpath(prop.getProperty("login_password"))).sendKeys(sUserName);
		driver.findElement(By.xpath(prop.getProperty("login_Submit_Btn"))).click();
	}
	public void ship_registration(WebDriver driver,String username,String first_name, String last_name, String password,String cpassword, String phone,String email,String memtype,String room_number,String deck_number) {
		
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
	}

}
