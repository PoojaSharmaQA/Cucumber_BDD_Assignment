package cucumber_BDD_Assignment.stepdefs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
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
	public void user_get_redirected_to(String string) {
		String actualUrl= hmePageObjects.getUrl();
		String expectedUrl= "http://automationpractice.com/index.php";
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
	public void width_of_logo_is_and_height_is(Integer int1, Integer int2) {
		hmePageObjects.checkImageDimension();
		scn.log("Dimensions of logo according to expectation");
	}

	@When("Enter text {string} in search text box")
	public void enter_text_in_search_text_box(String string) {
		cmnPageObjects.searchTextBox("T-shirt");
		scn.log("T-shirt enters in search text box");
	}

	@When("mouse hover on search text box")
	public void mouse_hover_on_search_text_box() {
		cmnPageObjects.mouseHoverAction();
	}

	@Then("search result contain {string} as text")
	public void search_result_contain_as_text(String string) {
		hmePageObjects.searchResultValidation();
		scn.log("search result contains T-shirt as text");
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
	public void url_contain(String string) {
	    String actualUrl=hmePageObjects.getUrl();
	    boolean b = actualUrl.contains("seleniumfrmwrk");
	    if(b==true) {
	    	logger.info( "Actual Url" +actualUrl+ "contain seleniumfrmwrk");
	    	scn.log("Actual Url" +actualUrl+ "contain seleniumfrmwrk");
	}
	    else
	    {
	    	logger.info("Actual Url" +actualUrl+ "contain does not contain seleniumfrmwrk");
	    	scn.log("Actual Url" +actualUrl+ "contain does not contain seleniumfrmwrk");
	    }
	}
	@Then("Twitter account name is {string}")
	public void twitter_account_name_is(String string) {
		hmePageObjects.validateAccountName();
		scn.log("Validated twitter account name");	
		
			}
	
	@Then("main product category count is {int}")
	public void main_product_category_count_is(Integer int1) {
	    int count=hmePageObjects.productCategoris();
	    logger.info("Count of the product will displayed on page is :"+count);
	    scn.log("Displayed of total count of main categories");
	}
	
	@Then("Text is displayed for all three categories")
	public void text_is_displayed_for_all_three_categories() {
		hmePageObjects.txtOfProductCategoris();
		logger.info("Main Categories are displayed");
		scn.log("Main Categories are displayed");
	}
		

}

 