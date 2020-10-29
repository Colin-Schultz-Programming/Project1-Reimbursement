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
		public String register(HttpServletRequest req) {
			String un = req.getParameter("Username").toLowerCase();
			String up = UtilityFunctions.encryptPassword(req.getParameter("Password"));
			String fn = req.getParameter("FirstName");
			String ln = req.getParameter("LastName");
			String e = req.getParameter("Email").toLowerCase();
			User u = new User(0, un, up, fn, ln, e, 0);
			if(us.usernameExists(u)) {
				return "usernameExists";
			}
			if(us.emailExists(u)) {
				return "emailExists";
			}
			us.createUser(u);
			return "accountCreated";
			
			
		}
		public String login(HttpServletRequest req) {
			String un = req.getParameter("Username").toLowerCase();
			String up = req.getParameter("Password");

			User u = us.findByUsername(un);
			if(u != null) {
				
				if(UtilityFunctions.verifyLogin(u.getUsername(),up, logger)) {
					req.getSession().setAttribute("userID", u.getUserID());
					
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


