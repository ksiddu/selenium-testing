package com.siddu.practice;

import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WindowsTest extends PracticeBaseTest {

	String url1 = "https://www.lambdatest.com";
	String lambdaTitle = "Most Powerful Cross Browser Testing Tool Online | LambdaTest";
	String url2 = "https://www.google.com/";
	String googleTitle = "Google";
	String url3 = "https://sg.yahoo.com/";
	String yahooTitle = "Yahoo Singapore | News, Finance and Lifestyle";

	// new tab feature demo
	@Test(enabled = true)
	public void multipleTabs() {

		System.out.println("======================================================");
		System.out.println("multipleTabs Thread ID: " + Thread.currentThread().getId());
		System.out.println("======================================================");

		System.out.println("multipleTabs test");

		// go to Google
		driver.get(url2);
		Assert.assertEquals(driver.getTitle(), googleTitle);

		// go to Yahoo in new window
		driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to(url3);
		Assert.assertEquals(driver.getTitle(), yahooTitle);
		System.out.println("No. of Window Handles: " + driver.getWindowHandles().size());
	}

	// new window feature demo
	@Test(enabled = false)
	public void multipleWindows() {

		System.out.println("======================================================");
		System.out.println("multipleWindows Thread ID: " + Thread.currentThread().getId());
		System.out.println("======================================================");

		System.out.println("multipleWindows test");

		// go to Google
		driver.get(url2);
		Assert.assertEquals(driver.getTitle(), googleTitle);

		// go to Yahoo in new window
		driver.switchTo().newWindow(WindowType.WINDOW);
		driver.navigate().to(url3);
		Assert.assertEquals(driver.getTitle(), yahooTitle);
		System.out.println("No. of Window Handles: " + driver.getWindowHandles().size());
	}

}
