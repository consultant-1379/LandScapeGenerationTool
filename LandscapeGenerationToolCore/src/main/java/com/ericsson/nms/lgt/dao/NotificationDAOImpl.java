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

import javax.persistence.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.nms.lgt.entity.notifications.Notification;

/**
 * NotificationDAOImpl implements the data access interface NotificationDAO
 * which defines methods to access and retrieve data from database.
 */

@Repository("notificationDAO")
public class NotificationDAOImpl implements NotificationDAO {

	private EntityManager em;

	/**
	 * Sets the EntityManager. This is set by spring. The unit name specifies
	 * which entityManagerFactory to be used, therefore mapping this class to
	 * the right database and entity classes.
	 * 
	 * @param em
	 *            the entity manager instance.
	 */
	@PersistenceContext(unitName = "entityManagerFactoryLGT")
	public void setEntityManager(final EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Notification> listNotification() {
		final Query query = em.createQuery("from Notification");
		return query.getResultList();

	}

}
