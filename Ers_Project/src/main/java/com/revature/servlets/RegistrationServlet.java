package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Users;
import com.revature.services.UserService;

@WebServlet("/create_account")
public class RegistrationServlet extends HttpServlet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger(RegistrationServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println();
		if (logger.isDebugEnabled()) {
			logger.debug("In RegistrationServlet.doPost()");
		}

		if (logger.isTraceEnabled()) {
			logger.trace("In RegistrationServlet.doPost()");
		}
		UserService userService = new UserService();
		ObjectMapper mapper = new ObjectMapper();

		// Jackson takes the string request and then looks the user class. Compares the requested values to the user class and maps them to create JSON object
		Users newUser = mapper.readValue(request.getInputStream(), Users.class);
		
		System.out.println(newUser);
		// Add the new user and store the response
		boolean isCreated = userService.addUser(newUser);
		
		// Create a printwriter. Write the boolean to the response and send it to the client to check if the user had been addded.
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		String isCreatedJSON = mapper.writeValueAsString(isCreated);
		pw.write(isCreatedJSON);
	}
}
