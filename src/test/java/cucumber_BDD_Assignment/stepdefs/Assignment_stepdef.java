package cucumber_BDD_Assignment.stepdefs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber_BDD_Assignment.core.WebDriverFactory;
import cucumber_BDD_Assignment.pageobjects.HomePageObjects;
import cucumber_BDD_Assignment.pageobjects.commonPageObjects;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class Assignment_stepdef {


	WebDriver driver;
	String base_url= "http://automationpractice.com";
	private static final Logger logger = LogManager.getLogger(Assignment_stepdef.class);
	int implicit_wait_timeout_in_sec=20;
	Scenario scn;


	HomePageObjects hmePageObjects;	
	commonPageObjects cmnPageObjects;

	@Before
	public void setUp(Scenario scn) throws Exception {
		this.scn = scn;
		String browserName = WebDriverFactory.getBrowserName();
		driver = WebDriverFactory.getWebDriverForBrowser(browserName);
		logger.info("Browser invoked.");


		hmePageObjects= new HomePageObjects(driver);
		cmnPageObjects = new commonPageObjects(driver);
	}

	@After(order=1)
	public void cleanup() {
		WebDriverFactory.quitDriver();
		scn.log("Browser Closed");
		logger.info("Browser closed");

	}

	@After(order=2)
	public void takeScreenShot(Scenario s) {
		if(s.isFailed()) {
			TakesScreenshot scrnShot = (TakesScreenshot)driver;
			byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png","Failed Step Name: " + s.getName());
		}else{
			scn.log("Test case is passed, no screen shot captured");
		}
	}

	@Given("User Navigated to URL")
	public void user_navigated_to_url() {
		cmnPageObjects.homePageUrlLaunch(base_url);
		scn.log("Browser navigated to URL: " + base_url);
	}


	@When("User navigate to home page")
	public void user_navigate_to_home_page() {
		logger.info("user is navigated to home page");
		scn.log("user is navigated to home page");
	}
	@Then("user get redirected to  {string}")
	public void user_get_redirected_to(String expectedUrl) {
		String actualUrl= hmePageObjects.getUrl();
		//String expectedUrl= "http://automationpractice.com/index.php";
		Assert.assertEquals(actualUrl,expectedUrl );
		logger.info("Expected "+expectedUrl+" and actual "+actualUrl+" are matched with each other");
		scn.log("Expected "+expectedUrl+" and actual "+actualUrl+" are matched with each other");
	}

	@Then("Logo is displayed")
	public void logo_is_displayed() {
		cmnPageObjects.checkLogoVisibility();
		scn.log("Logo is displayed");
	}


	@Then("width of logo is {int} and height is {int}")
	public void width_of_logo_is_and_height_is(Integer width, Integer height) {
		hmePageObjects.checkImageDimension(width,height);
		scn.log("Dimensions of logo according to expectation");
	}

	@When("search a product with name {string}")
	public void search_a_product_with_name(String productName) {
		cmnPageObjects.searchTextBox(productName);
		scn.log("T-shirt enters in search text box");
	}


	@Then("search result contain {string} as text")
	public void search_result_contain_as_text(String productName) throws InterruptedException
	{
		hmePageObjects.searchResultValidation(productName);
		scn.log("search result contains " +productName);
	}
	@When("User click on twitter link")
	public void user_click_on_twitter_link() {
		hmePageObjects.twitterLink();
		scn.log("clicks on twitter hyper link");
	}

	@When("User navigated to new window")
	public void user_navigated_to_new_window() {
		cmnPageObjects.windowHandler();
	}

	@Then("url contain {string}")
	public void url_contain(String expectedText) {
		String actualUrl=hmePageObjects.getUrl();
		logger.info( "Actual Url" +actualUrl);
		Assert.assertTrue(actualUrl.contains(expectedText));
		logger .info("Actual url contains "+ expectedText);
	}

	@Then("Twitter account name is {string}")
	public void twitter_account_name_is(String accountHolderName) {
		String accname=driver.findElement(By.xpath("//span[text()='Selenium Framework']")).getText();
		logger.info("Expected account holder name is"+ accountHolderName);
		Assert.assertEquals(accountHolderName,accname);		
		scn.log("Validated twitter account name");	

	}

	@Then("main product category count is {int}")
	public void main_product_category_count_is(Integer expectedCount) {
		Assert.assertEquals(expectedCount, hmePageObjects.productCategory());
	}

//	@Then("Text is displayed for all three categories")
//	public void text_is_displayed_for_all_three_categories() {
//		hmePageObjects.txtOfProductCategoris();
//		logger.info("Main Categories are displayed");
//		scn.log("Main Categories are displayed");
//	}
	
	@Then("{string} the displayed three categories is as shown below")
	public void the_displayed_three_categories_is_as_shown_below(String productName) {
		hmePageObjects.productName(productName);
	}
	
//	@Then("list of WOMEN categories is displayed")
//	public void list_of_women_categories_is_displayed(String productName) {
//		hmePageObjects.productName(productName);
//	}


}

