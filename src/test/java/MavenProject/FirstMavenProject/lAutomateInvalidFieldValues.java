package MavenProject.FirstMavenProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class lAutomateInvalidFieldValues {
	
	//Resources
		//https://www.techlistic.com/2020/06/automate-ecommerce-website.html
	
	public String baseURL = "http://automationpractice.com/index.php";
	public static WebDriver driver;
	SoftAssert sa = new SoftAssert();
	public static Random rand = new Random();
	
	/*Test Case 4 - Verify error messages for entering incorrect values in fields.
	Steps to Automate:
	1. Open this url  http://automationpractice.com/index.php
	2. Click on sign in link.
	3. Enter email address and click Register button.
	4. Enter incorrect values in fields like., enter numbers in first and last name, 
		city field etc and enter alphabets in Mobile no, Zip postal code etc., and click on 'Register' button.
	5. Verify that error messages for respective fields are displaying.
	Try automating the above scenarios using Selenium commands, 
		if you face any difficulty please refer the Selenium Tutorial series. Happy Learning!*/
	
	
	@BeforeTest
	public void setBaseURL()
	{
		System.setProperty("webdriver.chrome.driver", "/Users/josh.cacho/eclipse-workspace/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get(baseURL);
	}
	
	@Test
	public void mandatoryFields()
	{
		//use helper function to create account
		createAccount();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		WebElement fNameInvalid = driver.findElement(By.id("customer_firstname"));
		fNameInvalid.sendKeys("1233");
		
		WebElement lNameInvalid = driver.findElement(By.id("customer_lastname"));
		lNameInvalid.sendKeys("3455");
		
		WebElement cityInvalid = driver.findElement(By.id("city"));
		cityInvalid.sendKeys("*&^&;32423");
		
		WebElement mobPhone = driver.findElement(By.id("phone_mobile"));
		mobPhone.sendKeys("edfsafsd");
		
		WebElement register = driver.findElement(By.id("submitAccount"));
		register.click();
		
		// Declaring array of strings we want to compare error messages to for INVALID values
		String[] errorMessages = {
				"lastname is invalid.",
				"firstname is invalid.",
				"city is invalid.",
				"phone_mobile is invalid.",
				"The Zip/Postal code you've entered is invalid. It must follow this format: 00000"
			};
					
		//converting string array of errorMessages into ArrayList for comparison later
		List<String> errorsFinal = Arrays.asList(errorMessages);
		System.out.println(errorsFinal);
		
		//Obtain list of error messages returned by application
		List<WebElement> alertsList = driver.findElements(By.xpath("//div[@class='alert alert-danger'] //li"));
		//Create new ArrayList of Strings and add items within alertsList which match "invalid" criteria
		List<String> alertsFinal = new ArrayList<String>();
		for(WebElement element: alertsList)
		{
			//only add error messages for invalid fields
			if(element.getText().contains("invalid"))
			{
				alertsFinal.add(element.getText());
			}
		}
		
		System.out.println(alertsFinal);
		
		//compare both arrays
		sa.assertEquals(errorsFinal.equals(alertsFinal), true, "Test - mandatoryFields - error/alerts dont match");
		
		
	}
	
	
	@AfterTest
	public void assertAll() 
	{
		sa.assertAll();
		driver.close();
	}
	
	//helper method to create account
	public static void createAccount()
	{
		WebElement signIn = driver.findElement(By.xpath("//a[@class='login']"));
		signIn.click();
		
		int randomInt = rand.nextInt(1000) + 500;
		WebElement emailAddress = driver.findElement(By.xpath("//input[@id='email_create']"));
		emailAddress.sendKeys("jimmy" + randomInt + "@hotmail.com");
		
		WebElement butCreateAccount = driver.findElement(By.xpath("//button[@id='SubmitCreate']"));
		butCreateAccount.click();
	}

}
