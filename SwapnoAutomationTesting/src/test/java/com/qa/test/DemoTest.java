package com.qa.test;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;

public class DemoTest {

public static WebDriver driver;
public static String browser;
public static WebElement element, element1, element2;
public static String expectedLocation= "ADABOR";
	

@BeforeClass
	public static void YahooTest() throws InterruptedException{
		SetBrowser();
		SetBrowserConfig();
		SetConnection();
	}
	public static void SetBrowser() {
		browser="Chrome";
		
	}
	public static void SetBrowserConfig() {
		if(browser.contains("Chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();
			}
		
	}
	public static void SetConnection() {
		driver.get("https://www.shwapno.com/");
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		driver.manage().window().maximize();
		
	}

@Test(priority=0)
public static void setLocation() throws InterruptedException {
	
	Select drpState = new Select(driver.findElement(By.xpath("//select[@id='state']")));
	drpState.selectByVisibleText("Dhaka");
	Select drpCity = new Select(driver.findElement(By.xpath("//select[@id='city']")));
	drpCity.selectByVisibleText("Adabor");
	WebElement submitBtn = driver.findElement(By.xpath("//input[@id='btnFindStore']"));
	submitBtn.click();
	
	
}
@Test(priority=1)
public static void validateLocation() throws InterruptedException{
	element = driver.findElement(By.xpath("//span[@class='currentLocation']"));
	String currentLocation = element.getText();
	//validating if the current location is correct
	if(currentLocation.equals(expectedLocation)) {
		AssertJUnit.assertTrue(true);
		System.out.println(currentLocation+" matches with the expected location which is "+expectedLocation);
	}
	else {
		AssertJUnit.fail();
		System.out.println(currentLocation+" mismatches with the expected location which is "+expectedLocation);
	}
}


public static void register() throws InterruptedException{
	driver.findElement(By.xpath("//a[normalize-space()='register']")).click();
	driver.findElement(By.xpath("//input[@id='txtFirstName']")).sendKeys("Jonas Smith");
	driver.findElement(By.xpath("//input[@id='txtUserName']")).sendKeys("andrejonas.sth12345@gmail.com");
	driver.findElement(By.xpath("//input[@id='txtMobileNo']")).sendKeys("01656789023");
	driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
	driver.findElement(By.xpath("//input[@id='txtConfirmPassword']")).sendKeys("123456");
	driver.findElement(By.xpath("//input[@id='btnSubmit']")).click();
}


public static void validateRegister() throws InterruptedException{
	element1=driver.findElement(By.xpath("//div[normalize-space()='Your registration has been successful.']"));
	String expectedText = element1.getText();
	if(expectedText.contains("Your registration has been successful.")) {
		AssertJUnit.assertTrue(true);
	}else {
		AssertJUnit.fail();
	}
}
@Test(priority=2)
public static void login() throws InterruptedException{
	//driver.findElement(By.xpath("//a[@id='lnkLogout1']")).click();
	driver.findElement(By.xpath("//a[contains(@class,'header-login-link LoginPopUpLink')]")).click();
	driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_ctl00_ctl01_Login1_UserName']")).sendKeys("andrejonas.sth12345@gmail.com");
	driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_ctl00_ctl01_Login1_Password']")).sendKeys("123456");
	driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_ctl00_ctl01_Login1_LoginImageButton']")).click();
	Thread.sleep(3000);
}
@Test(priority=3)	
public static void validateLogin() throws InterruptedException{
	element2 = driver.findElement(By.xpath("//a[@class='loginlnk header-login-link']"));
	String user = element2.getText();
	if(user.equals("HI JONAS SMITH")) {
		AssertJUnit.assertTrue(true);
	}else {
		AssertJUnit.fail();
	}
}
	
@AfterClass
public static void closeBrowser() {
	driver.close();
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
