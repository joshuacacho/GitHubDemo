package MavenProject.FirstMavenProject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class oAutomateTotalPriceReflectCorrectlyShoppingCartSummary {
	
	
	//References
		//https://www.techlistic.com/2020/06/automate-ecommerce-website.html
	
	
	/*Test Case 3 - Verify that Total Price is reflecting correctly if user changes 
	 * quantity on 'Shopping Cart Summary' Page.
	Steps to Automate:
	1. Open link http://automationpractice.com/index.php
	2. Login to the website.
	3. Move your cursor over Women's link.
	4. Click on sub menu 'T-shirts'.
	5. Mouse hover on the second product displayed.
	6. 'More' button will be displayed, click on 'More' button.
	7. Make sure quantity is set to 1.
	8. Select size 'M'
	9. Select color of your choice.
	10. Click 'Add to Cart' button.
	11. Click 'Proceed to checkout' button.
	12. Change the quantity to 2.
	13. Verify that Total price is changing and reflecting correct price.
	Similar way you can add few more test cases.*/
	
	
	
	public String baseURL = "http://automationpractice.com/index.php";
	public WebDriver driver;
	public WebDriverWait wait;
	public SoftAssert sa = new SoftAssert();
	
	@BeforeTest()
	public void getDriver()
	{
		System.setProperty("webdriver.chrome.driver", "/Users/josh.cacho/eclipse-workspace/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get(baseURL);
	}
	
	
	/*1. Open link http://automationpractice.com/index.php
	2. Login to the website.*/
	
	@Test (priority=1)
	public void Login()
	{

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
	7. Make sure quantity is set to 1.
	8. Select size 'M'
	9. Select color of your choice.
	10. Click 'Add to Cart' button.
	11. Click 'Proceed to checkout' button.
	12. Change the quantity to 2.
	13. Verify that Total price is changing and reflecting correct price.*/
	
	@Test (priority=2)
	public void correctPrice() throws InterruptedException
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
		
		//7. Make sure quantity is set to 1.
		
		//first wait for nextpage to be displayed (quantity field)
		wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("quantity_wanted")));
		
		//assert that value is 1
		WebElement quantityWanted = driver.findElement(By.cssSelector("#quantity_wanted"));
		sa.assertEquals(quantityWanted.getAttribute("value"), "1");
		
		//8. Select size 'M'
		Select productSize = new Select(driver.findElement(By.id("group_1")));
		productSize.selectByValue("2"); //Medium
				
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
		
		//12. Change the quantity to 2.
		//13. Verify that Total price is changing and reflecting correct price.
		String getPriceBefore = driver.findElement(
				By.cssSelector(".cart_total span[id*='total_product_price']")).getText();
		//System.out.println(getPriceBefore);  //need to parse $16.51 to remove
		String priceBefore = getPriceBefore.replace("$", ""); //to remove the $ sign the easy way
		//System.out.println(priceBefore);
		double finalBeforePrice = Double.parseDouble(priceBefore);
		System.out.println("Before price " + finalBeforePrice);

		WebElement checkoutProductQty = driver.findElement(By.cssSelector(".icon-plus"));
		checkoutProductQty.click();
		
		WebElement shoppingCartQty = driver.findElement(By.cssSelector("#summary_products_quantity"));
		System.out.println(shoppingCartQty.getText());
		for(int i=0;i<10;i++)
		{
			Thread.sleep(5000); //remove this and find better way to wait for page to update
			//reference
				//https://stackoverflow.com/questions/32277992/selenium-webdriver-2-47-1-how-to-wait-for-a-page-reload/32278904#32278904
			
			if(shoppingCartQty.getText().equalsIgnoreCase("2 Products"))
			{
				String getPriceAfter = driver.findElement(
						By.cssSelector(".cart_total span[id*='total_product_price']")).getText();
				System.out.println(getPriceAfter);
				String priceAfter = getPriceAfter.replace("$", "");
				double finalAfterPrice = Double.parseDouble(priceAfter);
				System.out.println("After Price " + finalAfterPrice);
				sa.assertEquals(finalBeforePrice, (finalAfterPrice/2));
				System.out.println("in loop");
				break;
			}
			
			i++;
		}
		
		/*String getPriceAfter = driver.findElement(
				By.cssSelector(".cart_total span[id*='total_product_price']")).getText();
		System.out.println(getPriceAfter);
		String priceAfter = getPriceAfter.replace("$", "");
		double finalAfterPrice = Double.parseDouble(priceAfter);
		System.out.println("After Price" + finalAfterPrice);
		sa.assertEquals(finalBeforePrice, (finalAfterPrice/2));*/
		
		
	}
	
	@AfterTest
	public void tearDown()
	{
		sa.assertAll();
		//driver.close();
	}

}
