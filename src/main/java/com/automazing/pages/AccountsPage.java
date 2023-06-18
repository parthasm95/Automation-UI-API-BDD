package com.automazing.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountsPage {
	
	private WebDriver driver;
	
	
	//By Locators
	private By accountItems = By.cssSelector("div#center_column span");
	
		
	//Page Class Constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
	}
	
		
	//Page Actions
	public String getAccountsPageTitle() {
		return driver.getTitle();
	}
	
	public int getAccountItemsCount() {
		return driver.findElements(accountItems).size();
	}
	
	public List<String> getAccountItemsList() {
		List<String> accountsList = new ArrayList<String>();
		List<WebElement> accountsHeaderList = driver.findElements(accountItems);
		
		for(WebElement e : accountsHeaderList) {
			String text = e.getText();
			accountsList.add(text);
			System.out.println(text);
		}
		return accountsList;
	}

}
