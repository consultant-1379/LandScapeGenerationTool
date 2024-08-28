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
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

public class LoginControllerTest {

	private LoginController controller;
	private ModelMap modelMap;

	@Before
	public void setup() {
		controller = new LoginController();
		modelMap = mock(ModelMap.class);

	}

	/**
	 * Test method for
	 * {@link com.ericsson.nms.lgt.controller.LoginController#logout(org.springframework.ui.ModelMap)}
	 * .
	 */
	@Test
	public void testLogout() {
		assertEquals("login", controller.logout(modelMap));
	}

}
