package generic_listenerUtility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import generic_baseClass.BaseClass;
import generic_webdriverUtility.JavaUtility;
import generic_webdriverUtility.UtilityClassObj;

public class ListenerImp extends BaseClass implements ITestListener, ISuiteListener {
	
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public static ExtentTest test;
	JavaUtility ju = new JavaUtility();
	String date = ju.currentDate();

	@Override
	public void onStart(ISuite suite) {

		spark = new ExtentSparkReporter("./AdvanceReport/report" + date + ".html");
		spark.config().setDocumentTitle("Vitiger CRM Test Suite Results");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.STANDARD);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Windows", "OS");

	}

	@Override
	public void onFinish(ISuite suite) {
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult res) {
		String mthdname = res.getMethod().getMethodName();
		test = report.createTest(mthdname);
		UtilityClassObj.setTest(test);
		test.log(Status.INFO,mthdname+" ======>STARTED!!");
	}

	@Override
	public void onTestFailure(ITestResult res) {

		String name = res.getMethod().getMethodName() + date;
		TakesScreenshot ts = (TakesScreenshot) driver;
		String scrn = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(scrn,name);
		test.log(Status.FAIL,name+" got failed!!");
	}

	@Override
	public void onTestSuccess(ITestResult res) {
		String mthdname = res.getMethod().getMethodName();
		test.log(Status.PASS,mthdname+" executed sucessfully!!");
	}

	@Override
	public void onTestSkipped(ITestResult res) {
		String mthdname = res.getMethod().getMethodName();
		test.log(Status.SKIP,mthdname+" got skipped!!");
	}

}
