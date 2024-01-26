package com.siddu.grid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirefoxGridTest {

	private static final String START_MAXIMIZED = "--start-maximized";
	public static final String CHROME_HEADLESS = "--headless=new";
	public static final String GENERIC_HEADLESS = "-headless";

	@Test
	public void chromeTest() throws MalformedURLException, InterruptedException {

		FirefoxOptions firefoxOptions = new FirefoxOptions();

		firefoxOptions.addArguments(START_MAXIMIZED);
		firefoxOptions.addArguments("--disable-infobars");
		firefoxOptions.addArguments("--disable-notifications");
		//firefoxOptions.addArguments(GENERIC_HEADLESS);

		// Define desired capabilities for the browser and platform you want to test
		MutableCapabilities capabilities = new MutableCapabilities(firefoxOptions);

		// Replace "localhost" with the actual address of your Selenium Grid hub
		URL hubUrl = new URL("http://localhost:4444/wd/hub");

		// Create a RemoteWebDriver instance pointing to the hub
		WebDriver driver = new RemoteWebDriver(hubUrl, capabilities);

		System.out.println("googleTitle test");

		driver.get("https://www.google.com/");

		System.out.println("Page title is : " + driver.getTitle());

		Thread.sleep(20000);
		// Your test code here
		Assert.assertEquals(driver.getTitle(), "Google");

		// Close the WebDriver session
		driver.quit();

	}
}
