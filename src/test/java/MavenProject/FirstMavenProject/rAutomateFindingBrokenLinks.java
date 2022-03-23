package MavenProject.FirstMavenProject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.util.Strings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class rAutomateFindingBrokenLinks {

	
	//Resources
		//https://www.techlistic.com/p/selenium-assignments.html
		//https://www.techlistic.com/2020/03/selenium-webdriver-get-all-page-links.html
	
	/*Code Explanation:
	1. Open the webpage.
	2. Create a list of type WebElements' and store all elements with tagname 'a' in it using 'findElements()'
	3. Iterate over all the links using list size as it's maximum value.
	4. Get the text of the link by using getText() and print it.*/
	
	public String baseURL = "http://techlistic.com/";
	public WebDriver driver;
	SoftAssert sa = new SoftAssert();
	private static Logger Log = LogManager.getLogger(rAutomateFindingBrokenLinks.class.getName());
	
	@BeforeTest
	public void setBaseURL()
	{
		System.setProperty("webdriver.chrome.driver", 
				"/Users/josh.cacho/eclipse-workspace/Drivers/chromedriver");
		driver = new ChromeDriver();
		Log.info("Setting BaseURL");
		driver.get(baseURL);
	}
	
	@Test 
	public void findBrokenLinks() throws MalformedURLException, IOException
	{
		
		//Refer to Lesson 14 - Miscellanous Topics eAutomateAllLinksFindingsBroeknOnes
		List<WebElement> pageLinks = driver.findElements(By.tagName("a"));
		int totalLinks = pageLinks.size();
		int count = 1;
		ArrayList<String> brokenLinks = new ArrayList<String>();
		
		Log.info("Starting to iterate through all links");
		
		for(int i=0;i<totalLinks;i++)
		{
			String url = pageLinks.get(i).getAttribute("href");
			//System.out.println(urls);  //for debugging that shows that some urls return nulls
			
			//if we get null link it breaks so bypass nulls for now
			if(url != null)
			{
				//Find response of url and print out
				HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
				connection.setRequestMethod("HEAD");  //using HEAD Request method
				connection.connect();  //makes call and gets response which sits in the object connection we created
				int responseCode = connection.getResponseCode();
				System.out.println("URL number " + count + " Hit URL " + url + " and its response code is " + responseCode);
				count++;
				
				//Capture broken links
				int brokenlinkCount = 0;
				if(responseCode > 400)
				{
					sa.assertTrue(false, "Some links are broken");
					brokenLinks.add(url);
					brokenlinkCount++;
					Log.info("Broken link count is " + brokenlinkCount);
				}
				
				//Should be no broken links 
				if(brokenlinkCount > 0)
				{
					System.out.println("The broken links are " + brokenLinks + "\n");
					Log.error("Broken link detected " + url);
				}
			}
		
			
		}
		
		Log.info("Iterated through all links");
		
		
	}
	
	
	@AfterTest
	public void tearDown()
	{
		Log.info("Asserting All and Qutting Browser");
		sa.assertAll();
		driver.close();
	}

}
