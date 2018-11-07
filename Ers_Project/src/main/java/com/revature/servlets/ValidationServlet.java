package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.util.ValidationHelper;

@WebServlet("*.validate")
public class ValidationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger(ValidationServlet.class);

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		if (logger.isDebugEnabled()) {
			logger.debug("Request sent to Front Controller. Validation servlet.doGet()");
		}

		if (logger.isTraceEnabled()) {
			logger.trace("Request sent to Front Controller. Validation servlet.doGet()");
		}
		
		String validatedInput = ValidationHelper.process(request);
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		String validatedJSON = mapper.writeValueAsString(validatedInput);

		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		pw.write(validatedJSON);
		
	}
}
