package object_repository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic_webdriverUtility.WebDriverUtility;

public class HomePage 
{
	WebDriver driver;
	WebDriverUtility webutil;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Organizations")
	private WebElement orglink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactlink;
	
	@FindBy(xpath ="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement plusBtn;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement profileIcon;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signout;
	

	public WebElement getSignout() {
		return signout;
	}

	public WebElement getProfileIcon() {
		return profileIcon;
	}

	public WebElement getOrglink() {
		return orglink;
	}

	public WebElement getContactlink() {
		return contactlink;
	}

	public WebElement getAddOrgBtn() {
		return plusBtn;
	}
	
	public void logout() throws InterruptedException
	{
		webutil = new WebDriverUtility(driver);
		Thread.sleep(3000);
		webutil.mouseHover(profileIcon);
		webutil.waitForVisibility(signout).click();
	}
	
	
}
