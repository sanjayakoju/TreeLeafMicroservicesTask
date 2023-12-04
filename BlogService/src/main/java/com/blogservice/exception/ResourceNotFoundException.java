package com.blogservice.exception;

public class ResourceNotFoundException extends RuntimeException {
    private String message;
    public ResourceNotFoundException() {
        super("Resource Not Found On Server!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
