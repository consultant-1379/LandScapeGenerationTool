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

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LGTSolutionSetTest {

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
	public void testNoSolutionSetsAreInitiallySelected() {
		// Need to test for this!!
	}

	// A parent Solution set with one dependency is selected.

	@Test
	public void testSelectingDependenciesChecked() throws Exception {

		driver.findElement(By.id("nextButton")).click();

		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));
		tableSolutionSet.findElement(By.id("1")).click();

		final WebElement web1 = tableSolutionSet.findElement(By.id("1"));
		final WebElement web2 = tableSolutionSet.findElement(By.id("3"));

		final WebElement summaryTable = driver
				.findElement(By
						.xpath("//div[@class='scrollsection2']/table[@class='summarytable']"));
		final List<WebElement> summaryTableElement = summaryTable
				.findElements(By.xpath("tbody/tr"));

		assertTrue(web1.isSelected() && web2.isSelected());
		assertTrue(web1.isEnabled());
		assertFalse(web2.isEnabled());
		assertEquals(2, summaryTableElement.size());
	}

	// A parent Solution set with one dependency is un-selected.

	@Test
	public void testSelectingDependenciesUnChecked() throws Exception {
		driver.findElement(By.id("nextButton")).click();
		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));
		tableSolutionSet.findElement(By.id("1")).click();
		tableSolutionSet.findElement(By.id("1")).click();

		final WebElement web = tableSolutionSet.findElement(By.id("3"));
		final WebElement web2 = tableSolutionSet.findElement(By.id("1"));

		assertTrue(web.isSelected() && web.isEnabled());
		assertFalse(web2.isSelected());
	}

	// If a dependent solution set is selected before its parent solutionset.

	@Test
	public void testSelectingChildBeforeParent() throws Exception {
		driver.findElement(By.id("nextButton")).click();
		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));
		tableSolutionSet.findElement(By.id("3")).click();
		tableSolutionSet.findElement(By.id("1")).click();

		final WebElement web1 = tableSolutionSet.findElement(By.id("3"));
		final WebElement web2 = tableSolutionSet.findElement(By.id("1"));

		final WebElement summaryTable = driver
				.findElement(By
						.xpath("//div[@class='scrollsection2']/table[@class='summarytable']"));
		final List<WebElement> summaryTableElement = summaryTable
				.findElements(By.xpath("tbody/tr"));

		assertTrue(web1.isSelected());
		assertFalse(web1.isEnabled());
		assertTrue(web2.isSelected() && web2.isEnabled());
		assertEquals(2, summaryTableElement.size());
	}

	// If a parent solution set is selected check to ensure all its dependent
	// children are selected

	@Test
	public void testMultipleDependencies() throws Exception {
		driver.findElement(By.id("nextButton")).click();
		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));
		tableSolutionSet.findElement(By.id("4")).click();

		final WebElement summaryTable = driver
				.findElement(By
						.xpath("//div[@class='scrollsection2']/table[@class='summarytable']"));
		final List<WebElement> summaryTableElement = summaryTable
				.findElements(By.xpath("tbody/tr"));

		final WebElement web1 = tableSolutionSet.findElement(By.id("3"));
		final WebElement web2 = tableSolutionSet.findElement(By.id("4"));
		final WebElement web3 = tableSolutionSet.findElement(By.id("5"));
		final WebElement web4 = tableSolutionSet.findElement(By.id("7"));
		final WebElement web5 = tableSolutionSet.findElement(By.id("9"));

		assertTrue(web1.isSelected() && web2.isSelected() && web3.isSelected()
				&& web4.isSelected() && web5.isSelected());
		assertFalse(web1.isEnabled() && web3.isEnabled() && web4.isEnabled()
				&& web5.isEnabled());
		assertTrue(web2.isEnabled());

		assertEquals(5, summaryTableElement.size());
	}

	// If parent solution sets share dependencies with each other (Select)

	@Test
	public void testParentSetsSharingDependenciesSolutionsSets()
			throws Exception {
		driver.findElement(By.id("nextButton")).click();
		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));
		tableSolutionSet.findElement(By.id("4")).click();
		tableSolutionSet.findElement(By.id("6")).click();

		final WebElement summaryTable = driver
				.findElement(By
						.xpath("//div[@class='scrollsection2']/table[@class='summarytable']"));
		final List<WebElement> summaryTableElement = summaryTable
				.findElements(By
						.xpath("//div[@class='scrollsection2']/table[@class='summarytable']/tbody/tr"));

		final WebElement web1 = tableSolutionSet.findElement(By.id("3"));
		final WebElement web2 = tableSolutionSet.findElement(By.id("4"));
		final WebElement web3 = tableSolutionSet.findElement(By.id("5"));
		final WebElement web4 = tableSolutionSet.findElement(By.id("6"));
		final WebElement web5 = tableSolutionSet.findElement(By.id("7"));
		final WebElement web6 = tableSolutionSet.findElement(By.id("9"));

		assertTrue(web1.isSelected() && web2.isSelected() && web3.isSelected()
				&& web4.isSelected() && web5.isSelected() && web6.isSelected());
		assertFalse(web1.isEnabled() && web3.isEnabled() && web5.isEnabled()
				&& web6.isEnabled());
		assertTrue(web2.isEnabled() && web4.isEnabled());

		assertEquals(9, summaryTableElement.size());
	}

	// If parent solution sets share dependencies with each other (Un-Select)

	@Test
	public void testUnSelectSolutionSets() throws Exception {
		driver.findElement(By.id("nextButton")).click();
		final WebElement tableSolutionSet = driver.findElement(By
				.id("tablesolutionset"));

		tableSolutionSet.findElement(By.id("6")).click();
		tableSolutionSet.findElement(By.id("4")).click();
		tableSolutionSet.findElement(By.id("6")).click();

		final WebElement summaryTable = driver
				.findElement(By
						.xpath("//div[@class='scrollsection2']/table[@class='summarytable']"));
		final List<WebElement> summaryTableElement = summaryTable
				.findElements(By.xpath("tbody/tr"));

		final WebElement web1 = tableSolutionSet.findElement(By.id("3"));
		final WebElement web2 = tableSolutionSet.findElement(By.id("4"));
		final WebElement web3 = tableSolutionSet.findElement(By.id("5"));
		final WebElement web4 = tableSolutionSet.findElement(By.id("7"));
		final WebElement web5 = tableSolutionSet.findElement(By.id("9"));

		assertTrue(web1.isSelected() && web2.isSelected() && web3.isSelected()
				&& web4.isSelected() && web5.isSelected());
		assertFalse(web1.isEnabled() && web2.isEnabled() && web3.isEnabled()
				&& web4.isEnabled() && web5.isEnabled());

		assertEquals(5, summaryTableElement.size());
	}

	// Reads a solution set description

	@Test
	public void testSolutionSetDescription() throws Exception {
		driver.findElement(By.id("nextButton")).click();
		driver.findElement(
				By.xpath("//table[@id='tablesolutionset']/tbody/tr[@class='heading'][2]/td/fieldset[@class='e-formButton']/label[@id='label2']"))
				.click();
		final String descriptionText = driver
				.findElement(
						By.xpath("//table[@id='tablesolutionset']/tbody/tr[@class='heading'][2]/td[@class='shade']/p[@class='accordion']/label[@id='description2']"))
				.getText();

		assertEquals("Name: OSS Network Explorer, Dependent on nothing",
				descriptionText);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
	}

}
