package com.capgemini.airlinereservationsystem.exception;

public class UserDetailsException extends RuntimeException {
	static String message = "This userId not found";

	public UserDetailsException() {
		this(message);
	}

	public UserDetailsException(String message) {
		super(message);
	}

}
