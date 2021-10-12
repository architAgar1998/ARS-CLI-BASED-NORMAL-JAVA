package com.capgemini.airlinereservationsystem.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.airlinereservationsystem.bean.FlightInfoBean;
import com.capgemini.airlinereservationsystem.bean.UserInfoBean;
import com.capgemini.airlinereservationsystem.db.InfoDB;
import com.capgemini.airlinereservationsystem.exception.EmailAlreadyExistException;
import com.capgemini.airlinereservationsystem.exception.FlightIdAlreadyExistException;
import com.capgemini.airlinereservationsystem.exception.InvalidArrivalDateException;
import com.capgemini.airlinereservationsystem.exception.UserIdAlreadyExistException;

public class AllValidation {
	public static boolean isStringAlphabet(String str) {

		if (str.length() > 25) {
			return false;
		} else if (str.length() < 3) {
			return false;
		} else {
			return ((str != null) && (!str.equals("")) && (!str.equals(" ")) && (str.matches("^[a-zA-Z]*$")));
		}
	}

	public static String validateDepartureDate(String departureDate) {
		if (isValidateDate(departureDate)) {
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			try {
				Date dateFormat = format.parse(departureDate);
				if (dateFormat.compareTo(date) >= 0) {
					return "valid";
				} else {
					return "invalid";
				}
			} catch (ParseException e) {
				System.out.println("Invalid Date Format");
				return "invalidformat";
			}
		}
		return "invalidformat";
	}

	public static String validateArrivalDate(String arrivalDate, String departureDate) throws ParseException {
		if (isValidateDate(arrivalDate)) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date arriveDate = format.parse(arrivalDate);
			Date departDate = format.parse(departureDate);

			if (arriveDate.compareTo(departDate) >= 0 && arriveDate.compareTo(departDate) < 3) {
				return "valid";
			} else {
				throw new InvalidArrivalDateException();
				// return "invalid";
			}
		} else {
			return "invalidformat";
		}
	}

	public static boolean isValidateDate(String strDate) {
		/* Check if date is 'null' */
		if (strDate.trim().equals("")) {
			return true;
		}
		/* Date is not 'null' */
		else {
			/*
			 * Set preferred date format, For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.
			 */
			SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
			sdfrmt.setLenient(false);
			/*
			 * Create Date object parse the string into date
			 */
			try {
				Date javaDate = sdfrmt.parse(strDate);
				return true;
			}
			/* Date format is invalid */
			catch (ParseException e) {
				return false;
			}
		}
	}

	public static String validateFlightId(String flightId) {
		if (flightId.length() > 6 && flightId.length() < 15) {
			String flight = flightId;
			char arr[] = flight.toCharArray();
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == ' ') {
					return "invalid";
				}
			}
			for (FlightInfoBean flightBean : InfoDB.FLIGHT_INFO_DB) {
				if (flightBean.getFlightId().equals(flightId)) {
					throw new FlightIdAlreadyExistException();

				}
			}
			return "valid";
		} else {
			return "invalid";
		}

	}

	public static boolean validateSourceAndDestination(String source, String destination) {
		if (source.equals(destination)) {
			return false;
		}
		return true;
	}

	public static String validateUserId(String userId) {
		if (userId.length() > 6 && userId.length() < 15) {
			String idUser = userId;
			char arr[] = idUser.toCharArray();
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == ' ') {
					return "invalid";
				}
			}
			for (UserInfoBean user : InfoDB.USER_INFO_DB) {
				if (user.getUserId().equals(userId)) {
					throw new UserIdAlreadyExistException();

				}
			}
			return "valid";
		} else {
			return "invalid";
		}
	}

	public static boolean validateUserEmail(String emailAddress) {
		for (UserInfoBean userBean : InfoDB.USER_INFO_DB) {
			if (userBean.getMail().equals(emailAddress)) {
				throw new EmailAlreadyExistException();
			}
		}
		return true;
	}

	public static boolean validateAvailableTotalSeat(int available, int total) {
		if (available <= total) {
			if (available >= 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean validateEmail(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validatePassword(String password) {
		if (password.length() >= 6 && password.length() <= 10) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validatePhone(long number) {
		int count = 0;
		while (number > 0) {
			number = number / 10;
			count++;
		}
		if (count == 10) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validateTotalSeat(int totalSeat) {
		if (totalSeat > 30 && totalSeat < 80) {
			return true;
		}
		return false;
	}

	public static boolean validateFare(double fare) {
		if (fare > 500 && fare < 999999) {
			return true;
		}
		return false;
	}

	public static boolean validateRole(String role) {
		if (role.equals("admin") || role.equals("user")) {
			return true;
		}
		return false;
	}

	public static boolean noOfSeats(int noOfSeat) {
		if (noOfSeat > 0) {
			return true;
		}
		return false;
	}

	public static boolean validateAddress(String address) {
		if (address.length() >= 6 && address.length() < 20) {
			return true;
		}
		return false;
	}

	public static boolean validateDepartureTime(String departureTime) {
		//try {
			LocalTime lt = LocalTime.parse(departureTime);
			return true;
		//} catch (DateTimeParseException e) {
			//return false;
		//}
	}

	public static boolean validateArrivalTime(String departTime, String arrTime, String deptDate, String arvDate) {
		try {
			LocalTime depTime = LocalTime.parse(departTime);
			LocalTime arvTime = LocalTime.parse(arrTime);
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date arriveDate = format.parse(arvDate);
			Date departDate = format.parse(deptDate);

			if (arriveDate.compareTo(departDate) == 0) {
				if (depTime.compareTo(arvTime) >= 0) {
					return false;
				} else if (departTime.equals(arrTime)) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean validateUserUpdateEmail(String email, String userId) {
		for (UserInfoBean user : InfoDB.USER_INFO_DB) {
			if (user.getMail().equals(email) && user.getUserId().equals(userId)) {
				return true;
			} else if (user.getMail().equals(email) && !user.getUserId().equals(userId)) {
				return false;
			}
		}
		return true;
	}
}
