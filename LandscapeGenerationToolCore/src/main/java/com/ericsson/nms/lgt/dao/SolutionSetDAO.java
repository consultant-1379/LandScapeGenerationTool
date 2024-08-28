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

import com.ericsson.nms.lgt.domain.landscape.*;

/**
 * 
 * This is a data access object that provides access to the Solution set table
 * in the database
 * 
 */
public interface SolutionSetDAO {

	/**
	 * Returns a list of solution sets from the database
	 * 
	 * @return A list of solution sets
	 * 
	 */
	List<Solutionset> listSolutionSet();

	/**
	 * Returns a list of  dependencies ids for the given solution set id;
	 * @param solutionsetId  the solution set id
	 * @return a list of the dependent solution set ids.
	 */
	List<Long> getSolutionSetdependency(long solutionsetId);

	/**
	 * Returns a list of the Products.
	 * @return a list of the Products.
	 */
	List<Product> listProducts() ;
	
	/**
	 * For a given Product Id, returns all the ProductSolutionsetAssociation in that
	 * Product.
	 * @param productId the Product Id.
	 * @return the ProductSolutionsetAssociations in that Product.
	 */
	List<ProductSolutionsetAssociation> getSolutionsetsAssocsInProduct(long productId) ;
	
}
