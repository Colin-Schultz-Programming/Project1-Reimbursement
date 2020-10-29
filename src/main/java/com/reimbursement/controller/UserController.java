package com.reimbursement.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.reimbursement.model.User;
import com.reimbursement.service.UserService;
import com.reimbursement.service.UtilityFunctions;



public class UserController {
	

		private UserService us;
		private Logger logger;

		public UserController(UserService us, Logger logger) {
			super();
			this.logger = logger;
			this.us = us;
		}

		public UserController(Logger logger) {
			this.us = new UserService(logger);
			this.logger = logger;
		}
		
		public String login(HttpServletRequest req) {
			String un = req.getParameter("Username");
			String up = req.getParameter("Password");

			User u = us.findByUsername(un);
			if(u != null) {
				
				if(UtilityFunctions.verifyLogin(u.getUsername(), u.getPassword(), logger)) {
					if(u.getUserRoleID() == 1) {
						return "FrontEnd/html/managerpage.html";
					}
					else {	
						 return "FrontEnd/html/employeepage.html";
					}
	
				}
				else {
					return "FrontEnd/html/landing.html";
				}
			} else {
				return "FrontEnd/html/landing.html";
			}
		}
	}


