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

public class LGTQuickSearchSeleniumTest {

	protected static WebDriver driver;

	@BeforeClass
	public static void setUp() throws Exception {
		// Specifies that Firefox is the test browser
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		// Opens the URL of the test page for all of the tests
		driver.get("http://localhost:8080/LandscapeGenerationToolCore/select.html");

	}

	@Before
	public void initialise() {
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	// Test the functionality of the quick search functionality

	@Test
	public void testSolutionSetQuickSearch() throws Exception {
		driver.findElement(By.id("nextButton")).click();

		// Find the text input element by its name
		final WebElement element = driver
				.findElement(By
						.xpath("//div[@id='solution-sets-for-selection']/div[@id='searchdiv']/label/span[@id='search']/input[@class='kwd_search']"));
		// Enter something to search for
		element.sendKeys("OSS");

		final WebElement tableElement = driver.findElement(By
				.id("tablesolutionset"));
		assertTrue(tableElement.getText().contains("OSS Monitoring"));
		assertTrue(tableElement.getText().contains("OSS Network Explorer"));
		assertTrue(tableElement.getText().contains("OSS Common Explorer"));
		assertFalse(tableElement.getText().contains("Performance Mgt"));
		assertFalse(tableElement.getText().contains("Admin. Log Management"));
	}

	@Test
	public void testSolutionSetQuickSearchNoRsults() throws Exception {
		driver.findElement(By.id("nextButton")).click();

		// Find the text input element by its name
		final WebElement element = driver
				.findElement(By
						.xpath("//div[@id='solution-sets-for-selection']/div[@id='searchdiv']/label/span[@id='search']/input[@class='kwd_search']"));
		// Enter something to search for
		element.sendKeys("RANDOM");

		final WebElement tableElement = driver.findElement(By
				.id("tablesolutionset"));
		assertFalse(tableElement.getText().contains("OSS Monitoring"));
		assertFalse(tableElement.getText().contains("OSS Network Explorer"));
		assertFalse(tableElement.getText().contains("OSS Common Explorer"));
		assertFalse(tableElement.getText().contains("Performance Mgt"));
		assertFalse(tableElement.getText().contains("Admin. Log Management"));
	}

	@Test
	public void testProductsQuickSearch() throws Exception {

		// Find the text input element by its name
		final WebElement element = driver
				.findElement(By
						.xpath("//div[@id='Products-for-selection']/div[@id='searchdiv']/label/span[@id='search']/input[@class='kwd_search']"));
		// Enter something to search for
		element.sendKeys("A");

		final WebElement tableElement = driver.findElement(By
				.id("tableproducts"));
		assertTrue(tableElement.getText().contains("AT&T"));
		assertFalse(tableElement.getText().contains("T-Mobile"));
	}

	@Test
	public void testProductsQuickSearchNoResults() throws Exception {

		// Find the text input element by its name
		final WebElement element = driver
				.findElement(By
						.xpath("//div[@id='Products-for-selection']/div[@id='searchdiv']/label/span[@id='search']/input[@class='kwd_search']"));
		// Enter something to search for
		element.sendKeys("RANDOM");

		final WebElement tableElement = driver.findElement(By
				.id("tableproducts"));
		assertFalse(tableElement.getText().contains("AT&T"));
		assertFalse(tableElement.getText().contains("T-Mobile"));
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
	}

}
