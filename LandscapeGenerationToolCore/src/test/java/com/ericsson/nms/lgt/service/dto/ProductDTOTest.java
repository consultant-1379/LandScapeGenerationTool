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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ProductDTOTest {

	/**
	 * Test method for {@link com.ericsson.nms.lgt.service.dto.ProductDTO}.
	 */
	@Test
	public void testAll() {
		
		ProductDTO product = new ProductDTO();
		
		assertNull(product.getDescription());
		assertNull(product.getName());
		assertNull(product.getVersion());
		assertEquals(0, product.getProductId());
		assertNull(product.getProductSolutionsetAssociations());
		
		final String description ="the desc";
		final String name = "thename";
		final long productId = 12345;
		final String version = "9.5.4";
		
		product.setDescription(description);
		product.setName(name);
		product.setProductId(productId);
		product.setVersion(version);
		
		assertEquals(description, product.getDescription());
		assertEquals(name, product.getName());
		assertEquals(productId, product.getProductId());
		assertEquals(version, product.getVersion());

		List<ProductSolutionsetAssociationDTO> assocs = new ArrayList<ProductSolutionsetAssociationDTO>();
		ProductSolutionsetAssociationDTO assoc1 = new ProductSolutionsetAssociationDTO();
		SolutionSetDTO solutionSetDTO = new SolutionSetDTO();
		final String solSetName = "solset1";
		solutionSetDTO.setName(solSetName);
		assoc1.setSolutionSetDTO(solutionSetDTO);
		assocs.add(assoc1);
		
		product.setProductSolutionsetAssociations(assocs);
		
		assertNotNull(product.getProductSolutionsetAssociations());
		assertEquals(1, product.getProductSolutionsetAssociations().size());
		assertEquals(solSetName, product.getProductSolutionsetAssociations().get(0).getSolutionSetDTO().getName());
				
	}
}
