package com.revature.services;

import com.revature.DAO.UsersDAOImpl;
import com.revature.models.Users;

public class UserService {

	private UsersDAOImpl userDAO = new UsersDAOImpl();

	public Users Login(String usrnm, String psword) {
		// Get the User from DB using the entered credentials
		return userDAO.getUser(usrnm, psword);
	}

	public boolean isUsernameAvaliable(String username) {
		// Check is the same username has been entered into the DB
		if (userDAO.getUserByUsername(username) == null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEmailAvaliable(String email) {

		// Check is the same email has been entered into the DB
		if (userDAO.getUserByEmail(email) == null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean addUser(Users user) {
		return userDAO.addUser(user);
	}
}
