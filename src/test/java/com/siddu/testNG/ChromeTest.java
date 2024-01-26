package com.siddu.testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChromeTest {

	public WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		System.out.println("Initilizing the Google Chrome Driver");
		driver = new ChromeDriver();

	}

	@Test
	public void ChromeTestMethod() {
		// Initialize the chrome driver
		System.out.println("The thread ID for Chrome is " + Thread.currentThread().getId());
		driver.get("https://demoqa.com/");
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[1]/div/div[1]")).click();
	}

	@AfterTest
	public void afterTest() {
		System.out.println("Closing the browser ");

		driver.quit();
	}

}
