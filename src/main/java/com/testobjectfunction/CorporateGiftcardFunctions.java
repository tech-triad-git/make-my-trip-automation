package com.testobjectfunction;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.userdefinedlibraries.ScreenShot;

import org.openqa.selenium.JavascriptExecutor;


public class CorporateGiftcardFunctions {
	public WebDriver driver;
	public Properties prop;
	
	public void GiftPropertiesFile() throws IOException {
		/*if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("mozilla")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("Choose an appropriate driver chrome,mozilla and try again!!!");
		}
		
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);*/
		
		
		prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\GiftObjectRepo\\giftProp.properties");
		prop.load(file);

	}
	
	public void openUrl(String baseURL) {
		driver.get(prop.getProperty(baseURL));
	}
	
	public void tearDown() {
		driver.close();
	}
	
	public void quitBrowser() {
		driver.quit();
	}
	
	public void displayText(String pathOfElement) {
		System.out.println(driver.findElement(By.xpath(prop.getProperty(pathOfElement))).getText());
	}
	
	public void openGiftPage(String drpdown,String giftOption) {
		driver.findElement(By.xpath(prop.getProperty(drpdown))).click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty(giftOption)))).click();
	}
	
	
	public void clickElement(String pathOfElement) {
		driver.findElement(By.xpath(prop.getProperty(pathOfElement))).click();
	}
	
	public void optCorporate(String pathRadio,String cardPath) {
		driver.findElement(By.xpath(prop.getProperty(pathRadio))).click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty(cardPath)))).click();
	}
	
	public String getTitleOfPage() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String titleName=driver.getTitle();
		return titleName;
	}
	
	public void bodyClick(String pathOfElement) {
		driver.findElement(By.xpath(prop.getProperty(pathOfElement))).click();
	}
	
	public void scrollUpto(String xpathToScroll) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement l=driver.findElement(By.xpath(prop.getProperty(xpathToScroll)));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", l);
	}
	
	public void sendText(String xpathText,String fillText) {
		driver.findElement(By.xpath(prop.getProperty(xpathText))).sendKeys(prop.getProperty(fillText));
	}
	
	public void takeSS() {
		try {

			ScreenShot.screenShotTC(driver);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
