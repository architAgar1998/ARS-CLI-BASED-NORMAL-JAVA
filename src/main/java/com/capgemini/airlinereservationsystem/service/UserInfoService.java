package com.capgemini.airlinereservationsystem.service;

import java.util.Collection;

import com.capgemini.airlinereservationsystem.bean.UserInfoBean;

public interface UserInfoService {
	public boolean userAuthentication(String userId, String password, String role);
	public boolean userRegister(UserInfoBean user);
	public Collection<UserInfoBean> getAllUsers();
	public  boolean updateUserDetails(String userId, UserInfoBean user);
}
