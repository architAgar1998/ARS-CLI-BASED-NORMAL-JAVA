package com.capgemini.airlinereservationsystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.capgemini.airlinereservationsystem.bean.ContactInfoBean;
import com.capgemini.airlinereservationsystem.service.ContactInfoService;
import com.capgemini.airlinereservationsystem.service.ContactInfoServiceImpl;


class ContactInfoServiceImplTest {

	private ContactInfoService contactInfoService = new ContactInfoServiceImpl();
	private ContactInfoBean contactBean = null;

	@BeforeEach
	void createObject() {
		contactBean = new ContactInfoBean();
	}

	@AfterEach
	void destroyObject() {
		contactBean = null;
	}

	@Test
	@DisplayName("To test getCompanyContact method")
	public void testGetCompanyContact() {
		ContactInfoBean expected = contactInfoService.getCompanyContact("GoAir");
		String companyName = expected.getFlightCompany();
		String contactPersonName = expected.getContactName();
		long contactPersonNumber = expected.getContactNo();
		assertEquals(companyName, "GoAir");
		assertEquals(contactPersonName, "Uday Verma");
		assertEquals(contactPersonNumber, 1234567890l);
	}

	
}
