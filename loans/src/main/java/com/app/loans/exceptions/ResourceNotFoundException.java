package com.app.loans.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3393964057574295939L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}
