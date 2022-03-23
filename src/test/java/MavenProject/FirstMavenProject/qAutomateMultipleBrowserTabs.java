package MavenProject.FirstMavenProject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class qAutomateMultipleBrowserTabs {

	//Resources
		//https://www.techlistic.com/p/selenium-assignments.html
		//https://www.techlistic.com/2020/02/handle-multiple-browser-tabs.html
	
	/*Open Google.com
	Then open a new tab by making use of Key class. By combination of CTRL + t key a new browser tab will be opened.
	Open Gmail.com in that tab.
	Perform some actions on gmail sign in page.
	Switch back to google.com tab by pressing Ctrl + \t
	Now enter some keywords in google search box
	Close the browsers.*/
	
	
	public String baseURL = "https://www.google.com";
	public WebDriver driver;
	
	@BeforeTest
	public void setBaseURL()
	{
		System.setProperty("webdriver.chrome.driver", 
				"/Users/josh.cacho/eclipse-workspace/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get(baseURL);
	}
	
	
	@Test
	public void OpenNewTabKeyboard() throws InterruptedException 
	{
		//The below shit dont work
		  //driver.findElement(By.tagName("body")).sendKeys(Keys.COMMAND + "T");
			//Actions a = new Actions(driver);
			//a.moveToElement(driver.findElement(By.tagName("body"))).
			//keyDown(Keys.COMMAND)).sendKeys("T").build().perform();
		
		((JavascriptExecutor)driver).executeScript("window.open()");
		 Set<String> windows = driver.getWindowHandles();
		 ArrayList<String> tabs = new ArrayList<String>(windows);
		 driver.switchTo().window(tabs.get(1));
		 driver.get("http://gmail.com");
		 driver.switchTo().window(tabs.get(0));
		 //driver.switchTo().defaultContent();
		 System.out.println(driver.getCurrentUrl());
		 
	}
}
