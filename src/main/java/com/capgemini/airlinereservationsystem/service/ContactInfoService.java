package com.capgemini.airlinereservationsystem.service;

import com.capgemini.airlinereservationsystem.bean.ContactInfoBean;

public interface ContactInfoService {
	public ContactInfoBean getCompanyContact(String flightCompany);
}
