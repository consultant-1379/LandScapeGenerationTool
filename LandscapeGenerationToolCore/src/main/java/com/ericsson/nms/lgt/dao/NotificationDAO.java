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

import java.util.List;

import com.ericsson.nms.lgt.entity.notifications.Notification;

/**
 * 
 * This is a data access object that provides access to the Notification table
 * in the database
 * 
 */
public interface NotificationDAO {

	/**
	 * Returns a list of notifications from the database
	 * 
	 * @return A list of notifications
	 * 
	 */
	List<Notification> listNotification();
}
