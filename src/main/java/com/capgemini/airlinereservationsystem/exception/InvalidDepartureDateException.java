package com.capgemini.airlinereservationsystem.exception;

public class InvalidDepartureDateException extends RuntimeException {
	private static String message = "Invalid departure date";
	
	public InvalidDepartureDateException(){
		this(message);
	}
	
	public InvalidDepartureDateException(String messsage) {
		super(message);
	}
}
