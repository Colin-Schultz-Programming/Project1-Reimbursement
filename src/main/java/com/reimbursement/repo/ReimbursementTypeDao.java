package com.reimbursement.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reimbursement.config.RDBConnection;
import com.reimbursement.model.Reimbursement_Type;

public class ReimbursementTypeDao implements DaoContract<Reimbursement_Type, Integer>{
	Logger logger;
	
	public ReimbursementTypeDao(Logger logger) {
		super();
		this.logger = logger;
	}
	@Override
	public List<Reimbursement_Type> findAll() {
		List<Reimbursement_Type> r = new LinkedList<>();
		String sql = "select * from ers_reimbursement_type"; // this will sanitize the input
		try (Connection conn = RDBConnection.CreateRDBConnection(logger);
			PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r.add(new Reimbursement_Type(rs.getInt(1), rs.getString(2)));			}
			rs.close();
			ps.close();
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Reimbursement_Type findById(Integer i) {
		Reimbursement_Type r = new Reimbursement_Type(-1, "Not Found");
		String sql = "select * from ers_reimbursement_type where reimb_type_id = ?"; // this will sanitize the input
		try (Connection conn = RDBConnection.CreateRDBConnection(logger);
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r = (new Reimbursement_Type(rs.getInt(1), rs.getString(2)));
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
	public Reimbursement_Type update(Reimbursement_Type t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursement_Type create(Reimbursement_Type t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	
}
