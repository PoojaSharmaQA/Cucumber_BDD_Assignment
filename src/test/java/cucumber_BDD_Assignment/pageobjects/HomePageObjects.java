package cucumber_BDD_Assignment.pageobjects;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import junit.framework.Assert;

public class HomePageObjects {

	private static final Logger logger = LogManager.getLogger(HomePageObjects.class);

	WebDriver driver;


	private By logo= By.xpath("//img[@alt='My Store']");
	private By searchResult= By.xpath("//strong[normalize-space()='T-shirt']");
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



	public void checkImageDimension() {
		int width=(driver.findElement(logo)).getSize().getWidth();
		int height= (driver.findElement(logo)).getSize().getHeight();
		//to verify width
		Assert.assertEquals("Width is not correct",350,width);
		logger.info("Width of the image is as per requirement");
		//to verify height
		Assert.assertEquals("Height is not correct",99,height);
		logger.info("Height image is as per requirement");

	}	

	public void searchResultValidation() {
		if(driver.findElement(searchResult).getText().contains("T-shirt"))
		{
			System.out.println("Search Result contains T-shirt");
			logger.info("Search Result contains T-shirt");
		}

	}
	public Integer Product_Cat() {
		Integer Actual_Count = driver.findElements(Product_Category).size();
		return Actual_Count;
	}

	//	public void ProductName(String Product) {
	//		 List<WebElement> list = driver.findElements(Product_Category);
	//		Iterator<WebElement> itr = list.iterator();
	//		while(itr.hasNext()) {
	//			if(Product.equals(itr.next().getText())) {
	//				Assert.assertTrue(true);
	//				logger.info("Product Category Matched with Expected ");
	//			}

	public void twitterLink() {
		driver.findElement(twitterLogo).click();
		logger.info("Clicked on twitter hyperlink");


	}



	public void validateAccountName() {

		String accountName=driver.findElement(accountHolderName).getText();
		Assert.assertEquals("Account holder name is not correct",accountName,"Selenium Framework");
		logger.info("Account holder name is Selenium Framework");
	}

	public int productCategoris() {
		
	
		List<WebElement> listOfCategories = driver.findElements(listOfItems);

		int Count=0;

		for (int i = 0; i < listOfCategories.size(); i++) {


			String str=listOfCategories.get(i).getText();

			if(str.contains("WOMEN") || str.contains("DRESSES")||str.contains("T-SHIRTS")) {

				Count++;
			}

		}

		return Count;

	}

	public void txtOfProductCategoris() {
		List<WebElement> list = driver.findElements(listOfItems);

		for (int i = 0; i < list.size(); i++) {
			String str=list.get(i).getText();

			if(str.contains("WOMEN") || str.contains("DRESSES")||str.contains("T-SHIRTS")) {
				System.out.print(list.get(i).getText() + "  ");
			}
			}
		}
}


		


