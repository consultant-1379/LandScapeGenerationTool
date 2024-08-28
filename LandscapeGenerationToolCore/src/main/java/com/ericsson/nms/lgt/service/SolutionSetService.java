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

import com.ericsson.nms.lgt.service.dto.SolutionSetDTO;
import com.ericsson.nms.lgt.service.dto.ProductDTO;

/**
 * This interface handles all requests from the client to the Data layer.
 * 
 */
public interface SolutionSetService {

	/**
	 * Retrieves a list of solution sets from the data layer.
	 * 
	 * @return A list of solution sets
	 * 
	 */
	List<SolutionSetDTO> listSolutionSet();
	
	/**
	 * Gets a list of all Products. Each Product will contain its
	 * solution set associations.
	 * 
	 * @return the list of all Products.
	 */
	List<ProductDTO> listProducts();

}
