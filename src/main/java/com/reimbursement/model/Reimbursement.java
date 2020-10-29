package com.reimbursement.model;

import java.sql.Timestamp;

public class Reimbursement {
	private int reimbID;
	private double reimbAmount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private byte[] receipt;
	private int authorID;
	private int resolverID;
	private int statusID;
	private int typeID;
	
	public Reimbursement(int reimbID, double reimbAmount, Timestamp submitted, Timestamp resolved,
			String description, byte[] receipt, int authorID, int resolverID, int statusID, int typeID) {
		super();
		this.reimbID = reimbID;
		this.reimbAmount = reimbAmount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.authorID = authorID;
		this.resolverID = resolverID;
		this.statusID = statusID;
		this.typeID = typeID;
	}
	public int getReimbID() {
		return reimbID;
	}
	public void setReimbID(int reimbID) {
		this.reimbID = reimbID;
	}
	public double getReimbAmount() {
		return reimbAmount;
	}
	public void setReimbAmount(double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}
	public Timestamp getSubmitted() {
		return submitted;
	}
	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}
	public Timestamp getResolved() {
		return resolved;
	}
	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte[] getReceipt() {
		return receipt;
	}
	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}
	public int getAuthorID() {
		return authorID;
	}
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	public int getResolverID() {
		return resolverID;
	}
	public void setResolverID(int resolverID) {
		this.resolverID = resolverID;
	}
	public int getStatusID() {
		return statusID;
	}
	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	
	
	
	
}
