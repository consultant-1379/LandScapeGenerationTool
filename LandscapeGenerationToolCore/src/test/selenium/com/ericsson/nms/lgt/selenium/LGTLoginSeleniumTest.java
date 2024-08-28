package com.ericsson.nms.lgt.selenium;

/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LGTLoginSeleniumTest {

	protected static WebDriver driver;

	@BeforeClass
	public static void setUp() throws Exception {
		// Specifies that Firefox is the test browser
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		// Opens the URL of the test page for all of the tests
		driver.get("http://localhost:8080/LandscapeGenerationToolCore/select.html");

	}

	@Test
	public void testLogin() throws Exception {

		// Click on login
		driver.findElement(By.id("login")).click();

		try {

			final WebElement username = driver
					.findElement(By
							.xpath("//div[@id='LoginArea']/form/span[@class='e-input'][1]/input"));
			// Enter username
			username.sendKeys("admin");

			final WebElement password = driver
					.findElement(By
							.xpath("//div[@id='LoginArea']/form/span[@class='e-input'][2]/input"));
			// Enter password
			password.sendKeys("123456");

			// Press submit
			driver.findElement(
					By.xpath("//div[@id='LoginArea']/form/span[@class='e-icon']/input[@class='e-btn']"))
					.click();

			// wait for the logout button to load
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

			// Check that the logout button appears
			assertTrue(driver.findElement(
					By.xpath("//button[@id='logout']/span")).isDisplayed());

			// Check that the user name appears
			assertTrue(driver.findElement(By.id("loggedInUserIcon"))
					.isDisplayed());

			// Click 'next' button
			driver.findElement(By.id("nextButton")).click();

			// Check that the Alarm Management Solution Set is available
			assertTrue(driver.findElement(By.xpath("//label[@id='label11']"))
					.isDisplayed());

			// Click 'Log Out' button
			driver.findElement(By.id("logout")).click();
		} catch (final Exception e) {

			driver.get("http://localhost:8080/LandscapeGenerationToolCore/logout");
		}
	}

	@Test
	public void testLogOut() throws Exception {

		// Click 'next' button
		driver.findElement(By.id("nextButton")).click();

		// Find the text input element by its name
		final WebElement element = driver
				.findElement(By
						.xpath("//div[@id='solution-sets-for-selection']/div[@id='searchdiv']/label/span[@id='search']/input[@class='kwd_search']"));
		// Enter something to search for
		element.sendKeys("Hyperic  Management");

		// Check that the Hyperic Management Solution Set is
		// now unavailable
		final WebElement tableElement = driver.findElement(By
				.id("tablesolutionset"));
		assertFalse(tableElement.getText().contains("Hyperic  Management"));
	}

	@AfterClass
	public static void tearDown() throws Exception {

		// Close the browser
		driver.quit();
	}

}
