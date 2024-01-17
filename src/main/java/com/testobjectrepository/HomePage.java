package com.testobjectrepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage {

	//STORING LOCATORS IN BY VARIABLES
	public static By bookingType = By.xpath(("//nav//a"));
	public static By journeyType = By.xpath(("//div[2]/div/div/div[1]//li"));
	public static By sourceCity = By.xpath("//div[2]/div/div/div[2]/div/div[1]//span");
	public static By sourceCityTxtBox = By.id("fromCity");
	public static By cityAutoSuggestion = By.xpath("//li[@class='react-autosuggest__suggestion']");
	public static By citySuggestionFirst = By
			.xpath("//li[@class='react-autosuggest__suggestion react-autosuggest__suggestion--first']");
	public static By destinationCity = By.xpath("//div[2]/label/span");
	public static By destinationCityTxtBox = By.xpath("//div[2]/div/div[2]/div[1]//input");
	public static By departureDate = By.xpath("//div[3]/label");
	public static By monthYear=By.xpath("//div[@class='DayPicker-Months']/div[1]/div[3]/div[4]/div[6]");
	public static By forwardBtn=By.xpath("//div[@class='DayPicker-NavBar']/span[2]");
	public static By time = By.xpath("//div[5]/label/span");
	public static By timeList = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div[1]/div[5]/ul/li[14]");
	public static By searchBtn = By.xpath("//a[text()='Search']");
	public static By rooms=By.xpath("//div[4]/label/span");
	public static By adults=By.xpath("//*[@id='root']/div/div[2]/div/div//ul[1]/li");
	public static By autoSuggestionList = By.xpath("//*[@class = 'sr_city blackText']");
	public static By body=By.xpath("/html/body");
	public static By cityError = By.xpath("//span[@class='redText errorMsgText']");
	public static By destinationCityAfter = By.id("toCity");

	//FUNCTIONS TO ACCESS THE LOCATORS
	public static List<WebElement> getBookingType(WebDriver driver) {
		return driver.findElements(bookingType);
	}

	public static List<WebElement> getjourneyType(WebDriver driver) {
		return driver.findElements(journeyType);
	}

	public static WebElement getFromCity(WebDriver driver) {
		return driver.findElement(sourceCity);
	}

	public static WebElement getSourceCityTxtBox(WebDriver driver) {
		return driver.findElement(sourceCityTxtBox);
	}

	public static List<WebElement> getCityAutoSuggestion(WebDriver driver) {
		return driver.findElements(cityAutoSuggestion);
	}

	public static WebElement getCitySuggestionFirst(WebDriver driver) {
		return driver.findElement(citySuggestionFirst);
	}

	public static List<WebElement> getAutoSuggestionList(WebDriver driver) {
		return driver.findElements(autoSuggestionList);
	}
	
	public static WebElement getDestinationCity(WebDriver driver) {
		return driver.findElement(destinationCity);
	}

	public static WebElement getDestinationCityTxtBox(WebDriver driver) {
		return driver.findElement(destinationCityTxtBox);
	}

	public static WebElement getDepartureDate(WebDriver driver) {
		return driver.findElement(departureDate);
	}
	
	public static WebElement getMonthYear(WebDriver driver) {
		return driver.findElement(monthYear);
	}
	
	public static WebElement getDay(WebDriver driver, String day) {
		return driver.findElement(By.xpath("//div[@class='DayPicker-Months']/div[1]/div[3]/div/div[contains(text(),"+day+")]"));
	}
	
	public static WebElement getForwardBtn(WebDriver driver) {
		return driver.findElement(forwardBtn);
	}
	public static WebElement getTime(WebDriver driver) {
		return driver.findElement(time);
	}

	public static List<WebElement> getTimeList(WebDriver driver) {
		return driver.findElements(timeList);
	}

	public static WebElement getSearchBtn(WebDriver driver) {
		return driver.findElement(searchBtn);
	}

	public static WebElement getRooms(WebDriver driver) {
		return driver.findElement(rooms);
	}
	
	public static List<WebElement> getAdults(WebDriver driver) {
		return driver.findElements(adults);
	}
	
	public static List<WebElement> getBody(WebDriver driver) {
		return driver.findElements(body);
	}
	
	public static WebElement getCityError(WebDriver driver) {
		return driver.findElement(cityError);
	}
	
	public static WebElement getDestinationCityAfter(WebDriver driver) {
		return driver.findElement(destinationCityAfter);
	}


	
}
