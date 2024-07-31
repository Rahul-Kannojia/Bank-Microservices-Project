package com.app.accounts.exceptions;

public class CustomerAlreadyExistsException extends RuntimeException{

   
	private static final long serialVersionUID = 1L;

public CustomerAlreadyExistsException(String message){
        super(message);
    }
}
