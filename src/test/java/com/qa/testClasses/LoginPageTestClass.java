package com.qa.testClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTestClass {
	
	// What are logs : capturing info/activities during the program execution
//	types of logs :
//		1. Info 
//		2. Warning 
//		3. error
//		4. fatal
//	
//	How to generate logs : use apache log4j API
//	how it works> it reads log 4j configuration from log4j.properties file
	// where to create the log4j.properties files? we need to create in resources folder
	
	WebDriver driverObject;
	
	@BeforeMethod
	public void setup() {
		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\rosharma\\Downloads\\geckoDrivers\\Chrome\\chromedriver.exe");
		driverObject = new ChromeDriver();
		
		
		driverObject.manage().window().maximize();
		driverObject.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driverObject.manage().deleteAllCookies();
		driverObject.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		driverObject.get("https://ui.freecrm.com/");
	}
	
	@Test
	public void verifyTitle() {
		String title = driverObject.getTitle();
		Assert.assertEquals(title, "Cogmento CRM");
	}
	
	@Test
	public void logoDisplayed() {
		boolean isDisplayed = driverObject.findElement(By.xpath("//*[@class='foreground']")).isDisplayed();
		Assert.assertTrue(isDisplayed);
	}
	
	@AfterMethod
	public void tearDown() {
		driverObject.quit();
	}

}
