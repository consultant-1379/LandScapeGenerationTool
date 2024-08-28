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

import java.util.List;

import com.ericsson.nms.lgt.service.dto.NotificationDTO;

/**
 * This interface handles notification requests from the client to the Data
 * layer.
 * 
 */
public interface NotificationService {

	/**
	 * Retrieves a list of notifications from the data layer.
	 * 
	 * @return A list of notifications
	 * 
	 */
	List<NotificationDTO> listNotification();

}
