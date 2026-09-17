package object_repository_utility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic_fileUtility.FileUtility;
import generic_webdriverUtility.WebDriverUtility;

public class LoginPage   {
	WebDriver driver;
    WebDriverUtility webutil;
    FileUtility fileutil = new FileUtility();
	
    public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.webutil = new WebDriverUtility(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@type='text']")
	private WebElement username;

	@FindBy(xpath = "//input[@type='password']")
	private WebElement password;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement loginbtn;

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getUsername() {
		return username;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getLoginbtn() {
		return loginbtn;
	}
	
	public void loginToApp(String url,String usernm,String pswd) throws IOException
	{
		webutil.implicitWait(20);
		webutil.launchUrl(url);
		webutil.maximizeWindow();
		username.sendKeys(usernm);
		password.sendKeys(pswd);
		loginbtn.click();
	}

}
