package com.testscenarios;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
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
import com.testobjectfunction.CabResultsPageFunctions;
import com.testobjectfunction.HomePageFunctions;
import com.userdefinedlibraries.DriverSetup;
import com.userdefinedlibraries.ExcelReadWrite;
import com.userdefinedlibraries.ExtentReportFile;
import com.userdefinedlibraries.FailReport;
import com.userdefinedlibraries.GetPropertiesFile;
import com.userdefinedlibraries.ScreenShot;

//SCENARIO 1
public class CabBooking {
	public static WebDriver driver;
	public static Properties prop = GetPropertiesFile.getPropertiesInstance();
	public static String browser;
	public static String filename;
	public static ExtentReports report;
	public static ExtentTest logger;

	// OPENING THE BROWSER
	// @BeforeClass
	public void driverConfig() {
		browser = prop.getProperty("browser");
		driver = DriverSetup.driverInstantiate(browser);
		report = ExtentReportFile.getReportInstance();
	}

	//TESTING EXCEL FILE
	// @Test(priority = 0)
	public void readExcelFile() throws Exception {
		try {
			ExcelReadWrite.readExcelData("cab_booking");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//SELECT CABS FROM NAV MENU
	// @Test(priority = 1, groups = { "Regression" })
	public static void selectCabs() {
		logger = report.createTest("Select Booking Type");

		HomePageFunctions.bodyClick(driver);
		logger.log(Status.INFO, "Clicking anywhere in the homepage to close the login menu");

		HomePageFunctions.setBookingType(driver, ExcelReadWrite.data[0]);
		logger.log(Status.INFO, "Click on the 'Cabs' button");

		HomePageFunctions.setJourneyType(driver, ExcelReadWrite.data[1]);
		{
			logger.log(Status.INFO, "Click on the 'OUTSTATION ONE-WAY' button ");

		}

		try {

			ScreenShot.screenShotTC(driver);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	//VERIFYING TITLE OF THE PAGE
	// @Test(priority = 2, groups = { "Regression" })
	public static void verifyTitle() {
		logger = report.createTest("Title verification of the page");
		try {
			String expectedTitle = HomePageFunctions.getTitle(driver);
			logger.log(Status.INFO, "Get the page title");

			String actualTitle = "Online Cab Booking - Book Outstation Cabs at Lowest Fare @ MakeMyTrip";
			Assert.assertEquals(expectedTitle, actualTitle);
			logger.log(Status.PASS, "Assertion Passed");

		} catch (Exception e) {
			FailReport.reportFail(e.getMessage());
		}

	}

	//ENTER FROM CITY
	// @Test(priority = 2, groups = { "Regression", "Smoke" })
	public static void selectFromCity() {
	
		logger = report.createTest("Select From City");
		try {
			HomePageFunctions.selectFromCityTextbox(driver, ExcelReadWrite.data[2]);
			logger.log(Status.INFO, "Entered Delhi in From search bar");

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			HomePageFunctions.selectAutosuggestionCity(driver, ExcelReadWrite.data[2]);
			logger.log(Status.INFO, "Select Delhi,India from the AutoSuggestion List box");
			logger.log(Status.PASS, "Successfully entered source city");

		} catch (Exception e) {
			FailReport.reportFail(e.getMessage());
		}

	}

	//ENTER DESTINATION CITY
	// @Test(priority = 4, groups = { "Regression", "Smoke" })
	public static void selectToCity() {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		logger = report.createTest("Select 'To' City");
		try {
			HomePageFunctions.selectToCityTextbox(driver, ExcelReadWrite.data[3]);
			logger.log(Status.INFO, "Entered Manali in 'To' search bar");

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			HomePageFunctions.selectAutosuggestionCity(driver, ExcelReadWrite.data[3]);
			
			
			String str = HomePageFunctions.getToCityAfter(driver);
			
			logger.log(Status.INFO, "Select Manali,Himachal Pradesh from the AutoSuggestion List box");
			
			if(ExcelReadWrite.data[3].toLowerCase().contains(str.toLowerCase()))
			{
				logger.log(Status.PASS, "Successfully entered 'To' city");
			}
			else
			{
				logger.log(Status.FAIL, "Functional Defect - Wrong Destination city selected from Suggestion list");
			}
			

		} catch (Exception e) {
			FailReport.reportFail(e.getMessage());
		}
	}

	//CHOOSE DEPARTURE DATE
	// @Test(priority = 5, groups = { "Regression", "Smoke" })
	public static void selectDepartureDate() {
		
		logger = report.createTest("Select Departure Date");
		try {
			HomePageFunctions.selectDepartureDate(driver, ExcelReadWrite.data[4]);
			logger.log(Status.INFO, "Entered Date in departure section");
			logger.log(Status.PASS, "Successfully entered Departure Date");

		} catch (Exception e) {
			FailReport.reportFail(e.getMessage());
		}

	}

	//CHOOSE TIME FROM DROP-DOWN
	// @Test(priority = 6, groups = { "Regression", "Smoke" })
	public static void selectTime() {

		logger = report.createTest("Select Time");
		try {
			HomePageFunctions.selectPickUpTime(driver);
			logger.log(Status.INFO, "Click on Pick up time");
			
			HomePageFunctions.setTimeList(driver, ExcelReadWrite.data[5]);
			logger.log(Status.INFO, "Click on 6:30 A.M");
			logger.log(Status.PASS, "Time selected successfully");

		} catch (Exception e) {
			FailReport.reportFail(e.getMessage());
		}
	}

	//CLICK ON SEARCH BUTTON
	// @Test(priority = 7, groups = { "Smoke" })
	public static void clickSearchButton() {
		logger = report.createTest("Search Functionality");
		try {
			HomePageFunctions.bodyClick(driver);
			HomePageFunctions.clickSearch(driver);

			logger.log(Status.INFO, "Click on Search Button");
			logger.log(Status.PASS, "Clicked on search button successfully");
		} catch (Exception e) {
			FailReport.reportFail(e.getMessage());
		}
	}

	//SELECT CAB TYPE IN RESULTS PAGE
	// @Test(priority = 8, groups = { "Smoke" })
	public static void selectCabType() {
		logger = report.createTest("Select Car type");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		CabResultsPageFunctions.selectCabType(driver, ExcelReadWrite.data[6]);
		logger.log(Status.INFO, "Click on SUV car type");
	}

	//SORT THE RESULT BASED ON OPTION
	// @Test(priority = 9, groups = { "Smoke" })
	public static void selectSortByOption() {

		logger = report.createTest("Select Sort By Option");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		CabResultsPageFunctions.selectSortBy(driver);
		logger.log(Status.INFO, "Click on Sorted By option");
		CabResultsPageFunctions.selectSortOption(driver, ExcelReadWrite.data[7]);
		logger.log(Status.INFO, "Selected Price(Lowest to Highest) option");

	}

	//SELECT THE LOWEST PRICE
	// @Test(priority = 10, groups = { "Smoke" })
	public static void getLowestPrice() {
		logger = report.createTest("Get Lowest Price");

		System.out.println("Lowest Price: " + CabResultsPageFunctions.selectPrice(driver));
		//ExcelReadWrite.excelWriteData("cab_booking", "PASS", CabResultsPageFunctions.selectPrice(driver));
		logger.log(Status.PASS, "Successfully print lowest price");
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
		report.flush();
	}

	 
	
	//CLOSING THE BROWSER
	// @AfterClass
	public static void tearDown() {
		driver.quit();
		report.flush();
	}


}
