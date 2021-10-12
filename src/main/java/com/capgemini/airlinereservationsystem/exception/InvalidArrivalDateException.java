package com.capgemini.airlinereservationsystem.exception;

public class InvalidArrivalDateException extends RuntimeException{
	static String message = "Invalid date must be greate or equal then departure date";
	
	public InvalidArrivalDateException() {
		this(message);
	}
	
	public InvalidArrivalDateException(String message){
		super(message);
	}
	
}
