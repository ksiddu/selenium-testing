package com.siddu.driver;

import static com.siddu.config.ConfigurationManager.configuration;
import static java.lang.Boolean.TRUE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;

public enum BrowserFactory {

	CHROME {
		@Override
		public WebDriver createDriver() {

			return new ChromeDriver(getOptions());
		}

		@Override
		public WebDriver createTestContainerDriver() {
			BrowserWebDriverContainer<?> driverContainer = new BrowserWebDriverContainer<>()
					.withCapabilities(new ChromeOptions());
			driverContainer.start();

			return new RemoteWebDriver(driverContainer.getSeleniumAddress(), new ChromeOptions());
		}

		@Override
		public ChromeOptions getOptions() {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments(START_MAXIMIZED);
			chromeOptions.addArguments("--disable-infobars");
			chromeOptions.addArguments("--disable-notifications");

			if (configuration().headless())
				chromeOptions.addArguments(CHROME_HEADLESS);

			return chromeOptions;
		}
	},
	FIREFOX {
		@Override
		public WebDriver createDriver() {

			return new FirefoxDriver(getOptions());
		}

		@Override
		public WebDriver createTestContainerDriver() {
			BrowserWebDriverContainer<?> driverContainer = new BrowserWebDriverContainer<>()
					.withCapabilities(new FirefoxOptions());
			driverContainer.start();

			return new RemoteWebDriver(driverContainer.getSeleniumAddress(), new FirefoxOptions());
		}

		@Override
		public FirefoxOptions getOptions() {
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments(START_MAXIMIZED);
			if (configuration().headless())
				firefoxOptions.addArguments(GENERIC_HEADLESS);

			return firefoxOptions;
		}
	},
	EDGE {
		@Override
		public WebDriver createDriver() {

			return new EdgeDriver(getOptions());
		}

		public WebDriver createTestContainerDriver() {
			BrowserWebDriverContainer<?> driverContainer = new BrowserWebDriverContainer<>()
					.withCapabilities(new EdgeOptions());
			driverContainer.start();

			return new RemoteWebDriver(driverContainer.getSeleniumAddress(), new EdgeOptions());
		}

		@Override
		public EdgeOptions getOptions() {
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments(START_MAXIMIZED);
			if (configuration().headless())
				edgeOptions.addArguments(GENERIC_HEADLESS);

			return edgeOptions;
		}
	},
	SAFARI {
		@Override
		public WebDriver createDriver() {

			return new SafariDriver(getOptions());
		}

		public WebDriver createTestContainerDriver() {
			throw new IllegalArgumentException("Browser Safari not supported on TestContainers yet");
		}

		@Override
		public SafariOptions getOptions() {
			SafariOptions safariOptions = new SafariOptions();
			safariOptions.setAutomaticInspection(false);

			if (TRUE.equals(configuration().headless()))
				throw new IllegalStateException();

			return safariOptions;
		}
	};

	private static final String START_MAXIMIZED = "--start-maximized";
	public static final String CHROME_HEADLESS = "--headless=new";
	//public static final String GENERIC_HEADLESS = "-headless";
	public static final String GENERIC_HEADLESS = "--headless";

	public abstract WebDriver createDriver();

	public abstract AbstractDriverOptions<?> getOptions();

	/**
	 * Used to run the remote test execution using Testcontainers
	 *
	 * @return a new WebDriver instance based on the browser set
	 */
	public abstract WebDriver createTestContainerDriver();

}