package com.capgemini.airlinereservationsystem.exception;

public class UserIdAlreadyExistException extends RuntimeException {

	static String message = "This User Id is already exist please try with another User Id";

	public UserIdAlreadyExistException() {
		this(message);
	}

	public UserIdAlreadyExistException(String message) {
		super(message);
	}
}
