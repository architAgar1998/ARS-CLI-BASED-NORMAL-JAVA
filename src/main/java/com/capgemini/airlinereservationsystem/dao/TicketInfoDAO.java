package com.capgemini.airlinereservationsystem.dao;

import java.util.Collection;

import com.capgemini.airlinereservationsystem.bean.TicketInfoBean;

public interface TicketInfoDAO {
	public boolean bookFlightTicket(String userId, String flightId, int noOfSeats);
	public Collection<TicketInfoBean> viewReservedTicket(String flightId);
	public Collection<TicketInfoBean> viewCancelledTicket(String flightId);
	public boolean cancelReservedTicket(String ticketId);
	public Collection<TicketInfoBean> getUserReservedTicket(String userId);
}
