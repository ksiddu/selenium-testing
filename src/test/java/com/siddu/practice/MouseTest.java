package com.siddu.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

// https://github.com/SeleniumHQ/seleniumhq.github.io/blob/trunk/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java

// https://stackoverflow.com/questions/45492229/what-is-the-difference-between-action-and-actions-in-selenium
// You can string a bunch of actions together using Actions, and then once you call build() it will store that set of steps as an Action. Once you have an Action, you can call perform() to execute that set of steps
public class MouseTest extends PracticeBaseTest {

	@Test(enabled = false)
	public void clickAndHold() {
		driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");

		// using only Actions class
		WebElement clickable = driver.findElement(By.id("clickable"));
		new Actions(driver).clickAndHold(clickable).perform();

		Assert.assertEquals("focused", driver.findElement(By.id("click-status")).getText());

	}

	@Test(enabled = false)
	public void clickAndHoldNew() {

		driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");
		// using only Actions class & Action interface
		WebElement clickable = driver.findElement(By.id("clickable"));

		Actions actions = new Actions(driver);
		Action clickAction = actions.clickAndHold(clickable).build();

		clickAction.perform();
		Assert.assertEquals("focused", driver.findElement(By.id("click-status")).getText());
	}

	@Test(enabled = false)
	public void clickAndRelease() throws InterruptedException {
		driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");

		WebElement clickable = driver.findElement(By.id("click"));
		new Actions(driver).click(clickable).perform();
		Thread.sleep(5000);
		Assert.assertTrue(driver.getCurrentUrl().contains("resultPage.html"));
	}

	@Test(enabled = false)
	public void rightClick() {
		driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");

		WebElement clickable = driver.findElement(By.id("clickable"));
		new Actions(driver).contextClick(clickable).perform();

		Assert.assertEquals("context-clicked", driver.findElement(By.id("click-status")).getText());
	}

	@Test(enabled = false)
	public void doubleClick() {
		driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");

		WebElement clickable = driver.findElement(By.id("clickable"));
		new Actions(driver).doubleClick(clickable).perform();

		Assert.assertEquals("double-clicked", driver.findElement(By.id("click-status")).getText());
	}

	@Test(enabled = false)
	public void hovers() {
		driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");

		WebElement hoverable = driver.findElement(By.id("hover"));
		new Actions(driver).moveToElement(hoverable).perform();

		Assert.assertEquals("hovered", driver.findElement(By.id("move-status")).getText());
	}

	@Test
	public void dragsToElement() {
		driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");

		WebElement draggable = driver.findElement(By.id("draggable"));
		WebElement droppable = driver.findElement(By.id("droppable"));
		new Actions(driver).dragAndDrop(draggable, droppable).perform();

		Assert.assertEquals("dropped", driver.findElement(By.id("drop-status")).getText());
	}

}
