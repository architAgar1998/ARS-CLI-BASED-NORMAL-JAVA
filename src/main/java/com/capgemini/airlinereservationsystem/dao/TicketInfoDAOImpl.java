package com.capgemini.airlinereservationsystem.dao;

import java.util.Collection;
import java.util.HashSet;

import com.capgemini.airlinereservationsystem.bean.FlightInfoBean;
import com.capgemini.airlinereservationsystem.bean.TicketInfoBean;
import com.capgemini.airlinereservationsystem.bean.UserInfoBean;
import com.capgemini.airlinereservationsystem.db.InfoDB;

public class TicketInfoDAOImpl implements TicketInfoDAO {
	
	static int count = 0;

	public static TicketInfoDAO getTicketInfoDAO() {
		return new TicketInfoDAOImpl();
	}

	@Override
	public boolean bookFlightTicket(String userId, String flightId, int noOfSeats) {
		System.out.println("user id " + userId + " flight id " + flightId + " noOfSeats " + noOfSeats);
		UserInfoBean userBean = null;
		FlightInfoBean flightBean = null;
		for (UserInfoBean userDetails : InfoDB.USER_INFO_DB) {
			if (userDetails.getUserId().equals(userId)) {
				userBean = userDetails;
				break;
			}
		}

		for (FlightInfoBean flightDetails : InfoDB.FLIGHT_INFO_DB) {
			if (flightDetails.getFlightId().equals(flightId)) {
				flightBean = flightDetails;
				break;
			}
		}
		TicketInfoBean ticketBean = new TicketInfoBean();
		ticketBean.setNoOfSeats(noOfSeats);
		ticketBean.setFlightDetails(flightBean);
		ticketBean.setUserDetails(userBean);
		ticketBean.setStatus("reserved");
		ticketBean.setTicketId(userId + "#" + flightId+ "#"+ (++count));
		ticketBean.setTotalFare(flightBean.getFare() * noOfSeats);
		flightBean.setAvailableSeat(flightBean.getAvailableSeat() - noOfSeats);
		
		if(InfoDB.TICKET_INFO_DB.add(ticketBean)) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public Collection<TicketInfoBean> viewReservedTicket(String flightId) {
		HashSet<TicketInfoBean> collectionTickets = new HashSet<TicketInfoBean>();
		for (TicketInfoBean ticketDetails : InfoDB.TICKET_INFO_DB) {
			if (ticketDetails.getFlightDetails().getFlightId().equals(flightId)
					&& ticketDetails.getStatus().equals("reserved")) {
				collectionTickets.add(ticketDetails);
			}
		}
		return collectionTickets;
	}

	@Override
	public Collection<TicketInfoBean> viewCancelledTicket(String flightId) {
		HashSet<TicketInfoBean> collectionTickets = new HashSet<TicketInfoBean>();
		for (TicketInfoBean ticketDetails : InfoDB.TICKET_INFO_DB) {
			if (ticketDetails.getFlightDetails().getFlightId().equals(flightId)
					&& ticketDetails.getStatus().equals("cancelled")) {
				collectionTickets.add(ticketDetails);
			}
		}
		return collectionTickets;
	}

	@Override
	public boolean cancelReservedTicket(String ticketId) {
		try {
			for (TicketInfoBean ticketBean : InfoDB.TICKET_INFO_DB) {
				if (ticketBean.getTicketId().equals(ticketId)) {
					ticketBean.setStatus("cancelled");
					ticketBean.getFlightDetails().setAvailableSeat(
							ticketBean.getNoOfSeats() + ticketBean.getFlightDetails().getAvailableSeat());
					return true;
				}
			}
			return false;
		} catch (NullPointerException e) {
			System.out.println("There is no reserved ticket for cancel");
			return false;
		}
	}

	@Override
	public Collection<TicketInfoBean> getUserReservedTicket(String userId) {
		HashSet<TicketInfoBean> tickets = new HashSet<TicketInfoBean>();
		for (TicketInfoBean ticket : InfoDB.TICKET_INFO_DB) {
			if (ticket.getUserDetails().getUserId().equals(userId) && ticket.getStatus().equals("reserved")) {
				tickets.add(ticket);
			}
		}
		return tickets;
	}

}
