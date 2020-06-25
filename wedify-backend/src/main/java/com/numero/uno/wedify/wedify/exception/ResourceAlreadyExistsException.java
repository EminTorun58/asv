package com.numero.uno.wedify.wedify.exception;

import lombok.Getter;

@Getter
public class ResourceAlreadyExistsException extends RuntimeException {

	// the type of the requested resource.
	private final String resourceType;

	// the field that causes the exception.
	private final String target;

	// the value that caused the exception.
	private final String value;

	public ResourceAlreadyExistsException(Class resourceType, String target, String value) {
		this.resourceType = resourceType.getSimpleName();
		this.target = target;
		this.value = value;
	}

}
