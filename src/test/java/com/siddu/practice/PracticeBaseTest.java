package com.siddu.practice;

import static com.siddu.config.ConfigurationManager.configuration;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.siddu.driver.DriverManager;
import com.siddu.driver.TargetFactory;

public class PracticeBaseTest {

	protected WebDriver driver;

	String browser = configuration().browser();

	@BeforeMethod
	public void setup() {
		System.out.println("with in setup() method - @BeforeTest");
		System.out.println("Tests are starting!");

		DriverManager.getInstance().setDriver(new TargetFactory().createInstance(browser));
		driver = DriverManager.getInstance().getDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(60000));
		if (this.driver == null) {
			System.out.println(" Driver is null");
		}

	}

	@AfterMethod
	public void tearDown() {

		System.out.println("with in tearDown() method - @AfterTest");
		System.out.println("Tests are ending!");
		DriverManager.getInstance().quitDriver();
	}
}
