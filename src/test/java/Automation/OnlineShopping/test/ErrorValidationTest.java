package Automation.OnlineShopping.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
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
import automation.onlineShopping.pageObjects.PaymentPage;
import automation.onlineShopping.pageObjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationTest extends BaseTestUtility {
	
	
	@Test(dataProvider="getData",groups= {"ErrorHandling"})
	public void LoginErrorMessageValidation(String username, String password) throws IOException, InterruptedException {
	
		//landingPage.userLogin("javed13@gmail.com", "Welcome@123");
		landingPage.userLogin(username, password);
		Assert.assertEquals("Incorrect email or password.",landingPage.verifyErrorMessage());
	

		System.out.println("Test Failed");
		
		
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		
		String username = prop.getProperty("userID");
		String password = prop.getProperty("userPassword");
		ProductCatalogue ProductCatalogueList = landingPage.userLogin(username, password);
		
		//ProductCatalogue ProductCatalogueList = landingPage.userLogin("javed123@gmail.com", "Welcome@123");
		List<WebElement> products = ProductCatalogueList.getProductLists();
		ProductCatalogueList.addProductToCart(productName);
		CartPage cartPage = ProductCatalogueList.goToCartPage();
		Boolean match = cartPage.verifyProductDisplayOnCartpage("ZARA COAT 33");
		Assert.assertFalse(match);
		
	
		
		
	}
	
	@DataProvider
	public Object[][] getData() 
	{
		Object[][] data=new Object[2][2];
		data[0][0]="Sandeep.patil@gmail.com";
		data[0][1]="9754879954";
		
		data[1][0]="javedlamture@yahoo.com";
		data[1][1]="67890";
		
		return data;
	}

}
