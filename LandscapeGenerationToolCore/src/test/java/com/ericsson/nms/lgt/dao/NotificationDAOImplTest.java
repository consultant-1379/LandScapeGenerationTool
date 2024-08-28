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
package com.ericsson.nms.lgt.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.nms.lgt.entity.notifications.Notification;

public class NotificationDAOImplTest {

	private NotificationDAOImpl dao;
	private Notification note1;
	private Notification note2;

	private EntityManager mockEm;
	private Query mockQuery;

	@Before
	public void setup() {
		this.dao = new NotificationDAOImpl(); // class under test

		// mock classes
		this.mockEm = mock(EntityManager.class);
		this.mockQuery = mock(Query.class);
		this.note1 = mock(Notification.class); // Entity class mocked
		this.note2 = mock(Notification.class); // Entity class mocked

	}

	/**
	 * Test method for
	 * {@link com.ericsson.nms.lgt.dao.SolutionSetDAOImpl#listSolutionSet()}.
	 */
	@Test
	public void testListNotification() {
		// list of notifications that is to be returned and valuated against
		final List<Notification> notificationList = new ArrayList<Notification>();
		notificationList.add(note1);
		notificationList.add(note2);

		// setting the Entity Manager
		dao.setEntityManager(mockEm);

		when(mockEm.createQuery("from Notification")).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(notificationList);

		final List<Notification> noteList = this.dao.listNotification();

		assertNotNull(noteList);

		assertEquals(note1, noteList.get(0));
		assertEquals(note2, noteList.get(1));

	}

}
