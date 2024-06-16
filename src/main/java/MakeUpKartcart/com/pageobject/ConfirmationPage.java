package MakeUpKartcart.com.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import MakeUpKartcart.com.AbstractComponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	
	WebDriver driver; // this is simply null object here and we need to intialize it and real driver present in StandAloneTest.java and we need to bring hee
	 // to intialize that we are going for constructor 
	public  ConfirmationPage(WebDriver driver) { // this is the first method to excute when we touch this class
		// constructor is the best place to write the any intialization code
		super(driver); // we are sending this driver to the abstract claqss using super keyword and by extending the abstract classs
		// (FROM HERE WE ARE SENDING DRIVER TO PARENT CLASS )
		this.driver=driver; // craete the object of this class in STandLOng.java	
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;
	
	public String getConfirmMessage() {
		
		//CheckOutPage checkOut = new CheckOutPage(driver);
		//checkOut.selectCount;
		
		
		return  confirmMessage.getText();

	}

}
