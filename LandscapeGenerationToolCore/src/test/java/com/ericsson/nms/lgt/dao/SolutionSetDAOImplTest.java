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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.nms.lgt.domain.landscape.*;

public class SolutionSetDAOImplTest {

	private SolutionSetDAOImpl dao;
	private Solutionset sol1;
	private Solutionset sol2;

	private EntityManager mockEm;
	private Query mockQuery;

	@Before
	public void setup() {
		this.dao = new SolutionSetDAOImpl(); // class under test

		// mock classes
		this.mockEm = mock(EntityManager.class);
		this.mockQuery = mock(Query.class);
		this.sol1 = mock(Solutionset.class); // Entity class mocked
		this.sol2 = mock(Solutionset.class); // Entity class mocked

	}

	/**
	 * Test method for
	 * {@link com.ericsson.nms.lgt.dao.SolutionSetDAOImpl#listSolutionSet()}.
	 */
	@Test
	public void testListSolutionSet() {
		// list of solutionsets that is to be returned and valuated against
		final List<Solutionset> solutionSetList = new ArrayList<Solutionset>();
		solutionSetList.add(sol1);
		solutionSetList.add(sol2);

		// setting the Entity Manager
		dao.setEntityManager(mockEm);

		when(mockEm.createQuery("from Solutionset")).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(solutionSetList);

		final List<Solutionset> solSetList = this.dao.listSolutionSet();

		assertNotNull(solSetList);

		assertEquals(sol1, solSetList.get(0));
		assertEquals(sol2, solSetList.get(1));

	}

	@Test
	public void testGetSolutionSetDependency() {
		// add an item to the list
		final List<Long> dependencyIdList = new LinkedList<Long>();
		dependencyIdList.add((long) 1);

		// set the mockEntity
		dao.setEntityManager(mockEm);

		// create a query, set the solutionset Id for the query, a list of the
		// dependent Id is returned
		when(mockEm.createNamedQuery("Solutionset.dependency")).thenReturn(
				mockQuery);
		mockQuery.setParameter("theId", 1);
		when(mockQuery.getResultList()).thenReturn(dependencyIdList);

		// make the actual method call
		final List<Long> idList = this.dao.getSolutionSetdependency(1);

		// check that it is not null and the size is equal to 1.
		assertNotNull(idList);
		assertTrue(idList.size() == 1);

	}

	@Test
	public void testGetSolutionsetsInProduct() {

		dao.setEntityManager(mockEm);
		when(mockEm.createNamedQuery("Product.solutionset_associations"))
				.thenReturn(mockQuery);

		final List<Solutionset> resultSsList = new LinkedList<Solutionset>();
		resultSsList.add(new Solutionset());
		resultSsList.add(new Solutionset());
		resultSsList.add(new Solutionset());

		when(mockQuery.getResultList()).thenReturn(resultSsList);
		final long id = 1234;
		final List<ProductSolutionsetAssociation> assocs = dao
				.getSolutionsetsAssocsInProduct(id); // TODO!!!

		// chect that named query was called on the em
		verify(mockEm).createNamedQuery("Product.solutionset_associations");
		// chect that setParameter was called on the mockQuery
		verify(mockQuery).setParameter("productId", id);

		assertNotNull(assocs);
		assertEquals(3, assocs.size());
	}

	@Test
	public void testListProducts() {
		dao.setEntityManager(mockEm);
		when(mockEm.createQuery("from Product")).thenReturn(mockQuery);
		final List<Product> productList = new LinkedList<Product>();
		productList.add(new Product());
		when(mockQuery.getResultList()).thenReturn(productList);
		final List<Product> products = dao.listProducts();
		// chect that named query was called on the em
		verify(mockEm).createQuery("from Product");
		assertNotNull(products);
		assertEquals(1, products.size());
	}
}