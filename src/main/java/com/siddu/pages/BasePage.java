package com.siddu.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

	public WebDriver driver;

	public BasePage(WebDriver driver) {

		this.driver = driver;

	}

	public void navigateToUrl(String url) {
		driver.get(url);
	}

	public String getTitle() {
		return driver.getTitle();
	}

}
