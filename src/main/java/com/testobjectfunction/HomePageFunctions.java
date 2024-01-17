package com.testobjectfunction;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.testobjectrepository.*;

public class HomePageFunctions {

	public static WebDriverWait wait;
	public static JavascriptExecutor jse;
	public static String[] arr;
	public static int i=0;

	//FUNCTIONS TO PERFORM OPERATION ON WEBELEMENTS IN HOME PAGE
	public static void setBookingType(WebDriver driver, String type) {
		try {
		for (WebElement a : HomePage.getBookingType(driver)) {

			if (a.getText().equalsIgnoreCase(type)) {
				System.out.println(a.getText());
				a.click();
			}
		}
		}catch(Exception e)
		{
			
		}
	}

	public static void setJourneyType(WebDriver driver, String type) {
		for (WebElement a : HomePage.getjourneyType(driver)) {

			wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(a));
			if (a.getText().equalsIgnoreCase(type)) {
				System.out.println(a.getText());
				a.click();
			}
		}
	}

	public static void selectFromCity(WebDriver driver) {
	
		HomePage.getFromCity(driver).click();
	}

	public static void selectFromCityTextbox(WebDriver driver, String value) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(HomePage.sourceCityTxtBox));
		HomePage.getSourceCityTxtBox(driver).sendKeys(value);
	}

	public static void selectAutosuggestionCity(WebDriver driver, String city) {
		try {

			for (WebElement a : HomePage.getAutoSuggestionList(driver)) {
				
				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.elementToBeClickable(a));
				if (a.getText().toLowerCase().contains(city.toLowerCase())) {
					System.out.println(a.getText());
					a.click();
				}
			}
		} catch (Exception e) {

		}

	}

	public static void selectToCity(WebDriver driver) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(HomePage.getDestinationCity(driver)));
		HomePage.getDestinationCity(driver).click();
	}

	public static void selectToCityTextbox(WebDriver driver, String value) {
		selectToCity(driver);
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(HomePage.destinationCityTxtBox));
		HomePage.getDestinationCityTxtBox(driver).sendKeys(value);
	}

	public static void selectDepartureDate(WebDriver driver, String date) {

		driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/div[2]/div[1]/div[3]")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		/*try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/div[2]/div[1]/div[3]/div[1]/div/div/div/div[2]/div/div[2]/div[1]/div[3]/div[4]/div[6]")).click();
		
	}

	public static void clickSearch(WebDriver driver) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(HomePage.getSearchBtn(driver)));
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", HomePage.getSearchBtn(driver));
		
	}


	public static void selectPickUpTime(WebDriver driver) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(HomePage.getTime(driver)));
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", HomePage.getTime(driver));
	}

	public static void setTimeList(WebDriver driver, String time) {
		try {
			for (WebElement a : HomePage.getTimeList(driver)) {
				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.elementToBeClickable(a));
				if (a.getText().equalsIgnoreCase(time)) {
					System.out.println(a.getText());
					a.click();
				}

			}
		} catch (Exception e) {

		}
	}

	public static void selectRooms(WebDriver driver) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(HomePage.getRooms(driver)));
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", HomePage.getRooms(driver));
	}

	public static String[] getNoOfAdults(WebDriver driver) {
		arr = new String[HomePage.getAdults(driver).size()];
		for (WebElement a : HomePage.getAdults(driver)) {
			
			arr[i] = a.getText();
			i++;
		}
		return arr;
	}

	public static void bodyClick(WebDriver driver) {
		HomePage.getBody(driver).get(0).click();
	}
	
	public static String getTitle(WebDriver driver) {
		return (driver.getTitle());
	}
	
	public static String getCityError(WebDriver driver) {
		return HomePage.getCityError(driver).getText();
	}
	
	public static String getToCityAfter(WebDriver driver) {
		return HomePage.getDestinationCityAfter(driver).getAttribute("value");
	}

}
