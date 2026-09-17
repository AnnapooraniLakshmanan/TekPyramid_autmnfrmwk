package object_repository_utility;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic_webdriverUtility.WebDriverUtility;

public class CreatingNewOrganizationPage {

	WebDriver driver;
	WebDriverUtility webutil;

	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@type='text' and @name='accountname']")
	private WebElement orgNameTxt;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement plusBtn;
	
	@FindBy(xpath = "//input[@id='phone']")
	private WebElement phoneno;

	public WebElement getPhoneno() {
		return phoneno;
	}

	public WebElement getPlusBtn() {
		return plusBtn;
	}

	public WebElement getOrgNameTxt() {
		return orgNameTxt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public void createOrg(String orgname) throws EncryptedDocumentException, IOException {
		plusBtn.click();
		orgNameTxt.sendKeys(orgname);
		saveBtn.click();
	}

	public void createOrgWithIndus(String orgname,String indus) throws EncryptedDocumentException, IOException {
		plusBtn.click();
		orgNameTxt.sendKeys(orgname);
		webutil = new WebDriverUtility(driver);
		webutil.selectByVisibleText(By.xpath("//select[@name='industry']"),indus);
		saveBtn.click();
	}
	
	public void createOrgWithPhone(String orgname,String phonenum)
	{
		plusBtn.click();
		orgNameTxt.sendKeys(orgname);
		phoneno.sendKeys(phonenum);
		saveBtn.click();
	}

}
