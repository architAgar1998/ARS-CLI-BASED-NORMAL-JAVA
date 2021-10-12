package com.capgemini.airlinereservationsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.capgemini.airlinereservationsystem.bean.FlightInfoBean;
import com.capgemini.airlinereservationsystem.dao.FlightInfoDAO;
import com.capgemini.airlinereservationsystem.dao.FlightInfoDAOImpl;
import com.capgemini.airlinereservationsystem.exception.FlightDetailsException;

class FlightInfoDaoImplTest {
	private FlightInfoDAO flightInfoDAO = new FlightInfoDAOImpl();
	private FlightInfoBean flightBean = null;

	@BeforeEach
	void createObject() {
		flightBean = new FlightInfoBean();
	}

	@AfterEach
	void destroyObject() {
		flightBean = null;
	}

	@Test
	@DisplayName("To test getAllFlight method")
	public void testGetAllFlight() {
		Collection<FlightInfoBean> allFlights = flightInfoDAO.getAllFlight();
		assertNotNull(allFlights);
	}

	@Test
	@DisplayName("To test getAllFlight method based on source and destination")
	public void testGetFlight() {
		Collection<FlightInfoBean> expected = flightInfoDAO.getAllFlight("mysore", "andhra");
		assertNotNull(expected);

	}

	@Test
	@DisplayName("to test add flight method")
	public void testAddFlight() {
		flightBean.setFlightId("flight123");
		flightBean.setFlightCompany("GoAir");
		flightBean.setDepartureDate("21/05/2020");
		flightBean.setDepartureTime("4:00");
		flightBean.setArrivalDate("22/05/2020");
		flightBean.setArrivalTime("7:00");
		flightBean.setSource("mysore");
		flightBean.setDestination("andhra");
		flightBean.setFare(6600.00);
		flightBean.setAvailableSeat(56);
		flightBean.setTotalSeats(56);

		try {
			boolean expected = flightInfoDAO.addFlight(flightBean);
			assertEquals(expected, true);
		} catch (Exception e) {
			assertThrows(FlightDetailsException.class, () -> {
				flightInfoDAO.addFlight(flightBean);
			});
		}
	}

	@Test
	@DisplayName("to test update flight details method")
	public void testUpdateFlightDetails() {
		flightBean.setFlightId("fight123");
		flightBean.setFlightCompany("asia12");
		flightBean.setDepartureDate("21/05/2020");
		flightBean.setDepartureTime("4:00");
		flightBean.setArrivalDate("22/05/2020");
		flightBean.setArrivalTime("7:00");
		flightBean.setSource("mysore");
		flightBean.setDestination("andhra");
		flightBean.setFare(6600.00);
		flightBean.setAvailableSeat(56);
		flightBean.setTotalSeats(56);
		try {
			boolean expected = flightInfoDAO.updateFlightDetails(flightBean.getFlightId(), flightBean);
		} catch (Exception e) {
			assertThrows(FlightDetailsException.class, () -> {
				flightInfoDAO.updateFlightDetails(flightBean.getFlightId(), flightBean);
			});
		}

	}

}
