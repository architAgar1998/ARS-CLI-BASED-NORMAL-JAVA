package com.capgemini.airlinereservationsystem.service;

import java.util.Collection;

import com.capgemini.airlinereservationsystem.bean.FlightInfoBean;
import com.capgemini.airlinereservationsystem.dao.FlightInfoDAO;
import com.capgemini.airlinereservationsystem.dao.FlightInfoDAOImpl;

public class FlightInfoServiceImpl implements FlightInfoService{
	
	private FlightInfoDAO flightDAO = FlightInfoDAOImpl.getFlightInfoDAO();

	public static FlightInfoService getFlightInfoService() {
		return new FlightInfoServiceImpl();
	}
	
	@Override
	public Collection<FlightInfoBean> getAllFlight(String source, String Destination) {
		return flightDAO.getAllFlight(source, Destination);
	}

	@Override
	public boolean addFlight(FlightInfoBean flight) {
		return flightDAO.addFlight(flight);
	}

	@Override
	public boolean updateFlightDetails(String flightId, FlightInfoBean flight) {
		return flightDAO.updateFlightDetails(flightId, flight);
	}

	@Override
	public Collection<FlightInfoBean> getAllFlight() {
		return flightDAO.getAllFlight();
	}

}
