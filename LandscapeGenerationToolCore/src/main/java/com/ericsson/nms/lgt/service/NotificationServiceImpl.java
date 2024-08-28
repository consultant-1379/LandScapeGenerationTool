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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.nms.lgt.dao.NotificationDAO;
import com.ericsson.nms.lgt.entity.notifications.Notification;
import com.ericsson.nms.lgt.service.dto.NotificationDTO;

/**
 * 
 * This class implements the NotificationService interface.
 * 
 * This class is used to contact the data layer in order to retrieve a list of
 * notifications from the database. It acts as an engine between the client and
 * the data layer.
 * 
 */

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {

	private final NotificationDAO notificationDAO;

	/**
	 * The constructor takes in the dao which is used to retrieve data from the
	 * database. This is autowired by Spring.
	 * 
	 * @param dao
	 *            the notification dao which access the notification database.
	 */
	@Autowired
	public NotificationServiceImpl(final NotificationDAO dao) {
		this.notificationDAO = dao;
	}

	@Override
	@Transactional
	public List<NotificationDTO> listNotification() {
		return convertNotificationDomainObjectsToDTO(notificationDAO
				.listNotification());

	}

	/**
	 * This method is responsible for retrieving and setting each notification
	 * information into its corresponding DTO, which will be used by the client.
	 * 
	 * @param domainNotificationList
	 * @return a list of Notification DTO, containing info to be displayed on
	 *         the client.
	 */
	private List<NotificationDTO> convertNotificationDomainObjectsToDTO(
			final List<Notification> domainNotificationList) {
		final List<NotificationDTO> notificationDTOList = new ArrayList<NotificationDTO>();

		for (final Notification notification : domainNotificationList) {
			final NotificationDTO dto = new NotificationDTO();
			dto.setId(notification.getId());
			dto.setNotification(notification.getNotification());
			dto.setDate(notification.getDate());
			dto.setTime(notification.getTime());

			notificationDTOList.add(dto);
		}

		return notificationDTOList;
	}

}
