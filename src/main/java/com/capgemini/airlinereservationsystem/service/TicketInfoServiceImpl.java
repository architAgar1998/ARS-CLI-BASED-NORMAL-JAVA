package com.capgemini.airlinereservationsystem.service;

import java.util.Collection;

import com.capgemini.airlinereservationsystem.bean.TicketInfoBean;
import com.capgemini.airlinereservationsystem.dao.TicketInfoDAO;
import com.capgemini.airlinereservationsystem.dao.TicketInfoDAOImpl;

public class TicketInfoServiceImpl implements TicketInfoService{
	
	private TicketInfoDAO ticketDAO = TicketInfoDAOImpl.getTicketInfoDAO();

	public static TicketInfoService getTicketInfoService() {
		return new TicketInfoServiceImpl();
	}
	
	@Override
	public boolean bookFlightTicket(String userId, String flightId, int noOfSeats) {
		return ticketDAO.bookFlightTicket(userId, flightId, noOfSeats);
	}

	@Override
	public Collection<TicketInfoBean> viewReservedTicket(String flightId) {
		return ticketDAO.viewReservedTicket(flightId);
	}

	@Override
	public Collection<TicketInfoBean> viewCancelledTicket(String flightId) {
		return ticketDAO.viewCancelledTicket(flightId);
	}

	@Override
	public boolean cancelReservedTicket(String ticketId) {
		return ticketDAO.cancelReservedTicket(ticketId);
	}

	@Override
	public Collection<TicketInfoBean> getUserReservedTicket(String userId) {
		return ticketDAO.getUserReservedTicket(userId);
	}

}
