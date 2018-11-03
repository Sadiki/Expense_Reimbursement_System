package com.revature.DAO;

import com.revature.models.Users;

public interface UsersDAO {

	Users getUser(String username, String password);
	boolean addUser(Users newUser);
	
	String getUserByUsername(String username);
	String getUserByEmail(String email);
}
