package com.siddu.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	private static DriverManager instance = null;

	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();

	private DriverManager() {

	}

	public static DriverManager getInstance() {

		if (instance == null) {
			instance = new DriverManager();
		}

		return instance;

	}

	public void setDriver(WebDriver driver) {
		threadDriver.set(driver);
	}

	public WebDriver getDriver() {

		System.out.println("DriverManager : getDriver Thread ID: " + Thread.currentThread().getId());
		return threadDriver.get();
	}

	public void quitDriver() {

		System.out.println("DriverManager: quitDriver Thread ID: " + Thread.currentThread().getId());
		threadDriver.get().quit();
		threadDriver.remove();
		// threadDriver.set(null);
	}
}
