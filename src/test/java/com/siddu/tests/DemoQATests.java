package com.siddu.tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.siddu.driver.DriverManager;
import com.siddu.driver.TargetFactory;
import com.siddu.pages.AmazonPage;
import com.siddu.pages.DemoQAPage;
import com.siddu.pages.GooglePage;
import com.siddu.utils.BroswerFactory;
import com.siddu.utils.DriverFactory;

public class DemoQATests {

	protected WebDriver driver;
	BroswerFactory bf = new BroswerFactory();
	String browser = "firefox";
	AmazonPage amazonPage;
	GooglePage googlePage;
	DemoQAPage demoQAPage;

	String url_1 = "https://demoqa.com/text-box";
	String title_1 = "Text Box";

	String url_2 = "https://demoqa.com/elements";
	String title_2 = "Check Box";

	String url_3 = "https://demoqa.com/elements";
	String title_3 = "Radio Button";

	String url_4 = "https://demoqa.com/elements";
	String title_4 = "Web Tables";

	@BeforeMethod
	public void setup() {

		System.out.println("with in setup() method - @BeforeTest");
		System.out.println("Tests are starting!");

		DriverManager.getInstance().setDriver(new TargetFactory().createInstance(browser));
		driver = DriverManager.getInstance().getDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(60000));
		demoQAPage = new DemoQAPage(driver);

	}

	@Test
	public void demoQATitle() {

		System.out.println("======================================================");
		System.out.println("demoQATitle Thread ID: " + Thread.currentThread().getId());
		System.out.println("======================================================");

		System.out.println("demoQATitle test");

		demoQAPage.navigateToUrl(url_1);

		System.out.println("demoQAPage Page title is : " + demoQAPage.getTitle());

		AssertJUnit.assertEquals(demoQAPage.header.getText(), title_1);

	}

	@AfterMethod
	public void tearDown() {

		System.out.println("with in tearDown() method - @AfterTest");
		System.out.println("Tests are ending!");
		DriverFactory.getInstance().quitDriver();
	}
}
