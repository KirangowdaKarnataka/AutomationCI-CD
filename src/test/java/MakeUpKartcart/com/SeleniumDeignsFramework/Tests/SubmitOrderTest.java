package MakeUpKartcart.com.SeleniumDeignsFramework.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MakeUpKartcart.com.SeleniumDeignsFramework.TestComponent.BaseTest;
import MakeUpKartcart.com.pageobject.CartPage;
import MakeUpKartcart.com.pageobject.CheckOutPage;
import MakeUpKartcart.com.pageobject.ConfirmationPage;
import MakeUpKartcart.com.pageobject.OrderPage;
import MakeUpKartcart.com.pageobject.ProductCatelog;

public class SubmitOrderTest extends BaseTest // for inheretince
{
	String expectedProduct = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = {"PurchaseOrder"})
	// after this it will check any annptations like Beforemthod,aftermethod etc.
	// after that below method will be excuted
	// it scans both parent and child class as well
	// providing the data from getData() method using dataprovider attribute
	public void submitOrders(HashMap<String, String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		
        // to overcome the memory overhead we creating object method itself(landing page)
		ProductCatelog product = page.loginApplication(input.get("email"), input.get("password")); // enter username,
																									// password and
																									// click on login
		List<WebElement> pros = product.getProductList();
		product.addProductToCart(input.get("productname"));
		CartPage cartpages = product.gotoCartPage(); 
		boolean match = cartpages.verifyProductDisplay(input.get("productname"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPa = cartpages.goToCheckOut();
		checkOutPa.selectCountry("india");
		ConfirmationPage confirmPag = checkOutPa.submitOrder();
		String confirmMessage = confirmPag.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	//Dependency Strategy
	// Task :- verify the ordered product in order page is present or not (placed)
	// this test is completely depend on previous test case (submitOrders()) result(after orderinmg product we need to check oder history)
	// run this dependencyOrderHistory() after submitOrders() test - using dependsOnMethods attribute in test annotation
	// now what happens is that when we asked run this test first it make sure submitOrders() ran
	@Test(dependsOnMethods = {"submitOrders"})
	public void dependencyOrderHistory() {
		
		ProductCatelog product = page.loginApplication("sureshreaddy@gmail.com", "9480QWERTYaz");
		OrderPage order = product.gotoOrderPage();
		Assert.assertTrue(order.verifyOrderDisplay(expectedProduct));
		
		
		
	}
	
	@DataProvider
	//this is a two dimensional data and we are going object because it ism the super class of all the other data types (generic data type)
	//Data conversion
	// option 1 -- sending datas sets in Object class in two dimensional array (disad -- two many parametrts on methods)
	// option 2 -- sending data in hashmap (key,value)
	// option 3 -- sending data in json
	public Object[][] getData() throws IOException {
		//sequentially data sets will be sent not parallely
		// Using Hasmap to send multiple data in key value pair
		//option 2
/*		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "sureshreaddy@gmail.com");
		map.put("password", "9480QWERTYaz");
		map.put("productname", "ADIDAS ORIGINAL");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map.put("email", "MaheshKamath@outlook.com");
		map.put("password", "9480QWERTYaz");
		map.put("productname", "IPHONE 13 PRO");
		
		*/
		
		List<HashMap<String, String>> data =   getJSONData(System.getProperty("user.dir")+"//src//test//java//MakeUpKartcart//com//Data//PuchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1) }};
		
	}
	

	
	//Extent Reports - 
	
	
	

}
