package com.capgemini.airlinereservationsystem.db;

import java.util.HashSet;

import com.capgemini.airlinereservationsystem.bean.ContactInfoBean;
import com.capgemini.airlinereservationsystem.bean.FlightInfoBean;
import com.capgemini.airlinereservationsystem.bean.TicketInfoBean;
import com.capgemini.airlinereservationsystem.bean.UserInfoBean;

public class InfoDB {

	public static final HashSet<UserInfoBean> USER_INFO_DB = new HashSet<UserInfoBean>();
	public static final HashSet<FlightInfoBean> FLIGHT_INFO_DB = new HashSet<FlightInfoBean>();
	public static final HashSet<ContactInfoBean> CONTACT_INFO_DB = new HashSet<ContactInfoBean>();
	public static final HashSet<TicketInfoBean> TICKET_INFO_DB = new HashSet<TicketInfoBean>();

	static {
		ContactInfoBean conBean1 = new ContactInfoBean();
		conBean1.setFlightCompany("GoAir");
		conBean1.setContactName("Uday Verma");
		conBean1.setContactNo(1234567890l);

		ContactInfoBean conBean2 = new ContactInfoBean();
		conBean2.setFlightCompany("IndiGo");
		conBean2.setContactName("Abhinav Singh");
		conBean2.setContactNo(9074099249l);

		ContactInfoBean conBean3 = new ContactInfoBean();
		conBean3.setFlightCompany("AirAsia");
		conBean3.setContactName("Vikas Agrawal");
		conBean3.setContactNo(9074099249l);

		ContactInfoBean conBean4 = new ContactInfoBean();
		conBean4.setFlightCompany("AirIndia");
		conBean4.setContactName("Aporve Bhatnagar");
		conBean4.setContactNo(9074099249l);

		ContactInfoBean conBean5 = new ContactInfoBean();
		conBean5.setFlightCompany("AirIndia");
		conBean5.setContactName("Shubhankit Gautam");
		conBean5.setContactNo(9074099249l);

		InfoDB.CONTACT_INFO_DB.add(conBean1);
		InfoDB.CONTACT_INFO_DB.add(conBean2);
		InfoDB.CONTACT_INFO_DB.add(conBean3);
		InfoDB.CONTACT_INFO_DB.add(conBean4);
		InfoDB.CONTACT_INFO_DB.add(conBean5);

		UserInfoBean userBean1 = new UserInfoBean();
		userBean1.setUserId("architagar");
		userBean1.setFirstName("Archit");
		userBean1.setLastName("Agarwal");
		userBean1.setNationality("Indian");
		userBean1.setResidence("Bijnor");
		userBean1.setMobile(7055033236l);
		userBean1.setMail("architagar@gmail.com");
		userBean1.setPassword("123456");
		userBean1.setRole("admin");
		userBean1.setGender("Male");

		UserInfoBean userBean2 = new UserInfoBean();
		userBean2.setUserId("abhinav");
		userBean2.setFirstName("Abhinav");
		userBean2.setLastName("Singh");
		userBean2.setNationality("Indian");
		userBean2.setResidence("Bhopal");
		userBean2.setMobile(9074099249l);
		userBean2.setMail("abhinavsingh2792@gmail.com");
		userBean2.setPassword("123456");
		userBean2.setRole("user");
		userBean2.setGender("Male");

		UserInfoBean userBean3 = new UserInfoBean();
		userBean3.setUserId("nethraAlakal");
		userBean3.setFirstName("Nethra");
		userBean3.setLastName("Alakal Surendra");
		userBean3.setNationality("Indian");
		userBean3.setResidence("Kerala");
		userBean3.setMobile(875033236l);
		userBean3.setMail("nethra@gmail.com");
		userBean3.setPassword("123456");
		userBean3.setRole("admin");
		userBean3.setGender("Female");

		UserInfoBean userBean4 = new UserInfoBean();
		userBean4.setUserId("ramya303");
		userBean4.setFirstName("Ramya");
		userBean4.setLastName("Atthuluri");
		userBean4.setNationality("Indian");
		userBean4.setResidence("Andhra");
		userBean4.setMobile(976332036l);
		userBean4.setMail("ramya@gmail.com");
		userBean4.setPassword("123456");
		userBean4.setRole("admin");
		userBean4.setGender("Female");

		InfoDB.USER_INFO_DB.add(userBean1);
		InfoDB.USER_INFO_DB.add(userBean2);
		InfoDB.USER_INFO_DB.add(userBean3);
		InfoDB.USER_INFO_DB.add(userBean4);
		FlightInfoBean flightBean1 = new FlightInfoBean();
		flightBean1.setFlightId("AirIndia110");
		flightBean1.setFlightCompany("AirIndia");
		flightBean1.setDepartureDate("17/03/2020");
		flightBean1.setDepartureTime("5:30 PM");
		flightBean1.setArrivalDate("25/03/2020");
		flightBean1.setArrivalTime("6:17 PM");
		flightBean1.setSource("Mumbai");
		flightBean1.setDestination("Goa");
		flightBean1.setFare(2980);
		flightBean1.setTotalSeats(50);
		flightBean1.setAvailableSeat(50);

		FlightInfoBean flightBean2 = new FlightInfoBean();
		flightBean2.setFlightId("AirAsia110");
		flightBean2.setFlightCompany("AirAsia");
		flightBean2.setDepartureDate("17/03/2020");
		flightBean2.setDepartureTime("5:30 PM");
		flightBean2.setArrivalDate("25/03/2020");
		flightBean2.setArrivalTime("6:17 PM");
		flightBean2.setSource("Bengaluru");
		flightBean2.setDestination("Ladakh");
		flightBean2.setFare(4980);
		flightBean2.setTotalSeats(50);
		flightBean2.setAvailableSeat(50);

		InfoDB.FLIGHT_INFO_DB.add(flightBean2);
		InfoDB.FLIGHT_INFO_DB.add(flightBean1);

		TicketInfoBean ticketBean1 = new TicketInfoBean();
		ticketBean1.setNoOfSeats(5);
		ticketBean1.setTicketId(userBean1.getUserId() + "#" + flightBean1.getFlightId());
		ticketBean1.setUserDetails(userBean1);
		ticketBean1.setFlightDetails(flightBean1);
		ticketBean1.setTotalFare(5 * flightBean1.getFare());
		ticketBean1.setStatus("cancelled");

		TicketInfoBean ticketBean2 = new TicketInfoBean();
		ticketBean2.setNoOfSeats(2);
		ticketBean2.setTicketId(userBean2.getUserId() + "#" + flightBean2.getFlightId());
		ticketBean2.setUserDetails(userBean2);
		ticketBean2.setFlightDetails(flightBean2);
		ticketBean2.setTotalFare(2 * flightBean2.getFare());
		ticketBean2.setStatus("cancelled");

		InfoDB.TICKET_INFO_DB.add(ticketBean1);
		InfoDB.TICKET_INFO_DB.add(ticketBean2);

	}

}
