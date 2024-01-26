package com.siddu.practice;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

// https://github.com/SeleniumHQ/seleniumhq.github.io/blob/trunk/examples/java/src/test/java/dev/selenium/elements/FileUploadTest.java
public class FileUploadTest extends PracticeBaseTest {

	String url1 = "https://the-internet.herokuapp.com/upload";
	String url2 = "https://the-internet.herokuapp.com/login";
	String url3 = "https://the-internet.herokuapp.com/dropdown";
	String url4 = "https://the-internet.herokuapp.com/drag_and_drop";

	@Test
	public void fileUploadTest() {

		driver.get(url1);
		File uploadFile = new File("src/test/resources/selenium-snapshot.png");

		WebElement fileInput = driver.findElement(By.cssSelector("input[type=file]"));
		System.out.println("uploadFile.getAbsolutePath() : " + uploadFile.getAbsolutePath());
		fileInput.sendKeys(uploadFile.getAbsolutePath());
		driver.findElement(By.id("file-submit")).click();

		WebElement fileName = driver.findElement(By.id("uploaded-files"));
		Assert.assertEquals("selenium-snapshot.png", fileName.getText());
	}

}
