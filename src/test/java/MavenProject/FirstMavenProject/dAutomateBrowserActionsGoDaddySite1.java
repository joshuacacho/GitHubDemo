package MavenProject.FirstMavenProject;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class dAutomateBrowserActionsGoDaddySite1 {
	
	//Reference for below tests
			//https://www.techlistic.com/p/selenium-assignments.html
			//https://www.techlistic.com/2019/07/automate-menu-links-with-selenium.html
			
		
		
		//Set baseURL and driver public for all Test Methods to access
			//anyone can access
		public String baseURL = "https://www.godaddy.com/";
		public WebDriver driver;
		public SoftAssert sa = new SoftAssert();
		
		

		//Set new ChromeDriver and go to baseURL BEFORE running other test methods.
		@BeforeTest  
		public void setBaseURL()
		{
			System.setProperty("webdriver.chrome.driver", "/Users/josh.cacho/eclipse-workspace/Drivers/chromedriver");
			driver = new ChromeDriver();
			driver.get(baseURL);
		}
		
		/*Test Case 1 - Open Godaddy.com and maximize browser window.
		Steps to Automate:
		1. Launch browser of your choice say., Firefox, chrome etc.
		2. Open this URL - https://www.godaddy.com/
		3. Maximize or set size of browser window.
		4. Close browser.*/
		
		@Test(priority=1)
		public void maxBrowserSize()
		{
			//make screen full size
			driver.manage().window().fullscreen();
					
			//set correct max height and window (
			//Obtaing size to Assert
				//System.out.println(driver.manage().window().getSize());
			int maxHeight = 1920;
			int maxWidth = 1080;
			
			//obtain current size of window after being maximized
				//Reference = https://stackoverflow.com/questions/48114607/how-to-find-the-whether-the-current-using-window-is-maximize-or-minimize-in-sele
			Dimension dm = driver.manage().window().getSize();
			
			//If fullscreen < height & width test fails
			if(dm.height<maxHeight  && dm.width<maxWidth)
			{
				System.out.println("Made it here");
				sa.assertTrue(false, "Test - maxBrowserSize");
			}

			//driver.quit();
		}
		
		/*Test Case 2 - Open Godaddy.com and Print it's Page title.
		Steps to Automate:
		1. Launch browser of your choice say., Firefox, chrome etc.
		2. Open this URL - https://www.godaddy.com/
		3. Maximize or set size of browser window.
		4. Get Title of page and print it.
		4. Get URL of current page and print it.
		5. Close browser.*/
		
		@Test(priority=2)
		public void printTitlePageCurrentURL()
		{
		
			//Setting size of window
			Dimension dm = new Dimension(1200,900);
			driver.manage().window().setSize(dm);
			
			//Getting title of page
			String browserTitle = driver.getTitle();
			//System.out.println(browserTitle);
			
			sa.assertEquals(browserTitle, 
					"Domain Names, Websites, Hosting & Online Marketing Tools - GoDaddy", "Test - printTitlePage");
			
			//Getting current URL
			String currentURL = driver.getCurrentUrl();
			//System.out.println(currentURL);
			
			sa.assertEquals(currentURL, "https://www.godaddy.com/", "Test - printCurrentURL");

			//driver.quit();
		}
		
		/*Test Case 3 - Open Godaddy.com and Validate Page Title
		Steps to Automate:
		1. Launch browser of your choice say., Firefox, chrome etc.
		2. Open this URL - https://www.godaddy.com/
		3. Maximize or set size of browser window.
		4. Get Title of page and validate it with expected value.
		5. Get URL of current page and validate it with expected value.
		6. Get Page source of web page.
		7. And Validate that page title is present in page source.
		8. Close browser.*/
		
		@Test(priority=3)
		public void pageSourcePageTitle()
		{
		
			//Setting size of window
			Dimension dm = new Dimension(1000,1000);
			driver.manage().window().setSize(dm);
			
			//cant use this PageTitle in soft Assertion as driver.getTitle returns something different than page source
				//refer to previous Test
			//String pageTitle = driver.getTitle();
			//System.out.println(pageTitle);
			
			//Steps 4 and 5 done in previous test
			String pageSource = driver.getPageSource();
			//System.out.println(pageSource);
			
			sa.assertTrue(pageSource.contains(
					"Domain Names, Websites, Hosting &amp; Online Marketing Tools - GoDaddy"), "Test - pageSourcePageTitle");
			
			//driver.quit();
		}
		
		@AfterTest 
		public void assertFailures()
		{
			sa.assertAll();
			driver.close();
		}
		

}
