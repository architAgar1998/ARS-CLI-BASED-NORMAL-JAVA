package com.capgemini.airlinereservationsystem.service;

import java.util.Collection;

import com.capgemini.airlinereservationsystem.bean.FlightInfoBean;

public interface FlightInfoService {
	public Collection<FlightInfoBean> getAllFlight(String source, String Destination);
	public boolean addFlight(FlightInfoBean flight);
	public boolean updateFlightDetails(String flightId, FlightInfoBean flight);
	public Collection<FlightInfoBean> getAllFlight();
}
