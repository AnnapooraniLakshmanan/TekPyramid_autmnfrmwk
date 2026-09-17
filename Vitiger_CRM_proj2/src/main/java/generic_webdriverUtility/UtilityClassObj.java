package generic_webdriverUtility;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObj

{

	private static ThreadLocal<ExtentTest> ttest = new ThreadLocal<ExtentTest>();

	public static void setTest(ExtentTest acttest) {
		ttest.set(acttest);
	}

	public static ExtentTest getTest() {
		return ttest.get();
	}

}
