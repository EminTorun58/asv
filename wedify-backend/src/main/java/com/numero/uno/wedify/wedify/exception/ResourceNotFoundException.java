package com.numero.uno.wedify.wedify.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    // the type of the requested resource.
    private final String resourceType;

    // The id of the requested resource.
   transient private Object identifier;

    public ResourceNotFoundException(String resourceType, Object identifier) {
        this.resourceType = resourceType;
        this.identifier = identifier;
    }
}
