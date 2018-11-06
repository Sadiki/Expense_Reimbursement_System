package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.models.Users;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Request sent to the front controller, LogoutServlet.doGet()");

		Users user = new Users();

		user.setId(0);
		user.setUsername(null);
		user.setPassword(null);
		user.setFirstname(null);
		user.setLastname(null);
		user.setEmail(null);
		user.setRole_id(0);

		System.out.println("User logged out...");
	}
}