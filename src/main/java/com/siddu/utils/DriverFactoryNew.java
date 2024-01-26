package com.siddu.utils;

import static com.siddu.utils.ConfigurationManager.configuration;

import java.net.URI;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactoryNew {

	WebDriver driver = null;

	public WebDriver createInstance(String browser) {
		Target target = Target.valueOf(configuration().target().toUpperCase());

		switch (target) {
		case LOCAL:
			driver = BrowserFactoryNew.valueOf(browser.toUpperCase()).createDriver();
			break;
		case REMOTE:

			driver = createRemoteInstance(BrowserFactoryNew.valueOf(browser.toUpperCase()).getOptions());

		}
		return driver;

	}

	private RemoteWebDriver createRemoteInstance(MutableCapabilities capability) {
		RemoteWebDriver remoteWebDriver = null;
		try {
			String gridURL = String.format("http://%s:%s", configuration().gridUrl(), configuration().gridPort());

			remoteWebDriver = new RemoteWebDriver(URI.create(gridURL).toURL(), capability);
		} catch (java.net.MalformedURLException e) {
			System.out.println("Grid URL is invalid or Grid is not available");
			// logger.log(Level.SEVERE, String.format("Browser: %s",
			// capability.getBrowserName()), e);
		} catch (IllegalArgumentException e) {
			// logger.log(Level.SEVERE,
			// String.format("Browser %s is not valid or recognized",
			// capability.getBrowserName()), e);
		}

		return remoteWebDriver;
	}

	enum Target {
		LOCAL, REMOTE
	}

}
