package com.numero.uno.wedify.wedify.unit;

import com.numero.uno.wedify.wedify.exception.ResourceNotFoundException;
import com.numero.uno.wedify.wedify.features.usertype.UserType;
import com.numero.uno.wedify.wedify.features.usertype.UserTypeEnum;
import com.numero.uno.wedify.wedify.features.usertype.UserTypeRepository;
import com.numero.uno.wedify.wedify.features.usertype.UserTypeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserTypeServiceTests {

	@Mock
	private UserTypeRepository userTypeRepository;

	private UserTypeServiceImpl userTypeService;

	@BeforeEach
	void beforeEach() {
		userTypeService = new UserTypeServiceImpl(userTypeRepository);
	}

	@Test
	void testGetAllUserTypes_returnsTestData() {
		Mockito.when(userTypeRepository.findAll()).thenReturn(getTestData());

		Assertions.assertEquals(getTestData(), userTypeService.getAllUserTypes());
	}

	@Test
	void testGetUserTypeById_returnsCompanyType() {
		Mockito.when(userTypeRepository.findById(1L)).thenReturn(Optional.of(getTestData().get(0)));
		Assertions.assertEquals(getTestData().get(0), userTypeService.getUserTypeById(1L));
	}

	@Test
	void testGetUserTypeById_throwsResourceNotFoundException() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> userTypeService.getUserTypeById(1L));
	}

	@Test
	void testGetUserTypeByType_returnsCompanyType() {
		Mockito.when(userTypeRepository.findByType(UserTypeEnum.COMPANY)).thenReturn(Optional.of(getTestData().get(0)));
		Assertions.assertEquals(getTestData().get(0), userTypeService.getUserTypeByType(UserTypeEnum.COMPANY));
	}

	private List<UserType> getTestData() {
		List<UserType> userTypes = new ArrayList<>();
		userTypes.add(new UserType(1L, UserTypeEnum.COMPANY));
		userTypes.add(new UserType(2L, UserTypeEnum.CUSTOMER));
		userTypes.add(new UserType(3L, UserTypeEnum.ADMIN));

		return userTypes;
	}

}
