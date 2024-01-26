package com.siddu.driver;

import static com.siddu.config.ConfigurationManager.configuration;
import static com.siddu.driver.BrowserFactory.valueOf;
import static java.lang.String.format;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TargetFactory {

	private static final Logger logger = LogManager.getLogger(TargetFactory.class);

	public WebDriver createInstance(String browser) {
		Target target = Target.get(configuration().target().toUpperCase());
		WebDriver driver = null;

		switch (target) {

		case LOCAL:
			driver = valueOf(configuration().browser().toUpperCase()).createDriver();
			break;

		case LOCAL_SUITE:
			driver = valueOf(browser.toUpperCase()).createDriver();
			break;

		case SELENIUM_GRID:
			driver = createRemoteInstance(valueOf(browser.toUpperCase()).getOptions());
			break;

		case TESTCONTAINERS:
			driver = valueOf(configuration().browser().toUpperCase()).createTestContainerDriver();
			break;

		}

		return driver;

	}

	private RemoteWebDriver createRemoteInstance(MutableCapabilities capability) {
		RemoteWebDriver remoteWebDriver = null;
		try {
			String gridURL = format("http://%s:%s", configuration().gridUrl(), configuration().gridPort());
			System.out.println("Grid URL : "+ gridURL);
			remoteWebDriver = new RemoteWebDriver(URI.create(gridURL).toURL(), capability);
		} catch (java.net.MalformedURLException e) {
			System.out.println("Grid URL is invalid or Grid is not available");
			logger.error(format("Browser: %s", capability.getBrowserName()), e);
		} catch (IllegalArgumentException e) {
			logger.error(format("Browser %s is not valid or recognized", capability.getBrowserName()), e);
		}

		return remoteWebDriver;
	}

}
