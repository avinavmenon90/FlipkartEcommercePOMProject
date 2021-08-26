package com.flipkart.testcases;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.flipkart.factory.DriverFactory;
import com.flipkart.pageobjects.HomePage;
import com.flipkart.utilities.ConfigPropertiesFileReader;
import com.flipkart.utilities.ElementUtils;

public class BaseClass {

	DriverFactory df;
	ConfigPropertiesFileReader cp;
	Properties prop;
	WebDriver driver;
	HomePage homePage;
	
	ElementUtils elementUtils;
	int counter;
	Object[][] excelData;
	
	@BeforeSuite
	public void setUp() {
		
		counter=0;
		cp = new ConfigPropertiesFileReader();
		df = new DriverFactory();
		prop = cp.initProperties(); //initiate & load from properties file
		driver = df.initDriver(prop.getProperty("browser"), prop); //pass prop obj to df & launch URL
		homePage = new HomePage(driver);
		elementUtils = new ElementUtils(driver);

	}

	@AfterSuite
	public void tearDown() {
		
		driver.close();
		driver.quit();
	}
	
	@BeforeMethod()
	public void login() throws InterruptedException {
		
		ElementUtils.clickButtonUsingJS(driver.findElement(By.xpath("//a[contains(text(),'Login')]")), driver);
		//driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();
		System.out.println("login field enabled? "+driver.findElement(By.xpath("//span[contains(text(),'Email')]")).isEnabled());
		driver.findElement(By.xpath("//span[contains(text(),'Email')]//preceding::input[@type='text'][contains(@class,'VJZDxU')]")).sendKeys(prop.getProperty("username"));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(prop.getProperty("password"));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[contains(text(),'Privacy Policy')]//following::button[@type='submit']//following::span[contains(text(),'Login')]")).click();
		Thread.sleep(6000);
	}
	
	@AfterMethod()
	public void logout() throws InterruptedException {
		
		Thread.sleep(2000);
		homePage.logoutFromAcct();
	}
	
	
}
