package generic_baseClass;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.mysql.cj.jdbc.Driver;

import generic_fileUtility.FileUtility;
import object_repository_utility.HomePage;
import object_repository_utility.LoginPage;

public class BaseClass {

	public WebDriver driver;
	Connection con;
	FileUtility prop = new FileUtility();
	LoginPage lp;
	HomePage hp;
	

	@BeforeSuite(groups = { "smoke", "regression" })
	public void databaseConnectivity() throws SQLException {

		Driver d = new Driver();
		DriverManager.registerDriver(d);
		con = DriverManager.getConnection("jdbc:mysql://49.249.29.4:3307/ninza_hrm", "root@%", "root");
        System.out.println("Database Connected");
        
        
	}

	@AfterSuite(groups = { "smoke", "regression" })
	public void databaseClosure() throws SQLException {
		con.close();
		System.out.println("Database closed");
	}

	//@Parameters("BROWSER")
	@BeforeClass(groups = { "smoke", "regression" })
	//pass String browser as mthd arg for cross browser testing 
	public void browserLaunching() throws IOException {
		String browser = prop.getDataFromProperties("browser");
		if (browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();
		else if (browser.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else if (browser.equalsIgnoreCase("edge"))
			driver = new EdgeDriver();
		else
			driver = new ChromeDriver();

	}

	@AfterClass(groups = { "smoke", "regression" })
	public void browserClosing() {
		driver.quit();
		System.out.println("Browser closed");

	}

	@BeforeMethod(groups = { "smoke", "regression" })
	public void loginToApp() throws IOException {
		lp = new LoginPage(driver);
		String url = prop.getDataFromProperties("url");
		String username = prop.getDataFromProperties("username");
		String password = prop.getDataFromProperties("password");
		lp.loginToApp(url, username, password);

	}

	@AfterMethod(groups = { "smoke", "regression" })
	public void logoutFromApp() throws InterruptedException {
		hp = new HomePage(driver);
		Thread.sleep(3000);
		hp.logout();
	}

}
