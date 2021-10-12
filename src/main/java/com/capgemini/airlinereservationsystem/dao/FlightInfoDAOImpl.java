package com.capgemini.airlinereservationsystem.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.capgemini.airlinereservationsystem.bean.FlightInfoBean;
import com.capgemini.airlinereservationsystem.db.InfoDB;

public class FlightInfoDAOImpl implements FlightInfoDAO {

	public static FlightInfoDAO getFlightInfoDAO() {
		return new FlightInfoDAOImpl();
	}

	@Override
	public Collection<FlightInfoBean> getAllFlight(String source, String destination) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		ArrayList<FlightInfoBean> allFlight = new ArrayList<FlightInfoBean>();
		for (FlightInfoBean flightDetails : InfoDB.FLIGHT_INFO_DB) {
			if (flightDetails.getSource().equalsIgnoreCase(source) && flightDetails.getDestination().equalsIgnoreCase(destination)) {
				try {
					if (date.compareTo(format.parse(flightDetails.getDepartureDate())) < 0) {
						allFlight.add(flightDetails);
					}
				} catch (ParseException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		return allFlight;
	}

	@Override
	public boolean addFlight(FlightInfoBean flight) {
		if (InfoDB.FLIGHT_INFO_DB.add(flight)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateFlightDetails(String flightId, FlightInfoBean flight) {
		FlightInfoBean flightInfoBean = null;
		for (FlightInfoBean flightDetail : InfoDB.FLIGHT_INFO_DB) {
			if (flightDetail.getFlightId().equals(flightId)) {
				flightInfoBean = flightDetail;
			} else {
				return false;
			}
		}
		flightInfoBean.setArrivalDate(flight.getArrivalDate());
		flightInfoBean.setArrivalTime(flight.getArrivalTime());
		flightInfoBean.setDepartureDate(flight.getDepartureDate());
		flightInfoBean.setDepartureTime(flight.getDepartureTime());
		flightInfoBean.setDestination(flight.getDestination());
		flightInfoBean.setSource(flight.getSource());

		return true;
	}

	@Override
	public Collection<FlightInfoBean> getAllFlight() {
		return InfoDB.FLIGHT_INFO_DB;
	}

}
