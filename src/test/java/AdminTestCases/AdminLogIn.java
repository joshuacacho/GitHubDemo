package AdminTestCases;

import org.openqa.selenium.Dimension;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ObjectRepositroy.aPHPTravelsAdminLogIn;
import ObjectRepositroy.bPHPTravelsAdminDashboard;

public class AdminLogIn {
	
	//Resources 
		//https://phptravels.com/demo/ -> Admin Back-End
			//https://www.phptravels.net/admin
	
	WebDriver driver;
	WebDriverWait wait;
	SoftAssert sa = new SoftAssert();
	private static Logger Log = LogManager.getLogger(AdminLogIn.class.getName());
	
	@Test
	public void AdminLogin() throws Exception 
	{
		System.setProperty("webdriver.chrome.driver", 
				"/Users/josh.cacho/eclipse-workspace/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://phptravels.net/api/admin");
	
		//Setting size of the Window so links not jumbled on Dashboard screen
		Dimension dm = new Dimension(1500,1500);
		driver.manage().window().setSize(dm);
		Log.info("Created a valid dimension");
		
		//Using OR aPHPTravelsAdminLogIn class
		aPHPTravelsAdminLogIn ali = new aPHPTravelsAdminLogIn(driver);
		ali.email().sendKeys("admin@phptravels.com");
		Log.info("Entered a valid email");
		
		ali.password().sendKeys("demoadmin");
		Log.info("Entered a valid password");
		
		ali.rememberMe().click();
		Log.info("Clicking remember me");

		ali.loginButton().click();
		Log.info("Clicking login button");
		
		//wait for Dashboard to be present
		Log.info("Waiting for dashboard to be present");
		wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//small[contains(text(),'DASHBOARD')]")));
		Log.info("Dashboard Present");
		
		try
		{
			//Asserting LogOut link exists to verify successful login
			bPHPTravelsAdminDashboard adb = new bPHPTravelsAdminDashboard(driver);
			sa.assertEquals(adb.logOutLink().isDisplayed(), true, "TestCase AdminLogin - Not Logged In");
			Log.info("Verified that the Admin user logged in and sees the Dashboard");
		}
		catch (Exception e)
		{
			Log.error("Unsuccessful Login and Dashboard not seen");
		}
		
		
		//New developments after taking code
		System.out.println("I made some updates son");
		System.out.println("Coming from user x bastard. Here is your crap");
		System.out.println("Three lines of fun");
	}
	
	@AfterTest
	public void assertAll()
	{
		sa.assertAll();
		driver.close();
	}
	
}
