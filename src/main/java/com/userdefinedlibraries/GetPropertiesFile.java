package com.userdefinedlibraries;

import java.io.FileInputStream;
import java.util.Properties;

public class GetPropertiesFile {

	public static Properties prop;
	
	//FUNCTION TO INITIALIZE THE PROPERTY
	public static Properties getPropertiesInstance() {
		if (prop == null) {

			// INITIALIZE PROPERTY OBJECT
			prop = new Properties();

			try {
				FileInputStream file = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\ProjectConfig.properties");
				prop.load(file);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return prop;
	}
}
