package cucumber_BDD_Assignment.pageobjects;

import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class commonPageObjects {
	
	private static final Logger logger = LogManager.getLogger(commonPageObjects.class);

	WebDriver driver;
	
	
	private By searchTextBox= By.xpath("//input[@id='search_query_top']");
	private By logo= By.xpath("//img[@alt='My Store']");
	//private By searchResult= By.xpath("//strong[normalize-space()='T-shirt']");
	
	public commonPageObjects(WebDriver driver) {
		this.driver = driver;
	}
	
	public void homePageUrlLaunch(String URL) {
		logger.info("Launching URL");
		driver.get(URL);
		
	}
	
	public void checkLogoVisibility() {
		if (driver.findElement(logo).isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Logo is displayed on the page");
		}
		else
		{
			Assert.assertFalse(false);
			logger.info("Logo is not  displayed on the page");
		}
					
	}
	
		
	public void searchTextBox(String text ) {
		driver.findElement(searchTextBox).sendKeys(text);
		logger.info("value entered in search box:" +text);
	}
	
//	public void mouseHoverAction() {
//		Actions act=new Actions(driver);
//		act.moveToElement(driver.findElement(searchTextBox)).build().perform();
//		WebDriverWait wait = new WebDriverWait(driver,20);
//		wait.until(ExpectedConditions.presenceOfElementLocated(searchTextBox));
//		logger.info("Mouse hover on search text box");
//	}
	
	
public void windowHandler() {
		
		Set<String> windowIDs= driver.getWindowHandles();
		Iterator<String> it=windowIDs.iterator();

		String parentWindowID= it.next();
		String childWindowID=it.next();

		driver.switchTo().window(parentWindowID);
		driver.switchTo().window(childWindowID);
		logger.info("We are on child window");
		
	}
}
