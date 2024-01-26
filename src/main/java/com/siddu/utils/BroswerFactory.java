package com.siddu.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BroswerFactory {

	public WebDriver createBrowserInstance(String browserName) {

		WebDriver driver = null;
		String osType = getOsName();

		browserName = browserName.toLowerCase();

		if (browserName.contentEquals("chrome")) {

			// Add options for --headed or --headless browser launch
			ChromeOptions chromeOptions = new ChromeOptions();

			if (osType.contains("linux")) {
				chromeOptions.addArguments("-headless");
			}

			// initialize driver for chrome
			driver = new ChromeDriver(chromeOptions);

			// maximize window
			driver.manage().window().maximize();

			// add implicit timeout
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		}

		else if (browserName.contentEquals("firefox")) {

			// Add options for --headed or --headless browser launch
			FirefoxOptions firefoxOptions = new FirefoxOptions();

			if (osType.contains("linux")) {
				firefoxOptions.addArguments("-headless");
			}

			// firefoxOptions.addArguments("-headless");

			// initialize driver for firefox
			driver = new FirefoxDriver(firefoxOptions);

			// maximize window
			driver.manage().window().maximize();

			// add implicit timeout
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		}
		return driver;
	}

	public String getOsName() {
		String osName = null;

		String name = System.getProperty("os.name").toLowerCase();
		String architecture = System.getProperty("os.arch").toLowerCase();
		String dataModel = System.getProperty("sun.arch.data.model");

		System.out.println("OS NAME: " + name);
		if ((name.indexOf("win") >= 0)) {
			osName = "windows";
		} else if ((name.indexOf("mac") >= 0)) {
			osName = "mac";
		} else if (name.indexOf("nix") >= 0 || name.indexOf("nux") >= 0 || name.indexOf("aix") > 0) {

			if (architecture.contains("32") || dataModel.contentEquals("32"))
				osName = "linux32bit";
			else
				osName = "linux64bit";
			System.out.println("Linux Architecture: " + architecture);
			System.out.println("Linux Data Model: " + dataModel);
		} else
			osName = "other";
		return osName;
	}

}
