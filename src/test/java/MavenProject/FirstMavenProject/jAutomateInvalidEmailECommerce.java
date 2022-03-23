package MavenProject.FirstMavenProject;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class jAutomateInvalidEmailECommerce {
	
	//Resources
		//https://www.techlistic.com/2020/06/automate-ecommerce-website.html
	
	public String baseURL = "http://automationpractice.com/index.php";
	public static WebDriver driver;
	public WebDriverWait wait;
	public SoftAssert sa = new SoftAssert();
	public Random rand = new Random();
	
	/*Negative Scenarios
	Test Case 2 - Verify invalid email address error.
	Steps to Automate:
	1. Open this url  http://automationpractice.com/index.php
	2. Click on sign in link.
	3. Enter invalid email address in the email box and click enter.
	4. Validate that an error message is displaying saying "Invalid email address."*/

	@BeforeTest
	public void setBaseURL()
	{
		System.setProperty("webdriver.chrome.driver", "/Users/josh.cacho/eclipse-workspace/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get(baseURL);
	}
	
	@Test
	public void invalidEmail()
	{
		WebElement signIn = driver.findElement(By.cssSelector(".login"));
		signIn.click();
		
		wait = new WebDriverWait(driver,15);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("button[id='SubmitCreate']")));
		
		WebElement butCreateAccount = driver.findElement(
				By.cssSelector("button[id='SubmitCreate']"));
		butCreateAccount.click();
		
		//Assert error message box is displayed with correct text
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector(".alert.alert-danger")));
		
		WebElement alertErrorPresent = driver.findElement(By.cssSelector(".alert.alert-danger"));
		
		if(alertErrorPresent.isDisplayed())
		{
			sa.assertTrue(true, "Test - invalidEmail - error alert box is displayed");
			
			//Assert error message
			WebElement errorMessage = driver.findElement(By.cssSelector(".alert.alert-danger li"));
			sa.assertEquals(errorMessage.getText(), "Invalid email address.");
		}
		else
		{
			sa.assertTrue(false, "Test - invalidEmail - error alert box is NOT displayed");
		}
		
	
	}
	
	@AfterTest
	public void AssertAll()
	{
		sa.assertAll();
		driver.close();
	}

}
