package com.capgemini.airlinereservationsystem.controller;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.capgemini.airlinereservationsystem.bean.FlightInfoBean;
import com.capgemini.airlinereservationsystem.bean.TicketInfoBean;
import com.capgemini.airlinereservationsystem.bean.UserInfoBean;
import com.capgemini.airlinereservationsystem.db.InfoDB;
import com.capgemini.airlinereservationsystem.exception.UserIdAlreadyExistException;
import com.capgemini.airlinereservationsystem.service.FlightInfoService;
import com.capgemini.airlinereservationsystem.service.FlightInfoServiceImpl;
import com.capgemini.airlinereservationsystem.service.TicketInfoService;
import com.capgemini.airlinereservationsystem.service.TicketInfoServiceImpl;
import com.capgemini.airlinereservationsystem.service.UserInfoService;
import com.capgemini.airlinereservationsystem.service.UserInfoServiceImpl;
import com.capgemini.airlinereservationsystem.validation.AllValidation;

public class UserController {
	static Logger log = Logger.getLogger(UserController.class);
	private UserInfoService userService = UserInfoServiceImpl.getUserInfoService();
	private FlightInfoService flightService = FlightInfoServiceImpl.getFlightInfoService();
	private TicketInfoService ticketService = TicketInfoServiceImpl.getTicketInfoService();

	Scanner scanner = new Scanner(System.in);
	String userId;

	public void getUserFunction() {
		log.info("Enter 1 for User login");
		log.info("Enter 2 for User Registration");
		log.info("Enter 3 for go back");
		log.info("Enter Your Choice");
		try {
			String choice = scanner.next();
			try {
				choiceUserFuction(choice);
			} catch (Exception e) {
				log.info("Invalid choice");
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			getUserFunction();
		} finally {
			goBack();
		}
	}

	private void choiceUserFuction(String choice) {
		switch (choice) {
		case "1":
			toLogin();
			break;
		case "2":
			if (toRegister()) {
				log.info("Registered Successfully");
				toLogin();
			} else {
				log.info("Failed to Register ");
				getUserFunction();
			}
			break;
		case "3":
			MainController controller = new MainController();
			controller.getStart();
			break;
		default:
			log.info("invalid choice");
			getUserFunction();
		}
	}

	private void afterLogin() {
		scanner.nextLine();
		log.info("Enter 1 for Book Flight");
		log.info("Enter 2 for Cancel Reserved Ticket");
		log.info("Enter 3 for Update User details");
		log.info("Enter 4 for view your reserved tickets");
		log.info("Enter 5 for go back");
		log.info("Enter your choice");
		try {
			String userChoice = scanner.nextLine();
			switch (userChoice) {
			case "1":
				toBookFlight();
				break;
			case "2":
				cancelReservedTicket();
				break;
			case "3":
				updateUserDetails();
				break;
			case "5":
				MainController controller = new MainController();
				controller.getStart();
				break;
			case "4":
				viewYourReservedTicket();
				break;
			default:
				log.info("wrong choice! try again");
				afterLogin();
			}
		} catch (InputMismatchException e) {
			log.info("Enter only Integer Value");
			afterLogin();
		}
	}

	private void viewYourReservedTicket() {
		Collection<TicketInfoBean> tickets = ticketService.getUserReservedTicket(this.userId);
		boolean status = true;
		for (TicketInfoBean ticketInfoBean : tickets) {
			status = false;
			log.info("Reserved Tickets:" + ticketInfoBean);
		}
		if (status) {
			log.info("You do not have any Reserved Ticket");
		}
		goBack();
	}

	private void updateUserDetails() {

		UserInfoBean userDetails = null;

		for (UserInfoBean user : InfoDB.USER_INFO_DB) {
			if (user.getUserId().equals(this.userId)) {
				userDetails = user;
				break;
			}
		}
		
		while (true) {
			log.info("Enter First Name");
			String firstName = scanner.nextLine();
			if (AllValidation.isStringAlphabet(firstName)) {
				userDetails.setFirstName(firstName);
				break;
			} else {
				log.info("Invalid First Name");
			}
		}
		while (true) {
			log.info("Enter Last Name");
			String lastName = scanner.nextLine();
			if (AllValidation.isStringAlphabet(lastName)) {
				userDetails.setLastName(lastName);
				break;
			} else {
				log.info("invalid Last Name");
			}
		}
		while (true) {
			log.info("Enter Email Address");
			String email = scanner.nextLine();
			if (AllValidation.validateUserUpdateEmail(email, this.userId)) {
				userDetails.setMail(email);
				break;
			} else {
				log.info("Ivalid Email Address it must like xyz@example.com");
			}
		}
		while (true) {
			log.info("Enter Password");
			String password = scanner.nextLine();
			if (AllValidation.validatePassword(password)) {
				userDetails.setPassword(password);
				break;
			} else {
				log.info("Invalid password");
			}
		}
		while (true) {
			log.info("Enter Residence");
			String residence = scanner.nextLine();
			if (AllValidation.validateAddress(residence)) {
				userDetails.setResidence(residence);
				break;
			} else {
				log.info("Invalid Residence");
			}
		}
		while (true) {
			log.info("Enter Mobile Number");
			long mobileNo = scanner.nextLong();
			if (AllValidation.validatePhone(mobileNo)) {
				userDetails.setMobile(mobileNo);
				break;
			} else {
				log.info("Invalid Mobile Number");
			}
		}
		scanner.nextLine();
		while (true) {
			log.info("Enter Nationality");
			String nationality = scanner.nextLine();
			if (AllValidation.isStringAlphabet(nationality)) {
				userDetails.setNationality(nationality);
				break;
			} else {
				log.info("Invalid Nationality");
			}
		}
		userDetails.setRole("user");

		if (userService.updateUserDetails(this.userId, userDetails)) {
			log.info("User Details Updated Successfully");
			goBack();
		} else {
			log.info("Something went wrong! try again");
			goBack();
		}
	}

	private void cancelReservedTicket() {

		while (true) {
			log.info("enter Ticket Id");
			String ticketId = scanner.nextLine();
			if (ticketService.cancelReservedTicket(ticketId)) {
				log.info("Your Ticket has been cancelled");
				goBack();
			} else {
				log.info("Invalid Ticket ID or Something Went Wrong");
				goBack();
			}
		}
	}

	int count = 0;

	private void toBookFlight() {
		String source;
		String destination;
		while (true) {
			log.info("Enter the Source");
			source = scanner.nextLine();
			if (AllValidation.isStringAlphabet(source)) {
				break;
			} else {
				log.info("Invalid source");
			}
		}
		while (true) {
			log.info("Enter the Destination");
			destination = scanner.nextLine();
			if (AllValidation.isStringAlphabet(destination)) {
				if (AllValidation.validateSourceAndDestination(source, destination)) {
					break;
				} else {
					log.info("Source and Destination can not be same!");
				}
			} else {
				log.info("Invalid destination");
			}
		}
		Collection<FlightInfoBean> flightDetails = flightService.getAllFlight(source, destination);
		boolean status = true;

		for (FlightInfoBean flights : flightDetails) {
			status = false;
			log.info("flight details:" + flights);
		}
		if (status) {
			log.info("There is not any flight available !");
			goBack();
		} else {
			log.info("enter Flight Id");
			String flightId = scanner.nextLine();

			log.info("Enter no of tickets you want?");
			int seats = 0;
			try {
				seats = scanner.nextInt();
			} catch (InputMismatchException e) {
				log.info("Enter only Integer value");
			}
			if (AllValidation.noOfSeats(seats)) {
				if (getConfirmation(flightId, seats, source, destination)) {

					if (ticketService.bookFlightTicket(this.userId, flightId, seats)) {
						log.info("Your ticket has been Booked Successfully");
						goBack();
					} else {
						log.info("Something went wrong");
						toBookFlight();
					}
				} else {
					log.info("Invalid Seats");
					afterLogin();
				}
			} else {
				log.info("Invalid No of Seats");
				toBookFlight();
			}
		}
	}

	private void toLogin() {
		log.info("Enter your USERID");
		this.userId = scanner.next();
		log.info("Enter your password");
		String password = scanner.next();

		if (userService.userAuthentication(this.userId, password, "user")) {
			afterLogin();
		} else {
			log.info("Invalid Credential! try again");
			log.info("Would you like to continue");
			log.info("Press Y");
			String input = scanner.next();
			if (input.equalsIgnoreCase("Y")) {
				toLogin();
			} else {
				MainController controller = new MainController();
				controller.getStart();
			}

		}
	}

	private boolean toRegister() {
		UserInfoBean user = new UserInfoBean();
		scanner.nextLine();
		while (true) {
			log.info("Enter User Id");
			String userId = scanner.nextLine();
			try {
				if (!userId.equals("")) {
					if (AllValidation.validateUserId(userId).equals("valid")) {
						user.setUserId(userId);
						break;
					} else if (AllValidation.validateUserId(userId).equals("exist")) {
						log.info("User Id is already exist!");
					} else {
						log.info("Invalid User Id it must be 7 to 14 char long withour space");
					}
				}
			} catch (UserIdAlreadyExistException e) {
				log.info(e.getMessage());
			}
		}
		while (true) {
			log.info("Enter First Name");
			String firstName = scanner.nextLine();
			if (AllValidation.isStringAlphabet(firstName)) {
				user.setFirstName(firstName);
				break;
			} else {
				log.info("invalid first name it must contain only alphabets without space");
			}
		}
		while (true) {
			log.info("Enter Last Name");
			String lastName = scanner.nextLine();
			if (AllValidation.isStringAlphabet(lastName)) {
				user.setLastName(lastName);
				break;
			} else {
				log.info("invalid last name it must contain only alphabets without space");
			}
		}
		while (true) {
			log.info("Enter Email Address");
			String email = scanner.nextLine();
			if (AllValidation.validateEmail(email)) {
				user.setMail(email);
				break;
			} else {
				log.info("Invalid Email Address it must be like xyz@example.com");
			}
		}
		while (true) {
			log.info("Enter Password");
			String password = scanner.nextLine();
			if (AllValidation.validatePassword(password)) {
				user.setPassword(password);
				break;
			} else {
				log.info("Invalid password");
			}
		}
		while (true) {
			log.info("Enter Residence");
			String residence = scanner.nextLine();
			if (AllValidation.validateAddress(residence)) {
				user.setResidence(residence);
				break;
			} else {
				log.info("Invalid Residence");
			}
		}
		while (true) {
			log.info("Enter Mobile Number");
			try {
				long mobileNo = scanner.nextLong();
				if (AllValidation.validatePhone(mobileNo)) {
					user.setMobile(mobileNo);
					break;
				} else {
					log.info("Invalid mobile no.");
				}
			} catch (InputMismatchException e) {
				log.info("enter only numbers");
			}
		}
		scanner.nextLine();
		while (true) {
			log.info("Enter Nationality");
			String nationality = scanner.nextLine();
			if (AllValidation.isStringAlphabet(nationality)) {
				user.setNationality(nationality);
				break;
			} else {
				log.info("Invalid Nationality !");
			}
		}
		while (true) {
			log.info("Enter Gender");
			String gender = scanner.nextLine();
			if (AllValidation.isStringAlphabet(gender)) {
				user.setGender(gender);
				break;
			} else {
				log.info("Invalid !");
			}
		}
		user.setRole("user");

		if (userService.userRegister(user)) {
			log.info("User Register Successfully");
			afterLogin();
			return true;
		} else {
			return false;
		}

	}

	private void goBack() {
		log.info("");
		log.info("Enter 1 for go back");
		try {
			String choice = scanner.next();
			switch (choice) {
			case "1":
				afterLogin();
				break;

			default:
				log.info("Wrong choice");
				afterLogin();
				break;
			}
		} catch (InputMismatchException e) {
			log.info("enter only number");
			goBack();
		}
	}

	private boolean getConfirmation(String flightId, int noOfSeats, String source, String destination) {
		try {
			Collection<FlightInfoBean> flightDetails = flightService.getAllFlight(source, destination);
			FlightInfoBean getFlightInfoBean = null;
			for (FlightInfoBean flight : flightDetails) {
				if (flight.getFlightId().equals(flightId)) {
					getFlightInfoBean = flight;
					break;
				}
			}
			if (source.equalsIgnoreCase(getFlightInfoBean.getSource())
					&& destination.equalsIgnoreCase(getFlightInfoBean.getDestination())) {
				if (noOfSeats <= getFlightInfoBean.getAvailableSeat()) {
					return true;
				} else {
					log.info("This much seats are not available!");
					return false;
				}
			} else {
				return false;
			}
		} catch (NullPointerException e) {
			log.info("this flight is not going from '" + source.toUpperCase() + "' to '" + destination.toUpperCase()
					+ "'");
			return false;
		}
	}
}