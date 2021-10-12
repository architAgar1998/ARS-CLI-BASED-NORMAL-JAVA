package com.capgemini.airlinereservationsystem.dao;

import java.util.Collection;

import com.capgemini.airlinereservationsystem.bean.FlightInfoBean;

public interface FlightInfoDAO {
	public Collection<FlightInfoBean> getAllFlight(String source, String destination);
	public boolean addFlight(FlightInfoBean flight);
	public boolean updateFlightDetails(String flightId, FlightInfoBean flight);
	public Collection<FlightInfoBean> getAllFlight();
}
