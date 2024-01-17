package com.userdefinedlibraries;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class ExtentReportFile {
	public static ExtentReports report;
	
	//GENERATE THE PASS EXTENT REPORT
	public static ExtentReports getReportInstance() {

		if (report == null) {

			String reportName = System.currentTimeMillis() + ".html";
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./TestReport/extent" + reportName);
			report = new ExtentReports();
			report.attachReporter(htmlReporter);
		}

		return report;
	}

}


