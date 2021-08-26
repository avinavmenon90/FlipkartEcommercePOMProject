package com.flipkart.factory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {
	
	private WebDriver driver;
	
	public WebDriver initDriver(String browser, Properties prop) {
		
		if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver","./src/main/java/com/flipkart/utilities/Browser Drivers/chromedriver.exe");
			
/*		//open in Incognito mode
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options); */
			
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")){
			System.setProperty("webdriver.edge.driver","./src/main/java/com/flipkart/utilities/Browser Drivers/msedgedriver.exe");
			driver = new EdgeDriver();
		}
		
	//Launch URL	
		driver.get(prop.getProperty("URL"));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		return driver;
	}

}
