package com.automazing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	private WebDriver driver;
	
	//By Locators
	private By emailId = By.id("email");
	private By password = By.id("passwd");
	private By signInBtn = By.id("SubmitLogin");
	private By forgotPasswordLink = By.linkText("Forgot your password?123");
	
	//Page Class Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//Page Actions
	public String getLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean isForgotPasswordLinkExist() {
		return driver.findElement(forgotPasswordLink).isDisplayed();
	}
	
	public void enterUserName(String userName) {
		driver.findElement(emailId).sendKeys(userName);
	}
	
	public void enterPassword(String pwd) {
		driver.findElement(password).sendKeys(pwd);
	}
	
	public void clickOnLogin() {
		driver.findElement(signInBtn).click();
	}
	
	public AccountsPage doLogin(String uName, String pwd) {
		System.out.println("Login with "+uName+", and "+pwd);
		driver.findElement(emailId).sendKeys(uName);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(signInBtn).click();
		return new AccountsPage(driver);
	}

}
