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

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LGTProductsTest {

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

	@Test
	public void testCheckProductATandT() throws Exception {
		final WebElement tableProducts = driver.findElement(By
				.id("tableproducts"));
		tableProducts.findElement(By.id("1")).click();

		driver.findElement(By.id("nextButton")).click();
		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));

		final WebElement web1 = tableSolutionSet.findElement(By.id("11"));
		final WebElement web2 = tableSolutionSet.findElement(By.id("12"));
		final WebElement web3 = tableSolutionSet.findElement(By.id("3"));
		final WebElement web4 = tableSolutionSet.findElement(By.id("5"));
		final WebElement web5 = tableSolutionSet.findElement(By.id("6"));

		final WebElement tableProductSummary = driver
				.findElement(By
						.xpath("/html/body/div[@id='wizard_tabs']/div[@id='definition-summary']/div[@class='products-summary-table']/table[@class='summarytable']"));
		final List<WebElement> tableProductSummaryElement = tableProductSummary
				.findElements(By.xpath("tbody/tr"));

		assertTrue(web1.isSelected() && web2.isSelected() && web3.isSelected());
		assertFalse(web4.isSelected() && web5.isSelected());
		assertFalse(web1.isEnabled() && web2.isEnabled() && web3.isEnabled());
		assertTrue(web4.isEnabled() && web5.isEnabled());
		assertEquals(4, tableProductSummaryElement.size());
	}

	@Test
	public void testCheckProductTMobile() throws Exception {
		final WebElement tableProducts = driver.findElement(By
				.id("tableproducts"));
		tableProducts.findElement(By.id("2")).click();

		driver.findElement(By.id("nextButton")).click();
		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));

		final WebElement web1 = tableSolutionSet.findElement(By.id("17"));
		final WebElement web2 = tableSolutionSet.findElement(By.id("16"));
		final WebElement web3 = tableSolutionSet.findElement(By.id("15"));
		final WebElement web4 = tableSolutionSet.findElement(By.id("14"));
		final WebElement web5 = tableSolutionSet.findElement(By.id("12"));
		final WebElement web6 = tableSolutionSet.findElement(By.id("11"));
		final WebElement web7 = tableSolutionSet.findElement(By.id("3"));

		final WebElement tableProductSummary = driver
				.findElement(By
						.xpath("/html/body/div[@id='wizard_tabs']/div[@id='definition-summary']/div[@class='products-summary-table']/table[@class='summarytable']"));
		final List<WebElement> tableProductSummaryElement = tableProductSummary
				.findElements(By.xpath("tbody/tr"));

		assertTrue(web1.isSelected() && web2.isSelected() && web3.isSelected()
				&& web4.isSelected() && web7.isSelected());
		assertFalse(web5.isSelected() && web6.isSelected());
		assertFalse(web1.isEnabled() && web2.isEnabled() && web3.isEnabled()
				&& web4.isEnabled() && web7.isEnabled());
		assertTrue(web5.isEnabled() && web6.isEnabled());
		assertEquals(6, tableProductSummaryElement.size());
	}

	@Test
	public void testUncheckProductATandT() throws Exception {
		final WebElement tableProducts = driver.findElement(By
				.id("tableproducts"));
		tableProducts.findElement(By.id("1")).click();
		tableProducts.findElement(By.id("1")).click();

		driver.findElement(By.id("nextButton")).click();
		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));

		final WebElement web1 = tableSolutionSet.findElement(By.id("11"));
		final WebElement web2 = tableSolutionSet.findElement(By.id("12"));
		final WebElement web3 = tableSolutionSet.findElement(By.id("3"));

		assertFalse(web1.isSelected() && web2.isSelected() && web3.isSelected());
		assertTrue(web1.isEnabled() && web2.isEnabled() && web3.isEnabled());

	}

	@Test
	public void testUncheckProductTMobile() throws Exception {
		final WebElement tableProducts = driver.findElement(By
				.id("tableproducts"));
		tableProducts.findElement(By.id("2")).click();
		tableProducts.findElement(By.id("2")).click();

		driver.findElement(By.id("nextButton")).click();
		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));

		final WebElement web1 = tableSolutionSet.findElement(By.id("17"));
		final WebElement web2 = tableSolutionSet.findElement(By.id("16"));
		final WebElement web3 = tableSolutionSet.findElement(By.id("15"));
		final WebElement web4 = tableSolutionSet.findElement(By.id("14"));
		final WebElement web5 = tableSolutionSet.findElement(By.id("3"));

		assertFalse(web1.isSelected() && web2.isSelected() && web3.isSelected()
				&& web4.isSelected() && web5.isSelected());
		assertTrue(web1.isEnabled() && web2.isEnabled() && web3.isEnabled()
				&& web4.isEnabled() && web5.isEnabled());
	}

	@Test
	public void testCorrectSolutionSetsDisabledWhenMultipleProductsChecked()
			throws Exception {
		final WebElement tableProducts = driver.findElement(By
				.id("tableproducts"));
		tableProducts.findElement(By.id("1")).click();
		tableProducts.findElement(By.id("2")).click();

		driver.findElement(By.id("nextButton")).click();
		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));

		final WebElement web1 = tableSolutionSet.findElement(By.id("17"));
		final WebElement web2 = tableSolutionSet.findElement(By.id("16"));
		final WebElement web3 = tableSolutionSet.findElement(By.id("15"));
		final WebElement web4 = tableSolutionSet.findElement(By.id("14"));
		final WebElement web5 = tableSolutionSet.findElement(By.id("12"));
		final WebElement web6 = tableSolutionSet.findElement(By.id("11"));
		final WebElement web7 = tableSolutionSet.findElement(By.id("3"));

		final WebElement tableProductSummary1 = driver
				.findElement(By
						.xpath("//div[@class='products-summary-table']/table[@class='summarytable'][1]"));
		final List<WebElement> tableProductSummaryElement1 = tableProductSummary1
				.findElements(By.xpath("tbody/tr"));
		final WebElement tableProductSummary2 = driver
				.findElement(By
						.xpath("//div[@class='products-summary-table']/table[@class='summarytable'][2]"));
		final List<WebElement> tableProductSummaryElement2 = tableProductSummary2
				.findElements(By.xpath("tbody/tr"));

		assertTrue(web1.isSelected() && web2.isSelected() && web3.isSelected()
				&& web4.isSelected() && web5.isSelected() && web6.isSelected()
				&& web7.isSelected());
		assertFalse(web1.isEnabled() && web2.isEnabled() && web3.isEnabled()
				&& web4.isEnabled() && web5.isEnabled() && web6.isEnabled()
				&& web7.isEnabled());
		assertEquals(4, tableProductSummaryElement1.size());
		assertEquals(6, tableProductSummaryElement2.size());
	}

	@Test
	public void test1CheckProductsAndUncheckSomeToEnsureCorrectSolutionSetsDisabled()
			throws Exception {
		final WebElement tableProducts = driver.findElement(By
				.id("tableproducts"));
		tableProducts.findElement(By.id("1")).click();
		tableProducts.findElement(By.id("2")).click();
		tableProducts.findElement(By.id("1")).click();

		driver.findElement(By.id("nextButton")).click();
		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));

		final WebElement web1 = tableSolutionSet.findElement(By.id("17"));
		final WebElement web2 = tableSolutionSet.findElement(By.id("16"));
		final WebElement web3 = tableSolutionSet.findElement(By.id("15"));
		final WebElement web4 = tableSolutionSet.findElement(By.id("14"));
		final WebElement web5 = tableSolutionSet.findElement(By.id("12"));
		final WebElement web6 = tableSolutionSet.findElement(By.id("11"));
		final WebElement web7 = tableSolutionSet.findElement(By.id("3"));

		assertTrue(web1.isSelected() && web2.isSelected() && web3.isSelected()
				&& web4.isSelected() && web7.isSelected());
		assertFalse(web5.isSelected() && web6.isSelected());
		assertFalse(web1.isEnabled() && web2.isEnabled() && web3.isEnabled()
				&& web4.isEnabled() && web7.isEnabled());
		assertTrue(web5.isEnabled() && web6.isEnabled());
	}

	@Test
	public void test2CheckProductsAndUncheckSomeToEnsureCorrectSolutionSetsDisabled()
			throws Exception {
		final WebElement tableProducts = driver.findElement(By
				.id("tableproducts"));
		tableProducts.findElement(By.id("1")).click();
		tableProducts.findElement(By.id("2")).click();
		tableProducts.findElement(By.id("2")).click();

		driver.findElement(By.id("nextButton")).click();
		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));

		final WebElement web1 = tableSolutionSet.findElement(By.id("17"));
		final WebElement web2 = tableSolutionSet.findElement(By.id("16"));
		final WebElement web3 = tableSolutionSet.findElement(By.id("15"));
		final WebElement web4 = tableSolutionSet.findElement(By.id("14"));
		final WebElement web5 = tableSolutionSet.findElement(By.id("12"));
		final WebElement web6 = tableSolutionSet.findElement(By.id("11"));
		final WebElement web7 = tableSolutionSet.findElement(By.id("3"));

		assertTrue(web5.isSelected() && web6.isSelected() && web7.isSelected());
		assertFalse(web1.isSelected() && web2.isSelected() && web3.isSelected()
				&& web4.isSelected());
		assertTrue(web1.isEnabled() && web2.isEnabled() && web3.isEnabled()
				&& web4.isEnabled());
		assertFalse(web5.isEnabled() && web6.isEnabled() && web7.isSelected());
	}

	@Test
	public void test1CheckProductAndParentOfSolutionSet() throws Exception {
		final WebElement tableProducts = driver.findElement(By
				.id("tableproducts"));
		tableProducts.findElement(By.id("1")).click();

		driver.findElement(By.id("nextButton")).click();
		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));
		tableSolutionSet.findElement(By.id("1")).click();
		tableSolutionSet.findElement(By.id("1")).click();

		final WebElement web1 = tableSolutionSet.findElement(By.id("1"));
		final WebElement web2 = tableSolutionSet.findElement(By.id("3"));

		assertTrue(web2.isSelected());
		assertFalse(web1.isSelected());
	}

	@Test
	public void test2CheckProductAndParentOfSolutionSet() throws Exception {
		final WebElement tableProducts = driver.findElement(By
				.id("tableproducts"));
		tableProducts.findElement(By.id("1")).click();

		driver.findElement(By.id("nextButton")).click();
		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));
		tableSolutionSet.findElement(By.id("1")).click();

		driver.findElement(
				By.xpath("//div[@id='solution-sets-for-selection']/p/button[@class='e-btn prev float-left']/span"))
				.click();
		tableProducts.findElement(By.id("1")).click();

		driver.findElement(By.id("nextButton")).click();

		final WebElement web1 = tableSolutionSet.findElement(By.id("1"));
		final WebElement web2 = tableSolutionSet.findElement(By.id("3"));

		assertTrue(web1.isSelected() && web2.isSelected());
		assertTrue(web1.isEnabled());
		assertFalse(web2.isEnabled());
	}

	@Test
	public void testCheckProductAndSoultionSetParentWithMultipleChildren()
			throws Exception {
		final WebElement tableProducts = driver.findElement(By
				.id("tableproducts"));
		tableProducts.findElement(By.id("1")).click();

		driver.findElement(By.id("nextButton")).click();
		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));
		tableSolutionSet.findElement(By.id("4")).click();

		driver.findElement(
				By.xpath("/html/body/div[@id='wizard_tabs']/div[@id='definition-summary']/div[@class='products-summary-table']/table[@class='summarytable']/tbody/tr[1]/td/button[@id='close-button']"))
				.click();

		final WebElement web1 = tableSolutionSet.findElement(By.id("1"));
		final WebElement web2 = tableSolutionSet.findElement(By.id("3"));//
		final WebElement web3 = tableSolutionSet.findElement(By.id("4"));
		final WebElement web4 = tableSolutionSet.findElement(By.id("5"));//
		final WebElement web5 = tableSolutionSet.findElement(By.id("7"));//
		final WebElement web6 = tableSolutionSet.findElement(By.id("9"));//
		final WebElement web7 = tableSolutionSet.findElement(By.id("17"));

		assertFalse(web1.isSelected() && web7.isSelected());
		assertTrue(web2.isSelected() && web3.isSelected() && web4.isSelected()
				&& web5.isSelected() && web6.isSelected());
		assertTrue(web1.isEnabled() && web3.isEnabled() && web7.isEnabled());
		assertFalse(web2.isEnabled() && web4.isEnabled() && web5.isEnabled()
				&& web6.isEnabled());
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
	}
}
