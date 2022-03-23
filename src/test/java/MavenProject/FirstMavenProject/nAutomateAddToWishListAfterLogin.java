package MavenProject.FirstMavenProject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class nAutomateAddToWishListAfterLogin {
	
	//References
		//https://www.techlistic.com/2020/06/automate-ecommerce-website.html
	
	public String baseURL = "http://automationpractice.com/index.php";
	public WebDriver driver;
	public SoftAssert sa = new SoftAssert();
	
	/*Test Case 2 - Verify that 'Add to Wishlist' only works after login.
	Steps to Automate:
	1. Open link http://automationpractice.com/index.php
	2. Move your cursor over Women's link.
	3. Click on sub menu 'T-shirts'.
	4. Mouse hover on the second product displayed.
	5. 'Add to Wishlist' will appear on the bottom of that product, click on it.
	6. Verify that error message is displayed 'You must be logged in to manage your wishlist.'*/
	
	@BeforeTest
	public void setBaseURL()
	{
		System.setProperty("webdriver.chrome.driver", 
				"/Users/josh.cacho/eclipse-workspace/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get(baseURL);
	}
	
	@Test
	public void addToWishListNoLogin()
	{
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		Actions action = new Actions(driver);
		
		//2. Move your cursor over Women's link.
		WebElement women = driver.findElement(By.cssSelector("a[title='Women']"));
		action.moveToElement(women).build().perform();
		
		//3. Click on sub menu 'T-shirts'.
		WebElement subMenuSummerDresses = driver.findElement(By.cssSelector("a[title='Summer Dresses']"));
		action.moveToElement(subMenuSummerDresses).click().build().perform();
		
		//4. Mouse hover on the second product displayed.
		WebElement secondItem = driver.findElement(By.xpath("(//a[@class='product_img_link'])[2]"));
		action.moveToElement(secondItem).build().perform();
		
		//5. 'Add to Wishlist' will appear on the bottom of that product, click on it.
		WebElement addToWishList = driver.findElement(By.cssSelector("li .addToWishlist.wishlistProd_6"));
		action.moveToElement(addToWishList).click().build().perform();
		
		//6. Verify that error message is displayed 'You must be logged in to manage your wishlist.'*/
		WebElement errorMessage = driver.findElement(By.cssSelector(".fancybox-error"));
		
		if(errorMessage.isDisplayed())
		{
			sa.assertEquals(errorMessage.getText(), "You must be logged in to manage your wishlist.");
			//close error box
			WebElement closeTOSMessage = driver.findElement(By.cssSelector(".fancybox-item.fancybox-close"));
			closeTOSMessage.click();
		}
		else
		{
			sa.assertTrue(false, "Test - addToWishListNoLogin - errorMessage did not display");
		}
		
	}
	
	@AfterTest
	public void tearDown()
	{
		sa.assertAll();
		driver.close();
	}

}
