package object_repository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic_webdriverUtility.WebDriverUtility;

public class OrganizationInfoPage 
{
	WebDriver driver;
	WebDriverUtility webutil;

	public OrganizationInfoPage(WebDriver driver) {
		this.driver = driver;
		this.webutil = new WebDriverUtility(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement plusBtn;

	public WebElement getPlusBtn() {
		return plusBtn;
	}
	

}
