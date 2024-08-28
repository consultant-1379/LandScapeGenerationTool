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

import static org.junit.Assert.assertEquals;

import java.sql.*;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;

public class LGTNotificationSeleneiumTest {

	protected static WebDriver driver;
	protected static Wait<WebDriver> wait;

	final static String dbUrl = "jdbc:mysql://localhost:3306/NotificationManager";
	final static String username = "root";
	final static String password = "root";
	final static String dbClass = "com.mysql.jdbc.Driver";

	final static String queryOpen = "insert into notification (ID, NOTIFICATION, DATE, TIME) values ('1000', 'Test Notification', CURDATE(), CURTIME())";
	final static String queryClose = "delete from notification where id = 1000";

	@BeforeClass
	public static void setUp() throws Exception {
		// Specifies that Firefox is the test browser

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		// wait = new WebDriverWait(driver, 60);
		// Opens the URL of the test page for all of the tests
		driver.get("http://localhost:8080/LandscapeGenerationToolCore/select.html");

	}

	@Test
	public void testAddedToNotificationTable() throws Exception {

		try {

			Class.forName(dbClass);
			final Connection con = DriverManager.getConnection(dbUrl, username,
					password);
			final Statement stmt = con.createStatement();
			stmt.executeUpdate(queryOpen);

			con.close();
		} // end try

		catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}

		catch (final SQLException e) {
			e.printStackTrace();
		}

		final WebElement alertnotify = (new WebDriverWait(driver, 20))
				.until(new ExpectedCondition<WebElement>() {
					@Override
					public WebElement apply(final WebDriver d) {
						return d.findElement(By.id("1000"));
					}
				});

		assertEquals("Test Notification", alertnotify.getText());
	}

	@AfterClass
	public static void tearDown() throws Exception {

		try {

			Class.forName(dbClass);
			final Connection con = DriverManager.getConnection(dbUrl, username,
					password);
			final Statement stmt = con.createStatement();
			stmt.executeUpdate(queryClose);

			con.close();
		} // end try

		catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}

		catch (final SQLException e) {
			e.printStackTrace();
		}

		// Close the browser
		driver.quit();
	}

}
