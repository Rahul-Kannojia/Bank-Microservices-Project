package com.app.loans.exceptions;

public class LoanAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = -5606333477562098252L;

	public LoanAlreadyExistsException(String message){
        super(message);
    }
}
