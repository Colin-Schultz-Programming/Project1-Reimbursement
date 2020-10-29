package com.reimbursement.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimbursement.model.Reimbursement;
import com.reimbursement.service.ReimbursementService;


public class ReimbursementController {
	
	private ReimbursementService rs;
	
	public ReimbursementController(Logger logger) {
		rs = new ReimbursementService(logger);
	}
	public ReimbursementController(ReimbursementService r) {
		rs = r;
	}
	public String createNewReimbursement(HttpServletRequest req) {
		
		int sessionUser = Integer.parseInt(req.getSession().getAttribute("userID").toString());
		double amount =  Double.parseDouble(req.getParameter("amount").toString());
		String description = req.getParameter("description").toString();
		int reimb_type = Integer.parseInt(req.getParameter("reimb_type").toString());
		Reimbursement r = new Reimbursement(0,amount, null, null, description, null, sessionUser, 0, 0, reimb_type);
		rs.Create(r);
		return "FrontEnd/html/employeepage.html";
	}
	public void sendAllData(HttpServletResponse res) {
		res.setContentType("text/json");
		List<Reimbursement> monsters = rs.findAll();
		try {
			res.getWriter().println(new ObjectMapper().writeValueAsString(monsters));
		} catch (IOException e) {
		}
	}
	public void sendUserPending(HttpServletResponse res, HttpServletRequest req) {
		res.setContentType("text/json");
		
		int sessionUser = Integer.parseInt(req.getSession().getAttribute("userID").toString());

		List<Reimbursement> reimb = rs.findAllByPendingUserID(sessionUser);
		try {
			res.getWriter().println(new ObjectMapper().writeValueAsString(reimb));
		} catch (IOException e) {
		}
	}
	public String resolveReimbursement(HttpServletRequest req) {
		int sessionUser = Integer.parseInt(req.getSession().getAttribute("userID").toString());
		return "FrontEnd/html/managerpage.html";
	}
	
	
}
