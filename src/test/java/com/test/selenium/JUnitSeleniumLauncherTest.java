package com.test.selenium;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.test.selenium.utils.PropertiesLoader;

@Tag("fast")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitSeleniumLauncherTest {

	private static final Logger LOG = LogManager.getLogger(JUnitSeleniumLauncherTest.class);
	private static ChromeDriverService service;
	private static WebDriver driver;
	//Declared String attributes to contain the properties from config.properties
	private static String chromeDriverFilePath = "";
	private static String urlPath = "";

	@BeforeAll
	public static void setUpForAll(){
		LOG.info("Starting configuration ...");
		PropertiesLoader.loadConfigurationProperties();
		//Initialized the properties from config.properties
		chromeDriverFilePath = PropertiesLoader.properties.getProperty("chromedriver.file.path");
		urlPath = PropertiesLoader.properties.getProperty("url.path");
	}

	@BeforeAll
	public static void createAndStartService() throws IOException {
		LOG.info("Starting driver service ...");
		service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(chromeDriverFilePath))
				.usingAnyFreePort()
				.build();
		try {
			service.start();
		} catch (IOException e) {
			LOG.error("Error while starting driver service: " + e.getMessage());
		}
	}

	@AfterAll
	public static void createAndStopService() {
		service.stop();
	}

	@BeforeEach
	public void createDriver() {
		driver = new RemoteWebDriver(service.getUrl(),
				DesiredCapabilities.chrome());
	}

	@AfterAll
	public static void quitDriver() {
		driver.quit();
	}

	@Test
	public void testGoogleSearch() {
		driver.get(urlPath);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			LOG.error("Waiting was interrupted");
		}
	}
}
