package cucumber_BDD_Assignment.pageobjects;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;






public class HomePageObjects {

	private static final Logger logger = LogManager.getLogger(HomePageObjects.class);

	WebDriver driver;


	private By logo= By.xpath("//img[@alt='My Store']");
	private By searchResult= By.xpath("//div[@class='ac_results']//li");
	private By twitterLogo= By.xpath("//a[@href='https://twitter.com/seleniumfrmwrk']");
	private By Product_Category = By.xpath("//*[@id=\"block_top_menu\"]/ul/li");
	private By accountHolderName= By.xpath("//span[text()='Selenium Framework']");
	private By listOfItems = By.xpath("//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']//li");

	public HomePageObjects(WebDriver driver) {
		this.driver=driver;
	}


	public String getUrl() {
		String url= driver.getCurrentUrl();
		logger.info("currednt url is:" +url);
		return url;
	}



	public void checkImageDimension(Integer expectedWidth, Integer expectedHeight) {
		int actualwidth=(driver.findElement(logo)).getSize().getWidth();
		int actualheight= (driver.findElement(logo)).getSize().getHeight();
		//to verify width
		Assert.assertTrue(actualwidth==expectedWidth && actualheight==expectedHeight);
		logger.info("Actual width of the image is " +actualwidth);
		logger.info("Actual height of the image is " +actualheight);

	}	

	public void searchResultValidation(String productName) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(searchResult));
		if(driver.findElement(searchResult).getText().contains(productName))
		{
			System.out.println("Search Result contains " +productName);
			logger.info("Search Result contains " +productName);
		}

	}


	//	public Integer Product_Cat() {
	//		Integer Actual_Count = driver.findElements(Product_Category).size();
	//		return Actual_Count;
	//	}


	public void twitterLink() {
		driver.findElement(twitterLogo).click();
		logger.info("Clicked on twitter hyperlink");


	}
	
//	public void validateAccountName(String accName) {
//
//		String accountName=driver.findElement(accountHolderName).getText();
//		Assert.assertEquals("Account holder name is not correct",accountName,"Selenium Framework");
//		logger.info("Account holder name is Selenium Framework");
//	}



	public Integer productCategory() {

		Integer actualCount= driver.findElements(Product_Category).size();
		return actualCount;
	}

	public void productName(String product) {
		List<WebElement> productList= driver.findElements(Product_Category);
		Iterator<WebElement> itr=productList.iterator();
		while(itr.hasNext()) {
			if(product.equals(itr.next().getText())){
				Assert.assertTrue(true);
				logger.info("Product category is as expected");
			}

		}

	}

	
		
	}






