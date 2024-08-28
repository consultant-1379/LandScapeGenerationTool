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

import java.util.List;

/**
 * This is a Data Transfer Object for the Product, and it is used to transfer
 * data from the service layer-Engine to the client.
 */
public class ProductDTO {

	/* the product name */
	protected String name;
	/* the product description */
	protected String description;
	/* the product version */
	protected String version;
	/* the product productId */
	protected long productId;
	/* the product solutionset associations */
	protected List<ProductSolutionsetAssociationDTO> productSolutionsetAssociations;

	/**
	 * Returns the product name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the product name
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Gets the product description
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the product description
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Gets the product version
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets the product version
	 * @param version
	 *            the version to set
	 */
	public void setVersion(final String version) {
		this.version = version;
	}

	/**
	 * Gets the product Id
	 * @return the productId
	 */
	public long getProductId() {
		return productId;
	}

	/**
	 * Sets the product Id
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(final long productId) {
		this.productId = productId;
	}

	/**
	 * Gets this products Solutionset associations list
	 * @return the productSolutionsetAssociations
	 */
	public List<ProductSolutionsetAssociationDTO> getProductSolutionsetAssociations() {
		return productSolutionsetAssociations;
	}

	/**
	 * Sets this products Solutionset associations list
	 * @param productSolutionsetAssociations
	 *            the productSolutionsetAssociations to set
	 */
	public void setProductSolutionsetAssociations(
			final List<ProductSolutionsetAssociationDTO> productSolutionsetAssociations) {
		this.productSolutionsetAssociations = productSolutionsetAssociations;
	}

}
