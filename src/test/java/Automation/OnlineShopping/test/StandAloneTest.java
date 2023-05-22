package Automation.OnlineShopping.test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		//WebDriver driver = WebDriverManager.chromedriver().create();
		WebDriver driver = WebDriverManager.chromedriver().avoidShutdownHook().create();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("javed123@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Welcome@123");
		driver.findElement(By.cssSelector(".btn.btn-block.login-btn")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod =products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
	
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Product Added To Cart']")));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		
		//driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartItems.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		//checkout button
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//Handle dynamic dropdown with Action class
		WebElement countrySelection = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		Actions a = new Actions(driver);
		a.sendKeys(countrySelection, "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted'][2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println("Test Passed");
		
		
		
		
		

	}

}
