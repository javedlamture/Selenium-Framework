package Automation.OnlineShopping.reusableTestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import automation.onlineShopping.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestUtility {

	public static WebDriver driver;

	public LandingPage landingPage;

	public static Properties prop;

	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\automation\\onlineShopping\\pageObjects\\resources\\GlobalData.properties");
		prop.load(fis);

		// sending browser name from jenkins or command propmpt
		String browserName =System.getProperty("browser") !=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) 
		{
			// driver = WebDriverManager.chromedriver().create();

			driver = WebDriverManager.chromedriver().avoidShutdownHook().create();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		} 
		else if (browserName.equalsIgnoreCase("firefox")) 
		{
			driver = WebDriverManager.firefoxdriver().avoidShutdownHook().create();

			// WebDriverManager.firefoxdriver().avoidShutdownHook().create();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		} 
		else if (browserName.equalsIgnoreCase("edge")) 
		{
			driver = WebDriverManager.edgedriver().create();

			// WebDriverManager.edgedriver().avoidShutdownHook().create();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		} 
		else if (browserName.contains("headless")) 
		{
			ChromeOptions option = new ChromeOptions();
			if (browserName.contains("headless")) 
			{
				option.addArguments("--window-size=1920,1080");
				option.addArguments("--start-maximized");
				option.addArguments("headless");
			}
			driver = WebDriverManager.chromedriver().capabilities(option).avoidShutdownHook().create();
		}

		driver.manage().window().maximize();
		return driver;

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// Read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// Convert String to HashMap

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {

		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		// TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";

		FileUtils.copyFile(source, new File(destinationFile));

		return destinationFile;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
