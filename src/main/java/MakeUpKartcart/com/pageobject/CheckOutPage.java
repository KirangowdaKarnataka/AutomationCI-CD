package MakeUpKartcart.com.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import MakeUpKartcart.com.AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	
	WebDriver driver; // this is simply null object here and we need to intialize it and real driver present in StandAloneTest.java and we need to bring hee
	 // to intialize that we are going for constructor 
	public  CheckOutPage(WebDriver driver) { // this is the first method to excute when we touch this class
		// constructor is the best place to write the any intialization code
		super(driver); // we are sending this driver to the abstract claqss using super keyword and by extending the abstract classs
		// (FROM HERE WE ARE SENDING DRIVER TO PARENT CLASS )
		this.driver=driver; // craete the object of this class in STandLOng.java	
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement checkOut;
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	private WebElement selectCountry;
	
	@FindBy(css = ".action__submit")
	WebElement actSub;
	

	
	By result = By.cssSelector(".ta-results");
	
	public void selectCountry( String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(checkOut, "india").build().perform();
		waitingForElementToAppear(result);
		selectCountry.click();
		
	}
	
	
	public ConfirmationPage submitOrder() {
		
		actSub.click();
	   return new ConfirmationPage(driver);
	}
	
	
	

}
