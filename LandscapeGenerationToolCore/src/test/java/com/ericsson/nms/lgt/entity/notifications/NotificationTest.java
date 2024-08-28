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
package com.ericsson.nms.lgt.entity.notifications;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NotificationTest {

	@Test
	public void testNotificationGetId() {

		final Notification notification = new Notification();
		notification.setId(1);
		assertEquals(notification.getId(), 1);
	}

	@Test
	public void testNotificationGetNotification() {

		final Notification notification = new Notification();
		notification.setNotification("testing");
		assertEquals(notification.getNotification(), "testing");
	}

	@Test
	public void testNotificationGetDate() {

		final Notification notification = new Notification();
		notification.setDate("01:20:2012");
		assertEquals(notification.getDate(), "01:20:2012");
	}

	@Test
	public void testNotificationTime() {

		final Notification notification = new Notification();
		notification.setTime("01:20");
		assertEquals(notification.getTime(), "01:20");
	}

}
