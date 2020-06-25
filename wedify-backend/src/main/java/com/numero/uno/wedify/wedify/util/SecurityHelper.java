package com.numero.uno.wedify.wedify.util;

import com.numero.uno.wedify.wedify.security.UserPrincipal;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityHelper {

	public static UserPrincipal getPrincipal() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return (UserPrincipal) authentication.getPrincipal();
	}

	public static void validatePrincipalIsCompany(Long companyId) {
		if (!SecurityHelper.getPrincipal().getId().equals(companyId)) {
			throw new AccessDeniedException("Unauthorized to make this action.");
		}
	}

}
