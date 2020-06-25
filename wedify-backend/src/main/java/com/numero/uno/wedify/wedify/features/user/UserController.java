package com.numero.uno.wedify.wedify.features.user;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

	private final IUserService userService;

	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@DeleteMapping(path = "/users/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("isAuthenticated() and authentication.principal.id == #id")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}

	@GetMapping(path = "/users")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("isAuthenticated()")
	public User getUserByAuthentication() {
		return userService.getUserByAuthentication();
	}

}
