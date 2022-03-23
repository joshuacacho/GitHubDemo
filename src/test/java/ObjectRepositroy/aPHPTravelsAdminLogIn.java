package ObjectRepositroy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class aPHPTravelsAdminLogIn {
	
	WebDriver driver;
	
	public aPHPTravelsAdminLogIn(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(name = "email")
	WebElement email;
	
	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(css = ".icheckbox_square-grey")
	WebElement rememberMe;
	
	@FindBy(linkText = "Forget Password")
	WebElement forgotPassword;
	
	@FindBy(css = "button[type='submit']")
	WebElement loginButton;
	
	
	public WebElement email()
	{
		return email;
	}

	public WebElement password()
	{
		return password;
	}
	
	public WebElement rememberMe()
	{
		return rememberMe;
	}
	
	public WebElement forgotPassword()
	{
		return forgotPassword;
	}
	
	public WebElement loginButton()
	{
		return loginButton;
	}
	
}
