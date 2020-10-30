package com.reimbursement.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.reimbursement.controller.ReimbursementController;
import com.reimbursement.controller.UserController;
import com.reimbursement.model.Reimbursement;



public class RequestForwarder {

	
	public String routes(HttpServletRequest req, Logger logger) {

		
		
		switch (req.getRequestURI()){
		case "/Reimbursement/login.page":
			return new UserController(logger).login(req);
		case "/Reimbursement/register.page":
			System.out.println("register page");
			switch (new UserController(logger).register(req)) {
				case "userExists":
					System.out.println("Username Taken");
					break;
				case "emailExists":
					System.out.println("Email Taken");
					break;
				case "accountCreated":
					logger.info("Account Created");
					break;
				}
			return "FrontEnd/html/landing.html";
		case "/Reimbursement/submitrequest.page":
			if(req.getSession().getAttribute("userID") == null) {
				return "FrontEnd/html/landing.html";
			}
			return new ReimbursementController(logger).createNewReimbursement(req);
		case "/Reimbursement/resolve.page":
			if(req.getSession().getAttribute("userID") == null) {
				return "FrontEnd/html/landing.html";
			}
			return new ReimbursementController(logger).resolveReimbursement(req);
		case "/Reimbursement/logout.page":
			req.getSession().setAttribute("userID", null);
		default: 
			return "FrontEnd/html/landing.html";
		}
	}
	
	public void data(HttpServletRequest req, HttpServletResponse res, Logger logger) throws IOException {
		switch(req.getRequestURI()) {
		case "/Reimbursement/userPending.json":
			new ReimbursementController(logger).sendUserPending(res, req);
			break;
		case "/Reimbursement/userResolved.json":
			new ReimbursementController(logger).sendUserResolved(res, req);
			break;

		case "/Reimbursement/Resolved.json":
			new ReimbursementController(logger).sendResolved(res, req);
			break;
		
		case "/Reimbursement/Pending.json":
			new ReimbursementController(logger).sendPending(res, req);
			break;
		}
	}
	
}
