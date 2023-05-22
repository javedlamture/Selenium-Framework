package automation.onlineShopping.pageObjects.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	static ExtentReports extent;
	public static ExtentReports getReportObject()
	{
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		
		extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Automation Tester", "Javed Lamture");
		//extent.setSystemInfo("OS", System.getProperty("os.name"));
		//extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		//extent.setSystemInfo("Browser", System.getProperty("Chrome"));*/
		
		
		return extent;
	}

}
