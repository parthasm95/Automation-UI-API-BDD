package stepdefinitions.ui;

import java.util.List;

import org.junit.Assert;

import com.automazing.factory.DriverFactory;
import com.automazing.pages.AccountsPage;
import com.automazing.pages.HomePage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class HomePageStepDefinitions {

	private AccountsPage accountsPage = new AccountsPage(DriverFactory.getDriver());
	private HomePage homePage;
	private static String pageTitle;

	@Given("User navigate to Home Page")
	public void user_navigate_to_home_page() {
		homePage = accountsPage.goToHomePage();
	}

	@Given("user is on Home page")
	public void user_is_on_home_page() {
		pageTitle = homePage.getHomePageTitle();
		System.out.println("Home Page Title is : " + pageTitle);
	}

	@Then("user gets items in Navigation Bar")
	public void user_gets_items_in_navigation_bar(DataTable navBarItems) {
		List<String> expectedItems = navBarItems.asList();
		System.out.println("Expected Home Page Navigation Bar Items : " + expectedItems);

		List<String> actualItems = homePage.getHomePageNavBarItems();
		System.out.println("Actual Home Page Navigation Bar Items : " + actualItems);

		Assert.assertTrue(expectedItems.containsAll(actualItems));
	}

	@Then("search bar should be present")
	public void search_bar_should_be_present() {
		Assert.assertTrue(homePage.isSearchBarExist());
	}

	@Then("user get list of categories")
	public void user_get_list_of_categories(DataTable categoryList) {
		List<String> expectedItems = categoryList.asList();
		System.out.println("Expected Home Page Category List : " + expectedItems);

		List<String> actualItems = homePage.getCategoryList();
		System.out.println("Actual Home Page Category List : " + actualItems);

		Assert.assertTrue(expectedItems.containsAll(actualItems));
	}

}
