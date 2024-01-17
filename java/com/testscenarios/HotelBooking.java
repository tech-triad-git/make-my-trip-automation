package com.testscenarios;


import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.testobjectfunction.HomePageFunctions;
import com.userdefinedlibraries.DriverSetup;
import com.userdefinedlibraries.ExcelReadWrite;
import com.userdefinedlibraries.ExtentReportFile;
import com.userdefinedlibraries.FailReport;
import com.userdefinedlibraries.GetPropertiesFile;
import com.userdefinedlibraries.ScreenShot;

public class HotelBooking {

	public static WebDriver driver;
	public static Properties prop = GetPropertiesFile.getPropertiesInstance();
	public static String browser;
	public static String filename;
	public static String[] adults;
	public static ExtentReports report;
	public static ExtentTest logger;
	
	// @BeforeClass
	// OPENING THE BROWSER
	public void driverConfig() {
		browser = prop.getProperty("browser");

		driver = DriverSetup.driverInstantiate(browser);
		report = ExtentReportFile.getReportInstance();

	}
	
	//SELECT HOTELS FROM NAV MENU
	// @Test(priority=0, groups = { "Regression" })
	public static void selectHotels()
	{
		logger = report.createTest("Hotels");
		HomePageFunctions.bodyClick(driver);
		logger.log(Status.INFO, "Clicking anywhere in the home page");
		
		HomePageFunctions.setBookingType(driver, "Hotels");
		logger.log(Status.INFO, "Select Hotels");
		
	}
	
	//VERIFY THE TITLE OF THE PAGE
	// @Test(priority=1, groups = { "Regression" })
	public static void verifyTitle()
	{
		logger = report.createTest("Title verification of the page");
		try {
		String expectedTitle=HomePageFunctions.getTitle(driver);
		logger.log(Status.INFO, "Get page title");
		
		String actualTitle="MakeMyTrip.com: Save upto 60% on Hotel Booking 4,442,00+ Hotels Worldwide";
		Assert.assertEquals(expectedTitle,actualTitle);
		logger.log(Status.PASS, "Assertion Passed");
		
		} catch (Exception e) {
			FailReport.reportFail(e.getMessage());
		}
		
	}
	
	//GET THE NUMBER OF ADULTS FROM ROOMS AND GUESTS OPTION
	// @Test(priority=2, groups = { "Regression", "Smoke" })
	public static void getNoAdults()
	{
		logger = report.createTest("No. of Adults");
		try {
		HomePageFunctions.setRooms(driver);
		logger.log(Status.INFO, "Clicked on Rooms and Guests dropdown list");
		
		adults=HomePageFunctions.getNoOfAdults(driver);
		System.out.println("Number of Adults:");
		for(int i=0;i<adults.length;i++)
		{
			System.out.print(adults[i]+"=");
		}
		ExcelReadWrite.excelWriteData("hotel_booking", "PASS",adults[adults.length-1]);
		try {

			ScreenShot.screenShotTC(driver);
		} catch (Exception e) {

			e.printStackTrace();
		}
		logger.log(Status.PASS, "Successfully get no.of adults");
		
	} catch (Exception e) {
		FailReport.reportFail(e.getMessage());
	}
	}
	
	//END THE SCENARIO
	// @AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, "Assertion Failed");
			String path = ScreenShot.takeSnapShot(driver);
			try {
				logger.addScreenCaptureFromPath(path);
			} catch (IOException e) {
				FailReport.reportFail(e.getMessage());
			}
		}
	}


	//CLOSE THE BROWSER
	// @AfterClass
	public static void tearDown()
	{
		report.flush();
		driver.quit();
	}
	
	
	
}
