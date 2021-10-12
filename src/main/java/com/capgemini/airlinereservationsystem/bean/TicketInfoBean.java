package com.capgemini.airlinereservationsystem.bean;

import javax.persistence.Entity;

import lombok.Data;

public class TicketInfoBean {
	private int noOfSeats;
	private String ticketId;
	private UserInfoBean userDetails;
	private FlightInfoBean flightDetails;
	private double totalFare;
	private String status;

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public UserInfoBean getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserInfoBean userDetails) {
		this.userDetails = userDetails;
	}

	public FlightInfoBean getFlightDetails() {
		return flightDetails;
	}

	public void setFlightDetails(FlightInfoBean flightDetails) {
		this.flightDetails = flightDetails;
	}

	public double getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(double totalFare) {
		this.totalFare = totalFare;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
