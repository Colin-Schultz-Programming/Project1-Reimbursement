package com.reimbursement.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.reimbursement.controller.UserController;



public class RequestForwarder {

	
	public String routes(HttpServletRequest req, Logger logger) {
		switch (req.getRequestURI()){
		case "/Project1-Reimbursement/login.page":
			System.out.println("login page");
			return new UserController(logger).login(req);
			
		default: 
			return "FrontEnd/html/landing.html";
		}
	}
	
	public void data(HttpServletRequest req, HttpServletResponse res) throws IOException {
		switch(req.getRequestURI()) {
		case "/HallowsMonsters/all.json":
			//new MonsterDataController().sendAllData(res);
			break;
		case "/HallowsMonsters/monster.json":
			//new SaveController().save(req, res);
			break;
		}
	}
}
