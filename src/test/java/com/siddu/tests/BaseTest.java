package com.siddu.tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.siddu.driver.DriverManager;
import com.siddu.driver.TargetFactory;
import com.siddu.pages.AmazonPage;
import com.siddu.pages.GooglePage;
import com.siddu.pages.YahooPage;
import com.siddu.utils.BroswerFactory;

public class BaseTest {

	protected WebDriver driver;
	BroswerFactory bf = new BroswerFactory();
	String browser = "chrome";
	GooglePage googlePage;
	YahooPage yahooPage;
	AmazonPage amazonPage;

	@BeforeTest(alwaysRun = true)
	@Parameters("browser")
	public void preCondition(@Optional("chrome") String browser) {
		System.out.println("with in preCondition() method - @BeforeTest");
		System.out.println("Tests are starting!");

		DriverManager.getInstance().setDriver(new TargetFactory().createInstance(browser));
		driver = DriverManager.getInstance().getDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(60000));
		googlePage = new GooglePage(driver);
		yahooPage = new YahooPage(driver);
		amazonPage = new AmazonPage(driver);
	}

	@AfterTest(alwaysRun = true)
	public void postCondition() {

		System.out.println("with in postCondition() method - @AfterTest");
		System.out.println("Tests are ending!");

		DriverManager.getInstance().quitDriver();
	}

	public WebDriver getDriver() {
		return driver;
	}

}