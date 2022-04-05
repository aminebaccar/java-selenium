package com.test.selenium.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

	private static final Logger LOG = LogManager.getLogger(PropertiesLoader.class);
	private static final String PROPERTIES_FILE="src/test/resources/config.properties";
	public static Properties properties;

	public static final void loadConfigurationProperties() {
		
		LOG.info("Loading configuration properties...");
		properties = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(PROPERTIES_FILE);
			properties.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

