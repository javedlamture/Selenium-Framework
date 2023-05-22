package automation.onlineShopping.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.onlineShopping.reusableComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;

	// Constructor
	public ConfirmationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = (".hero-primary"))
	private WebElement orderMessage;
	
	public String verifyOrderConfirmationMessage() {
		waitForWebElementToAppear(orderMessage);
		String confirmationMessage = orderMessage.getText();
		return confirmationMessage;
	}
	
	

}
