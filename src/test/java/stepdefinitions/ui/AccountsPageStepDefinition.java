package stepdefinitions.ui;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.automazing.factory.DriverFactory;
import com.automazing.pages.AccountsPage;
import com.automazing.pages.LoginPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AccountsPageStepDefinition {
	
	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	private AccountsPage accountsPage;
	
	private static String pageTitle;
	
	@Given("user has already logged in to application")
	public void user_has_already_logged_in_to_application(DataTable credTable) {
	    List<Map<String, String>> credentials = credTable.asMaps();
	    String userName = credentials.get(0).get("username");
	    String password = credentials.get(0).get("password");
	    
	    DriverFactory.getDriver().get("http://automationpractice.pl/index.php?controller=authentication&back=my-account");
	    accountsPage = loginPage.doLogin(userName, password);
	}

	@Given("user is on Accounts page")
	public void user_is_on_accounts_page() {
		pageTitle = accountsPage.getAccountsPageTitle();
		System.out.println("Accounts Page Title is : "+pageTitle);
	}

	@Then("user gets accounts section")
	public void user_gets_accounts_section(DataTable accountPageItems) {
		List<String> expectedList = accountPageItems.asList();
		System.out.println("Expected Accouts Page Items : "+expectedList);
		
	    List<String> actualList = accountsPage.getAccountItemsList();
	    System.out.println("Actual Accouts Page Items : "+actualList);
	    
	    Assert.assertTrue(expectedList.containsAll(actualList));
	}

	@Then("accounts section count should be {int}")
	public void accounts_section_count_should_be(Integer expectedCount) {
	    Assert.assertTrue(accountsPage.getAccountItemsCount() == expectedCount);
	}
	
	@Then("Home Page icon should be visible")
	public void home_page_icon_should_be_visible() {
	    Assert.assertTrue(accountsPage.isHomeIconExist());
	}
	
	@Then("user should be able to go to Home page by clicking home button")
	public void user_should_be_able_to_go_to_home_page_by_clicking_home_button() {
	    accountsPage.goToHomePage();
	}
	
}
