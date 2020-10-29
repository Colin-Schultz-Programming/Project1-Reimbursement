package com.reimbursement.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reimbursement.config.RDBConnection;
import com.reimbursement.model.User_Role;

public class UserRoleDao implements DaoContract<User_Role, Integer>{
	Logger logger;
	
	public UserRoleDao(Logger logger) {
		super();
		this.logger = logger;
	}

	
	@Override
	public List<User_Role> findAll() {
		List<User_Role> r = new LinkedList<>();
		String sql = "select * from ers_user_roles"; // this will sanitize the input
		try (Connection conn = RDBConnection.CreateRDBConnection(logger);
			PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r.add(new User_Role(rs.getInt(1), rs.getString(2)));			}
			rs.close();
			ps.close();
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public User_Role findById(Integer i) {
		User_Role r = new User_Role(-1, "Not Found");
		String sql = "select * from ers_user_roles where ers_user_role_id = ?"; // this will sanitize the input
		try (Connection conn = RDBConnection.CreateRDBConnection(logger);
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r = (new User_Role(rs.getInt(1), rs.getString(2)));
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
	public User_Role update(User_Role t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User_Role create(User_Role t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	
}
