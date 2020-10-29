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
			System.out.println("login page");
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
				System.out.println("Account Created");
				break;
			}	
		default: 
			return "FrontEnd/html/landing.html";
		}
	}
	
	public void data(HttpServletRequest req, HttpServletResponse res, Logger logger) throws IOException {
		switch(req.getRequestURI()) {
		case "/Reimbursement/all.json":
			new ReimbursementController(logger).sendAllData(res);
			break;
		case "/HallowsMonsters/monster.json":
			//new SaveController().save(req, res);
			break;
		}
	}
}
