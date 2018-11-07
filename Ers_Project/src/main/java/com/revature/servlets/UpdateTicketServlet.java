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

@WebServlet("/update_ticket")
public class UpdateTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger(UpdateTicketServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("In UpdateTicketServlet.doPost()");
		}

		if (logger.isTraceEnabled()) {
			logger.trace("In UpdateTicketServlet.doPost()");
		}
		
		ReimbursementService reimbService = new ReimbursementService();
		
		ObjectMapper mapper = new ObjectMapper();
		String[] newTicketInfo = mapper.readValue(request.getInputStream(), String[].class);
		

		String resolver = newTicketInfo[0];
		String newStatusId = newTicketInfo[1];
		String reimbId = newTicketInfo[2];

		boolean isUpdated = reimbService.updateStatus(Integer.parseInt(resolver),Integer.parseInt(newStatusId), Integer.parseInt(reimbId));
		if (isUpdated == true) {
			if (logger.isDebugEnabled()) {
				logger.debug("Ticket updated");
			}

			if (logger.isTraceEnabled()) {
				logger.trace("Ticket updated");
			}

			PrintWriter pw = response.getWriter();
			response.setContentType("application/json");
			String updatedJSON = mapper.writeValueAsString(isUpdated);
			pw.write(updatedJSON);
		}
	}
}
