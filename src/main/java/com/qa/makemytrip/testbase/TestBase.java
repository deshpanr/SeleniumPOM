package com.qa.makemytrip.testbase;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.qa.makemytrip.utils.TestUtils;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase() throws IOException {
		FileInputStream file=new FileInputStream("C:\\Users\\deshpanr\\eclipse-workspace\\seleniumpom\\SeleniumAssignment2\\config.properties");
		prop=new Properties();
		prop.load(file);
	}
	
	public void initionalization() {
		if(prop.getProperty("Browser").equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions(); 
			options.addArguments("--incognito"); 
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
			driver.get(prop.getProperty("URL"));
			driver.manage().deleteAllCookies();
		}
	}

}
