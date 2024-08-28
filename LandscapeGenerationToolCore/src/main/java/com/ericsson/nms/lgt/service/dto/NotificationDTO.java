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
package com.ericsson.nms.lgt.service.dto;

/**
 * This is a Data Transfer Object for the Notification, and it is used to
 * transfer data from the service layer-Engine to the client. This class should
 * have no behaviour except the storage and retrieval of data. The main purpose
 * for this DTO is that client should not be able to access the underlying data
 * access objects, and hence possibly change the data.
 */

public class NotificationDTO {

	private long id;
	private String notification;
	private String date;
	private String time;

	/**
	 * Gets the notification id
	 * 
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the notification ID
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(final long id) {
		this.id = id;
	}

	/**
	 * Gets the notification description to be displayed.
	 * 
	 * @return the notification
	 */
	public String getNotification() {
		return notification;
	}

	/**
	 * Sets the notification description
	 * 
	 * @param notification
	 *            the notification to set
	 */
	public void setNotification(final String notification) {
		this.notification = notification;
	}

	/**
	 * Gets the notification date
	 * 
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the notification date
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(final String date) {
		this.date = date;
	}

	/**
	 * Gets the notification time
	 * 
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the notification time
	 * 
	 * @param time
	 *            the time to set
	 */
	public void setTime(final String time) {
		this.time = time;
	}

}
