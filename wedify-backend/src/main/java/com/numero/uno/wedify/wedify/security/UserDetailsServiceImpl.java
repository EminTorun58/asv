package com.numero.uno.wedify.wedify.security;

import com.numero.uno.wedify.wedify.exception.ResourceNotFoundException;
import com.numero.uno.wedify.wedify.features.user.IUserService;
import com.numero.uno.wedify.wedify.features.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.stereotype.Component;

/**
 * Facility to associate user with password and roles setup in the database.
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	private final IUserService userService;

	@Autowired
	public UserDetailsServiceImpl(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		try {
			User user = userService.getUserByEmail(s);
			return new UserPrincipal(
					user.getId(),
					user.getPassword(),
					user.getEmail(),
					user.getUserType()
			);

		} catch (ResourceNotFoundException e) {
			throw new InvalidGrantException("Bad credentials");
		}
	}
}
