package MavenProject.FirstMavenProject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class fAutomateTechtListingLinks1 {
	
	//Reference for below tests
	//https://www.techlistic.com/p/selenium-assignments.html
	//https://www.techlistic.com/2019/07/automate-menu-links-with-selenium.html
	//https://www.techlistic.com/2019/07/selenium-webdriver-locators-and-find.html
	
	public String baseURL = "https://www.techlistic.com/";
	public WebDriver driver;
	SoftAssert sa = new SoftAssert();
	public WebDriverWait wait;
	
	/*Test Case 2 - Automate all the Menu links of techlistic.com
	Steps to Automate:
	1. Launch browser of your choice say., firefox, chrome etc.
	2. Open this URL -  https://www.techlistic.com/
	3. Maximize or set size of browser window.
	4. Click on 'Java Tutorial' link and validate page title.
	5. Navigate back to Home Page.
	6. Click on 'Selenium Tutorial' link and validate page title.
	7. Navigate back to Home Page.
	8. Click on 'Selenium Blogs' link and validate page title.
	9. Navigate back to Home Page.
	10. Click on 'TestNG Blogs' link and validate page title.
	11. Close the browser.*/
	
	@BeforeTest
	public void setBaseURL()
	{
		System.setProperty("webdriver.chrome.driver", "/Users/josh.cacho/eclipse-workspace/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get(baseURL);
	}
	
	
	@Test
	public void javaTutorialPageTitle()
	{
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		driver.manage().window().fullscreen();

		
		WebElement javaTutLink = driver.findElement(By.xpath("//a[contains(text(),'Java')]"));
		javaTutLink.click();
		
		//System.out.println(driver.getTitle());
		
		sa.assertEquals(driver.getTitle(), "Techlistic", "Test - javaTutorialPageTitle");
		
		driver.navigate().back();
		
	}
	
	@Test
	public void seleniumWithTestNGPageTitle()
	{
		Dimension dm = new Dimension(1200,1200);
		driver.manage().window().setSize(dm);
		
		WebElement selenTestNGLink = driver.findElement(By.xpath("//a[contains(text(),'Selenium')]"));
		selenTestNGLink.click();
		
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".post-title.entry-title")));
		
		//System.out.println(driver.getTitle());
		sa.assertEquals(driver.getTitle(), "Selenium Webdriver Tutorial - Techlistic", "Test - seleniumWithTestNGPageTitle");
		
		driver.navigate().back();
	}
	
	@Test
	public void selenuimBlogsTitle()
	{
		
		Dimension dm = new Dimension(1200,1080);
		driver.manage().window().setSize(dm);
		
		WebElement selenBlogsLink = driver.findElement(By.xpath("//a[contains(text(),'Blogs')]"));
		selenBlogsLink.click();
		
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".post-title.entry-title")));
		
		//System.out.println(driver.getTitle());
		
		sa.assertEquals(driver.getTitle(), "Best Selenium Blogs", "Test - selenuimBlogsTitle");
		
		driver.navigate().back();

	}

	@AfterTest
	public void assertAll()
	{
		sa.assertAll();
		driver.close();
	}

}
