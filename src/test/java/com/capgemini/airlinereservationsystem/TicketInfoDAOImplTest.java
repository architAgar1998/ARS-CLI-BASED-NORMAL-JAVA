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
import com.capgemini.airlinereservationsystem.dao.TicketInfoDAO;
import com.capgemini.airlinereservationsystem.dao.TicketInfoDAOImpl;
import com.capgemini.airlinereservationsystem.exception.TicketDetailsException;

class TicketInfoDAOImplTest {

	private TicketInfoDAO ticketInfoDAO = new TicketInfoDAOImpl();
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
			boolean expected = ticketInfoDAO.bookFlightTicket(ticketBean.getTicketId(), flightBean.getFlightId(),
					ticketBean.getNoOfSeats());
			assertEquals(expected, true);
		} catch (Exception e) {
			assertThrows(TicketDetailsException.class, () -> {
				ticketInfoDAO.bookFlightTicket(ticketBean.getTicketId(), flightBean.getFlightId(),
						ticketBean.getNoOfSeats());
			});
		}
	}

	@Test
	@DisplayName("To test viewReservedTicket method")
	public void testViewReservedTicket() {
		Collection<TicketInfoBean> expected = ticketInfoDAO.viewReservedTicket(flightBean.getFlightId());
		assertNotNull(expected);
	}

	@Test
	@DisplayName("To test viewCancelledTicket method")
	public void testViewCancelledTicket() {
		Collection<TicketInfoBean> expected = ticketInfoDAO.viewCancelledTicket(flightBean.getFlightId());
		assertNotNull(expected);
	}

	@Test
	@DisplayName("To test cancelReservedTicket method")
	public void testCancelReservedTicket() {
		ticketBean.setTicketId("ticket123");

		try {
			boolean expected = ticketInfoDAO.cancelReservedTicket("architagar#AirIndia110");
			assertEquals(expected, true);
		} catch (Exception e) {
			assertThrows(TicketDetailsException.class, () -> {
				ticketInfoDAO.cancelReservedTicket(ticketBean.getTicketId());
			});
		}

	}

	@Test
	@DisplayName("To test getUserReservedTicket method")
	public void testGetReservedTicket() {
		Collection<TicketInfoBean> expected = ticketInfoDAO.getUserReservedTicket(userBean.getUserId());
		assertNotNull(expected);
	}

}
