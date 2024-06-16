package MakeUpKartcart.com.pageobject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MakeUpKartcart.com.AbstractComponent.AbstractComponent;


public class LandingPage extends AbstractComponent {
	
	// extends (instead of creating the object which will overhead memory, we are going for inheretence)
	//assert validation need to be done on testcase page only not on pageobject file(pageobject file is menat only for actions)
	
	// imp- page object shoule not hold any data and it should hold only objects, locators etc
	// imp- we cannot applay page factory within webelement.findelement
	// here we are pushing the locators belongs to landing page (email,password) to here
	// and also we need bring dirver this landing page
	
	WebDriver driver; // this is simply null object here and we need to intialize it and real driver present in StandAloneTest.java and we need to bring hee
	 // to intialize that we are going for constructor 
	public  LandingPage(WebDriver driver) { // this is the first method to excute when we touch this class
		// constructor is the best place to write the any intialization code
		super(driver); // we are sending this driver to the abstract claqss using super keyword and by extending the abstract classs
		// (FROM HERE WE ARE SENDING DRIVER TO PARENT CLASS )
		this.driver=driver; // craete the object of this class in STandLOng.java	
		PageFactory.initElements(driver, this);
	}
	// Step 1 : webeleemnt
	//option 1
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//option 2
	// page factory -- using that we can reduce the syntext of creating web element
	@FindBy(id="userEmail") // at runtime it will be constructed like option 1 using thhis simle FindBy anntatyion in page factory
	// IN order this annotation to know abut the driver , we need to write one strep in constructor (PageFactory.initElements(driver, this);)
	// reason for wrtiting this step inside constriuctor only becauase constructor is first one to excute 
	// IMP- using the initElements() function findby annotation will come to know about the driver
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login") // if it is xpath/css then we simeply write xpath= or css= or className=
	// hover over findBy or ctrl+findby+click
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	

	
	
	// Step 2 - Action method
	// Action :- click on login button in order access application (action to be performed)
	public ProductCatelog loginApplication(String userEmail, String pass) {
		email.sendKeys(userEmail);
		password.sendKeys(pass);
		submit.click(); 
		// TO overcome memory overhead we are creating the object here only
		ProductCatelog productss = new ProductCatelog(driver);
		return productss;
		
	
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	public String getErrorMessages() {
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
	

}
