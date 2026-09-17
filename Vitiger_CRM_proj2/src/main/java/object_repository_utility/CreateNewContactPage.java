package object_repository_utility;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic_webdriverUtility.WebDriverUtility;

public class CreateNewContactPage 
{
	WebDriver driver;
	WebDriverUtility webutil;
	
	public CreateNewContactPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText = "Contacts")
	private WebElement contactlink;
	
	@FindBy(xpath ="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement plusBtn;
	
	@FindBy(xpath = "//input[@name='lastname']")
	private WebElement lastNameTxt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//input[@name='support_start_date']")
	private WebElement startDate;
	
	@FindBy(xpath = "//input[@name='support_end_date']")
	private WebElement endDate;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img[@title='Select']")
	private WebElement orgLookUp;
	
	@FindBy(xpath = "//input[@name='search_text']")
	private WebElement searchBar;
	
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement text;
	
	public WebElement getSearchBar() {
		return searchBar;
	}

	public WebElement getOrgLookUp() {
		return orgLookUp;
	}

	public WebElement getStartDate() {
		return startDate;
	}

	public WebElement getEndDate() {
		return endDate;
	}

	public WebElement getContactlink() {
		return contactlink;
	}

	public WebElement getPlusBtn() {
		return plusBtn;
	}

	public WebElement getLastNameTxt() {
		return lastNameTxt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createContact(String lastName)
	{
		contactlink.click();
		plusBtn.click();
		lastNameTxt.sendKeys(lastName);
		saveBtn.click();
	}
	
	public void createContactWithDate(String lastName,String curDate,String endDt)
	{
		contactlink.click();
		plusBtn.click();
		lastNameTxt.sendKeys(lastName);
		startDate.clear();
		startDate.sendKeys(curDate);
		endDate.clear();
		endDate.sendKeys(endDt);
		saveBtn.click();
	}
	
	public void createContactWithOrg(String lastName,String orgName) throws InterruptedException
	{
		
	    contactlink.click();
		plusBtn.click();
		lastNameTxt.sendKeys(lastName);
		webutil = new WebDriverUtility(driver);
		orgLookUp.click();
		String parent = driver.getWindowHandle();
		webutil.switchToWindowById(parent);
		searchBar.sendKeys(orgName,Keys.ENTER);
		driver.findElement(By.xpath("//table[@class='small']/tbody/tr/td/a[text()='"+orgName+"']")).click();
        driver.switchTo().window(parent);
		saveBtn.click();
		
	}
	
}
