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
import com.revature.models.Users;
import com.revature.services.ReimbursementService;

@WebServlet("/new_ticket")
public class AddNewTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger(AddNewTicketServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Request sent to AddNewTicketServlet.doPost()");
		}
		
		if(logger.isTraceEnabled()) {
			logger.trace("Request sent to AddNewTicketServlet.doPost()");
		}
		
		Users user = new Users();
		ReimbursementService reimbService = new ReimbursementService();
		ObjectMapper mapper = new ObjectMapper();
		String[] newTicketInfo = mapper.readValue(request.getInputStream(), String[].class);
		String amount = newTicketInfo[0];
		String description = newTicketInfo[1];
		String status = newTicketInfo[2];
		String type = newTicketInfo[3];
		String userId = newTicketInfo[4];
		
		boolean isCreated = reimbService.addNewTicket(Double.parseDouble(amount), description, Integer.parseInt(status),
				Integer.parseInt(type), Integer.parseInt(userId));
		if (isCreated == true) {
			if(logger.isDebugEnabled()) {
				logger.debug("New Ticket Created");
			}
			
			if(logger.isTraceEnabled()) {
				logger.trace("New Ticket Created");
			}
			
			PrintWriter pw = response.getWriter();
			response.setContentType("application/json");
			String reimbJSON = mapper.writeValueAsString(isCreated);
			pw.write(reimbJSON);
		}
	}

}