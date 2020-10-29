package com.reimbursement.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reimbursement.config.RDBConnection;
import com.reimbursement.model.User;

public class UserDao implements DaoContract<User, Integer>{
	Logger logger;
	
	public UserDao(Logger logger) {
		super();
		this.logger = logger;
	}
	@Override
	public List<User> findAll() {
		List<User> u = new LinkedList<>();
		String sql = "select * from ers_users"; // this will sanitize the input
		try (Connection conn = RDBConnection.CreateRDBConnection(logger);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6), rs.getInt(7)));
			}
			rs.close();
			ps.close();
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public User findById(Integer i) {
		User u = null;
		String sql = "select * from ers_users where ers_users_id = ?"; // this will sanitize the input
		try (Connection conn = RDBConnection.CreateRDBConnection(logger);
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u = (new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6), rs.getInt(7)));
			}
			rs.close();
			ps.close();
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	
	}
	public User findByUserName(String i) {
		User u = null;
		String sql = "select * from ers_users where ers_username = ?"; // this will sanitize the input
		try (Connection conn = RDBConnection.CreateRDBConnection(logger);
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u = (new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6), rs.getInt(7)));
			}
			rs.close();
			ps.close();
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	
	}
	@Override
	public User update(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User create(User t) {
		String sql = "insert into ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) values (?, ?, ?, ?, ?, ?)"; // this will sanitize the input
		try (Connection conn = RDBConnection.CreateRDBConnection(logger);
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, t.getUsername());
				ps.setString(2, t.getPassword());
				ps.setString(3,t.getFirstName());
				ps.setString(4, t.getLastName());
				ps.setString(5, t.getEmail());
				ps.setInt(6, t.getUserRoleID());
				ps.execute();
				
				t = findByUserName(t.getUsername());
			
				ps.close();
				logger.info(t.getUsername() + " Created an account.");
				return t;
		} catch (SQLException e) {
			
		}
		return null;
	}

	@Override
	public int delete(Integer i) {
		String sql = "delete from ers_users where ers_users_id = ?";
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
