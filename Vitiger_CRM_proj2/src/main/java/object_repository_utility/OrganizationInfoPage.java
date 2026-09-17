package object_repository_utility;

import org.openqa.selenium.WebDriver;
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
	
	
	
	

}
