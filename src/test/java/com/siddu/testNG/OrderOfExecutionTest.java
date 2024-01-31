package com.siddu.testNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OrderOfExecutionTest {

	@BeforeClass
	public void beforeClass() {
		System.out.println("within beforeClass method");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("within afterClass method");
	}

	@Test
	public void testMethod1() {
		System.out.println("within testMethod1 method");
	}

	@Test
	public void testMethod2() {
		System.out.println("within testMethod2 method");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("within beforeTest method");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("within afterTest method");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("within beforeSuite method");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("within afterSuite method");
	}

}
