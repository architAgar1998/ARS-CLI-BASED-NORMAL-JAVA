package com.capgemini.airlinereservationsystem.controller;

import java.text.ParseException;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.capgemini.airlinereservationsystem.bean.FlightInfoBean;
import com.capgemini.airlinereservationsystem.bean.TicketInfoBean;
import com.capgemini.airlinereservationsystem.bean.UserInfoBean;
import com.capgemini.airlinereservationsystem.exception.FlightIdAlreadyExistException;
import com.capgemini.airlinereservationsystem.exception.InvalidArrivalDateException;
import com.capgemini.airlinereservationsystem.service.FlightInfoService;
import com.capgemini.airlinereservationsystem.service.FlightInfoServiceImpl;
import com.capgemini.airlinereservationsystem.service.TicketInfoService;
import com.capgemini.airlinereservationsystem.service.TicketInfoServiceImpl;
import com.capgemini.airlinereservationsystem.service.UserInfoService;
import com.capgemini.airlinereservationsystem.service.UserInfoServiceImpl;
import com.capgemini.airlinereservationsystem.validation.AllValidation;

public class AdminController {
	static Logger log = Logger.getLogger(AdminController.class);
	private UserInfoService userService = UserInfoServiceImpl.getUserInfoService();
	private FlightInfoService flightService = FlightInfoServiceImpl.getFlightInfoService();
	private TicketInfoService ticketService = TicketInfoServiceImpl.getTicketInfoService();

	Scanner scanner = new Scanner(System.in);
	String adminId;

	public void getAuthenticate() {
		log.info("Enter your ADMINID");
		this.adminId = scanner.next();
		log.info("Enter your password");
		String password = scanner.next();

		if (userService.userAuthentication(this.adminId, password, "admin")) {
			getAdminfunction();
		} else {
			log.info("Invalid Credential! try again");
		}
		log.info("Press Y for Continue");
		if (scanner.next().equalsIgnoreCase("Y")) {
			getAuthenticate();
		} else {
			MainController controller = new MainController();
			controller.getStart();
		}
	}

	static int i = 0;

	public void getAdminfunction() {
		scanner.nextLine();
		log.info("Enter 1 for Add Flight");
		log.info("Enter 2 for View Reserved Tickets");
		log.info("Enter 3 for View Cancelled Tickets");
		log.info("Enter 4 for View All Users");
		log.info("Enter 5 for  View All the Flights");
		log.info("Enter 6 for go back");
		log.info("Enter your choice");

		String choice = scanner.nextLine();

		switch (choice) {
		case "1":
			if (addFlightDetails()) {
				log.info("Flight Added Successfully");
				goBack();
			} else {
				log.info("Failed to Add Flight");
				goBack();
			}
			break;
		case "2":
			viewReservedTicket();
			break;
		case "3":
			viewCancelledTicket();
			break;
		case "6":
			MainController mainController = new MainController();
			mainController.getStart();
			break;
		case "4":
			viewAllUsers();
			break;
		case "5":
			viewAllFlights();
			break;
		default:
			log.info("Wrong Choice! Try Again");
			getAdminfunction();
		}
	}

	private void viewAllFlights() {
		Collection<FlightInfoBean> flights = flightService.getAllFlight();
		boolean status = true;
		for (FlightInfoBean flight : flights) {
			status = false;
			log.info("Available Flights:" + flight);
		}
		if (status) {
			log.info("There is not any Flight available!");
		}
		goBack();
	}

	private void viewAllUsers() {
		Collection<UserInfoBean> users = userService.getAllUsers();
		boolean status = true;
		for (UserInfoBean userBeans : users) {
			status = false;
			log.info("All Users:" + userBeans);
		}
		if (status) {
			log.info("There is not any User avaliable");
		}
		goBack();
	}

	private void viewCancelledTicket() {
		log.info("Enter Flight Id which cancelled seat you are looking for");
		String flightId = scanner.next();
		Collection<TicketInfoBean> ticCollection = ticketService.viewCancelledTicket(flightId);
		boolean status = true;
		for (TicketInfoBean ticketInfoBean : ticCollection) {
			status = false;
			log.info("Cancelled Tickets:" + ticketInfoBean);
		}
		if (status) {
			log.info("There is no Cancelled Tickets available for this flight!");
		}
		goBack();
	}

	private void viewReservedTicket() {
		log.info("Enter Flight Id which reserved seat you are looking for");
		String flightId = scanner.next();
		try {
			Collection<TicketInfoBean> ticCollection = ticketService.viewReservedTicket(flightId);
			boolean status = true;
			for (TicketInfoBean ticketInfoBean : ticCollection) {
				status = false;
				log.info(" Reserved Tickets:" + ticketInfoBean);
			}
			if (status) {
				log.info("There is no Reserved Ticket available for this flight!");
			}
			goBack();
		} catch (NullPointerException exception) {
			log.info("There is not any Reserved Ticket of this flight");
			goBack();
		}
	}

	private boolean addFlightDetails() {

		FlightInfoBean flightInfoBean = new FlightInfoBean();
		while (true) {
			log.info("Enter Flight Id");
			String flightId = scanner.nextLine();
			try {
				if (AllValidation.validateFlightId(flightId).equals("valid")) {
					flightInfoBean.setFlightId(flightId);
					break;
//			} else if (AllValidation.validateFlightId(flightId).equals("exist")) {
//				log.info("Flight Id is already exist!");
				} else {
					log.info("Invalid Flight Id it must be 7 to 14 char long without space");
				}
			} catch (FlightIdAlreadyExistException e) {
				log.info(e.getMessage());
			}
		}
		while (true) {
			log.info("Enter flight Company");
			String flightCompany = scanner.nextLine();
			if (AllValidation.isStringAlphabet(flightCompany)) {
				flightInfoBean.setFlightCompany(flightCompany);
				break;
			} else {
				log.info("Invalid flight company it must be 3 to 25 char long without space");
			}
		}
		String deptDate;
		while (true) {
			log.info("Enter Departure Date");
			deptDate = scanner.nextLine();
			if (AllValidation.validateDepartureDate(deptDate).equals("valid")) {
				flightInfoBean.setDepartureDate(deptDate);
				break;
			} else if (AllValidation.validateDepartureDate(deptDate).equals("invalid")) {
				log.info("date should be of future");
			} else {
				log.info("Invalid format it must be like dd/MM/yyyy formate");
			}
		}
		String deptTime;
		while (true) {
			log.info("Enter Departure Time");
			deptTime = scanner.nextLine();
			if (AllValidation.validateDepartureTime(deptTime)) {
				flightInfoBean.setDepartureTime(deptTime);
				break;
			} else {
				log.info("Invalid Departure Time");
			}
		}
		String arrvDate;
		while (true) {
			log.info("Enter Arrival Date");
			arrvDate = scanner.nextLine();
			try {
				if (AllValidation.validateArrivalDate(arrvDate, deptDate).equals("valid")) {
					flightInfoBean.setArrivalDate(arrvDate);
					break;
//				} else if(AllValidation.validateArrivalDate(arrvDate, deptDate).equals("invalid")) {
//					log.info("Invalid date must be greate or equal then departure date");
				} else {
					log.info("invalid date format it must be like dd/MM/yyyy");
				}
			} catch (ParseException e) {
				log.info("Something went wrong");
			} catch (InvalidArrivalDateException e) {
				log.info(e.getMessage());
			}
		}
		while (true) {
			log.info("Enter  Arrival Time");
			String arrvTime = scanner.nextLine();
			if (AllValidation.validateArrivalTime(deptTime, arrvTime, deptDate, arrvDate)) {
				flightInfoBean.setArrivalTime(arrvTime);
				break;
			} else {
				log.info("Invalid Arrival Time it must be greater then departure time");
			}
		}
		String source;
		while (true) {
			log.info("Enter Source");
			source = scanner.nextLine();
			if (AllValidation.isStringAlphabet(source)) {
				flightInfoBean.setSource(source);
				break;
			} else {
				log.info("Invalid source city");

			}
		}
		while (true) {
			log.info("Enter Destination");
			String destination = scanner.nextLine();
			if (AllValidation.validateSourceAndDestination(source, destination)) {
				if (AllValidation.isStringAlphabet(destination)) {
					flightInfoBean.setDestination(destination);
					break;
				} else {
					log.info("Invalid destination city");
				}
			} else {
				log.info("Source and Destination can not be same");
			}
		}
		int totalSeat = 0;
		int c = 0;
		while (true) {
			if (c != 0) {
				scanner.nextLine();
			}
			log.info("Enter Total Seats");
			try {
				totalSeat = scanner.nextInt();
				if (AllValidation.validateTotalSeat(totalSeat)) {
					flightInfoBean.setTotalSeats(totalSeat);
					flightInfoBean.setAvailableSeat(totalSeat);
					break;
				} else {
					log.info("Invalid total seats ");
				}
			} catch (InputMismatchException e) {
				log.info("Enter only Integer Value");
				++c;
			}
		}

		int count = 0;
		while (true) {
			if (count != 0) {
				scanner.nextLine();
			}
			log.info("Enter Fare of Flight");
			try {
				double fare = scanner.nextDouble();
				if (AllValidation.validateFare(fare)) {
					flightInfoBean.setFare(fare);
					break;
				} else {
					log.info("Invalid fare");
				}
			} catch (InputMismatchException e) {
				log.info("Enter only integer value!");
				++count;
			}
		}
		if (flightService.addFlight(flightInfoBean)) {
			return true;
		} else {
			return false;
		}
	}

	private void goBack() {
		log.info("");
		log.info("Enter 1 for go back");
		try {
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				getAdminfunction();
				break;
			default:
				log.info("Wrong choice");
				getAdminfunction();
				break;
			}
		} catch (InputMismatchException e) {
			log.info("Enter only integer value");
			goBack();
		}
	}
}
