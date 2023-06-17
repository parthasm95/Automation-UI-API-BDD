package stepdefinitions.ui;

import org.junit.Assert;

import com.automazing.factory.DriverFactory;
import com.automazing.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageStepDefinition {
	
	private static String pageTitle;
	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	
	@Given("user is on login page")
	public void user_is_on_login_page() {
	    DriverFactory.getDriver().get("http://automationpractice.pl/index.php?controller=authentication&back=my-account");
	}

	@When("user gets the title of the page")
	public void user_gets_the_title_of_the_page() {
		pageTitle = loginPage.getLoginPageTitle();
		System.out.println("Page Title is : "+pageTitle);
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String expectedPageTitle) {
	    Assert.assertTrue(pageTitle.contains(expectedPageTitle));
	}

	@Then("forgot your password link should be displayed")
	public void forgot_your_password_link_should_be_displayed() {
	    Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}

	@When("user enters username {string}")
	public void user_enters_username(String userName) {
	   loginPage.enterUserName(userName);
	}

	@When("user enters password {string}")
	public void user_enters_password(String password) {
		loginPage.enterPassword(password);
	}

	@When("user clicks on Login button")
	public void user_clicks_on_login_button(){
	    loginPage.clickOnLogin();
	}

}
