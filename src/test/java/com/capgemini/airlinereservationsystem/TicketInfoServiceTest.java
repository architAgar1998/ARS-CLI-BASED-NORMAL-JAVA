package com.capgemini.airlinereservationsystem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.capgemini.airlinereservationsystem.bean.FlightInfoBean;
import com.capgemini.airlinereservationsystem.bean.TicketInfoBean;
import com.capgemini.airlinereservationsystem.bean.UserInfoBean;
import com.capgemini.airlinereservationsystem.exception.TicketDetailsException;
import com.capgemini.airlinereservationsystem.service.TicketInfoService;
import com.capgemini.airlinereservationsystem.service.TicketInfoServiceImpl;

class TicketInfoServiceTest {
	private TicketInfoService ticketInfoService = new TicketInfoServiceImpl();
	private TicketInfoBean ticketBean = null;
	private FlightInfoBean flightBean = new FlightInfoBean();
	private UserInfoBean userBean = new UserInfoBean();

	@BeforeEach
	void createObject() {
		ticketBean = new TicketInfoBean();
	}

	@AfterEach
	void destroyObject() {
		ticketBean = null;
	}

	@Test
	@DisplayName("To test bookFlightTicket method")
	public void testBookFlightTicket() {
		flightBean.setFlightId("AirIndia110");
		userBean.setUserId("tyrye119");
		ticketBean.setNoOfSeats(4);
		ticketBean.setTotalFare(3000);
		ticketBean.setStatus("reserved");
		ticketBean.setTicketId("ticket123");
		try {
			boolean expected = ticketInfoService.bookFlightTicket(ticketBean.getTicketId(), flightBean.getFlightId(),
					ticketBean.getNoOfSeats());
			assertEquals(expected, true);
		} catch (Exception e) {
			assertThrows(TicketDetailsException.class, () -> {
				ticketInfoService.bookFlightTicket(ticketBean.getTicketId(), flightBean.getFlightId(),
						ticketBean.getNoOfSeats());
			});
		}
	}

	@Test
	@DisplayName("To test viewReservedTicket method")
	public void testViewReservedTicket() {
		Collection<TicketInfoBean> expected = ticketInfoService.viewReservedTicket(flightBean.getFlightId());
		assertNotNull(expected);
	}

	@Test
	@DisplayName("To test viewCancelledTicket method")
	public void testViewCancelledTicket() {
		Collection<TicketInfoBean> expected = ticketInfoService.viewCancelledTicket(flightBean.getFlightId());
		assertNotNull(expected);
	}

	@Test
	@DisplayName("To test cancelReservedTicket method")
	public void testCancelReservedTicket() {
		ticketBean.setTicketId("ticket123");

		try {
			boolean expected = ticketInfoService.cancelReservedTicket("architagar#AirIndia110");
			assertEquals(expected, true);
		} catch (Exception e) {
			assertThrows(TicketDetailsException.class, () -> {
				ticketInfoService.cancelReservedTicket(ticketBean.getTicketId());
			});
		}

	}

	@Test
	@DisplayName("To test getUserReservedTicket method")
	public void testGetReservedTicket() {
		Collection<TicketInfoBean> expected = ticketInfoService.getUserReservedTicket(userBean.getUserId());
		assertNotNull(expected);
	}

}
