package com.capgemini.airlinereservationsystem.dao;

import com.capgemini.airlinereservationsystem.bean.ContactInfoBean;

public interface ContactInfoDAO {
	public ContactInfoBean getCompanyContact(String flightCompany);
}
