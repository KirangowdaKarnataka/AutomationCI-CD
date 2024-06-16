package MakeUpKartcart.com.SeleniumDeignsFramework.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import MakeUpKartcart.com.SeleniumDeignsFramework.TestComponent.BaseTest;
import MakeUpKartcart.com.SeleniumDeignsFramework.TestComponent.RetryAnalyzerDemo;
import MakeUpKartcart.com.pageobject.CartPage;
import MakeUpKartcart.com.pageobject.ProductCatelog;

public class ErrorValidationTest extends BaseTest // for inheretince
{

	
	@Test(groups = "ErrorHandling")
	public void productErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		String expectedProduct = "ADIDAS ORIGINAL";
        // to overcome the memory overhead we creating object method itself(landing page)
		ProductCatelog product = page.loginApplication("MaheshKamath@outlook.com", "9480QWERTYaz"); // enter username,
																									// password and
																									// click on login
		List<WebElement> pros = product.getProductList();
		product.addProductToCart(expectedProduct);
		CartPage cartpages = product.gotoCartPage(); 
		boolean match = cartpages.verifyProductDisplay("ADIDAS ORIGINALs");
		Assert.assertFalse(match);
		

	}
	
	
	@Test(groups = "ErrorHandling",retryAnalyzer = RetryAnalyzerDemo.class)
	// after this it will check any annptations like Beforemthod,aftermethod etc.
	// after that below method will be excuted
	// it scans both parent and child class as well
	public void loginErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		String expectedProduct = "ADIDAS ORIGINAL";
		// to overcome the memory overhead we creating object method itself(landing
		// page)
		page.loginApplication("Maheshreaddy@outlooky.com", "9480QWERTYaz"); // enter username,
																			// password and
																			// click on login
		// div[@aria-label='']
		Assert.assertEquals("Incorrect email or password.", page.getErrorMessages());

	}
	

	
	

}
