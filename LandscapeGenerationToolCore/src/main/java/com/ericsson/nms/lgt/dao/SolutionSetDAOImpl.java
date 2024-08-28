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

import com.ericsson.nms.lgt.domain.landscape.*;

/**
 * SolutionSetDAOImpl implements the data access interface SolutionSetDAO which
 * defines methods to access and retrieve data from database.
 */

@Repository("solutionSetDAO")
public class SolutionSetDAOImpl implements SolutionSetDAO {

	private EntityManager em;

	/**
	 * Sets the EntityManager. This is set by spring. The unit name specifies
	 * which entityManagerFactory to be used, therefore mapping this class to
	 * the right database and entity classes.
	 * 
	 * @param em
	 *            the entity manager instance.
	 */
	@PersistenceContext(unitName = "entityManagerFactoryDomain")
	public void setEntityManager(final EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Solutionset> listSolutionSet() {
		final Query query = em.createQuery("from Solutionset");
		return query.getResultList();

	}

	@Override
	public List<Long> getSolutionSetdependency(final long solutionsetId) {
		final Query query = em.createNamedQuery("Solutionset.dependency");
		query.setParameter("theId", solutionsetId);
		return query.getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Product> listProducts() {
		final Query query = em.createQuery("from Product");
		return query.getResultList();
	}

	@Override
	public List<ProductSolutionsetAssociation> getSolutionsetsAssocsInProduct(
			final long productId) {
		final Query query = em
				.createNamedQuery("Product.solutionset_associations");

		query.setParameter("productId", productId);
		return query.getResultList();
	}
}
