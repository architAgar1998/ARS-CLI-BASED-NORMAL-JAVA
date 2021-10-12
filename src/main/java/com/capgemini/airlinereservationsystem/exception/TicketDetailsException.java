package com.capgemini.airlinereservationsystem.exception;

public class TicketDetailsException extends RuntimeException {
	static String message = "unable to book ticket";

	public TicketDetailsException() {
		this(message);
	}

	public TicketDetailsException(String message) {
		super(message);
	}

}
