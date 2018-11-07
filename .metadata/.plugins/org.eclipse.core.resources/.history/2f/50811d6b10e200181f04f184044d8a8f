package com.revature.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Users;
import com.revature.util.ConnectionFactory;

import oracle.jdbc.OracleTypes;

public class UsersDAOImpl implements UsersDAO {

	@Override
	public Users getUser(String username, String password) {
		Users user = new Users();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			String sql = "SELECT * FROM ers_users WHERE ers_username = ? AND ers_password = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, username);
			pstmt.setString(2, password);
			System.out.println(pstmt);
			// Get resultset
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirstname(rs.getString(4));
				user.setLastname(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setRole_id(rs.getInt(7));
			}
			conn.commit();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return user;
	}

	@Override
	public boolean addUser(Users newUser) {
		Users user = new Users();
		System.out.println("ERRORRRS");
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			String sql = "INSERT INTO ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) VALUES (?,?,?,?,?,?)";

			// Create a single element array that will hold column name
			String[] id = new String[1];
			id[0] = "ers_user_id";

			// Get the PreparedStatement object from the connection and generate an account
			// id
			PreparedStatement pstmt = conn.prepareStatement(sql, id);

			pstmt.setString(1, newUser.getUsername());
			pstmt.setString(2, newUser.getPassword());
			pstmt.setString(3, newUser.getFirstname());
			pstmt.setString(4, newUser.getLastname());
			pstmt.setString(5, newUser.getEmail());
			pstmt.setInt(6, newUser.getRole_id());

			// Get number of rows that have been inserted and execute the sql statement
			int rowInserted = pstmt.executeUpdate();

			// Get resultset
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rowInserted != 0 || rs != null) {
				while (rs.next()) {
					user.setId(rs.getInt(1));
				}
				conn.commit();
				return true;

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return false;
	}

	@Override
	public String getUserByUsername(String username) {
		Users user = new Users();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			String sql = "SELECT * FROM ers_users WHERE ers_username = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, username);

			ResultSet rs = pstmt.executeQuery();

			if (rs != null) {

				while (rs.next()) {
					user.setId(rs.getInt(1));
					user.setUsername(rs.getString(2));
					user.setPassword(rs.getString(3));
					user.setFirstname(rs.getString(4));
					user.setLastname(rs.getString(5));
					user.setEmail(rs.getString(6));
					user.setRole_id(rs.getInt(7));
				}

				System.out.println(user.getUsername());
				conn.commit();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return user.getUsername();

	}

	@Override
	public String getUserByEmail(String email) {
		Users user = new Users();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			String sql = "SELECT * FROM ers_users WHERE user_email = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email);

			ResultSet rs = pstmt.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					user.setId(rs.getInt(1));
					user.setUsername(rs.getString(2));
					user.setPassword(rs.getString(3));
					user.setFirstname(rs.getString(4));
					user.setLastname(rs.getString(5));
					user.setEmail(rs.getString(6));
					user.setRole_id(rs.getInt(7));
				}

				conn.commit();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return user.getEmail();

	}

}
