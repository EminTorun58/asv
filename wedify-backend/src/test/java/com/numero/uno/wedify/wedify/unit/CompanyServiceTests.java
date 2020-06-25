package com.numero.uno.wedify.wedify.unit;

import com.numero.uno.wedify.wedify.exception.ResourceAlreadyExistsException;
import com.numero.uno.wedify.wedify.exception.ResourceNotFoundException;
import com.numero.uno.wedify.wedify.features.company.Company;
import com.numero.uno.wedify.wedify.features.company.CompanyRepository;
import com.numero.uno.wedify.wedify.features.company.CompanyServiceImpl;
import com.numero.uno.wedify.wedify.features.user.IUserService;
import com.numero.uno.wedify.wedify.features.usertype.IUserTypeService;
import com.numero.uno.wedify.wedify.features.usertype.UserType;
import com.numero.uno.wedify.wedify.features.usertype.UserTypeEnum;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CompanyServiceTests {

	@Mock
	private CompanyRepository companyRepository;

	@Mock
	private IUserService userService;

	@Mock
	private IUserTypeService userTypeService;

	@Mock
	private PasswordEncoder passwordEncoder;

	private CompanyServiceImpl companyService;

	@BeforeEach
	void beforeEach() {
		companyService = new CompanyServiceImpl(companyRepository, userService, userTypeService, passwordEncoder);
	}

	@Test
	void testGetAllCompanies_returnsAllCompanies() {
		Mockito.when(companyRepository.findAll()).thenReturn(Collections.singletonList(getTestData()));

		Assertions.assertEquals(Collections.singletonList(getTestData()), companyService.getAllCompanies());
	}

	@Test
	void testGetCompanyById_returnsCompany() {
		Mockito.when(companyRepository.findById(1L)).thenReturn(Optional.of(getTestData()));
		MatcherAssert.assertThat(companyService.getCompanyById(1L), Matchers.samePropertyValuesAs(getTestData()));
	}

	@Test
	void testGetNonExistentCompanyById_returnsResourceNotFoundException() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> companyService.getCompanyById(1L));
	}

	@Test
	void testSaveCompany_returnsCompany() {
		Mockito.when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
		Mockito.when(companyRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
		Mockito.when(userTypeService.getUserTypeByType(UserTypeEnum.COMPANY)).thenReturn(new UserType(1L, UserTypeEnum.COMPANY));

		Company savedCompany = companyService.saveCompany(getTestData());

		Company expectedCompany = getTestData();
		expectedCompany.setPassword(passwordEncoder.encode(expectedCompany.getPassword()));


		MatcherAssert.assertThat(savedCompany, Matchers.samePropertyValuesAs(expectedCompany));
	}

	@Test
	void testSaveCompanyAlreadyExists_returnsResourceAlreadyExistsException() {
		Mockito.doThrow(ResourceAlreadyExistsException.class).when(userService).validateUserDoesntExist(any());
		Assertions.assertThrows(ResourceAlreadyExistsException.class, () -> companyService.saveCompany(getTestData()));
	}

	private Company getTestData() {
		return new Company(1L, "venues@company.com", "pass123", new UserType(1L, UserTypeEnum.COMPANY),
				"Venue", "street", "20", "1190PT", "Amsterdam", "0201234567",
				"1234567890", "venue.com");
	}

}
