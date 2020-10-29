package com.reimbursement.service;

import org.apache.log4j.Logger;

import com.reimbursement.model.User;
import com.reimbursement.repo.UserDao;

public class UserService {
	private UserDao ud;
	
	public UserService(Logger logger) {
		this.ud = new UserDao(logger);
	}
	public User findByUsername(String un) {
		return ud.findByUserName(un);
	}
	
	public User findByUserID(int id) {
		return ud.findById(id);
	}
	
	public User createUser(User u) {
		User ur = ud.create(u);
		if(ur.equals(null)) {
			System.out.println("Account Exists");
		}
		return ur;
	}
	
}
