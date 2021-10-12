package com.capgemini.airlinereservationsystem.exception;


public class FlightIdAlreadyExistException extends RuntimeException {
	static String message = "This flight id is already exist please try with another one";

	public FlightIdAlreadyExistException() {
		this(message);
	}

	public FlightIdAlreadyExistException(String message) {
		super(message);
	}
}
