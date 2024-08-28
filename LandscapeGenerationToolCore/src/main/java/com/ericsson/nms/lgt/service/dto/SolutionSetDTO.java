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
 * This is a Data Transfer Object for the SolutionSet, and it is used to
 * transfer data from the service layer-Engine to the client. This class should
 * have no behaviour except the storage and retrieval of data. The main purpose
 * for this DTO is that client should not be able to access the underlying data
 * access objects, and hence possibly change the data.
 */

public class SolutionSetDTO {

	private String name;
	private long dependentSolutionSetId;
	private long solutionSetId;
	private String description;

	/**
	 * Returns the Solution Set Name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Solution Set name. This is set by the service layer.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Returns the id of the solution set that this solution set DTO is
	 * dependent on.
	 * 
	 * @return the id of the solution set it is dependent on
	 */

	public long getSolutionSetDependentId() {
		return dependentSolutionSetId;
	}

	/**
	 * Sets the id of its dependent solution.
	 * 
	 * @param dependentSolutionSetId
	 *            the id of the solution set it is dependent on.
	 */
	public void setSolutionSetDependentId(final long dependentSolutionSetId) {
		this.dependentSolutionSetId = dependentSolutionSetId;
	}

	/**
	 * Gets this solution set id.
	 * 
	 * @return the solution set id.
	 */
	public long getSolutionSetId() {
		return solutionSetId;
	}

	/**
	 * Sets this solution set id.
	 * 
	 * @param solSetId
	 *            the solution set id
	 */
	public void setSolutionSetId(final long solSetId) {
		this.solutionSetId = solSetId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
}
