package com.flipkart.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigPropertiesFileReader {
	
	private Properties prop;
	private FileInputStream fis;
	private File src;

	public Properties initProperties() {
		
		prop = new Properties();
		src = new File("./src/main/java/com/flipkart/properties/config.properties");
		
		try {
			fis = new FileInputStream(src);
			prop.load(fis);
		}catch (Exception e) {
			System.out.println("File not found: "+e.getMessage());
		}
	
	return prop;
		
	}

}
