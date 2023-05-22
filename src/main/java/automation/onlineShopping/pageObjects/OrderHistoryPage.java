package automation.onlineShopping.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.onlineShopping.reusableComponents.AbstractComponent;

public class OrderHistoryPage extends AbstractComponent {

	WebDriver driver;

	// Constructor
	public OrderHistoryPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//tbody/tr/td[2]")
	private List<WebElement> orderNames;

	public Boolean verifyOrderNameDisplay(String productName) {
		waitListOfElementsToAppear(orderNames);
		Boolean match = orderNames.stream().anyMatch(orderName -> orderName.getText().equalsIgnoreCase(productName));

		return match;
	}
}
