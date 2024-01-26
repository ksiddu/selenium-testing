package com.siddu.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest extends BaseTest {
	
	@Test
	public void googleTitle() {
        String url = "https://www.google.com/";
        
		System.out.println("======================================================");
		System.out.println("googleTitle Thread ID: " + Thread.currentThread().getId());
		System.out.println("======================================================");

		System.out.println("googleTitle test");

		googlePage.navigateToUrl(url);

		System.out.println("Page title is : " + googlePage.getTitle());

		Assert.assertEquals(googlePage.getTitle(), "Google");
		
		Assert.assertTrue(googlePage.signInButton.isDisplayed());

	}


}
