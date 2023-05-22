package Automation.OnlineShopping.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Automation.OnlineShopping.reusableTestComponent.BaseTestUtility;
import automation.onlineShopping.pageObjects.CartPage;
import automation.onlineShopping.pageObjects.ConfirmationPage;
import automation.onlineShopping.pageObjects.LandingPage;
import automation.onlineShopping.pageObjects.OrderHistoryPage;
import automation.onlineShopping.pageObjects.PaymentPage;
import automation.onlineShopping.pageObjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTestUtility {

	// public String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		// String username = prop.getProperty("userID");
		// String password = prop.getProperty("userPassword");
		ProductCatalogue ProductCatalogueList = landingPage.userLogin(input.get("email"), input.get("password"));

		// ProductCatalogue ProductCatalogueList =
		// landingPage.userLogin("javed123@gmail.com", "Welcome@123");
		List<WebElement> products = ProductCatalogueList.getProductLists();
		ProductCatalogueList.addProductToCart(input.get("productName"));
		CartPage cartPage = ProductCatalogueList.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplayOnCartpage(input.get("productName"));
		
		Assert.assertTrue(match);
		PaymentPage paymentPage = cartPage.checkoutButton();
		paymentPage.selectCountry("india");
		ConfirmationPage confirmationPage = paymentPage.placeOrder();
		String confirmationMessage = confirmationPage.verifyOrderConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistory() throws InterruptedException {

		String username = prop.getProperty("userID");
		String password = prop.getProperty("userPassword");
		ProductCatalogue ProductCatalogueList = landingPage.userLogin(username, password);
		OrderHistoryPage orderHistory = ProductCatalogueList.goToOrderPage();
		Assert.assertTrue(orderHistory.verifyOrderNameDisplay("ZARA COAT 3"));
	}

	/*
	 * @DataProvider public Object[][] getData() { return new Object[][]
	 * {{"javed123@gmail.com", "Welcome@123", "ZARA COAT 3"}, {"javed786@gmail.com",
	 * "Welcome@786", "IPHONE 13 PRO"}}; }
	 */

	/*
	 * @DataProvider public Object[][] getData() { HashMap<String, String> map = new
	 * HashMap<String, String>(); map.put("email", "javed123@gmail.com");
	 * map.put("password", "Welcome@123"); map.put("productName", "ZARA COAT 3");
	 * 
	 * HashMap<String, String> map1 = new HashMap<String, String>();
	 * map1.put("email", "javed786@gmail.com"); map1.put("password", "Welcome@786");
	 * map1.put("productName", "IPHONE 13 PRO");
	 * 
	 * return new Object[][] {{map}, {map1}}; }
	 */

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")
				+ "\\src\\test\\java\\Automation\\OnlineShopping\\Data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
