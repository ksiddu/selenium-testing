package com.siddu.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AmazonTest extends BaseTest {

	@Test
	public void amazonTitle() {

		System.out.println("======================================================");
		System.out.println("amazonTitle Thread ID: " + Thread.currentThread().getId());
		System.out.println("======================================================");

		System.out.println("amazonTitle test");
		amazonPage.navigateToUrl("https://www.amazon.com/");

		System.out.println("Page title is : " + amazonPage.getTitle());
		System.out.println("Page title is : " + amazonPage.getTitle());

		Assert.assertEquals(amazonPage.getTitle(), "Amazon.com. Spend less. Smile more.");

	}

}