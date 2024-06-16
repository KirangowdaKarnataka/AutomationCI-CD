package MakeUpKartcart.com.SeleniumDeignsFramework.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import MakeUpKartcart.com.pageobject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		// thanks for your service
		
		//Help to find the check list 
		String expectedProduct = "ADIDAS ORIGINAL";

		WebDriverManager.chromedriver().setup(); // chrome driver automatically downloadded into system based on chrome
													// version
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Task 1 - login
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("sureshreaddy@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("9480QWERTYaz");
		driver.findElement(By.id("login")).click();
		

		// to wait for loading of all the items on the page(best way to avoid syncronisation issues )
		WebDriverWait wate = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		wate.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	
		//
		// Task  2 - Add to particular cart 
		// step 1 - Get the list of items
		// step 2 - iterate through all the itmes in the list
		// step 3 - verfiy the product name with the list of items
		// step 4 - capture the matched product  and click on add to cart 
		
		// step 1 - Get the list of items(first  filter the product with macthing name
		List<WebElement> items = driver.findElements(By.cssSelector(".mb-3"));
		
		// first pushed all list of items into streams, every iteration one product is rerived and
		//on that prodcut we are going inside that block and get the actual text name of product and if that equls to give text
		//that is what we are filtering.  no mattter how many results we are getting just retrun the first one and store it in 'item' webeleemnt
		// if there is no match then return null	
		WebElement item = items.stream().filter(p-> p.findElement(By.cssSelector("b")).getText().equals(expectedProduct)).findFirst().orElseGet(null); 
		// above element 'item' will store tthe product with name 'ADIDAS ORIGINAL'
		
		// Step 4 -- adding to cart(add to cart is in 'card-body')(second last button - so last-of-type)(first-of-type - only view will get selected)
		item.findElement(By.cssSelector(".card-body button:last-of-type")).click();  // scope will be within product only
		
		
		
		// Note
		//here search scope is inside this card section only  (inside that card it will check for b tag ) and get the text on b tag and match with the give text
		// if they matches return the given element
		
		// task 3 - verify that messgae is displayed and after that verify that product is added to cart
		//(after sucessfull adding to cart) (wait till loading icon get invisiable
        //('toast-container')(wait until tat toast get displayed)
		// here we are using explicit wait (targetting only particulat thing)
	//	WebDriverWait wate = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//next step (wait until on expected condition)(wait until toast container is displayed and then confirm with message and
		//proceed to cart and need to verify the dissparancwe)
		
		// waiting for toast to appear
		wate.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//need to verify the dissparancwe(aniated icon dissaoarence) (here we need to check dissaperence means invisiablity)
		//wate.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		// above step is taking more time - perofrmance issue 
		// use below line to overcome above error
		wate.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
				
		
		
		//
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		
		
		// Task :- verify the product from the cart page(putting the validation)
		// scan the cart page to verify the added product.
		// First get items present on cart page into list
		List<WebElement> cartItem = driver.findElements(By.cssSelector(".cartSection h3"));
		// return boolena value if it matches present in the above list
		boolean match = cartItem.stream().anyMatch(a->a.getText().equalsIgnoreCase(expectedProduct));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		// after clicking, it needs some time to load the pop-up so we need to add below below explicit code
		wate.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		//step to select the second option(india comes in second option)
		// wrap the xpath and select the 2 index
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		// next page for details of order
		String confirmMessage =  driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		Thread.sleep(5000);	
		driver.close();

		
		
		

	}

}
