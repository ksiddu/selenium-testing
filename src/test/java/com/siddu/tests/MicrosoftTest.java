package com.siddu.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MicrosoftTest extends BaseTest {

	@Test
	public void amazonTitle() {

		System.out.println("======================================================");
		System.out.println("amazonTitle Thread ID: " + Thread.currentThread().getId());
		System.out.println("======================================================");

		System.out.println("amazonTitle test");
		microsoftPage.navigateToUrl("https://www.microsoft.com/en-sg");

		System.out.println("Page title is : " + microsoftPage.getTitle());

		Assert.assertEquals(microsoftPage.getTitle(), microsoftTitle);

	}

}