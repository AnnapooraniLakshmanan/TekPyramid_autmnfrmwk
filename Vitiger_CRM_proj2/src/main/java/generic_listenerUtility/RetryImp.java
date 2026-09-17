package generic_listenerUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import generic_baseClass.BaseClass;

public class RetryImp extends BaseClass implements IRetryAnalyzer {

	
	int count=0,limit=3;
	
	@Override
	public boolean retry(ITestResult result) 
	{
		if(count<limit)
		{
			count++;
			return true;
		}
		
		return false;
	}

}
