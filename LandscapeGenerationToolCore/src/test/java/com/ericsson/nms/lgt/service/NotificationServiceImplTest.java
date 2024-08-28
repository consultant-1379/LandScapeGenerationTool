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
package com.ericsson.nms.lgt.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.nms.lgt.dao.NotificationDAO;
import com.ericsson.nms.lgt.entity.notifications.Notification;
import com.ericsson.nms.lgt.service.dto.NotificationDTO;

public class NotificationServiceImplTest {

	private NotificationServiceImpl service;
	private NotificationDTO noteDTO1;
	private NotificationDTO noteDTO2;
	private Notification note1;
	private Notification note2;
	private NotificationDAO notificationDAOMock;

	private List<Notification> notificationList;
	private List<NotificationDTO> notificationDtoList;

	@Before
	public void setup() {
		notificationDAOMock = mock(NotificationDAO.class);
		// class under test
		this.service = new NotificationServiceImpl(notificationDAOMock);

		this.noteDTO1 = new NotificationDTO();
		this.noteDTO2 = new NotificationDTO();
		this.note1 = mock(Notification.class);
		this.note2 = mock(Notification.class);

		when(note1.getNotification()).thenReturn("noteDTO1");
		when(note2.getNotification()).thenReturn("noteDTO2");
		when(note1.getId()).thenReturn((long) 1);
		when(note2.getId()).thenReturn((long) 2);

		when(note1.getNotification()).thenReturn("noteDTO1 Description");

		notificationList = new LinkedList<Notification>();
		notificationList.add(note1);
		notificationList.add(note2);
	}

	@Test
	public void testCorrectNumberOfNotificationsAddedToList() {
		when(this.notificationDAOMock.listNotification()).thenReturn(
				notificationList);
		notificationDtoList = this.service.listNotification();
		assertEquals(2, notificationDtoList.size());
	}

}
