package object_repository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage 
{
	WebDriver driver;

	public OrganizationsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement plusBtn;

	public WebElement getPlusBtn() {
		return plusBtn;
	}
	
	/*
	 * This change is added to check the git conflit by Amit
	 */
	@FindBy(xpath = "//img[@alt='Create Organization']")
	private WebElement plusBtnByAmit;

	public WebElement getPlusBtnByAmit() {
		return plusBtnByAmit;
	}
	
	/*
	 * this is the second change
	 */
	
	@FindBy(xpath = "//img[@alt='Create Organization']")
	private WebElement plusBtnByAmit2;

	public WebElement getPlusBtnByAmit2() {
		return plusBtnByAmit2;
	}
}
