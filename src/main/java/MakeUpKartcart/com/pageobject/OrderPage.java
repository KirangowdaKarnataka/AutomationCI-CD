package MakeUpKartcart.com.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MakeUpKartcart.com.AbstractComponent.AbstractComponent;

public class OrderPage extends AbstractComponent {
	
	WebDriver driver; // this is simply null object here and we need to intialize it and real driver present in StandAloneTest.java and we need to bring hee
	 // to intialize that we are going for constructor 
	public  OrderPage(WebDriver driver) { // this is the first method to excute when we touch this class
		// constructor is the best place to write the any intialization code
		super(driver); // we are sending this driver to the abstract claqss using super keyword and by extending the abstract classs
		// (FROM HERE WE ARE SENDING DRIVER TO PARENT CLASS )(every child should be using this super keyword)
		this.driver=driver; // craete the object of this class in STandLOng.java	
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> element;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	public Boolean verifyOrderDisplay(String productName) {
		// Task :- verify the product from the cart page(putting the validation)
		// scan the cart page to verify the added product.
		// First get items present on cart page into list
		boolean match = element.stream().anyMatch(a->a.getText().equalsIgnoreCase(productName));
		// return boolena value if it matches present in the above list
		return match;
	}
	

	

}
