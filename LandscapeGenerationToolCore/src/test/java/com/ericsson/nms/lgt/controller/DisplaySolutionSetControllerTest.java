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

package com.ericsson.nms.lgt.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.nms.lgt.service.NotificationService;
import com.ericsson.nms.lgt.service.SolutionSetService;
import com.ericsson.nms.lgt.service.dto.*;

public class DisplaySolutionSetControllerTest {
	private NotificationService notificationService;
	private SolutionSetService solutionSetService;
	private DisplaySolutionSetController controller;
	private SolutionSetDTO sol1;
	private SolutionSetDTO sol2;
	private NotificationDTO note1;
	private NotificationDTO note2;

	@Before
	public void setup() {

		this.solutionSetService = mock(SolutionSetService.class);
		controller = new DisplaySolutionSetController(solutionSetService,
				notificationService);
		this.sol1 = mock(SolutionSetDTO.class);
		this.sol2 = mock(SolutionSetDTO.class);

		this.notificationService = mock(NotificationService.class);
		controller = new DisplaySolutionSetController(solutionSetService,
				notificationService);
		this.note1 = mock(NotificationDTO.class);
		this.note2 = mock(NotificationDTO.class);

	}

	@Test
	public void shouldShowDisplaySolutionSetPage() {
		assertEquals("displaySolutionSets", controller.displayApplication());
	}

	@Test
	public void getSolutionSetList() {
		final List<SolutionSetDTO> solutionsetList = new ArrayList<SolutionSetDTO>();
		solutionsetList.add(sol1);
		solutionsetList.add(sol2);
		when(solutionSetService.listSolutionSet()).thenReturn(solutionsetList);

		final List<SolutionSetDTO> solSetList = this.controller
				.listSolutionSets();

		assertNotNull(solSetList);
		assertEquals(solSetList.size(), 2);

	}

	@Test
	public void testGetProducts() {
		// we want the controller to go to the service, and get the product list
		final List<ProductDTO> expectedPoductList = new LinkedList<ProductDTO>();
		expectedPoductList.add(new ProductDTO());
		expectedPoductList.add(new ProductDTO());
		when(solutionSetService.listProducts()).thenReturn(expectedPoductList);
		final List<ProductDTO> productList = this.controller.getProducts();
		assertNotNull(productList);

		assertEquals(expectedPoductList.size(), productList.size());
	}

	@Test
	public void getNotificationList() {
		final List<NotificationDTO> notificationList = new ArrayList<NotificationDTO>();
		notificationList.add(note1);
		notificationList.add(note2);
		when(notificationService.listNotification()).thenReturn(
				notificationList);

		final List<NotificationDTO> noteList = this.controller
				.listNotification();

		assertNotNull(noteList);
		assertEquals(noteList.size(), 2);
	}
}