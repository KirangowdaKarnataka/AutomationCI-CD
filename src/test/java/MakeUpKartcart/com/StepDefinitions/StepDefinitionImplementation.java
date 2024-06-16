package MakeUpKartcart.com.StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import MakeUpKartcart.com.SeleniumDeignsFramework.TestComponent.*;
import MakeUpKartcart.com.pageobject.CartPage;
import MakeUpKartcart.com.pageobject.CheckOutPage;
import MakeUpKartcart.com.pageobject.ConfirmationPage;
import MakeUpKartcart.com.pageobject.LandingPage;
import MakeUpKartcart.com.pageobject.ProductCatelog;

public class StepDefinitionImplementation  extends BaseTest{
	
	
	public LandingPage landPage;
	public ProductCatelog product;
	public ConfirmationPage confirmPag;
	
	// here we have 4 step defintions/methods
	// and one pre-requiste 
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException{
		// this code will be excute whenever the 'Given' is executed and checks for matching pattern also
		landPage = launchWebApplication(); //calling the launch application method
		
	}
	
	@Given("^Logged In with (.+) and password (.+)$")
	public void loggedin_username_and_password(String username, String password) {
		
		product = page.loginApplication(username, password);
		
		
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productname) throws InterruptedException {
		List<WebElement> pros = product.getProductList();
		product.addProductToCart(productname);		
	}
	
	// And Checkout <productname> and submit the order
	// this 'And' is conjuction / related to previous step
	@And("^Checkout (.+) and submit the order$")
	public void checkout_and_submit(String productname) {
		
		CartPage cartpages = product.gotoCartPage(); 
		boolean match = cartpages.verifyProductDisplay(productname);
		Assert.assertTrue(match);
		CheckOutPage checkOutPa = cartpages.goToCheckOut();
		checkOutPa.selectCountry("india");
		confirmPag = checkOutPa.submitOrder();
		
	}
	
	//Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page
	// above we have static keyword and (.+) will not work here because this will be appolicable only for data coming from Examples table
	@Then("{string} message is displayed on confirmation page")
	public void messsgage_dsplayed_confirmation_page(String confirm) {
		String confirmMessage = confirmPag.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(confirm));
	}
	
	@Then("{string} message is displayed ")
	public void messgage_is_displayed(String errors) throws InterruptedException {
		Assert.assertEquals(errors, landPage.getErrorMessages());
		driver.close();
	}

}
