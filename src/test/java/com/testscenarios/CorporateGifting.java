package com.testscenarios;

import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.testobjectfunction.CorporateGiftcardFunctions;
import com.userdefinedlibraries.DriverSetup;
import com.userdefinedlibraries.ExtentReportFile;
import com.userdefinedlibraries.GetPropertiesFile;
import com.userdefinedlibraries.ScreenShot;
public class CorporateGifting extends CorporateGiftcardFunctions{
	public static ExtentReports report;
	public static ExtentTest logger;
	public static Properties prop = GetPropertiesFile.getPropertiesInstance();
	public static String browser; 
	
	// @BeforeClass
	public void activateDriver() throws IOException {
		report = ExtentReportFile.getReportInstance();
		browser = prop.getProperty("browser");
		driver = DriverSetup.driverInstantiate(browser);
		GiftPropertiesFile();
	}
	
	// @Test(priority=0)
	public void openSite() throws InterruptedException {
		logger = report.createTest("Open the Website for Gift Cards");
		openUrl("baseURL");
		bodyClick("body");
		logger.log(Status.INFO, "Navigating to Gift Card Page");
		openGiftPage("moreDropdown","giftSelection");
	}
	
	// @Test(priority=1, dependsOnMethods="openSite", groups = { "Regression" })
	public void selectCorporateCards() {
		logger = report.createTest("Choose Corporate Gifting");
		logger.log(Status.INFO, "Selecting the Corporate Gifting section");
		optCorporate("corporateRadioButton","giftcardLink");
	}
	
	// @Test(priority=1,dependsOnMethods="selectCorporateCards")
	public void verifyTitleOfGiftpage() {
		logger = report.createTest("Select the Gift card");
		logger.log(Status.INFO, "Selecting the Corporate Gift Card");
		String titleCorporatePage=getTitleOfPage();
		System.out.println(titleCorporatePage);
		Assert.assertEquals("Gift Cards - Buy Gift Vouchers Online, Gift Vouchers | MakeMyTrip.com", titleCorporatePage);
	}
	
	// @Test(priority=2,dependsOnMethods="verifyTitleOfGiftpage", groups = { "Smoke" })
	public void fillForm() throws InterruptedException {
		logger = report.createTest("Fill the sender details form");
		logger.log(Status.INFO, "Entering sender details");
		scrollUpto("senderName");
		sendText("senderName","name");
		sendText("senderMobile","mobile");
		sendText("senderMail","email");
	}
	
	// @Test(priority=2,dependsOnMethods="fillForm", groups = { "Regression","Smoke" })
	public void formSubmit() throws InterruptedException {
		logger = report.createTest("Submit Form");
		logger.log(Status.INFO, "buying the card");
		scrollUpto("buttonRefrenceScroll");
		clickElement("buyGiftCardBtn");
	}
	
	// @Test(priority=3,dependsOnMethods="formSubmit",groups = { "Smoke" })
	public void getErrorMessage() throws InterruptedException {
		logger = report.createTest("Capure Error");
		logger.log(Status.INFO, "Capturing the error");
		Thread.sleep(1000);
		scrollUpto("senderName");
		displayText("errorXpath");
		ScreenShot.screenShotTC(driver);

	}
	
	// @AfterClass
	public void endDriverProcess() {
		report.flush();
		quitBrowser();
	}
}
