package automation.onlineShopping.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.onlineShopping.reusableComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	
	
	//Constructor
		public CartPage(WebDriver driver) {
			// TODO Auto-generated constructor stub
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(css=".cartSection h3")
		private List<WebElement> ListOfAllCartItems; 
		
		@FindBy(css=".totalRow button")
		private WebElement ClickOnCheckout; 
		
		public List<WebElement> getListOfAllCartItems() {
			
			return ListOfAllCartItems;
		}
		
		public Boolean verifyProductDisplayOnCartpage(String productName) {
			
			waitListOfElementsToAppear(ListOfAllCartItems);
			
			Boolean match = ListOfAllCartItems.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
			
			return match;
		}
		
		public PaymentPage checkoutButton(){
			waitUntilWebElementClickable(ClickOnCheckout);
			ClickOnCheckout.click();
			PaymentPage paymentPage = new PaymentPage(driver);
			return paymentPage;
		}
		
		
}
