package com.capgemini.airlinereservationsystem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.capgemini.airlinereservationsystem.bean.FlightInfoBean;
import com.capgemini.airlinereservationsystem.dao.FlightInfoDAO;
import com.capgemini.airlinereservationsystem.dao.FlightInfoDAOImpl;
import com.capgemini.airlinereservationsystem.exception.FlightDetailsException;
import com.capgemini.airlinereservationsystem.service.FlightInfoService;
import com.capgemini.airlinereservationsystem.service.FlightInfoServiceImpl;

class FlightInfoServiceImplTest {
	private FlightInfoService flightInfoService = new FlightInfoServiceImpl();
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
		Collection<FlightInfoBean> allFlights = flightInfoService.getAllFlight();
		assertNotNull(allFlights);
	}

	@Test
	@DisplayName("To test getAllFlight method based on source and destination")
	public void testGetFlight() {
		Collection<FlightInfoBean> expected = flightInfoService.getAllFlight("mysore", "andhra");
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
			boolean expected = flightInfoService.addFlight(flightBean);
			assertEquals(expected, true);
		} catch (Exception e) {
			assertThrows(FlightDetailsException.class, () -> {
				flightInfoService.addFlight(flightBean);
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
			boolean expected = flightInfoService.updateFlightDetails(flightBean.getFlightId(), flightBean);
		} catch (Exception e) {
			assertThrows(FlightDetailsException.class, () -> {
				flightInfoService.updateFlightDetails(flightBean.getFlightId(), flightBean);
			});
		}

	}


}
