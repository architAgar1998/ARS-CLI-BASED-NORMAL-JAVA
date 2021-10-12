package com.capgemini.airlinereservationsystem.service;

import java.util.Collection;

import com.capgemini.airlinereservationsystem.bean.UserInfoBean;
import com.capgemini.airlinereservationsystem.dao.UserInfoDAO;
import com.capgemini.airlinereservationsystem.dao.UserInfoDAOImpl;

public class UserInfoServiceImpl implements UserInfoService{
	private UserInfoDAO userDAO = UserInfoDAOImpl.getUserInfoDAO();

	public static UserInfoService getUserInfoService() {
		return new UserInfoServiceImpl();
	}
	
	@Override
	public boolean userAuthentication(String userId, String password, String role) {
		return userDAO.userAuthentication(userId, password, role);
	}

	@Override
	public boolean userRegister(UserInfoBean user) {
		return userDAO.userRegister(user);
	}

	@Override
	public boolean updateUserDetails(String userId, UserInfoBean user) {
		return userDAO.updateUserDetails(userId, user);
	}

	@Override
	public Collection<UserInfoBean> getAllUsers() {
		return userDAO.getAllUsers();
	}
	
	
}
