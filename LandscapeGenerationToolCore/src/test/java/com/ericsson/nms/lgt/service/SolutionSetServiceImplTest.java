package com.ericsson.nms.lgt.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ericsson.nms.lgt.dao.SolutionSetDAO;
import com.ericsson.nms.lgt.domain.landscape.*;
import com.ericsson.nms.lgt.service.dto.ProductDTO;
import com.ericsson.nms.lgt.service.dto.SolutionSetDTO;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SecurityContextHolder.class)
public class SolutionSetServiceImplTest {
	
	/**
	 * If running this test in an IDE add the following to VM arguments:
	 * -XX:-UseSplitVerifier
	 */

	private SolutionSetServiceImpl service;
	private SolutionSetDTO solDTO1;
	private SolutionSetDTO solDTO2;
	private Solutionset sol1;
	private Solutionset sol2;
	private Solutionset sol3;
	private SolutionSetDAO solutionSetDAOMock;
	private SolutionsetDependencyListType solSetDepListType1;
	private List<Solutionset> solutionsetList;
	private List<SolutionSetDTO> solutionSetDtoList;

 
 
	@Before
	public void setup() {
		solutionSetDAOMock = mock(SolutionSetDAO.class);
		// class under test
		this.service = new SolutionSetServiceImpl(solutionSetDAOMock);

		solSetDepListType1 = mock(SolutionsetDependencyListType.class);
		this.solDTO1 = new SolutionSetDTO();
		this.solDTO2 = new SolutionSetDTO();
		this.sol1 = mock(Solutionset.class);
		this.sol2 = mock(Solutionset.class);
		this.sol3 = mock(Solutionset.class);
		PowerMockito.mockStatic(SecurityContextHolder.class);
		SecurityContext context = mock(SecurityContext.class);
		Authentication auth = mock(Authentication.class);
		// this.roles = mock(Collection.class);
		GrantedAuthority grantedAuthority = mock(GrantedAuthority.class);
		when(grantedAuthority.getAuthority()).thenReturn("");
		when(sol1.getName()).thenReturn("solDTO1");
		when(sol2.getName()).thenReturn("solDTO2");
		when(sol3.getName()).thenReturn("solDTO3");
		when(sol1.getSolutionsetId()).thenReturn((long) 1);
		when(sol2.getSolutionsetId()).thenReturn((long) 2);

		when(sol1.getAuthorityLevel()).thenReturn(AuthorityLevelEnumType.ROLE_DEFAULT);
		when(sol2.getAuthorityLevel()).thenReturn(null);//was null
		when(sol3.getAuthorityLevel()).thenReturn(AuthorityLevelEnumType.ROLE_ADMIN);


		when(sol1.getDescription()).thenReturn("solDTO1 Description");

		when(SecurityContextHolder.getContext()).thenReturn(context);

		when(context.getAuthentication()).thenReturn(auth);
		Collection roles = new ArrayList<>();
		roles.add(grantedAuthority);
		when(auth.getAuthorities()).thenReturn(roles);

//		//when(grantedAuthority.getAuthority()).thenReturn("");

		solutionsetList = new LinkedList<Solutionset>();
		solutionsetList.add(sol1);
		solutionsetList.add(sol2);
		solutionsetList.add(sol3);
	}

	@Test
	public void testCorrectNumberOfSolutionSetsAddedToList() {
		when(this.solutionSetDAOMock.listSolutionSet()).thenReturn(
				solutionsetList);
		solutionSetDtoList = this.service.listSolutionSet();
		PowerMockito.verifyStatic();
		SecurityContextHolder.getContext();
		assertEquals(2, solutionSetDtoList.size());
	}

	@Test
	public void testCorrectNumberOfSolutionSetsAddedToListRoleAdmin() {
		// SolutionSetServiceImpl impl = mock(SolutionSetServiceImpl.class);

		PowerMockito.mockStatic(SecurityContextHolder.class);
		SecurityContext context = mock(SecurityContext.class);
		Authentication auth = mock(Authentication.class);
		// this.roles = mock(Collection.class);
		GrantedAuthority grantedAuthority = mock(GrantedAuthority.class);
		when(grantedAuthority.getAuthority()).thenReturn("ROLE_ADMIN");
		when(SecurityContextHolder.getContext()).thenReturn(context);

		when(context.getAuthentication()).thenReturn(auth);
		Collection roles = new ArrayList<>();
		roles.add(grantedAuthority);
		when(auth.getAuthorities()).thenReturn(roles);
		
		when(this.solutionSetDAOMock.listSolutionSet()).thenReturn(
				solutionsetList);

		when(sol1.getAuthorityLevel()).thenReturn(
				AuthorityLevelEnumType.ROLE_ADMIN);
		when(sol2.getAuthorityLevel()).thenReturn(
				AuthorityLevelEnumType.ROLE_DEFAULT);

		solutionSetDtoList = this.service.listSolutionSet();
		PowerMockito.verifyStatic();
		SecurityContextHolder.getContext();

		assertEquals(3, solutionSetDtoList.size());
	}

	@Test
	public void testListAllSolutionSets() {
		final List<Long> dependencyIdList = new LinkedList<Long>();
		dependencyIdList.add((long) 3);

		when(sol1.getDependencies()).thenReturn(solSetDepListType1);
		when(
				solutionSetDAOMock.getSolutionSetdependency(sol1
						.getSolutionsetId())).thenReturn(dependencyIdList);
		when(this.solutionSetDAOMock.listSolutionSet()).thenReturn(
				solutionsetList);

		solutionSetDtoList = this.service.listSolutionSet();
		solDTO1.setName("solDTO1");
		solDTO2.setName("solDTO2");
		solDTO1.setSolutionSetId((long) 1);
		solDTO2.setSolutionSetId((long) 2);
		solDTO1.setSolutionSetDependentId((long) 3);

		solDTO1.setDescription("solDTO1 Description");

//		assertEquals(solDTO1.getName(), solutionSetDtoList.get(0).getName());
		assertEquals(solDTO1.getSolutionSetId(), solutionSetDtoList.get(0)
				.getSolutionSetId());
		assertEquals(solDTO1.getSolutionSetDependentId(), solutionSetDtoList
				.get(0).getSolutionSetDependentId());
		assertEquals(solDTO1.getDescription(), solutionSetDtoList.get(0)
				.getDescription());
	}

	@Test
	public void testListAllSolutionSetsNoSolutionSetDependency() {
		// returning no dependency
		when(sol1.getDependencies()).thenReturn(null);
		when(sol2.getDependencies()).thenReturn(null);
		// return mocked result set on read...
		when(this.solutionSetDAOMock.listSolutionSet()).thenReturn(
				solutionsetList);

		solutionSetDtoList = this.service.listSolutionSet();
		solDTO1.setName("solDTO1");
		solDTO2.setName("solDTO2");
		solDTO1.setSolutionSetId((long) 1);
		solDTO2.setSolutionSetId((long) 2);
		// being set to zero as there is no dependency between solution sets,
		// therefore it is not being set.
		solDTO1.setSolutionSetDependentId((long) 0);
		solDTO2.setSolutionSetDependentId((long) 0);

//		assertEquals(solDTO1.getName(), solutionSetDtoList.get(0).getName());
		assertEquals(solDTO1.getSolutionSetId(), solutionSetDtoList.get(0)
				.getSolutionSetId());
		assertEquals(solDTO1.getSolutionSetDependentId(), solutionSetDtoList
				.get(0).getSolutionSetDependentId());
	}

	@Test
	public void testListProducts() {
		Product product1 = new Product();
		product1.setName("product1");
		product1.setProductId(1);

		List<ProductSolutionsetAssociation> assocs = new ArrayList<ProductSolutionsetAssociation>();
		ProductSolutionsetAssociation assoc = new ProductSolutionsetAssociation();

		Solutionset solSet1 = new Solutionset();
		assoc.setSolutionset(solSet1);
		assocs.add(assoc);

		Product product2 = new Product();
		product2.setName("product2");
		product2.setProductId(2);

		List<Product> products = new ArrayList<Product>();
		products.add(product1);
		products.add(product2);

		when(this.solutionSetDAOMock.listProducts()).thenReturn(products);
		when(
				this.solutionSetDAOMock.getSolutionsetsAssocsInProduct(product1
						.getProductId())).thenReturn(assocs);

		List<ProductDTO> productsRes = this.service.listProducts();

		assertEquals(2, productsRes.size());
	}
}