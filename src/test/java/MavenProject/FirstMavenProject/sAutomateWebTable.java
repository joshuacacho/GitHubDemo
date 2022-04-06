package MavenProject.FirstMavenProject;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.db.jdbc.DriverManagerConnectionSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class sAutomateWebTable {
	
	
		//Resources
			//https://www.techlistic.com/p/selenium-assignments.html
			//https://www.techlistic.com/p/demo-selenium-practice.html
				//https://www.techlistic.com/2017/02/how-to-handle-dynamic-web-table-in.html

		
		public String baseURL = "https://www.techlistic.com/p/demo-selenium-practice.html";
		public WebDriver driver;
		SoftAssert sa = new SoftAssert();
		private static Logger Log = LogManager.getLogger(rAutomateFindingBrokenLinks.class.getName());
		
		@BeforeTest
		public void setBaseURL()
		{
			System.setProperty("webdriver.chrome.driver", 
					"/Users/josh.cacho/eclipse-workspace/Drivers/chromedriver");
			driver = new ChromeDriver();
			Log.info("Setting BaseURL");
			driver.get(baseURL);
		}
		
		/*Assignment 1: Verify that there are only 4 structure values present.
		Read the 'Structure' column and find out the total number of structures present.
		Read the value of 'Total' column and match with the previous step.
		Solution Hint:*/
		
		@Test(priority=0)
		public void compareStructureCount()
		{
			//Obtain structureCount
			List<WebElement> getStructures = driver.findElements(By.xpath("//tbody/tr/th"));
			
			//obtain list of structures
			int tabStrctCount = 0;
			for(WebElement element:getStructures)
			{
				tabStrctCount++;
			}
			
			//Obtain Total Structure Count from Total Column
			String totalCount = driver.findElement(By.cssSelector("td[colspan='7']")).getText();
			String[] finalTotalCount = totalCount.split(" ");
			Log.debug(finalTotalCount[0]); 
			
			//Assert values and compare
			sa.assertEquals(tabStrctCount, Integer.parseInt(finalTotalCount[0]));
		
		}
		
		/*Assignment 2: Read All the Values from the table row-wise and print them all*/
		@Test(priority=1)
		public void printAllRows()
		{
			List<WebElement> rows = driver.findElements(By.xpath("//tr"));
			for(WebElement element:rows)
			{
				System.out.println(element.getText());
			}
		
		}
		
		/*Assignment 3: Verify that Burj Khalifa has a height of 829m (similar for other structures)*/
		@Test(priority=2)
		public void verBrjKhlfaHeight()
		{
			//Obtain structures
			List<WebElement> getStruct = driver.findElements(By.xpath("//tbody/tr/th"));
			//Obtain Height of Structures
			String getHeightStruct = driver.findElement(By.xpath("(//tr/td[3])[1]")).getText();
			
			for(WebElement element:getStruct)
			{
				if(element.getText().equalsIgnoreCase("Burj Khalifa"))
				{
					//Assert Height is correct
					sa.assertEquals(getHeightStruct, "829m");
				}
			}
			
		}
		
		/*Assignment 4: Verify that 6th row (Last Row) has only two columns.*/
		@Test(priority=3)
		public void verSixthRowColumns()
		{
			String lastRow = driver.findElement(By.xpath("//tfoot")).getText();
			String[] finalRowCount = lastRow.split("Total");
			Log.debug(finalRowCount.length);
			sa.assertEquals(finalRowCount.length, 2, "verSixthRowColumns - 6th row column value doesnt match");
			
		}
		
		@Test(priority=4)
		public void verTheUpdateFromGit()
		{
			System.out.println("Our time is now son");
			
		}

		@AfterTest
		public void tearDown()
		{
			sa.assertAll();
			driver.close();
		}
		

}
