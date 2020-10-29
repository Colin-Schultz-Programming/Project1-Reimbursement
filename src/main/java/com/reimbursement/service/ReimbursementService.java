package com.reimbursement.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.log4j.Logger;

import com.reimbursement.model.Reimbursement;
import com.reimbursement.repo.ReimbursementDao;

public class ReimbursementService {
	private ReimbursementDao rd;
	
	public ReimbursementService(Logger logger) {
		this.rd = new ReimbursementDao(logger);
	}
	
	public List<Reimbursement> findAllByUserID(int i){
		return rd.findAllByUser(i);
	}
	
	public List<Reimbursement> findAll(){
		return rd.findAll();
	}
	public Reimbursement Approve(Reimbursement r, int i) {
		r.setResolverID(i);
		r.setResolved(Timestamp.valueOf(LocalDateTime.now()));
		r.setStatusID(2);
		rd.update(r);
		return r;
	}
	
	public Reimbursement Deny(Reimbursement r, int i) {
		r.setResolverID(i);
		r.setResolved(Timestamp.valueOf(LocalDateTime.now()));
		r.setStatusID(1);
		rd.update(r);
		return r;
	}
	
	public Reimbursement Create(Reimbursement r) {
		r.setSubmitted(Timestamp.valueOf(LocalDateTime.now()));
		r.setStatusID(0);
		Reimbursement rt = rd.create(r);
		return rt;
	}
}
