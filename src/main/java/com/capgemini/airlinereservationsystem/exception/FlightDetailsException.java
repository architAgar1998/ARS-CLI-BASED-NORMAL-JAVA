package com.capgemini.airlinereservationsystem.exception;

public class FlightDetailsException extends RuntimeException{
	static String message = "invalid flight details";
	public FlightDetailsException() {
		this(message);
	}
	public FlightDetailsException(String message) {
		super(message);
	}
}
