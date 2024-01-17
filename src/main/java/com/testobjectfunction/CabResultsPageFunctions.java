package com.testobjectfunction;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testobjectrepository.CabResultsPage;

public class CabResultsPageFunctions {
	
	public static int count=0;
	public static JavascriptExecutor jse;
	public static WebDriverWait wait;
	
	//FUNCTIONS TO PERFORM OPERATION ON WEBELEMENTS IN CABRESULT PAGE
	public static void selectCabType(WebDriver driver, String cab)
	{
		
		for (WebElement a : CabResultsPage.getCabType(driver)) {
			 System.out.println(a.getText());
			 count++;
			 if (a.getText().equalsIgnoreCase(cab)) {
					System.out.println(a.getText());
					wait = new WebDriverWait(driver, 30);
					wait.until(ExpectedConditions.elementToBeClickable(a));
					jse = (JavascriptExecutor)driver;
					jse.executeScript("arguments[0].click()", CabResultsPage.getCabTypeCheckbox(driver,count));
					
				}					
		}
	}
	
	public static void selectSortBy(WebDriver driver)
	{
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CabResultsPage.sortBy));
		jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", CabResultsPage.getSortBy(driver));
	}
	
	public static void selectSortOption(WebDriver driver, String option)
	{
		try {
		for (WebElement a : CabResultsPage.getSortByOptions(driver)) {
						 if (a.getText().contains(option)) {
					System.out.println(a.getText());
					wait = new WebDriverWait(driver, 30);
					wait.until(ExpectedConditions.elementToBeClickable(a));
					jse = (JavascriptExecutor)driver;
					jse.executeScript("arguments[0].click()", a);
					
				}				
		}
		}
		catch(Exception e)
		{
			
		}
	}
	
	public static String selectPrice(WebDriver driver)
	{
		for (WebElement a : CabResultsPage.getPrice(driver)) {
			 System.out.println(a.getText());
		}
		return CabResultsPage.getPrice(driver).get(0).getText().trim();
		
	}


}
