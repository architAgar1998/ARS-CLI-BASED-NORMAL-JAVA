package com.capgemini.airlinereservationsystem.controller;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.capgemini.airlinereservationsystem.bean.ContactInfoBean;
import com.capgemini.airlinereservationsystem.bean.FlightInfoBean;
import com.capgemini.airlinereservationsystem.service.ContactInfoService;
import com.capgemini.airlinereservationsystem.service.ContactInfoServiceImpl;
import com.capgemini.airlinereservationsystem.service.FlightInfoService;
import com.capgemini.airlinereservationsystem.service.FlightInfoServiceImpl;
import com.capgemini.airlinereservationsystem.validation.AllValidation;

public class GuestController {
	static Logger log = Logger.getLogger(GuestController.class);
	private FlightInfoService flightService = FlightInfoServiceImpl.getFlightInfoService();
	private ContactInfoService contactService = ContactInfoServiceImpl.getContactInfoService();

	public void getStart() {
		Scanner scanner = new Scanner(System.in);
		// scanner.nextLine();
		log.info("Enter 1 for Search Flights");
		log.info("Enter 2 for Show Company Contacts");
		log.info("Enter 3 for go back");
		String option = scanner.nextLine();

		switch (option) {
		case "1":
			getFlightDetails();
			break;
		case "2":
			showCompanyContact();
			break;
		case "3":
			MainController controller = new MainController();
			controller.getStart();
			break;
		default:
			log.info("Invalid Choice");
			getStart();
		}
		scanner.close();

	}

	private void showCompanyContact() {
		Scanner scanner = new Scanner(System.in);
		log.info("Enter Company Name");
		String companyName = scanner.nextLine();
		if (AllValidation.isStringAlphabet(companyName)) {
			ContactInfoBean bean = contactService.getCompanyContact(companyName);
			if (bean != null) {
				log.info(bean);
			} else {
				log.info("There is no Such  Company Available");
			}
		} else {
			log.info("There is no Such  Company Available");
		}
		goBack();
		scanner.close();
	}

	public void getFlightDetails() {
		Scanner scanner = new Scanner(System.in);
		String source;
//		scanner.nextLine();
		while (true) {
			log.info("Enter the Source");
			source = scanner.nextLine();
			if (AllValidation.isStringAlphabet(source)) {
				break;
			} else {
				log.info("Invalid source city");
			}
		}
		String destination;
		while (true) {
			log.info("Enter the destination");
			destination = scanner.nextLine();
			if (AllValidation.isStringAlphabet(destination)) {
				break;
			} else {
				log.info("Invalid destination city");
			}
		}
		Collection<FlightInfoBean> flightDetails = flightService.getAllFlight(source, destination);
		boolean status = true;
		for (FlightInfoBean flight : flightDetails) {
			status = false;

			log.info(flight);
			/*
			 * log.info(flight.getFlightId()+"   ");
			 * System.out.print(flight.getFlightCompany()+"  ");
			 * log.info(flight.getDepartureDate()+"  ");
			 * log.info(flight.getArrivalDate()+"  ");
			 * log.info(flight.getDepartureTime()+"  ");
			 * log.info(flight.getArrivalTime()+"  "); log.info(flight.getFare());
			 * log.info();
			 */
		}

		if (status) {
			log.info("There is no flight available from " + source.toUpperCase() + " to " + destination.toUpperCase());
		}
		goBack();
		scanner.close();

	}

	private void goBack() {
		Scanner scanner = new Scanner(System.in);
		log.info("Enter 1 for go back");
		try {
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				MainController controller = new MainController();
				controller.getStart();
				break;

			default:
				log.info("Wrong Choice! try again");
				getStart();
			}
		} catch (InputMismatchException e) {
			log.info("Enter Only Integer Values");
			goBack();
			scanner.close();
		}
	}

}
