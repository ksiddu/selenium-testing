package com.siddu.grid;

import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class GridExample {

	private static final String START_MAXIMIZED = "--start-maximized";
	public static final String CHROME_HEADLESS = "--headless=new";
	public static final String GENERIC_HEADLESS = "-headless";

	public static void main(String[] args) throws Exception {
		ChromeOptions chromeOptions = new ChromeOptions();

		chromeOptions.addArguments(START_MAXIMIZED);
		chromeOptions.addArguments("--disable-infobars");
		chromeOptions.addArguments("--disable-notifications");
		chromeOptions.addArguments(CHROME_HEADLESS);

		// Define desired capabilities for the browser and platform you want to test
		MutableCapabilities capabilities = new MutableCapabilities(chromeOptions);

		// Replace "localhost" with the actual address of your Selenium Grid hub
		URL hubUrl = new URL("http://localhost:4444/wd/hub");

		// Create a RemoteWebDriver instance pointing to the hub
		WebDriver driver = new RemoteWebDriver(hubUrl, capabilities);

		System.out.println("googleTitle test");

		driver.get("https://www.google.com/");

		System.out.println("Page title is : " + driver.getTitle());

		// Your test code here
		Assert.assertEquals(driver.getTitle(), "Google");

		// Close the WebDriver session
		driver.quit();
	}

}
