package automation.onlineShopping.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.onlineShopping.reusableComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent {

	WebDriver driver;

	// Constructor
	public PaymentPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath ="//input[@placeholder='Select Country']")  
	private WebElement countryEditBox;
	
	private By countrySelectionToAppear = By.cssSelector(".ta-results");
	
	@FindBy(xpath ="//button[@class='ta-item list-group-item ng-star-inserted'][2]")
	private WebElement clickOnCountry;
	
	@FindBy(css =".action__submit")
	private WebElement placeOrder;
	
	
	public void selectCountry(String countryName) {
		
		Actions a = new Actions(driver);
		a.sendKeys(countryEditBox, countryName).build().perform();
		waitElementToAppear(countrySelectionToAppear);
		clickOnCountry.click();
		
	}
	
	public ConfirmationPage placeOrder() {
		waitUntilWebElementClickable(placeOrder);
		placeOrder.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

}
