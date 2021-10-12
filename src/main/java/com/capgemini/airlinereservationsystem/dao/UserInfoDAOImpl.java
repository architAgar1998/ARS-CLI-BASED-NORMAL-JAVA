package com.capgemini.airlinereservationsystem.dao;

import java.util.Collection;

import com.capgemini.airlinereservationsystem.bean.UserInfoBean;
import com.capgemini.airlinereservationsystem.db.InfoDB;

public class UserInfoDAOImpl implements UserInfoDAO {

	public static UserInfoDAO getUserInfoDAO() {
		return new UserInfoDAOImpl();
	}

	@Override
	public boolean userAuthentication(String userId, String password, String role) {
		for (UserInfoBean userDetails : InfoDB.USER_INFO_DB) {
			if (userDetails.getUserId().equals(userId) && userDetails.getPassword().equals(password) && userDetails.getRole().equals(role)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean userRegister(UserInfoBean user) {
		for (UserInfoBean userDetails : InfoDB.USER_INFO_DB) {
			if (userDetails.getUserId().equals(user.getUserId())) {
				return false;
			} else {
				InfoDB.USER_INFO_DB.add(user);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateUserDetails(String userId, UserInfoBean user) {
//		UserInfoBean userInfoBean = null;
//		for (UserInfoBean userDetails : InfoDB.userInfoDb) {
//			if (userDetails.getUserId().equals(userId)) {
//				userInfoBean = userDetails;
//			} else {
//				return false;
//			}
//		}
//
//		userInfoBean.setFirstName(user.getFirstName());
//		userInfoBean.setLastName(user.getLastName());
//		userInfoBean.setMail(user.getMail());
//		userInfoBean.setMobile(user.getMobile());
//		userInfoBean.setNationality(user.getNationality());
//		userInfoBean.setPassword(user.getPassword());
//		userInfoBean.setResidence(user.getResidence());
//		userInfoBean.setRole(user.getRole());

		return true;
	}

	@Override
	public Collection<UserInfoBean> getAllUsers() {
		return InfoDB.USER_INFO_DB;
	}

}
