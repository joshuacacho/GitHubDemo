package ObjectRepositroy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class bPHPTravelsAdminDashboard {
	
	WebDriver driver;
	
	//Constructor
	public bPHPTravelsAdminDashboard(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//strong[contains(text(),'Logout')]")
	WebElement logOutLink;
	
	public WebElement logOutLink()
	{
		return logOutLink;
	}
	
}
