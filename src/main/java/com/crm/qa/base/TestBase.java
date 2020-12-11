package com.crm.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	public TestBase() {
		try { // To load the .property file
			prop = new Properties();

			FileInputStream ip = new FileInputStream(
					"/Users/n/eclipse-workspace/WebDriverPracticeByNaveen/FreeCRMTest/src/main/java/com"
							+ "/crm/qa/config/config.properties");

			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

//	SingleTon DesignPattern

	public static void initialization() {
		String BroswerName = prop.getProperty("browser");

		if (BroswerName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\n\\Desktop\\chromedriver_win32\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.setCapability("capability_name", "capability_value");
			options.addArguments("disable-popup-blocking");
			options.addArguments("test-type");
			options.addArguments("start-maximized");
			options.addArguments("disable-infobars");
			options.addArguments("--no-sandbox");
			driver = new ChromeDriver(options);
			// C:\\Users\n\Desktop\chromedriver_win32
			// C:\\Users\\n\\Desktop\\Swapnil\\chromedriver_win32\\chromedriver.exe

		} else if (BroswerName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\n\\Desktop\\Swapnil\\geckodriver\\geckodriver.exe");
			driver = new FirefoxDriver();

		}

		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		// driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUTS, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));

	}
}
