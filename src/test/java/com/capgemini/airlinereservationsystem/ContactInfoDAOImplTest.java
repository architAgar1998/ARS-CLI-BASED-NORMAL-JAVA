package com.capgemini.airlinereservationsystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.capgemini.airlinereservationsystem.bean.ContactInfoBean;
import com.capgemini.airlinereservationsystem.dao.ContactInfoDAO;
import com.capgemini.airlinereservationsystem.dao.ContactInfoDAOImpl;

class ContactInfoDAOImplTest {

	private ContactInfoDAO contactInfoDAO = new ContactInfoDAOImpl();
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
		ContactInfoBean expected = contactInfoDAO.getCompanyContact("GoAir");
		String companyName = expected.getFlightCompany();
		String contactPersonName = expected.getContactName();
		long contactPersonNumber = expected.getContactNo();
		assertEquals(companyName, "GoAir");
		assertEquals(contactPersonName, "Uday Verma");
		assertEquals(contactPersonNumber, 1234567890l);
	}

}
