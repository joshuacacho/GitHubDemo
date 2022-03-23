package MavenProject.FirstMavenProject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class hAutomateGoogleSearch {
	
	//Resources 
		//https://www.techlistic.com/2019/07/automate-google-with-selenium.html
	
		
	public String baseURL = "https://www.google.com";
	public WebDriver driver;
	SoftAssert sa = new SoftAssert();
	WebDriverWait wait;
	
	/*Assignment - Print all the options displayed in Google suggestion box

	Test Steps:
	1. Launch the Firefox.
	2. Open URL - http://www.google.com
	3. Enter keyword "techlistic" in search bar
	4. Wait for ajax suggestion box to appear
	5. Get/store all the options of suggestion box in a list
	6. Print all the suggestions one by one. */
	
	@BeforeTest
	public void setBaseURL()
	{
		System.setProperty("webdriver.gecko.driver", "/Users/josh.cacho/eclipse-workspace/Drivers/geckodriver");
		driver = new FirefoxDriver();
		driver.get(baseURL);
	}
	
	@Test
	public void printListOptions()
	{
		WebElement gSearch = driver.findElement(By.name("q"));
		gSearch.sendKeys("tech store");
		
		wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("UUbT9")));
		
		//Obtain options in list
			//its nice to find the exact list but can make it difficult to find what you are looking for
				//By.tagname("li") would work also but parent child seems to be better
		List<WebElement> searchOptions = driver.findElements(By.cssSelector(".UUbT9 .sbct"));
		
		//traverse and print out items where applicable
		for(WebElement element: searchOptions)
		{
			//System.out.println(element.getText());
			//bug with .isBlank (DONT USE), could use replaced isBlank() with isEmpty && equals(" ") to work
			if(!element.getText().isEmpty())
			{
				System.out.println(element.getText());
				sa.assertEquals(element.getText().contains("tech store"), true, "Test - printListOptions");
			}
		}
		
	}
	
	@AfterTest
	public void assertAll()
	{
		sa.assertAll();
		driver.close();
	}
		
	
}
