package com.testobjectrepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CabResultsPage {
	
	//STORING LOCATORS IN BY VARIABLES
	public static By cabType = By.xpath("//div[2]//div[2]/div[1]/div/div[2]//label");
	public static By cabTypeCheckbox=By.xpath("//div[2]//div[2]/div[1]/div/div[2]//input");
	public static By sortBy=By.xpath("//span[@class='cursorPointer dodgerBlueColor']");
	public static By sortByOptions=By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]//p/span");
	public static By price=By.xpath("//*[@id=\"List\"]//div/div[3]/div/div[2]/div/p[1]");
	
	//FUNCTIONS TO ACCESS THE LOCATORS
	public static List<WebElement> getCabType(WebDriver driver) {
		return driver.findElements(cabType);
	}
	
	public static WebElement getCabTypeCheckbox(WebDriver driver, int index) {
		return driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/div[1]/div/div[2]/div["+index+"]/span/input"));
	}
	
	
	public static WebElement getSortBy(WebDriver driver)
	{
		return driver.findElement(sortBy);
	}
	
	public static List<WebElement> getSortByOptions(WebDriver driver)
	{
		return driver.findElements(sortByOptions);
	}
	
	public static List<WebElement> getPrice(WebDriver driver)
	{
		
		
		return driver.findElements(price);
	}
	
	
	
	
	
}
