package com.automazing.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	private WebDriver driver;

	// By Locators
	private By navBarItems = By.xpath("//div[@class='nav']//div[@class='row']//div//a");
	private By searchBar = By.id("search_query_top");
	private By categoryLinks = By.xpath("//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a");

	// Page Class Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	// Page Actions
	public String getHomePageTitle() {
		return driver.getTitle();
	}
	
	public List<String> getHomePageNavBarItems(){
		List<String> navItems = new ArrayList<>();
		List<WebElement> links = driver.findElements(navBarItems);
		for(WebElement e : links) {
			navItems.add(e.getText());
		}
		return navItems;
	}
	
	public Boolean isSearchBarExist() {
		return driver.findElement(searchBar).isDisplayed();
	}
	
	public List<String> getCategoryList(){
		List<String> catList = new ArrayList<>();
		List<WebElement> catLinks = driver.findElements(categoryLinks);
		for(WebElement e : catLinks) {
			catList.add(e.getText());
		}
		return catList;
	}
	
}
