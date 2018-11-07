package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Users;
import com.revature.services.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger(LoginServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		if(logger.isDebugEnabled()) {
			logger.debug("Request sent to LoginServlet.doPost()");
		}
		
		if(logger.isTraceEnabled()) {
			logger.trace("Request sent to LoginServlet.doPost()");
		}
		
		UserService userService = new UserService();
		ObjectMapper mapper = new ObjectMapper();
		
		String[] userCreds = mapper.readValue(request.getInputStream(), String[].class);
		String username = userCreds[0];
		String password = userCreds[1];
		
		Users currUser = userService.Login(username, password);
		
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		String authUserJSON = mapper.writeValueAsString(currUser);
		pw.write(authUserJSON);
	}
}
