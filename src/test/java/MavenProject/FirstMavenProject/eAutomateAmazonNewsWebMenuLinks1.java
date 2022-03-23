package MavenProject.FirstMavenProject;

import java.util.ArrayList;
import java.util.List;
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

public class eAutomateAmazonNewsWebMenuLinks1 {
			//Reference for below tests
			//https://www.techlistic.com/p/selenium-assignments.html
			//https://www.techlistic.com/2019/07/automate-menu-links-with-selenium.html
			//https://www.techlistic.com/2019/07/selenium-webdriver-locators-and-find.html
		
		//Set baseURL and driver public for all Test Methods to access
			//anyone can access
		public String baseURL = "https://amazon.in";
		public WebDriver driver;
		public SoftAssert sa = new SoftAssert();
		public WebDriverWait wait;
			
		
		//Set new ChromeDriver and go to baseURL BEFORE running other test methods.
		@BeforeTest  
		public void setBaseURL()
		{
		System.setProperty("webdriver.chrome.driver", "/Users/josh.cacho/eclipse-workspace/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get(baseURL);
		}
		
		/*Test Case 1 - Automate first Menu link of amazon.in and print page title
		Steps to Automate:
		1. Launch browser of your choice say., firefox, chrome etc.
		2. Open this URL -  www.amazon.in
		3. Maximize or set size of browser window.
		4. Get the page title and print it.
		5. Now, click on first menu link say 'Amazon Pay' (or choose any other link from the menu list)
		6. Get the page title and print it.
		7. Navigate back to Home Page.
		8. Get the page title and print it. Verify that value matches with output of point no. 4
		9. Close the browser.*/
		
		@Test(priority=1)
		public void amazonPayLink()
		{
		//Set browser dimensions big enough so Amazon Pay is clickable OR faiulre will occur
		
		
		//Dimension dm = new Dimension(1080,980); //amazon pay not seen
		Dimension dm = new Dimension(1400,1400);
		driver.manage().window().setSize(dm);
		
		//https://www.amazon.in/gp/sva/dashboard?ref_=nav_cs_apay_fe0c735739554ca1a7cccf7c41941f2f
		
		//Print out page title
		String amazonHomePageTitle = driver.getTitle();
		System.out.println(amazonHomePageTitle);
		
		//Click on Amazon Pay
		WebElement amazonPay = driver.findElement(By.linkText("Amazon Pay"));
		amazonPay.click();
		
		//Verify amazon pay balance is present then continue
		wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Amazon Pay balance')]")));
		
		//Print out AmazonPay Tile
		String amazonPayPageTitle = driver.getTitle();
		System.out.println(amazonPayPageTitle);
		
		String amazonPayURL = driver.getCurrentUrl();
		sa.assertTrue(amazonPayURL.contains("cs_apay"), "Test - amazonPayLinks");  //customer server amazon pay
		
		//Navigate back to home page and get title
		driver.navigate().back();
		String backToHomePageURL = driver.getTitle();
		
		sa.assertEquals(amazonHomePageTitle, backToHomePageURL, "Test - amazonPayLinks - baseURLTitle matches BackToHomeTitle");
		
		//driver.quit();
		
		}
		
		
		/*Test Case 3 - Automate all the Menu links of amazon.in and Verify page titles
		Steps to Automate:
		1. Launch browser of your choice say., firefox, chrome etc.
		2. Open this URL -  www.amazon.in
		3. Maximize or set size of browser window.
		4. Get the page title and print it.
		5. Click on first menu link, say 'Amazon Pay'.
		6. Get Page title and verify it with expected value.
		7. Navigate back to Home Page. 
		8. Get Page title and verify it with expected value.
		9. Repeat steps 5 to 8 for other menu links.
		10. Close the browser.*/
		
		@Test(priority=2)
		public void allAutomateLinks()
		{
		//Set global wait due to navigating back to home page
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				
		//bring screen to full screen
		driver.manage().window().fullscreen();
		
		//Contain list of menuLinks
		List<WebElement> menuLinks = driver.findElements(By.cssSelector("a[data-csa-c-slot-id*='nav_cs']"));
		
		/*for (WebElement element : menuLinks)
		{
			System.out.println(element.getText());
		}
		
		Mobiles
		Best Sellers
		Customer Service
		Fashion
		Electronics
		Amazon Pay
		Today's Deals
		Computers
		Home & Kitchen
		New Releases
		Books
		Toys & Games
		Coupons
		Sell*/
		
		
		//Convert list of Web Elements to list of strings
			//Resource = https://stackoverflow.com/questions/36886825/how-to-convert-a-list-of-webelement-to-another-list-in-the-form-of-string
		List<String> strings = new ArrayList<String>();
		for(WebElement element : menuLinks){
		    strings.add(element.getText());
		}
					
		//loop through first 10 menu links and perform steps 5-8
		for(int i=0;i<10;i++)
		{
			
			//bring to full screen to ensure all items are clickable
				//we do this because navigating back puts screen outside of full screen
			driver.manage().window().fullscreen();
			
			//click each link
			String getCurrentURL = driver.getCurrentUrl();
			//System.out.println(getCurrentURL);
			
			//navigate to next item in menuItems link to click
			driver.findElement(By.linkText(strings.get(i))).click();
			
			driver.navigate().back();
			String navigateBackToURL = driver.getCurrentUrl();
			//System.out.println(navigateBackToURL);
			
			sa.assertEquals(getCurrentURL, navigateBackToURL, "Test - allAutomateLinks");
		}
		}	
		
		@AfterTest
		public void assertAll()
		{
		sa.assertAll();
		driver.close();
}

}
