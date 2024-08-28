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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ericsson.nms.lgt.service.NotificationService;
import com.ericsson.nms.lgt.service.SolutionSetService;
import com.ericsson.nms.lgt.service.dto.*;

/**
 * This controller class is used to handle HTTP requests for
 * displaySolutionSets.jsp.
 */

@Controller
public class DisplaySolutionSetController {

	private final SolutionSetService solutionSetService;
	private final NotificationService notificationService;

	@Autowired
	public DisplaySolutionSetController(final SolutionSetService serviceImpl,
			final NotificationService notificationImpl) {
		this.solutionSetService = serviceImpl;
		this.notificationService = notificationImpl;
	}

	/**
	 * Returns the displaySolutionSet.jsp to be display.
	 * 
	 * @return The name of the jsp page that is to be displayed.
	 */

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public String displayApplication() {

		return "displaySolutionSets"; // displaySolutionSets.jsp page
	}

	/**
	 * Returns a list of a solution sets as a JSON object
	 * 
	 * @return a list of solution sets.
	 */
	@RequestMapping(value = "/SolutionsetList", method = RequestMethod.GET)
	public @ResponseBody
	List<SolutionSetDTO> listSolutionSets() {
		return solutionSetService.listSolutionSet();
	}

	/**
	 * Returns the list of Products with contained Solutionsets.
	 * 
	 * @return the list of Products.
	 */
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public @ResponseBody
	List<ProductDTO> getProducts() {
		final List<ProductDTO> ps = solutionSetService.listProducts();
		return ps;
	}

	/**
	 * Returns a list of a notifications as a JSON object
	 * 
	 * @return a list of notifications.
	 */
	@RequestMapping(value = "/NotificationList", method = RequestMethod.GET)
	public @ResponseBody
	List<NotificationDTO> listNotification() {
		return notificationService.listNotification();
	}

}