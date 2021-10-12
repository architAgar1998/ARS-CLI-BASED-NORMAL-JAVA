package com.capgemini.airlinereservationsystem.service;

import com.capgemini.airlinereservationsystem.bean.ContactInfoBean;
import com.capgemini.airlinereservationsystem.dao.ContactInfoDAO;
import com.capgemini.airlinereservationsystem.dao.ContactInfoDAOImpl;

public class ContactInfoServiceImpl implements ContactInfoService {
	
	private ContactInfoDAO contactDAO = ContactInfoDAOImpl.getContactInfoDAO();

	public static ContactInfoService getContactInfoService() {
		return new ContactInfoServiceImpl();
	}
	
	@Override
	public ContactInfoBean getCompanyContact(String flightCompany) {
		return contactDAO.getCompanyContact(flightCompany);
	}
}
