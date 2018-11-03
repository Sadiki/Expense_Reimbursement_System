package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Users;
import com.revature.services.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("IN LOGINSERVLET");
		
		UserService userService = new UserService();
		ObjectMapper mapper = new ObjectMapper();
		
		String[] userCreds = mapper.readValue(request.getInputStream(), String[].class);
		String username = userCreds[0];
		String password = userCreds[1];
		
		Users authUser = userService.Login(username, password);
		
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		String authUserJSON = mapper.writeValueAsString(authUser);
		pw.write(authUserJSON);
	}
}
