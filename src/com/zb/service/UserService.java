package com.zb.service;

import java.sql.SQLException;


import com.zb.dao.UserDao;
import com.zb.pojo.Userinfo;

public class UserService {
	public boolean register(String email, String name, String password) {
		UserDao userDao = new UserDao();
		boolean register = false;
		boolean chechUser = userDao.checkUser(email);
		System.out.println("chechUser:" + chechUser);
		if (chechUser) {
			register = false;
		} else {
			register = userDao.register(email, name, password);
		}
		System.out.println("register:" + register);
		return register;
	}

	public Userinfo Login(String email, String password) throws SQLException {
		UserDao userDao = new UserDao();
		Userinfo user = userDao.login(email, password);
		return user;

	}
}
