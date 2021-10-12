package com.capgemini.airlinereservationsystem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.capgemini.airlinereservationsystem.bean.UserInfoBean;
import com.capgemini.airlinereservationsystem.dao.UserInfoDAO;
import com.capgemini.airlinereservationsystem.dao.UserInfoDAOImpl;
import com.capgemini.airlinereservationsystem.exception.UserDetailsException;
import com.capgemini.airlinereservationsystem.service.UserInfoService;
import com.capgemini.airlinereservationsystem.service.UserInfoServiceImpl;

class UserInfoServiceImplTest {

	private UserInfoService userInfoService = new UserInfoServiceImpl();
	private UserInfoBean userBean = null;

	@BeforeEach
	void createObject() {
		userBean = new UserInfoBean();
	}

	@AfterEach
	void destroyObject() {
		userBean = null;
	}

	@Test
	@DisplayName("To test User Register method")
	public void testUserRegister() {
		userBean.setFirstName("Navami");
		userBean.setLastName("Salyan");
		userBean.setGender("female");
		userBean.setMail("navami123@gmail.com");
		userBean.setMobile(9177121340l);
		userBean.setUserId("navami");
		userBean.setPassword("12345");
		userBean.setNationality("Indian");
		userBean.setResidence("Mangalore");
		userBean.setRole("user");

		boolean expected = userInfoService.userRegister(userBean);
		assertEquals(expected, true);

	}

	@Test
	@DisplayName("To test User Authentication method")
	public void testUserAuthentication() {
		userBean.setUserId("abhinav");
		userBean.setPassword("12345");
		userBean.setRole("user");
		boolean expected = userInfoService.userAuthentication(userBean.getUserId(), userBean.getPassword(),
				userBean.getRole());
		assertEquals(expected, true);

	}

	@Test
	@DisplayName("To test getAllUsers method")
	public void testGetAllUsers() {
		Collection<UserInfoBean> allUsers = userInfoService.getAllUsers();
		assertNotNull(allUsers);
	}

	@Test
	@DisplayName("To test updateUserDetails")
	public void testUpdateUserDetails() {
		userBean.setUserId("abhinav");
		userBean.setFirstName("Abhinav");
		userBean.setLastName("Singh");
		userBean.setGender("male");
		userBean.setMail("singh123@gmail.com");
		userBean.setMobile(8790123456l);
		userBean.setNationality("Pakisthan");
		userBean.setPassword("abcd123");
		userBean.setResidence("fgsdfhjjgdh");
		userBean.setRole("user");
		try {
			boolean expected = userInfoService.updateUserDetails(userBean.getUserId(), userBean);
		} catch (Exception e) {
			assertThrows(UserDetailsException.class, () -> {
				userInfoService.updateUserDetails(userBean.getUserId(), userBean);
			});
		}
	}

}
