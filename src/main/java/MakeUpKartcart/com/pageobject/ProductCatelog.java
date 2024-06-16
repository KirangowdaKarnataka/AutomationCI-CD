package MakeUpKartcart.com.pageobject;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MakeUpKartcart.com.AbstractComponent.AbstractComponent;


public class ProductCatelog extends AbstractComponent {
	
	// in page object file we don't need to add validation.
	// imp- page object shoule not hold any data and it should hold only objects, locators etc
	// here we are pushing the locators belongs to landing page (email,password) to here
	// and also we need bring dirver this landing page
	
	WebDriver driver; // this is simply null object here and we need to intialize it and real driver present in StandAloneTest.java and we need to bring hee
	 // to intialize that we are going for constructor 
	public  ProductCatelog(WebDriver driver) { // this is the first method to excute when we touch this class
		// constructor is the best place to write the any intialization code
		super(driver); // we are sending this driver to the abstract claqss using super keyword and by extending the abstract classs
		// (FROM HERE WE ARE SENDING DRIVER TO PARENT CLASS )(every child should be using this super keyword)
		this.driver=driver; // craete the object of this class in STandLOng.java	
		PageFactory.initElements(driver, this);
	}
	// Step 1 : webeleemnt
	//option 1
	//List<WebElement> items = driver.findElements(By.cssSelector(".mb-3"));
	//option 2
	// page factory -- using that we can reduce the syntext of creating web element
	@FindBy(css =".mb-3") // at runtime it will be constructed like option 1 using thhis simle FindBy anntatyion in page factory
	// IN order this annotation to know abut the driver , we need to write one strep in constructor (PageFactory.initElements(driver, this);)
	// reason for wrtiting this step inside constriuctor only becauase constructor is first one to excute 
	// IMP- using the initElements() function findby annotation will come to know about the driver
	List<WebElement> product;
	
	@FindBy(css = ".ng-animating") // if it is xpath/css then we simeply write xpath= or css= or className=
	// hover over findBy or ctrl+findby+click
	WebElement animating;
	
	By products = By.cssSelector(".cartSection h3");
	
	
	By productd = By.cssSelector(".mb-3");
	
	
	// Action method  1 :- get the list of webelements
	// we cannot use pagefacory because it is only driver.findeleemnt
	public List<WebElement> getProductList() {
		waitingForElementToAppear(productd);
		return product;
	}
	
	// Action method 2 :- Get the product name
	public WebElement getProductByName(String productName) {
		
		// first pushed all list of items into streams, every iteration one product is rerived and
		//on that prodcut we are going inside that block and get the actual text name of product and if that equls to give text
		//that is what we are filtering.  no mattter how many results we are getting just retrun the first one and store it in 'item' webeleemnt
		// if there is no match then return null	
		WebElement item = getProductList().stream().filter(p-> p.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElseGet(null); 
		// above element 'item' will store tthe product with name 'ADIDAS ORIGINAL'
		
		return item;
		
	}
	
	
	public void addProductToCart(String productName) throws InterruptedException {

		// Step 4 -- adding to cart(add to cart is in 'card-body')(second last button - so last-of-type)(first-of-type - only view will get selected)
		  // scope will be within product only
		WebElement prod = getProductByName(productName);
		By addToCart = By.cssSelector(".card-body button:last-of-type");
		prod.findElement(addToCart).click();
		
		
		// waiting for toast to appear
		By toastmessage = By.cssSelector("#toast-container");
		waitingForElementToAppear(toastmessage);
		
		// waiting to  animating disppear
		//need to verify the dissparancwe(aniated icon dissaoarence) (here we need to check dissaperence means invisiablity)
		//wate.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		// above step is taking more time - perofrmance issue 
		// use below line to overcome above error
		waitingForElementToDisAppear(animating);
		
	}
	

	
	

}
