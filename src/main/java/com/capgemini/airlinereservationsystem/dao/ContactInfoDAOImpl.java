package com.capgemini.airlinereservationsystem.dao;

import com.capgemini.airlinereservationsystem.bean.ContactInfoBean;
import com.capgemini.airlinereservationsystem.db.InfoDB;

public class ContactInfoDAOImpl implements ContactInfoDAO {

	public static ContactInfoDAO getContactInfoDAO() {
		return new ContactInfoDAOImpl();
	}
	
	@Override
	public ContactInfoBean getCompanyContact(String flightCompany) {
		for (ContactInfoBean contactDetails : InfoDB.CONTACT_INFO_DB) {
			if (contactDetails.getFlightCompany().equalsIgnoreCase(flightCompany)) {
				return contactDetails;
			}
		}
		return null;
	}
}
