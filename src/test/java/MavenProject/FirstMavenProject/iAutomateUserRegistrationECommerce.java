package MavenProject.FirstMavenProject;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class iAutomateUserRegistrationECommerce {
	
	//Resource 
		//https://www.techlistic.com/2020/06/automate-ecommerce-website.html
		//https://stackoverflow.com/questions/5527120/the-difference-between-public-and-public-static
		//https://www.techlistic.com/2019/06/selenium-webdriver-browser-navigation.html
		//https://www.techlistic.com/2019/07/selenium-webdriver-locators-and-find.html
		//https://www.techlistic.com/2019/06/selenium-webdriver-form-commands-enter.html
	
	public String baseURL = "http://automationpractice.com/index.php"; 
	public WebDriver driver;
	public SoftAssert sa = new SoftAssert();
	public WebDriverWait wait;
	public Random rand = new Random();
	public String globalEmail = null;
	public String globalPassword = null;
	public String globalFirstName = null;
	public String globalLastName = null;
	
	
	/*Test Case 1 - Automate User Registration Process
	Assignment Level - Intermediate
	Positive Scenario
	Steps to Automate:
	1. Open this url  http://automationpractice.com/index.php
	2. Click on sign in link.
	3. Enter your email address in 'Create and account' section.
	4. Click on Create an Account button.
	5. Enter your Personal Information, Address and Contact info.
	6. Click on Register button.
	7. Validate that user is created.*/
	

	

	@BeforeTest
	public void setBaseURL() 
	{
		System.setProperty("webdriver.chrome.driver", "/Users/josh.cacho/eclipse-workspace/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get(baseURL);
	}
	
	@Test(priority=1)
	public void createAccount()
	{
		//generate random number form 0-100 then add 50
		//Random rand = new Random();
		int randomInt = rand.nextInt(1000) + 500;  
		
		WebElement signIn = driver.findElement(By.className("login"));
		signIn.click();
		
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SubmitCreate")));
		
		WebElement emailAddress = driver.findElement(By.id("email_create"));
		emailAddress.sendKeys("joeylorenzo" + randomInt + "@yahoo.com");
		globalEmail = emailAddress.getAttribute("value"); //getText wont work here, returns blank
		//System.out.println(globalEmail);
		
		WebElement createAccount = driver.findElement(By.id("SubmitCreate"));
		createAccount.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//h3[contains(text(),'Your personal information')]")));
		
		WebElement personalInfo = driver.findElement(
				By.xpath("//h3[contains(text(),'Your personal information')]"));
		
		//assert your personal information is sign verifying user created account and was navigated to here
		sa.assertEquals(personalInfo.isDisplayed(), true, "Test - createAccount - Personal Info not fisplayed");

	}
	
	@Test(priority=2)
	public void personalInfo()
	{
	
		WebElement title = driver.findElement(By.id("id_gender1"));
		title.click();
		
		WebElement fName = driver.findElement(By.id("customer_firstname"));
		fName.sendKeys("firstName");
		globalFirstName = fName.getAttribute("value");
		
		WebElement lName = driver.findElement(By.id("customer_lastname"));
		lName.sendKeys("lastName");
		globalLastName = lName.getAttribute("value");
		
		
		WebElement email = driver.findElement(By.id("email"));
		//System.out.println(globalEmail);
		//System.out.println( email.getAttribute("value"));
		sa.assertEquals(globalEmail, email.getAttribute("value"), "Test - personalInfo - globalEmail failed");
		
		//generate random number form 0-100 then add 10
		//Random rand = new Random();
		int randomInt = rand.nextInt(100) + 10; 
		WebElement password = driver.findElement(By.id("passwd"));
		password.sendKeys("rafike" + randomInt);
		globalPassword = password.getAttribute("value");  //getText wont work
		//System.out.println("final password is "+ globalPassword);
		
		WebElement Day = driver.findElement(By.cssSelector("#uniform-days"));
		Day.click();
		Select dobDay = new Select(driver.findElement(By.cssSelector("#days")));
		dobDay.selectByValue("10");
		
		WebElement Months = driver.findElement(By.cssSelector("#uniform-months"));
		Months.click();
		Select dobMonth = new Select(driver.findElement(By.cssSelector("#months")));
		dobMonth.selectByValue("7");  //July
		
		WebElement Year = driver.findElement(By.cssSelector("#uniform-years"));
		Year.click();
		Select dobYear = new Select(driver.findElement(By.cssSelector("#years")));
		dobYear.selectByValue("2001");
		
		WebElement offersCheckBox = driver.findElement(By.cssSelector("#optin"));
		offersCheckBox.click();
		
		//Asserting newsletter not selected
		WebElement newsLetter = driver.findElement(By.cssSelector("#newsletter"));
		sa.assertFalse(newsLetter.isSelected());
		
		
	}
	
	@Test(priority=3)
	public void address()
	{
	
		//Assert first name and last name already filled in
		WebElement firstName = driver.findElement(By.xpath("//input[@id='firstname']"));
		String addFirstName = firstName.getAttribute("value");
		sa.assertEquals(globalFirstName, addFirstName);
		
		WebElement lastName = driver.findElement(By.xpath("//input[@id='lastname']"));
		String addLastName = lastName.getAttribute("value");
		sa.assertEquals(globalLastName, addLastName);
		
		WebElement company = driver.findElement(By.xpath("//input[@id='company']"));
		company.sendKeys("Student");
		
		//generate random number form 0-100 then add 50
		//Random rand = new Random();
		int randomInt = rand.nextInt(1000);  
		//convert string to int
		String finalAddress = String.valueOf(randomInt);
		
		WebElement address1 = driver.findElement(By.xpath("//input[@id='address1']"));
		address1.sendKeys(finalAddress);
	
		WebElement address2 = driver.findElement(By.xpath("//input[@id='address2']"));
		address2.sendKeys("Unit 2");
		
		WebElement city = driver.findElement(By.xpath("//input[@id='city']"));
		city.sendKeys("Baltimore");
		
		WebElement state = driver.findElement(By.xpath("//div[@id='uniform-id_state']"));
		state.click();
		Select chooseState = new Select(driver.findElement(By.xpath("//select[@id='id_state']")));
		chooseState.selectByValue("20"); //Maryland
		
		WebElement postalCode = driver.findElement(By.xpath("//input[@id='postcode']"));
		postalCode.sendKeys("21201");
		
		WebElement country = driver.findElement(By.xpath("//div[@id='uniform-id_country']"));
		country.click();
		Select chooseCountry = new Select(driver.findElement(By.xpath("//select[@id='id_country']")));
		chooseCountry.selectByValue("21"); //United States
		
		WebElement additionalInfo = driver.findElement(By.xpath("//textarea[@id='other']"));
		additionalInfo.sendKeys("This is a test");
		
		//Click Register before entering phone and verify below error message appears
			//You must register at least one phone number.
		WebElement register = driver.findElement(By.xpath("//button[@id='submitAccount']"));
		register.click();
		
		//Obtain list of inline info to search for error message
		List<WebElement> inlineInfo = driver.findElements(By.cssSelector(".inline-infos"));
		boolean errorMessageDisplayed = false;
		for(WebElement element:inlineInfo)
		{
			//System.out.println(element.getText());
			if(element.getText().equals("You must register at least one phone number."))
			{
				errorMessageDisplayed = true;
				//Now satisfy error message
				WebElement homePhone = driver.findElement(By.xpath("//input[@id='phone_mobile']"));
				homePhone.sendKeys("2403695254");
			}
			
		}
		
		//Assert error message was displayed and if statement above in for each was hit
		sa.assertEquals(errorMessageDisplayed, true);
		
		//Need to reenter password
		WebElement password = driver.findElement(By.id("passwd"));
		password.sendKeys(globalPassword);
		
		WebElement addressAlias = driver.findElement(By.id("alias"));
		addressAlias.sendKeys("2548 MadeUP Drive");
		
		//After initial register the register value is lost in the dom
		WebElement registerAgain = driver.findElement(By.xpath("//button[@id='submitAccount']"));
		registerAgain.click();
		
		
	}
	
	@Test(priority=4)
	public void validateAcccount()
	{
		
		wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//h1[contains(text(),'My account')]")));
		
		//Verify user navigated to myaccount via information on page
		WebElement myAccount = driver.findElement(By.xpath("//h1[contains(text(),'My account')]"));
		sa.assertEquals(myAccount.isDisplayed(), true, "Test - createAccount - My account not fisplayed");
		
		//If signed in signout and first and last name should be present in top right hand corner 
		
		//Assert signout
		WebElement signedIn = driver.findElement(By.cssSelector("a[title='Log me out']"));
		sa.assertEquals(signedIn.getText(),"Sign out");
		
		//Assert name present
		WebElement accountNameFinal = driver.findElement(By.cssSelector(".account"));
		sa.assertEquals(accountNameFinal.getText(), globalFirstName + " " + globalLastName);

		
	}
	
	@AfterTest
	public void assertAll()
	{
		sa.assertAll();
		driver.close();
	}
	

	//helper methods
	
	//to get driver to be able to use in another class
	public WebDriver getDriver()
	{
		if (driver == null){
			System.setProperty("webdriver.chrome.driver", "/Users/josh.cacho/eclipse-workspace/Drivers/chromedriver");  
			driver = new ChromeDriver();
		      return driver;
		    }else{
		      return driver;
		    }
	}
}
