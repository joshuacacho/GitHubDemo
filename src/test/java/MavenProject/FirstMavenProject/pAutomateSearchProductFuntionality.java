package MavenProject.FirstMavenProject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class pAutomateSearchProductFuntionality {
	
	
	//References
		//https://www.techlistic.com/2021/10/selenium-webdriver-commands.html
		//https://www.techlistic.com/2019/07/selenium-webdriver-locators-and-find.html
		//https://www.techlistic.com/2021/10/selenium-webdriver-commands.html
		//https://www.techlistic.com/2020/06/automate-ecommerce-website.html
		//https://www.techlistic.com/p/selenium-assignments.html
	
	/*Assignment 3 - Automate 'Search Product' functionality of an e-commerce website
	Test Case 1- Automate 'Search Product' Functionality of an e-commerce website with Selenium
	Steps to Automate:
	1. Open link http://automationpractice.com/index.php
	2. Move your cursor over Women's link.
	3. Click on sub menu 'T-shirts'
	4. Get Name/Text of the first product displayed on the page.
	5. Now enter the same product name in the search bar present on top of page and click search button.
	6. Validate that same product is displayed on searched page with same details which were 
	displayed on T-Shirt's page.*/

	
	public String baseURL = "http://automationpractice.com/index.php";
	public WebDriver driver;
	public SoftAssert sa = new SoftAssert();
	
	
	@BeforeTest
	public void setBaseURL()
	{
		System.setProperty("webdriver.chrome.driver", 
				"/Users/josh.cacho/eclipse-workspace/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get(baseURL);
	}
	
	@Test
	public void searchProduct()
	{
		chooseWomenItem("T-shirts");
		String beforeSearch = getProductItem(0);
		
		
		//5. Now enter the same product name in the search bar present on top of page and click search button.
		WebElement searchText = driver.findElement(By.cssSelector("#search_query_top"));
		searchText.sendKeys(beforeSearch);
		
		WebElement submitSearch = driver.findElement(By.name("submit_search"));
		submitSearch.click();
		
		//6. Validate that same product is displayed on searched page with same details which were 
		String afterSearch = getProductItem(0);
		sa.assertEquals(beforeSearch, afterSearch);
	}
	
	
	@AfterTest
	public void tearDown()
	{
		sa.assertAll();
		driver.close();
	}
	
	/*
	 * @Annotation
	 * 2. Move your cursor over Women's link.
	 * 3. Click on sub menu 'T-shirts'
	 */
	public void chooseWomenItem(String item)
	{
		Actions move = new Actions(driver);
		WebElement catWomen = driver.findElement(By.cssSelector("a[title='Women']"));
		move.moveToElement(catWomen).build().perform();
		
		WebElement selSubItem = driver.findElement(
				By.className("sfHover")).findElement(By.linkText(item));
		move.moveToElement(selSubItem).click().build().perform();
	}

	
	/*
	 * @Annotation
	 * 4. Get Name/Text of the first product displayed on the page.
	 */
	public String getProductItem(int itemNumber)
	{
		String itemName = null;
		List<WebElement> products = driver.findElements(By.cssSelector(".product-container .product-name"));
		for(int i=0;i<products.size();i++)
		{
			//System.out.println(products.get(i).getText());
			if(i == itemNumber)
			{
				 itemName = products.get(i).getText();
				 break;
			}
			i++;
		}
		//System.out.println(itemName);
		return itemName;
	}

}
