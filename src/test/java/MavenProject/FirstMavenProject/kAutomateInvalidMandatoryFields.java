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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.asserts.SoftAssert;

public class kAutomateInvalidMandatoryFields {

	//Resources
		//https://www.techlistic.com/2020/06/automate-ecommerce-website.html
		//https://stackoverflow.com/questions/12853595/how-to-add-elements-of-a-string-array-to-a-string-array-list
		//https://www.geeksforgeeks.org/comparing-two-arraylist-in-java/
	
	public String baseURL = "http://automationpractice.com/index.php";
	public static WebDriver driver;
	public SoftAssert sa = new SoftAssert();
	public static Random rand = new Random();
	
	/*Test Case 3 - Verify error messages for mandatory fields.
	Steps to Automate:
	1. Open this url  http://automationpractice.com/index.php
	2. Click on sign in link.
	3. Enter email address and click Register button.
	4. Leave the mandatory fields (marked with *) blank and click Register button.
	5. Verify that error has been displayed for the mandatory fields.*/
	
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
		// Declaring array of strings we want to compare error messages to
		String[] errorMessages = {
				"You must register at least one phone number.",
				"lastname is required.",
				"firstname is required.",
				"passwd is required.",
				"address1 is required.",
				"city is required.",
				"The Zip/Postal code you've entered is invalid. It must follow this format: 00000",
				"This country requires you to choose a State."
				};
		
		//Convert string array error messages to array list for comparison later
		List<String> errMsgArrayList = Arrays.asList(errorMessages);
		//System.out.println("errMsgArrayList " + errMsgArrayList);
		
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		//Create Account
			//create helper class with different helper methods
		createAccount();
		
		//Select Register button to see error messages for all fields
		WebElement butRegister = driver.findElement(By.xpath("//button[@id='submitAccount']"));
		butRegister.click();
		
		//Assert all error messages
		WebElement errorAlertBox = driver.findElement(By.xpath("//div[@class='alert alert-danger']"));
		boolean alertBoxDisplayed = errorAlertBox.isDisplayed();
		
		//Assert error alert box
		sa.assertEquals(alertBoxDisplayed, true);
		
		//Assert if box true it contains all 8 error messages
		if(alertBoxDisplayed)
		{
			//Obtain list of errors returned by System
			List<WebElement> alertsList = driver.findElements(By.xpath("//div[@class='alert alert-danger'] //li"));
			
			//Put alertsList into array for comparison later
			List<String> returnedErrorMessages = new ArrayList<String>();
			for(WebElement element : alertsList){
				returnedErrorMessages.add(element.getText());
			}
			
			//System.out.println("returnedErrorMessages " + returnedErrorMessages);
			
			//compare list
			sa.assertEquals(errMsgArrayList.equals(returnedErrorMessages), true,
					"Test - mandatoryFields - values are not equal");
			
			
			//the old less efficient way we dont want to do
			/*for(int i=0;i<alertsList.size();i++)
			{
				System.out.println(alertsList.get(i).getText());
				
				//phone number
				if(alertsList.get(0).getText().equals(errorMessages[0]))
				{
					sa.assertTrue(true);
				}
				else
				{
					sa.assertTrue(false, "Test - mandatoryFields - phone number not required");
				}
				
				//last name
				if(alertsList.get(1).getText().equals(errorMessages[1]))
				{
					sa.assertTrue(true);
				}
				else
				{
					sa.assertTrue(false, "Test - mandatoryFields - lastname is not required.");
				}
				
				//first name
				if(alertsList.get(2).getText().equals(errorMessages[2]))
				{
					sa.assertTrue(true);
				}
				else
				{
					sa.assertTrue(false, "Test - mandatoryFields - firstname is not required.");
				}
				
				//password
				if(alertsList.get(3).getText().equals(errorMessages[3]))
				{
					sa.assertTrue(true);
				}
				else
				{
					sa.assertTrue(false, "Test - mandatoryFields - passwd is not required.");
				}
				
				//address 1
				if(alertsList.get(4).getText().equals(errorMessages[4]))
				{
					sa.assertTrue(true);
				}
				else
				{
					sa.assertTrue(false, "Test - mandatoryFields - address1 is not required.");
				}
				
				//city is required.
				if(alertsList.get(5).getText().equals(errorMessages[5]))
				{
					sa.assertTrue(true);
				}
				else
				{
					sa.assertTrue(false, "Test - mandatoryFields - city is not required.");
				}
				
				//postal code
				if(alertsList.get(6).getText().equals(errorMessages[6]))
				{
					sa.assertTrue(true);
				}
				else
				{
					sa.assertTrue(false, "Test - mandatoryFields - Zip/Postal code not required");
				}
				
				//Country 
				if(alertsList.get(7).getText().equals(errorMessages[7]))
				{
					sa.assertTrue(true);
				}
				else
				{
					sa.assertTrue(false, "Test - mandatoryFields - Country is not required");
				}*/
				
			//}
		}
		else
		{
			sa.assertTrue(false, "Test - mandatoryFields - alert box is not displayed");
		}
		
	}
	
	
	@AfterTest
	public void assertAll()
	{
		sa.assertAll();
		driver.close();
	}
	
	@Test
	public static void createAccount()
	{
		WebElement signIn = driver.findElement(By.xpath("//a[@class='login']"));
		signIn.click();
		
		int randomInt = rand.nextInt(1000) + 500;
		WebElement emailAddress = driver.findElement(By.xpath("//input[@id='email_create']"));
		emailAddress.sendKeys("jimmy" + randomInt + "@yahoo.com");
		
		WebElement butCreateAccount = driver.findElement(By.xpath("//button[@id='SubmitCreate']"));
		butCreateAccount.click();
	}
}


