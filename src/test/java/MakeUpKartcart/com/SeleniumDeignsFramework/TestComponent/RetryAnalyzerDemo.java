package MakeUpKartcart.com.SeleniumDeignsFramework.TestComponent;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerDemo implements IRetryAnalyzer {
	
	// when testcase failed then it should come here
	
	int count =0;
	int maxtry =1;
	

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		if(count < maxtry) {
			// we didn't do single rerun , then it should come to this block and should rerun
			// before rerun incremnt the count because to make sure it already reran that test case 
			// and also we should goto that particukar test use attribute retryAnalyzer="class_name.class" in that test case 
			count++;
			return true;
		}
		
		// Whenever test failure occurs it should come this class / test and should rtery the failed one and ask us do i need to rerun again 
		return false;
	}

}
