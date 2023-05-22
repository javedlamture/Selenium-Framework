package automation.onlineShopping.pageObjects;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.onlineShopping.reusableComponents.AbstractComponent;

import io.github.bonigarcia.wdm.WebDriverManager;


public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	//Constructor
		public LandingPage(WebDriver driver) {
			// TODO Auto-generated constructor stub
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		
		//WebElement email = driver.findElement(By.id("userEmail"));
		@FindBy(id=("userEmail"))
		private WebElement userEmail;
		
		@FindBy(id=("userPassword"))
		private WebElement userPassword;
		
		@FindBy(css=(".btn.btn-block.login-btn"))
		private WebElement userLoginButton;
		
		@FindBy(css="div[aria-label='Incorrect email or password.']")
		private WebElement ErrorMessage;
		
		
		
		public ProductCatalogue userLogin(String username, String userpassword)
		{
			userEmail.sendKeys(username);
			userPassword.sendKeys(userpassword);
			waitUntilWebElementClickable(userLoginButton);
			//userLoginButton.click();
			
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", userLoginButton);
			ProductCatalogue ProductCatalogueList = new ProductCatalogue(driver);
			return ProductCatalogueList;
			
		}
		
		public void goTo() throws IOException
		{
			
			
			driver.get("https://rahulshettyacademy.com/client");
		
			
		}
		
		public String verifyErrorMessage() {
			
			waitForWebElementToAppear(ErrorMessage);
			String errorMessage = ErrorMessage.getText();
			return errorMessage;
		}
}
