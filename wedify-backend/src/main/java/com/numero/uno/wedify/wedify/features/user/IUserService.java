package com.numero.uno.wedify.wedify.features.user;

public interface IUserService {

	User getUserByEmail(String email);

	User getUserById(Long id);

	void deleteUser(Long id);

	void validateUserDoesntExist(String email);

	User getUserByAuthentication();

}
