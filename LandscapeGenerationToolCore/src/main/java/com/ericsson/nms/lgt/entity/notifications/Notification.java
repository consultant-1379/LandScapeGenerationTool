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

import javax.persistence.*;

/**
 * This is the entity class for the notification, and it is used to map the the
 * table and rows to the database.
 */

@Entity(name = "Notification")
@Table(name = "NOTIFICATION")
public class Notification {

	private long id;
	private String notification;
	private String date;
	private String time;

	/**
	 * Gets the notification id.
	 * 
	 * @return the id
	 */

	@Id
	@Column(name = "ID", scale = 0)
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	 * Gets the notification.
	 * 
	 * @return the notification
	 */
	@Basic
	@Column(name = "NOTIFICATION", length = 255)
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
	 * Gets the notification date.
	 * 
	 * @return the date
	 */
	@Basic
	@Column(name = "DATE", length = 255)
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
	 * Gets the notification time.
	 * 
	 * @return the time
	 */
	@Basic
	@Column(name = "TIME", length = 255)
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
