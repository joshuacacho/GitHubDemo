package MavenProject.FirstMavenProject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class gAutomatePracticeForm2 {
	
	//Resources 
		//https://www.techlistic.com/p/selenium-practice-form.html
		//https://www.edureka.co/blog/uploading-file-usiing-selenium/
	
	public String baseURL = "https://www.techlistic.com/p/selenium-practice-form.html";
	public WebDriver driver;
	SoftAssert sa = new SoftAssert();
	WebDriverWait wait;
	
	@BeforeTest
	public void setBaseURL()
	{
		System.setProperty("webdriver.chrome.driver", "/Users/josh.cacho/eclipse-workspace/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get(baseURL);
	}
	
	@Test(priority=0)
	public void personalInfo()
	{
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		WebElement fName = driver.findElement(By.cssSelector("input[name='firstname']"));
		fName.clear();
		//fName.click();
		fName.sendKeys("Joey");
		
		WebElement lName = driver.findElement(By.cssSelector("input[name='lastname']"));
		lName.clear();
		//fName.click();
		lName.sendKeys("Lorenzo");
		
		WebElement gender = driver.findElement(By.cssSelector("input[value='Male']"));
		gender.click();
	}
	
	@Test(priority=1)
	public void profressioanlInfo()
	{
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		WebElement yrsExprnce = driver.findElement(By.cssSelector("input[value='7']"));
		yrsExprnce.click();
		
		WebElement date = driver.findElement(By.cssSelector("#datepicker"));
		date.clear();
		date.sendKeys("1/1/1971");
		
		WebElement profession = driver.findElement(By.cssSelector("input[value='Automation Tester']"));
		profession.click();
		
		WebElement automationTool = driver.findElement(By.cssSelector("input[value='Selenium Webdriver']"));
		automationTool.click();
	}
	
	@Test(priority=2)
	public void locationsOfInterest()
	{
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		WebElement continents = driver.findElement(By.cssSelector("#continents"));
		continents.click();
		
		Select chooseCont = new Select(continents);
		chooseCont.selectByVisibleText("North America");
		
		WebElement commands = driver.findElement(By.cssSelector("#selenium_commands"));
		commands.click();;
		
		Select chooseCmnd = new Select(commands);
		chooseCmnd.selectByVisibleText("Wait Commands");;  //Wait Commands
		chooseCmnd.selectByVisibleText("Navigation Commands");;  //Wait Commands
	}
	
	
	@Test(priority=3)
	public void UploadFile()
	{
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		WebElement chooseFile = driver.findElement(By.cssSelector(".input-file"));
		//the below wont work
			//If you click on the choose file button, then you will be taken to your machine window 
			//to select a file and then you will not be able to select a file using selenium.
			//chooseFile.click(); 
		
		//Instead just out entire file path with selenium
		chooseFile.sendKeys("/Users/josh.cacho/Desktop/Install ID.txt");
		
		//Assert correct file was uploaded
		String value = chooseFile.getAttribute("value");
		//String text = chooseFile.getText();
		//System.out.println("this value " + value + " " + "this text " + text);
		
		//this value C:\fakepath\Install ID.txt this text 
		sa.assertEquals(value.contains("Install ID.txt"), true, "Test - UploadFile");
		
	}
	
	@Test(priority=4)
	public void DownloadFile()
	{
		//havent got there yet...
	}
	
	
	@AfterTest
	public void assertAll()
	{
		sa.assertAll();
		//driver.close();
	}
	
}
