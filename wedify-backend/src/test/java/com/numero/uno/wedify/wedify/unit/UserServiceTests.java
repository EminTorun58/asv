package com.numero.uno.wedify.wedify.unit;

import com.numero.uno.wedify.wedify.exception.ResourceAlreadyExistsException;
import com.numero.uno.wedify.wedify.exception.ResourceNotFoundException;
import com.numero.uno.wedify.wedify.features.company.Company;
import com.numero.uno.wedify.wedify.features.user.User;
import com.numero.uno.wedify.wedify.features.user.UserRepository;
import com.numero.uno.wedify.wedify.features.user.UserServiceImpl;
import com.numero.uno.wedify.wedify.features.usertype.UserType;
import com.numero.uno.wedify.wedify.features.usertype.UserTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTests {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	void testValidateUserDoesntExist_throwsUserNotFoundException() {
		Mockito.when(userRepository.findByEmail("food@company.com")).thenReturn(Optional.of(getTestData()));

		Assertions.assertThrows(ResourceAlreadyExistsException.class, () -> userService.validateUserDoesntExist("food@company.com"));
	}

	@Test
	void testValidateUserDoesntExist_throwsNothing() {
		Assertions.assertDoesNotThrow(() -> userService.validateUserDoesntExist("food@company.com"));
	}

	@Test
	void testDeleteCompany_invokedCompanyRepositoryDelete() {
		User companyToDelete = getTestData();
		Mockito.when(userRepository.findById(companyToDelete.getId())).thenReturn(Optional.of(getTestData()));

		userService.deleteUser(1L);
		Mockito.verify(userRepository).delete(getTestData());
	}

	@Test
	void testDeleteNonExistingCompany_throwsResourceNotFoundException() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(1L));
	}

	private User getTestData() {
		return new User(1L, "greatfood@company.com", "pass123", new UserType(1L, UserTypeEnum.COMPANY));
	}

}
