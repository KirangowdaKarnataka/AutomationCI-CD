package MakeUpKartcart.com.SeleniumDeignsFramework.TestComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import MakeUpKartcart.com.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extentRepo = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> locals = new ThreadLocal<ExtentTest>(); // to fix concurrency issue 

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		// before running any test this will be excuted
		// it is better to create any entry for createtest for extent report 
		
		test = extentRepo.createTest(result.getMethod().getMethodName());
		//craete a test case with the given test case name 
		//and using result attribute we should get the methodname and method also 
		// Before excuting any test case , we are calling onTestStart and this will get test case name and dynamically setting it in report
		
		locals.set(test); // unique thred id(errrovalidation) --> test  (set will store the objects in the  thread id (those are not  thread safe))
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		// after test sucess , it will com to this
		// when test failed the excution diretly come to this 
		//screenshot code can be written
		
		// 1. test entry dynamically
		locals.get().log(Status.PASS, "Test case description - Test case passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		// 2. testcase fail dynamically
		locals.get().fail(result.getThrowable()); // this will print error message in report 
		//locals.get(will get the tread id of test and returns the test)
		
		// code to get the driver information
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			
			// first get the test class where this particular method is present(in xml <test><class></class></test> --> this class it will get) , 
			// then getRealClass will go to that particular/actual real  class (.java file) from the above class presemt xml file. 
			// from that java class  whatever the filed driver is using get it (whatever the driver the class/test case us using get that driver )
			// we are going class level because fields are part of class level and that driver will passed to below to get the screenshot
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		// Task - take screenshot
		//Step 1 :- take screenshot
		 String filePath = null;
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Step 2: attact it will test case
		
		locals.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName()); 
		// using result(it also holds driver informatin realted to that particular test case ) variable we should be able to get that  driver variable used for test case to run and that driver only we should pass it here
		// result hold all the information about test case
		// it wil take path and titile and before doing this we need to take screenshot
		// it is attaching to extent report
		
		
		//imp:- we should tell xml about the listeners and then only screenshot will be taken
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
		//this is the final method which will be xcuted at the last 
		extentRepo.flush(); // for the report generation
		
	}

}
