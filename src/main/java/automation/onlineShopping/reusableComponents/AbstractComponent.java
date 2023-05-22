package automation.onlineShopping.reusableComponents;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import automation.onlineShopping.pageObjects.CartPage;
import automation.onlineShopping.pageObjects.OrderHistoryPage;

public class AbstractComponent{

	WebDriver driver;
	public static Properties prop;
	
	
	
	
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	private WebElement clickOnCart;
	
	@FindBy(xpath = "//button[contains(@routerlink,'myorders')]")
	private WebElement myOrdres;
	

	public void waitListOfElementsToAppear(List<WebElement> list) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(list));
	}
	public void waitElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement web) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(web));
	}
	
	public void waitUntilWebElementClickable(By eleClick) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(eleClick));
	}
	
	public void waitUntilWebElementClickable(WebElement eleClick) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(eleClick));
	}


	public void waitElementToDisappear(WebElement element) throws InterruptedException {
		Thread.sleep(1000);
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// wait.until(ExpectedConditions.invisibilityOf(element));

	}

	public CartPage goToCartPage() {
		clickOnCart.click();
		
		
		CartPage cartPage = new CartPage(driver);
		
		return cartPage;
	}
	
	public OrderHistoryPage goToOrderPage() 

	{
		waitUntilWebElementClickable(myOrdres);
		myOrdres.click();
		OrderHistoryPage orderHistory = new OrderHistoryPage(driver);
		return orderHistory;
	}
	

}
