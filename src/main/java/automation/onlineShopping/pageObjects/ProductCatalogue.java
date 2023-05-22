package automation.onlineShopping.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.onlineShopping.reusableComponents.AbstractComponent;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	//Constructor
		public ProductCatalogue(WebDriver driver) {
			// TODO Auto-generated constructor stub
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		
		
		@FindBy(css=".mb-3")
		private List<WebElement> productsList;
		
		private By productBy = By.cssSelector(".mb-3");
		
		private By addToCart = By.cssSelector(".card-body button:last-of-type");
		
		//WebElement addToCart =driver.findElement(By.cssSelector(".card-body button:last-of-type"));
		
		private By toasterPopUpVisibility = By.xpath("//div[@aria-label='Product Added To Cart']");
		
		@FindBy(css=".ng-animating")
		private WebElement animationSpiner;
		
		
		
		public List<WebElement> getProductLists() {
			
			waitElementToAppear(productBy);
			return productsList;
		}
		
		public WebElement getProductByName(String productName) {
			
			WebElement prod =getProductLists().stream().filter(product->
			product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
			return prod;
			
		}
	
		
	 public void addProductToCart(String productName) throws InterruptedException {
		 
		 	WebElement prod = getProductByName(productName);
		 	waitUntilWebElementClickable(addToCart);
		 	
			prod.findElement(addToCart).click();
			waitElementToAppear(toasterPopUpVisibility);
			waitElementToDisappear(animationSpiner);
	 }
	 
	
}
