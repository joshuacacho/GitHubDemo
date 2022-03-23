package MavenProject.FirstMavenProject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class mAutomatebuyProductFunctionalityECommerce {

	
	//Resources
		//https://www.techlistic.com/2020/06/automate-ecommerce-website.html
		//https://stackoverflow.com/questions/42331659/how-to-pass-selenium-webdriver-instance-to-another-class
		//https://www.techlistic.com/2021/10/selenium-webdriver-commands.html
		//https://www.techlistic.com/2019/07/selenium-webdriver-locators-and-find.html
		//https://www.techlistic.com/2021/10/selenium-webdriver-commands.html
		//https://www.techlistic.com/2019/07/selenium-webdriver-actions-class.html
	
	/*Assignment 2 - Automate 'Buy Product' functionality of an e-commerce website with Selenium
	Most important functionality of an e-commerce website is buying a product, which includes various steps like select product, select size/color, add to cart, checkout etc. You will find every test scenario along with automation code in following section.

	Test Case 1 - Automate End to End Buy Order functionality.
	Automation Level - Expert
	Steps to Automate:
	1. Open link http://automationpractice.com/index.php
	2. Login to the website.
	3. Move your cursor over Women's link.
	4. Click on sub menu 'T-shirts'.
	5. Mouse hover on the second product displayed.
	6. 'More' button will be displayed, click on 'More' button.
	7. Increase quantity to 2.
	8. Select size 'L'
	9. Select color.
	10. Click 'Add to Cart' button.
	11. Click 'Proceed to checkout' button.
	12. Complete the buy order process till payment.
	13. Make sure that Product is ordered.*/
	
	public String baseURL = "http://automationpractice.com/index.php";
	public WebDriver driver;
	public WebDriverWait wait;
	SoftAssert sa = new SoftAssert();
	
	@BeforeTest
	public void setBaseURL()
	{
		System.setProperty("webdriver.chrome.driver", "/Users/josh.cacho/eclipse-workspace/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get(baseURL);
	}
	
	/*public void createNewUser()
	{
		//to create new user
		
		/*iAutomateUserRegistrationECommerce newUser = new iAutomateUserRegistrationECommerce();
		newUser.getDriver().get(baseURL);
		newUser.createAccount();
		newUser.personalInfo();
		newUser.address();
		newUser.validaeAcccount();
				
		//Once logged in perform the below
			//1 Log out
			//2. Log back in with account created
		System.out.println(newUser.globalEmail);
		System.out.println(newUser.globalPassword); 
	}*/
	
	
	//1. Open link http://automationpractice.com/index.php
	//2. Login to the website.
	@Test(priority=1)
	public void Login()
	{
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		WebElement signIn = driver.findElement(By.className("login"));
		signIn.click();
		
		//wait until SignIn button present
		wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SubmitLogin")));
		
		//Login creds created from createNewUser()
			//joeylorenzo914@yahoo.com
			//rafike79
		WebElement exstngEmail = driver.findElement(By.id("email"));
		exstngEmail.sendKeys("joeylorenzo914@yahoo.com");
		
		WebElement password = driver.findElement(By.id("passwd"));
		password.sendKeys("rafike79");
		
		WebElement signInButton = driver.findElement(By.id("SubmitLogin"));
		signInButton.click();
		
		//Assert Logged in successful
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'My account')]")));
		
		//Assert you are logged in if successful
		WebElement loggedIn = driver.findElement(By.linkText("Sign out"));
		sa.assertEquals(loggedIn.isDisplayed(), true);
		
	}
	
	/*3. Move your cursor over Women's link.
	4. Click on sub menu 'T-shirts'.
	5. Mouse hover on the second product displayed.
	6. 'More' button will be displayed, click on 'More' button.
	7. Increase quantity to 2.
	8. Select size 'L'
	9. Select color.
	10. Click 'Add to Cart' button.
	11. Click 'Proceed to checkout' button.
	12. Complete the buy order process till payment.
	13. Make sure that Product is ordered.*/
	@Test(priority=2)
	public void addProduct()
	{
		
		//3. Move your cursor over Women's link.
		Actions mouseMove = new Actions(driver);
		//dont use linktext to find item, use parent child css or xpath if you can
		WebElement women = driver.findElement(By.cssSelector("li a[title='Women']"));
		mouseMove.moveToElement(women).build().perform();
		
		//4. Click on sub menu 'T-shirts'.
		WebElement tShirts = driver.findElement(
				By.cssSelector("ul[class*='submenu-container'] a[title='T-shirts']"));
		mouseMove.moveToElement(tShirts).click().build().perform();
		
		//5. Mouse hover on the second product displayed.
		WebElement productDisplay = driver.findElement(By.cssSelector(".right-block"));
		mouseMove.moveToElement(productDisplay);
		
		//6. 'More' button will be displayed, click on 'More' button.
		WebElement moreOptions = driver.findElement(By.xpath("//span[contains(text(),'More')]"));
		mouseMove.moveToElement(moreOptions).click().build().perform();

				
		//7. Increase quantity to 2.
			//first wait for nextpage to be displayed (quantity field)
		wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("quantity_wanted")));
		WebElement itemquantity = driver.findElement(By.cssSelector(".icon-plus"));
		itemquantity.click();
	
		//8. Select size 'L'
		Select productSize = new Select(driver.findElement(By.id("group_1")));
		productSize.selectByValue("3"); //Large
		
		//9. Select color.
		WebElement productColor = driver.findElement(By.name("Blue"));
		productColor.click();
		
		//10. Click 'Add to Cart' button.
		WebElement addToCart = driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]"));
		addToCart.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("div[class*='layer_cart_cart']")));
		
		//11. Click 'Proceed to checkout' button.
		WebElement checkOut = driver.findElement(By.cssSelector("a[title='Proceed to checkout']"));
		checkOut.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1[id='cart_title']")));
		
		//12. Complete the buy order process till payment.
			//Verify items in card
		WebElement checkOutFinal = driver.findElement(
				By.cssSelector(".cart_navigation.clearfix a[title='Proceed to checkout']"));
		checkOutFinal.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".page-heading")));
		
			//Verify address
		WebElement verifyAddressProcced = driver.findElement(
					By.name("processAddress"));
		verifyAddressProcced.click();
		
			//Verify Shipping
			//Assert You must agree with terms of service
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Shipping')]")));
		
		WebElement verifyShippingProcced = driver.findElement(
				By.name("processCarrier"));
		verifyShippingProcced.click();
		
		//Assert error message is displayed and text reads "You must agree to the terms of service before continuing."
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fancybox-error")));
		
		WebElement tosMessage = driver.findElement(By.cssSelector(".fancybox-error"));
		
		if(tosMessage.isDisplayed())
		{
			//System.out.println(tosMessage.getText());
			sa.assertEquals(tosMessage.getText(), 
					"You must agree to the terms of service before continuing.");
			
			//wait until checkbox present to select
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fancybox-item.fancybox-close"))); 
			
			//close error box
			WebElement closeTOSMessage = driver.findElement(By.cssSelector(".fancybox-item.fancybox-close"));
			closeTOSMessage.click();
		}
		else
		{
			sa.assertFalse(true);
		}
		
		
		//Click terms of Service
		WebElement tosConfirm = driver.findElement(By.id("cgv"));
		tosConfirm.click();
		
		//Click Proceed to shipping
		verifyShippingProcced.click();
		
		//Choose payment method
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".page-heading")));
		
		WebElement payByCheck = driver.findElement(By.cssSelector(".cheque"));
		payByCheck.click();
		
		//Order Summary
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".page-heading")));
		
		WebElement confirmOrder = driver.findElement(
				By.cssSelector("#cart_navigation button[type='submit']"));
		confirmOrder.click();
		
		//13. Make sure that Product is ordered.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success")));
		
		WebElement orderCompleteMessage = driver.findElement(By.cssSelector(".alert.alert-success"));
		sa.assertEquals(orderCompleteMessage.getText(), "Your order on My Store is complete.");
		
	}
	
	@AfterTest
	public void assertAll() 
	{
		sa.assertAll();
		driver.close();
	}
	
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
