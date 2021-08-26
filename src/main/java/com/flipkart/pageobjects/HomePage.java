package com.flipkart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	private WebDriver driver;
	
	private Actions action;
	
	@FindBy(xpath="//div[contains(text(),'My Account')]")
	private WebElement myAcctBtn;
	
	@FindBy(xpath="//div[contains(text(),'Logout')]")
	private WebElement logoutBtn;

	public HomePage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void logoutFromAcct() throws InterruptedException {

		action = new Actions(driver);
		action.moveToElement(myAcctBtn).click(logoutBtn).build().perform();
	
	}

	public String getHomePageTitle() {

		return driver.getTitle();
	}
	
	

}
