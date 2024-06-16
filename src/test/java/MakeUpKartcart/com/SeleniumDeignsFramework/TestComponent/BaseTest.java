package MakeUpKartcart.com.SeleniumDeignsFramework.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import MakeUpKartcart.com.pageobject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver; // no intialisation for this drriver
	public LandingPage page;

	// Code to intialize Driver () and setup global properttties to invoke
	// respective browser
	// Step 1 :- intialize driver
	public WebDriver intializeDriver() throws IOException {

		// In java we have class called propetties (runtime it will decide which browser
		// to excute)
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir")+"/src/main/java/MakeUpKartcart/com/resources/GlobalData.properties");
		prop.load(fis); // file need to be sent on inppit stream
		
		//setting maven commands for browser (if browser propettie is not set then set the propeetes mentioned in mavn command) using ternary
		String browserName =  System.getProperty("browser") != null ? System.getProperty("browser") :prop.getProperty("browser") ;
		
		//here browser data is reading from the propetties file 
		//prop.getProperty("browser");
		if (browserName.contains("chrome")) {
			
			
			ChromeOptions option = new ChromeOptions();
			
			WebDriverManager.chromedriver().setup(); // chrome driver automatically downloadded into system based on
														// chrome
			// version
			
			if (browserName.contains("headless")) {
			option.addArguments("headless");
			}
			driver = new ChromeDriver(option);
			driver.manage().window().setSize(new Dimension(1440, 900));

		} else if (browserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup(); // chrome driver automatically downloadded into system based on
			// chrome
// version
			driver = new FirefoxDriver();

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}
	
	@BeforeMethod(alwaysRun = true)
	// Step 2 : launch application (which is common to all test cases)
	public LandingPage launchWebApplication() throws IOException {

		driver = intializeDriver();
		// hitting the URL -- make it generic

       // Task 1 - login
       //driver.get("https://rahulshettyacademy.com/client");
		page = new LandingPage(driver); // we are assigning separeate driver for each and every test cases
		page.goTo(); // first goto landing page
		
		return page;

	}
	
	
	
	// Method to convert json to list and send it back to function (generic function)
	public List<HashMap<String, String>> getJSONData(String filePath) throws IOException {
		// scan the sntire json and cobert it into JSON
		// read json to string
		String jsonData = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		// Convert String to hashmap using Jackson DataBind (depedendency)
		ObjectMapper mapper = new ObjectMapper();
		// in this calss we have method called readValue (read the string and convert to json) 
		// readValue method (it accpets two arguments, 1. json string and 2.how we wnat convert it )
		//we have two arrays/indexes in json file and go ahead and create  hashmaps based on number of indexes/arrays (in our case two arrays)
		// for two arrays/indexes two haspmaps will be created
		//and put these two hashmaps into list
		// after putting it in list , retun that list
		List<HashMap<String, String>> data = mapper.readValue(jsonData, new TypeReference<List<HashMap<String,String>>>(){} );
		
		return data;
		
	}
	
	//take screenshot
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot screenShot =  (TakesScreenshot)driver;
		File source =  screenShot.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports// "+ testCaseName +" .png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports// "+ testCaseName +" .png"; // returYing path where screenshot is stored
	}
	
	


}
