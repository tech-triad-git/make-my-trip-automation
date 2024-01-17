package com.userdefinedlibraries;



import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSetup {

	public static Properties prop = GetPropertiesFile.getPropertiesInstance();//CREATING OBJECT OF PROPERTY FILE
	public static WebDriver driver;
	public static String exePath;
	public static String url = prop.getProperty("websiteURL");//GETTING THE URL FROM PROPERTY FILE
	public static String browsertype;
	

	public static WebDriver driverInstantiate(String browser) {
		browsertype = browser;

		// instantiate chrome browser
		if (browsertype.equalsIgnoreCase("chrome")) {
			exePath = System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", exePath);
			driver = new ChromeDriver();
		}

		// instantiate firefox browser
		else if (browser.equalsIgnoreCase("firefox")) {
			exePath = System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", exePath);
			driver = new FirefoxDriver();
		}
		else if (browser.equalsIgnoreCase("edge")) {
			exePath = System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\msedgedriver.exe";
			System.setProperty("webdriver.msedge.driver", exePath);
			driver = new EdgeDriver();
		}
		
		else if(browser.equalsIgnoreCase("chromeGrid")){
			DesiredCapabilities dr=null;
	        dr=DesiredCapabilities.chrome();
	        dr.setBrowserName("chrome");
	        dr.setPlatform(Platform.WINDOWS);
	             
	        try {
				driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dr);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

		return driver;

	}

	public static void driverClose() {
		driver.quit();
	}

}
