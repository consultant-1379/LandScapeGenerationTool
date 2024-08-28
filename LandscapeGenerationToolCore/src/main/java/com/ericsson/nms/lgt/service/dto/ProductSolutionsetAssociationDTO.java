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
 * This is a Data Transfer Object for the ProductSolutionsetAssociation, and 
 * it is used to transfer data from the service layer-Engine to the 
 * client. This class represents an association with a Solutionset.
 */
public class ProductSolutionsetAssociationDTO {

	/* the associated SolutionSet */
	protected SolutionSetDTO solutionSetDTO;

	/**
	 * Gets the SolutionSet for this association.
	 * @return the solutionSetDTO
	 */
	public SolutionSetDTO getSolutionSetDTO() {
		return solutionSetDTO;
	}

	/**
	 * Sets the SolutionSet for this association.
	 * @param solutionSetDTO
	 *            the solutionSetDTO to set
	 */
	public void setSolutionSetDTO(final SolutionSetDTO solutionSetDTO) {
		this.solutionSetDTO = solutionSetDTO;
	}

}
