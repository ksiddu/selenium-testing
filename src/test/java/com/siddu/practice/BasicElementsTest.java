package com.siddu.practice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

// Refer : https://bugbug.io/blog/software-testing/best-selenium-practice-websites/
// https://the-internet.herokuapp.com/
public class BasicElementsTest extends PracticeBaseTest {

	String url1 = "https://the-internet.herokuapp.com/checkboxes";
	String url2 = "https://the-internet.herokuapp.com/login";
	String url3 = "https://the-internet.herokuapp.com/dropdown";
	String url4 = "https://the-internet.herokuapp.com/drag_and_drop";

	@Test(enabled = false)
	public void testCheckBoxes() {

		System.out.println("======================================================");
		System.out.println("testCheckBoxes Thread ID: " + Thread.currentThread().getId());
		System.out.println("======================================================");

		System.out.println("testCheckBoxes test");

		driver.get(url1);

		WebElement chkBox1 = driver.findElement(By.cssSelector("#checkboxes > input:nth-of-type(1)"));
		WebElement chkBox2 = driver.findElement(By.cssSelector("#checkboxes > input:nth-of-type(2)"));

		System.out.println("Checkbox1 status : " + chkBox1.getAttribute("checked"));
		System.out.println("Checkbox2 status : " + chkBox2.getAttribute("checked"));

		Assert.assertEquals(chkBox1.getAttribute("checked"), null);
		Assert.assertEquals(chkBox2.getAttribute("checked"), "true");

		chkBox1.click();
		Assert.assertEquals(chkBox1.getAttribute("checked"), "true");
		System.out.println("Checkbox1 status : " + chkBox1.getAttribute("checked"));
	}

	@Test(enabled = false)
	public void testLogin() {

		System.out.println("======================================================");
		System.out.println("testLogin Thread ID: " + Thread.currentThread().getId());
		System.out.println("======================================================");

		System.out.println("testLogin test");

		driver.get(url2);

		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
		String strSuccessMsg = "You logged into a secure area!";

		System.out.println("Entering username and password");
		username.sendKeys("tomsmith");
		password.sendKeys("SuperSecretPassword!");
		loginButton.click();

		WebElement logoutButton = driver.findElement(By.cssSelector("a[href='/logout']"));
		Assert.assertTrue(logoutButton.isDisplayed());
		WebElement successMsg = driver.findElement(By.cssSelector(".flash.success"));
		Assert.assertTrue(successMsg.isDisplayed());
		Assert.assertTrue(successMsg.getText().contains(strSuccessMsg));

	}

	@Test(enabled = false)
	public void testDropdown() {

		System.out.println("======================================================");
		System.out.println("testDropdown Thread ID: " + Thread.currentThread().getId());
		System.out.println("======================================================");

		System.out.println("testDropdown test");

		driver.get(url3);

		Select drpDown = new Select(driver.findElement(By.id("dropdown")));
		List<WebElement> list = drpDown.getOptions();

		list.forEach(ele -> System.out.println("Option is : " + ele.getText()));

		drpDown.selectByVisibleText("Option 1");

		System.out.println("Selected option is : " + drpDown.getFirstSelectedOption().getText());
		System.out.println("Selected status : " + drpDown.getFirstSelectedOption().getAttribute("selected"));

		drpDown.selectByValue("2");

		System.out.println("Selected option is : " + drpDown.getFirstSelectedOption().getText());
		System.out.println("Selected status : " + drpDown.getFirstSelectedOption().getAttribute("selected"));

	}

	@Test(enabled = true)
	public void testDragAndDrop() throws InterruptedException {

		System.out.println("======================================================");
		System.out.println("testDragAndDrop Thread ID: " + Thread.currentThread().getId());
		System.out.println("======================================================");

		System.out.println("testDragAndDrop test");

		driver.get(url4);

		WebElement srcEle = driver.findElement(By.id("column-a"));
		WebElement dstEle = driver.findElement(By.id("column-b"));
		WebElement srcEleHeader = driver.findElement(By.xpath("//div[@id='column-a']/header"));
		WebElement dstEleHeader = driver.findElement(By.xpath("//div[@id='column-b']/header"));

		System.out.println("srcEleHeader Label is : " + srcEleHeader.getText());
		System.out.println("srcEleHeader Label is : " + dstEleHeader.getText());

		Actions action = new Actions(driver);
		action.dragAndDrop(srcEle, dstEle).perform();

		Thread.sleep(5000);
		/*
		 * // Wait for the element to be visible WebDriverWait wait = new
		 * WebDriverWait(driver, Duration.ofSeconds(10));
		 * wait.until(ExpectedConditions.visibilityOf(srcEleHeader));
		 * wait.until(ExpectedConditions.visibilityOf(dstEleHeader));
		 * 
		 */
		// https://www.linkedin.com/pulse/how-handle-stale-element-exception-selenium-while-testing-serhat-u%C3%A7ar#:~:text=This%20exception%20occurs%20when%20an,or%20the%20element's%20location%20changing.
		// StaleElementReferenceException
		try {

			srcEleHeader.isDisplayed();
		} catch (StaleElementReferenceException ex) {
			// Try to locate the element again
			srcEleHeader = driver.findElement(By.xpath("//div[@id='column-a']/header"));
			dstEleHeader = driver.findElement(By.xpath("//div[@id='column-b']/header"));
			// element.click();
		}
		Assert.assertEquals(srcEleHeader.getText(), "B");
		Assert.assertEquals(dstEleHeader.getText(), "A");

		System.out.println("srcEleHeader Label is : " + srcEleHeader.getText());
		System.out.println("srcEleHeader Label is : " + dstEleHeader.getText());

	}

}
