package com.reimbursement.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;


import com.reimbursement.config.RDBConnection;
import com.reimbursement.model.Reimbursement;

public class ReimbursementDao implements DaoContract<Reimbursement, Integer>{
	Logger logger;
	
	public ReimbursementDao(Logger logger) {
		super();
		this.logger = logger;
	}
	@Override
	public List<Reimbursement> findAll() {
		List<Reimbursement> r = new LinkedList<>();
		String sql = "select * from ers_reimbursement"; // this will sanitize the input
		try (Connection conn = RDBConnection.CreateRDBConnection(logger);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4),rs.getString(5), rs.getBytes(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
			rs.close();
			ps.close();
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	public List<Reimbursement> findAllPending() {
		List<Reimbursement> r = new LinkedList<>();
		String sql = "select * from ers_reimbursement where reimb_status_id = 0"; // this will sanitize the input
		try (Connection conn = RDBConnection.CreateRDBConnection(logger);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4),rs.getString(5), rs.getBytes(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
			rs.close();
			ps.close();
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	public List<Reimbursement> findAllResolved() {
		List<Reimbursement> r = new LinkedList<>();
		String sql = "select * from ers_reimbursement where reimb_status_id != 0"; // this will sanitize the input
		try (Connection conn = RDBConnection.CreateRDBConnection(logger);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4),rs.getString(5), rs.getBytes(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
			rs.close();
			ps.close();
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	public List<Reimbursement> findAllByUser(Integer i) {
		List<Reimbursement> r = new LinkedList<>();
		String sql = "select * from ers_reimbursement where reimb_author = ?"; // this will sanitize the input
		try (Connection conn = RDBConnection.CreateRDBConnection(logger);
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4),rs.getString(5), rs.getBytes(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
			rs.close();
			ps.close();
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	public List<Reimbursement> findAllPendingByUser(Integer i) {
		List<Reimbursement> r = new LinkedList<>();
		String sql = "select * from ers_reimbursement where reimb_author = ? AND reimb_status_id = 0"; // this will sanitize the input
		try (Connection conn = RDBConnection.CreateRDBConnection(logger);
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4),rs.getString(5), rs.getBytes(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
			rs.close();
			ps.close();
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	public List<Reimbursement> findAllResolvedByUser(Integer i) {
		List<Reimbursement> r = new LinkedList<>();
		String sql = "select * from ers_reimbursement where reimb_author = ? and reimb_status_id != 0"; // this will sanitize the input
		try (Connection conn = RDBConnection.CreateRDBConnection(logger);
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4),rs.getString(5), rs.getBytes(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
			rs.close();
			ps.close();
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Reimbursement findById(Integer i) {
		Reimbursement r = null;
		String sql = "select * from ers_reimbursement where reimb_id = ?"; // this will sanitize the input
		try (Connection conn = RDBConnection.CreateRDBConnection(logger);
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r = (new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4),rs.getString(5), rs.getBytes(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
			rs.close();
			ps.close();
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Reimbursement update(Reimbursement t) {
		String sql = "update ers_reimbursement set (reimb_resolved = ?, reimb_receipt = ?, reimb_resolver = ? , reimb_status_id = ?) where reimb_id = ?";	
		try (Connection conn = RDBConnection.CreateRDBConnection(logger);
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setTimestamp(1, t.getResolved());
				ps.setBytes(2, t.getReceipt());
				ps.setInt(3,t.getResolverID());
				ps.setInt(4, t.getStatusID());
				ps.setInt(5,t.getReimbID());
				if(t.getStatusID() == 2) logger.info("UserID: " + t.getResolverID() + " resolved reimbursement: Approved");
				if(t.getStatusID() == 1) logger.info("UserID: " + t.getResolverID() + " resolved reimbursement: Denied");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Reimbursement create(Reimbursement t) {
		String sql = "insert into ers_reimbursement (reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_status_id, reimb_type_id) values (?, ?, ?, ?, ?, ?)"; // this will sanitize the input
		try (Connection conn = RDBConnection.CreateRDBConnection(logger);
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setDouble(1, t.getReimbAmount());
				ps.setTimestamp(2, t.getSubmitted());
				ps.setString(3,t.getDescription());
				ps.setInt(4, t.getAuthorID());
				ps.setInt(5, t.getStatusID());
				ps.setInt(6, t.getTypeID());
				ps.execute();
			
				t = findAllByUser(t.getAuthorID()).get(0);
				
				ps.close();
				logger.info("UserID: " + t.getAuthorID() + " created a reimbursement.");
				return t;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int delete(Integer i) {
		String sql = "delete from ers_reimbursement where reimb_id = ?";
		try (Connection conn = RDBConnection.CreateRDBConnection(logger);
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, i);
				ps.execute();
				return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	
}
