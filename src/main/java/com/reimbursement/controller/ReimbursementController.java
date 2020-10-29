package com.reimbursement.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
	
	public void sendAllData(HttpServletResponse res) {
		res.setContentType("text/json");
		List<Reimbursement> monsters = rs.findAll();
		try {
			res.getWriter().println(new ObjectMapper().writeValueAsString(monsters));
		} catch (IOException e) {
		}
	}
}
