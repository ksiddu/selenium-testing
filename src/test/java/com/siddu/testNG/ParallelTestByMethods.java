package com.siddu.testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

// https://www.toolsqa.com/testng/testng-parallel-execution/

public class ParallelTestByMethods {

	public WebDriver driver1;
	public WebDriver driver2;

	@Test
	public void FirefoxTest() {
		// Initializing the firefox driver (Gecko)
		System.out.println("The thread ID for Firefox is " + Thread.currentThread().getId());
		driver1 = new FirefoxDriver();
		driver1.get("https://demoqa.com/");
		driver1.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[1]/div/div[1]")).click();
		driver1.quit();

	}

	@Test
	public void ChromeTest() {
		// Initialize the chrome driver
		System.out.println("The thread ID for Chrome is " + Thread.currentThread().getId());
		driver2 = new ChromeDriver();
		driver2.get("https://demoqa.com/");
		driver2.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[1]/div/div[1]")).click();
		driver2.quit();
	}

}
