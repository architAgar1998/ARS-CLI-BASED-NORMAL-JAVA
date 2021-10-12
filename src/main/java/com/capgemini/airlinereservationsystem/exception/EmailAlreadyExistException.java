package com.capgemini.airlinereservationsystem.exception;

public class EmailAlreadyExistException extends RuntimeException {
	static String message = "This email address is already exist please try with another email adddress";

	public EmailAlreadyExistException() {
		this(message);
	}

	public EmailAlreadyExistException(String message) {
		super(message);
	}
}
