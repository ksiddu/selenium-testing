package com.siddu.practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.siddu.driver.DriverManager;
import com.siddu.driver.TargetFactory;

// https://github.com/SeleniumHQ/seleniumhq.github.io/blob/trunk/examples/java/src/test/java/dev/selenium/waits/WaitsTest.java
// https://www.browserstack.com/guide/wait-commands-in-selenium-webdriver
public class WaitsTest {

	protected WebDriver driver;
	String browser = "chrome";
	String url1 = "https://www.selenium.dev/selenium/web/dynamic.html";

	@BeforeMethod
	public void setup() {
		System.out.println("with in setup() method - @BeforeMethod");
		System.out.println("Tests are starting!");

		DriverManager.getInstance().setDriver(new TargetFactory().createInstance(browser));
		driver = DriverManager.getInstance().getDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(60000));
		if (this.driver == null) {
			System.out.println(" Driver is null");
		}

	}

	@Test(enabled = true)
	public void sleepTest() throws InterruptedException {

		System.out.println("======================================================");
		System.out.println("implicitWaitTest Thread ID: " + Thread.currentThread().getId());
		System.out.println("======================================================");

		System.out.println("implicitWaitTest test");

		driver.get(url1);

		driver.findElement(By.id("adder")).click();

		Thread.sleep(1000);

		WebElement added = driver.findElement(By.id("box0"));

		Assert.assertEquals("redbox", added.getDomAttribute("class"));

	}

	@Test(enabled = false)
	public void implicitWaitTest() {

		System.out.println("======================================================");
		System.out.println("implicitWaitTest Thread ID: " + Thread.currentThread().getId());
		System.out.println("======================================================");

		System.out.println("implicitWaitTest test");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.get(url1);

		driver.findElement(By.id("adder")).click();

		WebElement added = driver.findElement(By.id("box0"));

		Assert.assertEquals("redbox", added.getDomAttribute("class"));
	}

	@Test(enabled = false)
	public void explicitWaitTest() {

		System.out.println("======================================================");
		System.out.println("explicitWaitTest Thread ID: " + Thread.currentThread().getId());
		System.out.println("======================================================");

		System.out.println("explicitWaitTest test");

		driver.get(url1);

		WebElement revealed = driver.findElement(By.id("revealed"));
		driver.findElement(By.id("reveal")).click();

		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(d -> revealed.isDisplayed());

		revealed.sendKeys("Displayed");
		Assert.assertEquals("Displayed", revealed.getDomProperty("value"));
	}

	@Test(enabled = false)
	public void explicitWithOptionsTest() {

		System.out.println("======================================================");
		System.out.println("explicitWithOptionsTest Thread ID: " + Thread.currentThread().getId());
		System.out.println("======================================================");

		System.out.println("explicitWithOptionsTest test");

		driver.get(url1);

		WebElement revealed = driver.findElement(By.id("revealed"));
		driver.findElement(By.id("reveal")).click();

		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(2))
				.pollingEvery(Duration.ofMillis(300)).ignoring(ElementNotInteractableException.class);

		wait.until(d -> revealed.isDisplayed());

		revealed.sendKeys("Displayed");
		Assert.assertEquals("Displayed", revealed.getDomProperty("value"));
	}

	@AfterMethod
	public void tearDown() {

		System.out.println("with in tearDown() method - @AfterMethod");
		System.out.println("Tests are ending!");
		DriverManager.getInstance().quitDriver();
	}

}
