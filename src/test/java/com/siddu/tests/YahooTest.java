package com.siddu.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class YahooTest extends BaseTest {
	
	@Test
	public void yahooTitle() {
        String url = "https://sg.yahoo.com/";
        
		System.out.println("======================================================");
		System.out.println("yahooTitle Thread ID: " + Thread.currentThread().getId());
		System.out.println("======================================================");

		System.out.println("yahooTitle test");

		yahooPage.navigateToUrl(url);

		System.out.println("Page title is : " + googlePage.getTitle());

		Assert.assertEquals(yahooPage.getTitle(), "Yahoo Singapore | News, Finance and Lifestyle");
		
		Assert.assertTrue(yahooPage.signInButton.isDisplayed());

	}


}
