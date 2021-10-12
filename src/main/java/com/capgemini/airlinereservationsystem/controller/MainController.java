package com.capgemini.airlinereservationsystem.controller;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class MainController {
	static Logger log = Logger.getLogger(MainController.class);

	public void getStart() {
		log.info("Welcome to Airline Reservation System");
		log.info("Enter 1 for Admin Controller");
		log.info("Enter 2 for User Controller");
		log.info("Enter 3 for Guest Controller");

		log.info("Enter what you want");

		Scanner scanner = new Scanner(System.in);

		String choice = scanner.next();

		switch (choice) {
		case "1":
			AdminController admin = new AdminController();
			admin.getAuthenticate();
			break;
		case "2":
			UserController user = new UserController();
			user.getUserFunction();
			break;
		case "3":
			GuestController guest = new GuestController();
			guest.getStart();
			break;
		default:
			log.info("Wrong choice! try again ");
			getStart();
		}

		scanner.close();

	}
}
