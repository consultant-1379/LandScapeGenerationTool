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
package com.ericsson.nms.lgt.selenium;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LGTDefinitionSummaryDeleteSeleniumTest {

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

	// Single Product or Solution
	@Test
	public void testDeleteButtonInProductsAndSolutionSetsSummaryTable()
			throws Exception {
		final WebElement tableProducts = driver.findElement(By
				.id("tableproducts"));
		tableProducts.findElement(By.id("1")).click();

		final WebElement web1 = tableProducts.findElement(By.id("1"));

		driver.findElement(By.id("nextButton")).click();

		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));
		final WebElement web2 = tableSolutionSet.findElement(By.id("12"));
		final WebElement web3 = tableSolutionSet.findElement(By.id("3"));

		assertTrue(web1.isSelected() && web2.isSelected() && web3.isSelected());
		assertFalse(web1.isEnabled() && web2.isEnabled() && web3.isEnabled());

		driver.findElement(
				By.xpath("//div[@class='products-summary-table']/table[@class='summarytable']/tbody/tr[1]/td/button[@id='close-button']"))
				.click();

		assertFalse(web1.isSelected() && web2.isSelected() && web3.isSelected());
		assertTrue(web1.isEnabled() && web2.isEnabled() && web3.isEnabled());
	}

	@Test
	public void testDeleteButtonInSolutionSetSummaryTable() throws Exception {
		driver.findElement(By.id("nextButton")).click();

		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));
		tableSolutionSet.findElement(By.id("4")).click();

		// Get All Checkboxes that are related to the SolutionSet
		final WebElement web1 = tableSolutionSet.findElement(By.id("3"));
		final WebElement web2 = tableSolutionSet.findElement(By.id("4"));
		final WebElement web3 = tableSolutionSet.findElement(By.id("5"));
		final WebElement web4 = tableSolutionSet.findElement(By.id("7"));
		final WebElement web5 = tableSolutionSet.findElement(By.id("9"));

		assertTrue(web1.isSelected() && web2.isSelected() && web3.isSelected()
				&& web4.isSelected() && web5.isSelected());

		// Remove all SolutionSets
		driver.findElement(
				By.xpath("//div[@class='scrollsection2']/table[@class='summarytable']/tbody/tr[@class='first']/td/button[@id='close-button']"))
				.click();

		assertFalse(web1.isSelected() && web2.isSelected() && web3.isSelected()
				&& web4.isSelected() && web5.isSelected());
	}

	// Multiple Products Delete
	@Test
	public void testDeleteButtonInMultipleProductsAndSolutionSetsSummaryTable()
			throws Exception {

		final WebElement tableProducts = driver.findElement(By
				.id("tableproducts"));
		tableProducts.findElement(By.id("1")).click();
		tableProducts.findElement(By.id("2")).click();

		// Products to be checked by the checkbox id
		final WebElement web1 = tableProducts.findElement(By.id("1"));
		final WebElement web2 = tableProducts.findElement(By.id("2"));

		driver.findElement(By.id("nextButton")).click();

		// Get All Checkboxes that are related to the 2 products
		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));
		final WebElement web3 = tableSolutionSet.findElement(By.id("3"));
		final WebElement web4 = tableSolutionSet.findElement(By.id("12"));
		final WebElement web5 = tableSolutionSet.findElement(By.id("14"));
		final WebElement web6 = tableSolutionSet.findElement(By.id("15"));
		final WebElement web7 = tableSolutionSet.findElement(By.id("16"));
		final WebElement web8 = tableSolutionSet.findElement(By.id("17"));

		assertTrue(web1.isSelected() && web2.isSelected() && web3.isSelected()
				&& web4.isSelected() && web5.isSelected() && web6.isSelected()
				&& web7.isSelected() && web8.isSelected());
		assertFalse(web3.isEnabled() && web4.isEnabled() && web5.isEnabled()
				&& web6.isEnabled() && web7.isEnabled() && web8.isEnabled());

		// Delete first product, will not remove dependencies from the second
		// product
		driver.findElement(
				By.xpath("//div[@class='products-summary-table']/table[@class='summarytable'][1]/tbody/tr[1]/td/button[@id='close-button']"))
				.click();

		assertTrue(web2.isSelected() && web3.isSelected() && web5.isSelected()
				&& web6.isSelected() && web7.isSelected() && web8.isSelected());
		assertFalse(web1.isSelected() && web4.isSelected());

		assertTrue(web2.isEnabled());

		// Delete Second Product top check if the solutionSets clear themselves
		driver.findElement(
				By.xpath("//div[@class='products-summary-table']/table[@class='summarytable']/tbody/tr[1]/td/button[@id='close-button']"))
				.click();
		assertFalse(web2.isSelected() && web3.isSelected() && web5.isSelected()
				&& web6.isSelected() && web7.isSelected() && web8.isSelected());

	}

	// Select all SolutionSets then Products, Delete Products and ensure
	// SolutionSets remain intact
	@Test
	public void testDeleteButtonSelectAllSolutionSetsAndProductsThenDeleteSolutionSets()
			throws Exception {

		driver.findElement(By.id("nextButton")).click();

		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));
		tableSolutionSet.findElement(By.id("1")).click();
		tableSolutionSet.findElement(By.id("2")).click();
		tableSolutionSet.findElement(By.id("4")).click();
		tableSolutionSet.findElement(By.id("6")).click();
		tableSolutionSet.findElement(By.id("8")).click();
		tableSolutionSet.findElement(By.id("10")).click();
		tableSolutionSet.findElement(By.id("12")).click();
		tableSolutionSet.findElement(By.id("14")).click();
		tableSolutionSet.findElement(By.id("15")).click();
		tableSolutionSet.findElement(By.id("16")).click();
		tableSolutionSet.findElement(By.id("17")).click();

		driver.findElement(
				By.xpath("//div[@id='solution-sets-for-selection']/p/button[@class='e-btn prev float-left']"))
				.click();

		final WebElement tableProducts = driver.findElement(By
				.id("tableproducts"));
		tableProducts.findElement(By.id("1")).click();
		tableProducts.findElement(By.id("2")).click();

		// Products to be checked by the checkbox id
		final WebElement web1 = tableProducts.findElement(By.id("1"));
		final WebElement web2 = tableProducts.findElement(By.id("2"));

		assertTrue(web1.isSelected() && web2.isSelected());

		driver.findElement(By.id("nextButton")).click();

		// Get All Checkboxes that are related to the 2 products
		final WebElement web3 = tableSolutionSet.findElement(By.id("1"));
		final WebElement web4 = tableSolutionSet.findElement(By.id("2"));
		final WebElement web5 = tableSolutionSet.findElement(By.id("3"));
		final WebElement web6 = tableSolutionSet.findElement(By.id("4"));
		final WebElement web7 = tableSolutionSet.findElement(By.id("5"));
		final WebElement web8 = tableSolutionSet.findElement(By.id("6"));
		final WebElement web9 = tableSolutionSet.findElement(By.id("7"));
		final WebElement web10 = tableSolutionSet.findElement(By.id("8"));
		final WebElement web11 = tableSolutionSet.findElement(By.id("9"));
		final WebElement web12 = tableSolutionSet.findElement(By.id("10"));
		final WebElement web13 = tableSolutionSet.findElement(By.id("12"));
		final WebElement web14 = tableSolutionSet.findElement(By.id("14"));
		final WebElement web15 = tableSolutionSet.findElement(By.id("15"));
		final WebElement web16 = tableSolutionSet.findElement(By.id("16"));
		final WebElement web17 = tableSolutionSet.findElement(By.id("17"));

		assertTrue(web3.isSelected() && web4.isSelected() && web5.isSelected()
				&& web6.isSelected() && web7.isSelected() && web8.isSelected()
				&& web9.isSelected() && web10.isSelected()
				&& web11.isSelected() && web12.isSelected()
				&& web13.isSelected() && web14.isSelected()
				&& web15.isSelected() && web16.isSelected()
				&& web17.isSelected());
		assertFalse(web5.isEnabled() && web7.isEnabled() && web9.isEnabled()
				&& web11.isEnabled() && web13.isEnabled() && web14.isEnabled()
				&& web15.isEnabled() && web16.isEnabled() && web17.isEnabled());

		// Delete all solutionsets, will not remove dependencies from the
		// products
		for (int i = 1; i < 5; i++) {
			driver.findElement(
					By.xpath("//div[@class='scrollsection2']/table[@class='summarytable'][1]/tbody/tr/td/button[@id='close-button']"))
					.click();
		}
		driver.findElement(
				By.xpath("//div[@class='scrollsection2']/table[@class='summarytable'][2]/tbody/tr/td/button[@id='close-button']"))
				.click();
		driver.findElement(
				By.xpath("//div[@class='scrollsection2']/table[@class='summarytable'][1]/tbody/tr/td/button[@id='close-button']"))
				.click();

		assertFalse(web5.isEnabled() && web13.isEnabled() && web14.isEnabled()
				&& web15.isEnabled() && web16.isEnabled() && web17.isEnabled());
		assertTrue(web5.isSelected() && web13.isSelected()
				&& web14.isSelected() && web15.isSelected()
				&& web16.isSelected() && web17.isSelected());

		driver.findElement(
				By.xpath("//div[@id='solution-sets-for-selection']/p/button[@class='e-btn prev float-left']"))
				.click();
		// Delete all products
		for (int i = 1; i < 3; i++) {
			driver.findElement(
					By.xpath("//div[@class='products-summary-table']/table[@class='summarytable'][1]/tbody/tr[1]/td/button[@id='close-button']"))
					.click();
		}
		assertTrue(web1.isEnabled() && web2.isEnabled());
		assertFalse(web1.isSelected() && web2.isSelected());

		driver.findElement(By.id("nextButton")).click();
		assertTrue(web5.isEnabled() && web13.isEnabled() && web14.isEnabled()
				&& web15.isEnabled() && web16.isEnabled() && web17.isEnabled());
		assertFalse(web5.isSelected() && web13.isSelected()
				&& web14.isSelected() && web15.isSelected()
				&& web16.isSelected() && web17.isSelected());

	}

	// Select all SolutionSets then Products, Delete Products and ensure
	// SolutionSets remain intact
	@Test
	public void testDeleteButtonSelectAllSolutionSetsAndProductsThenDeleteProducts()
			throws Exception {

		final WebElement tableProducts = driver.findElement(By
				.id("tableproducts"));
		tableProducts.findElement(By.id("1")).click();
		tableProducts.findElement(By.id("2")).click();

		// Products to be checked by the checkbox id
		final WebElement web1 = tableProducts.findElement(By.id("1"));
		final WebElement web2 = tableProducts.findElement(By.id("2"));

		assertTrue(web1.isSelected() && web2.isSelected());

		driver.findElement(By.id("nextButton")).click();

		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));
		tableSolutionSet.findElement(By.id("1")).click();
		tableSolutionSet.findElement(By.id("2")).click();
		tableSolutionSet.findElement(By.id("4")).click();
		tableSolutionSet.findElement(By.id("6")).click();
		tableSolutionSet.findElement(By.id("8")).click();
		tableSolutionSet.findElement(By.id("10")).click();
		tableSolutionSet.findElement(By.id("12")).click();
		tableSolutionSet.findElement(By.id("14")).click();
		tableSolutionSet.findElement(By.id("15")).click();
		tableSolutionSet.findElement(By.id("16")).click();
		tableSolutionSet.findElement(By.id("17")).click();

		// Get All Checkboxes that are related to the 2 products
		final WebElement web3 = tableSolutionSet.findElement(By.id("1"));
		final WebElement web4 = tableSolutionSet.findElement(By.id("2"));
		final WebElement web5 = tableSolutionSet.findElement(By.id("3"));
		final WebElement web6 = tableSolutionSet.findElement(By.id("4"));
		final WebElement web7 = tableSolutionSet.findElement(By.id("5"));
		final WebElement web8 = tableSolutionSet.findElement(By.id("6"));
		final WebElement web9 = tableSolutionSet.findElement(By.id("7"));
		final WebElement web10 = tableSolutionSet.findElement(By.id("8"));
		final WebElement web11 = tableSolutionSet.findElement(By.id("9"));
		final WebElement web12 = tableSolutionSet.findElement(By.id("10"));
		final WebElement web13 = tableSolutionSet.findElement(By.id("12"));
		final WebElement web14 = tableSolutionSet.findElement(By.id("14"));
		final WebElement web15 = tableSolutionSet.findElement(By.id("15"));
		final WebElement web16 = tableSolutionSet.findElement(By.id("16"));
		final WebElement web17 = tableSolutionSet.findElement(By.id("17"));

		assertTrue(web3.isSelected() && web4.isSelected() && web5.isSelected()
				&& web6.isSelected() && web7.isSelected() && web8.isSelected()
				&& web9.isSelected() && web10.isSelected()
				&& web11.isSelected() && web12.isSelected()
				&& web13.isSelected() && web14.isSelected()
				&& web15.isSelected() && web16.isSelected()
				&& web17.isSelected());
		assertFalse(web5.isEnabled() && web7.isEnabled() && web9.isEnabled()
				&& web11.isEnabled() && web13.isEnabled() && web14.isEnabled()
				&& web15.isEnabled() && web16.isEnabled() && web17.isEnabled());

		driver.findElement(
				By.xpath("//div[@id='solution-sets-for-selection']/p/button[@class='e-btn prev float-left']"))
				.click();
		// Delete all products
		for (int i = 1; i < 3; i++) {
			driver.findElement(
					By.xpath("//div[@class='products-summary-table']/table[@class='summarytable'][1]/tbody/tr[1]/td/button[@id='close-button']"))
					.click();
		}
		assertTrue(web1.isEnabled() && web2.isEnabled());
		assertFalse(web1.isSelected() && web2.isSelected());

		driver.findElement(By.id("nextButton")).click();

		// Delete all solutionsets, will not remove dependencies from the
		// products
		for (int i = 1; i < 5; i++) {
			driver.findElement(
					By.xpath("//div[@class='scrollsection2']/table[@class='summarytable'][1]/tbody/tr/td/button[@id='close-button']"))
					.click();
		}
		driver.findElement(
				By.xpath("//div[@class='scrollsection2']/table[@class='summarytable'][1]/tbody/tr/td/button[@id='close-button']"))
				.click();
		driver.findElement(
				By.xpath("//div[@class='scrollsection2']/table[@class='summarytable'][1]/tbody/tr/td/button[@id='close-button']"))
				.click();

		// final WebElement solutionSetTableEmpty =
		// driver.findElement(By.xpath("//div[@class='scrollsection2']"));
		// final List <WebElement> solutionSetEmpty =
		// solutionSetTableEmpty.findElements(By.xpath("//div[@class='scrollsection2']/table[@class='summarytable']"));
		// assertTrue(solutionSetEmpty.isEmpty());

		assertTrue(web5.isEnabled() && web13.isEnabled() && web14.isEnabled()
				&& web15.isEnabled() && web16.isEnabled() && web17.isEnabled());
		assertFalse(web5.isSelected() && web13.isSelected()
				&& web14.isSelected() && web15.isSelected()
				&& web16.isSelected() && web17.isSelected());

	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
	}

}
