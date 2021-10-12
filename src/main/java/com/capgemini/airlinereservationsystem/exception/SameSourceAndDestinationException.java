package com.capgemini.airlinereservationsystem.exception;

public class SameSourceAndDestinationException extends RuntimeException {
	private static String message = "destination and source should not be equal";

	public SameSourceAndDestinationException() {
		this(message);
	}

	public SameSourceAndDestinationException(String message) {
		super(message);
	}
}
