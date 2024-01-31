package com.siddu.testNG;

import org.testng.annotations.Test;

public class TestNGAttributesTest {

	@Test
	public void test1() {
		System.out.println("within test1 method");
	}

	@Test
	public void test4() {
		System.out.println("within test4 method");
	}

	@Test(enabled = true)
	public void test2() {
		System.out.println("within test2 method");
	}

	@Test(enabled = true)
	public void test3() {
		System.out.println("within test3 method");
	}

	@Test(priority = -1)
	public void test5() {
		System.out.println("within test5 method");
	}

	@Test(priority = 1)
	public void aaa() {
		System.out.println("within aaa method");
	}

	@Test(enabled = false)
	public void zzz() {
		System.out.println("within zzz method");
	}
}
