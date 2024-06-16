package MakeUpKartcart.com.resources;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	static ExtentReports extentRepo;
	
	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir")+"//reportst//index.html"; // project path until extent reports folder
		ExtentSparkReporter reporter = new ExtentSparkReporter(path); // it is repsoible for creating html file and do some configuration
		reporter.config().setReportName("Web Automation Result");
		reporter.config().setDocumentTitle("Test Result");
		
		
		extentRepo = new ExtentReports(); //create and consildate our report excution 
		extentRepo.attachReporter(reporter); // this class have knowlege of main clkass (matadata like reportname and title etc )
		extentRepo.setSystemInfo("Tester", "Abhijith GowdaS");
		
		//option - 1 -- but it is not suitable because we cannot go write this (menas extentRepo.createTest(path))  in each and every testcases 
		//extentRepo.createTest(path);  // this will create an ery for test an it wil show in HTML reports
		// option 2 -- testng listeners (refer listeners class)
		// write the above code in listenertrs class (mainly inside onTestStart(ITestResult result) method)
		
		return extentRepo;
		
		
	}

}
