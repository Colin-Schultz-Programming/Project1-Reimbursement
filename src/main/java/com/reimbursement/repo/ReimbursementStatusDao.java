package com.reimbursement.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reimbursement.config.RDBConnection;
import com.reimbursement.model.Reimbursement_Status;

public class ReimbursementStatusDao implements DaoContract<Reimbursement_Status, Integer>{
	Logger logger;
	
	public ReimbursementStatusDao(Logger logger) {
		super();
		this.logger = logger;
	}
	@Override
	public List<Reimbursement_Status> findAll() {
		List<Reimbursement_Status> r = new LinkedList<>();
		String sql = "select * from ers_reimbursement_status"; // this will sanitize the input
		try (Connection conn = RDBConnection.CreateRDBConnection(logger);
			PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r.add(new Reimbursement_Status(rs.getInt(1), rs.getString(2)));			}
			rs.close();
			ps.close();
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Reimbursement_Status findById(Integer i) {
		Reimbursement_Status r = new Reimbursement_Status(-1, "Not Found");
		String sql = "select * from ers_reimbursement_status where reimb_status_id = ?"; // this will sanitize the input
		try (Connection conn = RDBConnection.CreateRDBConnection(logger);
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r = (new Reimbursement_Status(rs.getInt(1), rs.getString(2)));
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
	public Reimbursement_Status update(Reimbursement_Status t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursement_Status create(Reimbursement_Status t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	
}
