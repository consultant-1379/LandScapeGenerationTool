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

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.nms.lgt.dao.SolutionSetDAO;
import com.ericsson.nms.lgt.domain.landscape.*;
import com.ericsson.nms.lgt.service.dto.*;

/**
 * 
 * This class implements the SolutionSetService interface.
 * 
 * This class is used to contact the data layer in order to retrieve a list of
 * Solution sets from the database. It acts as an engine between the client and
 * the data layer.
 * 
 */

@Service("solutionSetService")
public class SolutionSetServiceImpl implements SolutionSetService {

	private final SolutionSetDAO solutionSetDAO;

	// private final static String PRIVILEGED = "ROLE_PRIVILEGED";

	@Autowired
	public SolutionSetServiceImpl(final SolutionSetDAO dao) {
		this.solutionSetDAO = dao;
	}

	@Override
	@Transactional
	public List<SolutionSetDTO> listSolutionSet() {
		final List<Solutionset> solSetList = getSolutionSetListByRole(solutionSetDAO
				.listSolutionSet());
		return convertSolutionSetDomainObjectsToDTO(solSetList);

	}

	/**
	 * Returns a list of solutionsets based on the users authority
	 * 
	 * @param listSolutionSet
	 *            list of solutionset to be sorted based on user authority
	 * @return a list of solutionset sorted based on user authority
	 */
	private static List<Solutionset> getSolutionSetListByRole(
			final List<Solutionset> listSolutionSet) {
		List<Solutionset> listSolutSet = listSolutionSet;

		final Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();

		final List<String> userRoles = getUserRoles(grantedAuthorities);
		// allowed to see everything
		if (userRoles.contains(AuthorityLevelEnumType.ROLE_ADMIN.toString())) {
			return listSolutSet;
			// Privelege user can be added here... special case not fully
			// defined..
		} else {
			// their role is defined as ROLE_DEFAULT
			listSolutSet = getSolutionListDefaultUsers(listSolutionSet);
		}

		return listSolutSet;
	}

	/**
	 * Returns Users roles.
	 * 
	 * @param grantedAuthorities
	 *            granted authority object containing users details
	 * @return a list of roles for the user
	 */
	private static List<String> getUserRoles(
			final Collection<? extends GrantedAuthority> grantedAuthorities) {
		final List<String> userRoles = new ArrayList<String>();

		for (final GrantedAuthority grantedAuthority : grantedAuthorities) {
			userRoles.add(grantedAuthority.getAuthority());
		}
		return userRoles;
	}

	/**
	 * Returns a list of solutionsets for normal users.
	 * 
	 * @param listSolutionSet
	 *            list of solutionsets
	 * @param grantedAuthorities
	 *            the authority of the individual signed in.
	 * @return a list of solution to be displayed for normal login
	 */
	private static List<Solutionset> getSolutionListDefaultUsers(
			final List<Solutionset> listSolutionSet) {
		final List<Solutionset> normalUsersList = new ArrayList<Solutionset>();
		for (final Solutionset solutionSet : listSolutionSet) {
			if (solutionSet.getAuthorityLevel() != null) {
				// add later when the privilege ROLE has been clearly defined
				if (!solutionSet.getAuthorityLevel().value()
						.equals(AuthorityLevelEnumType.ROLE_ADMIN.toString())) {
					normalUsersList.add(solutionSet);
				}

			} else {
				// adding solutionset as the access level has not been
				// defined for the solutionset
				normalUsersList.add(solutionSet);
			}
		}
		return normalUsersList;
	}

	/**
	 * This method is responsible for retrieving and setting each solution set
	 * information into its corresponding DTO, which will be used by the client.
	 * 
	 * @param domainSolutionSetList
	 *            a list of solution sets
	 * @return a list solutionset DTO objects, which are a simple getter and
	 *         setter class containing information for displayed by the client
	 */
	private List<SolutionSetDTO> convertSolutionSetDomainObjectsToDTO(
			final List<Solutionset> domainSolutionSetList) {
		final List<SolutionSetDTO> solutionSetDTOList = new ArrayList<SolutionSetDTO>();

		for (final Solutionset solutionSet : domainSolutionSetList) {
			final SolutionSetDTO dto = solutionSetToSolutionSetDTO(solutionSet);
			solutionSetDTOList.add(dto);
		}
		return solutionSetDTOList;
	}

	private SolutionSetDTO solutionSetToSolutionSetDTO(
			final Solutionset solutionSet) {
		final SolutionSetDTO dto = new SolutionSetDTO();
		dto.setName(solutionSet.getName());
		dto.setDescription(solutionSet.getDescription());
		dto.setSolutionSetId(solutionSet.getSolutionsetId());
		setDependentSolutionSetId(solutionSet, dto);
		return dto;
	}

	/**
	 * Retrieves and sets the id of its dependent solution set.
	 * 
	 * @param solutionSet
	 *            the solution set whose dependent id is being retrieved
	 * @param dto
	 *            the DTO, which is being set with the solution set dependent id
	 */
	private void setDependentSolutionSetId(final Solutionset solutionSet,
			final SolutionSetDTO dto) {
		if (solutionSet.getDependencies() != null) {
			final Long dependentSolSetId = solutionSetDAO
					.getSolutionSetdependency(solutionSet.getSolutionsetId())
					.get(0);// at the moment
							// there is only 1
							// dependent
			dto.setSolutionSetDependentId(dependentSolSetId);
		}
	}

	@Override
	public List<ProductDTO> listProducts() {
		final List<Product> products = solutionSetDAO.listProducts();
		for (final Product product : products) {
			// get the solsets for this product
			final long pid = product.getProductId();
			final List<ProductSolutionsetAssociation> prodSolsetAssocs = solutionSetDAO
					.getSolutionsetsAssocsInProduct(pid);
			// add the solsets to this product
			product.setProductSolutionsetAssociations(prodSolsetAssocs);
		}
		// convert and return
		return productsToProductDTOs(products);
	}

	/**
	 * Converts a list of Product into a list of ProductDTO.
	 * 
	 * @param products
	 *            the Products
	 * @return the ProductDTOs
	 */
	private List<ProductDTO> productsToProductDTOs(final List<Product> products) {
		final List<ProductDTO> productDtoList = new ArrayList<ProductDTO>();
		for (final Product product : products) {
			final ProductDTO productDTO = productToProductDTO(product);
			productDtoList.add(productDTO);
		}
		return productDtoList;
	}

	/**
	 * Converts a Product into a ProductDTO.
	 * 
	 * @param product
	 *            the Product
	 * @return the ProductDTO
	 */
	private ProductDTO productToProductDTO(final Product product) {
		final ProductDTO productDTO = new ProductDTO();
		productDTO.setDescription(product.getDescription());
		productDTO.setName(product.getName());
		productDTO.setProductId(product.getProductId());
		final List<ProductSolutionsetAssociation> assocSolsetList = product
				.getProductSolutionsetAssociations();
		productDTO
				.setProductSolutionsetAssociations(convertProductSolutionsetAssociationsToDTO(assocSolsetList));
		productDTO.setVersion(product.getVersion());
		return productDTO;
	}

	/**
	 * Converts a list of ProductSolutionsetAssociation into a list of
	 * ProductSolutionsetAssociationDTOs.
	 * 
	 * @param assocSolsetList
	 * @return
	 */
	private List<ProductSolutionsetAssociationDTO> convertProductSolutionsetAssociationsToDTO(
			final List<ProductSolutionsetAssociation> assocSolsetList) {
		final List<ProductSolutionsetAssociationDTO> productSolutionsetAssociationDTO = new ArrayList<ProductSolutionsetAssociationDTO>();
		for (final ProductSolutionsetAssociation productSolutionsetAssociation : assocSolsetList) {
			final ProductSolutionsetAssociationDTO pDto = new ProductSolutionsetAssociationDTO();
			final Solutionset solutionset = productSolutionsetAssociation
					.getSolutionset();
			final SolutionSetDTO solutionSetDTO = solutionSetToSolutionSetDTO(solutionset);
			pDto.setSolutionSetDTO(solutionSetDTO);
			productSolutionsetAssociationDTO.add(pDto);
		}
		return productSolutionsetAssociationDTO;
	}
}
