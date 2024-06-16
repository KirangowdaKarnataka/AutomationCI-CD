package MakeUpKartcart.com.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MakeUpKartcart.com.pageobject.CartPage;
import MakeUpKartcart.com.pageobject.OrderPage;

public class AbstractComponent {
	
	//AbstractComponent is the Base PageObject file

	// This ABstarct class will become parent class for all the page object class
	// like landingpage.java, productcatelog.java etc
	// because it holds all reusable stufff(instead of creating the object which
	// will overhead memory, we are going for inheretence)
	// this class is holding the reusable code which is required in the application
	// ex:-

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this); // to activate pagefactory
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;
	
	
	
	

	// Step 1
	// to wait for loading of all the items on the page(best way to avoid
	// syncronisation issues )
	public void waitingForElementToAppear(By findBy) {

		WebDriverWait wate = new WebDriverWait(driver, Duration.ofSeconds(10));

		wate.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitingForElementToDisAppear(WebElement ele) {

		WebDriverWait wate = new WebDriverWait(driver, Duration.ofSeconds(10));
		wate.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void waitForElementToAppear(WebElement ele) {

		WebDriverWait wate = new WebDriverWait(driver, Duration.ofSeconds(10));
		wate.until(ExpectedConditions.visibilityOf(ele));
	}
	
	// here we are defining cart selection because this header is same for all the pages
	
	public CartPage gotoCartPage() {
		
		cartHeader.click();
		
		// to overcome the memory overhead, we are creating the object here itself and we already know that after click on cart =header it will goto cart oage
		// so we are creating object here itselef.
		CartPage cartpage = new CartPage(driver);
		return cartpage;
		
		
	}
	
	public OrderPage gotoOrderPage() {
		
		orderHeader.click();
		
		// to overcome the memory overhead, we are creating the object here itself and we already know that after click on cart =header it will goto cart oage
		// so we are creating object here itselef.
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
		
		
	}
	


}
