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
import com.revature.services.ReimbursementService;

@WebServlet("/update_ticket")
public class UpdateTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("In update ticket doPost()");
		
		ReimbursementService reimbService = new ReimbursementService();
		Users user = new Users();
		
		ObjectMapper mapper = new ObjectMapper();
		String[] newTicketInfo = mapper.readValue(request.getInputStream(), String[].class);
		
		String reimbId = newTicketInfo[0];
		String newStatusId = newTicketInfo[1];

		int resolver = user.getId();

		boolean isUpdated = reimbService.updateStatus(Integer.parseInt(reimbId), resolver,
				Integer.parseInt(newStatusId));
		if (isUpdated == true) {
			System.out.println("Ticket updated");
			PrintWriter pw = response.getWriter();
			response.setContentType("application/json");
			String updatedJSON = mapper.writeValueAsString(isUpdated);
			pw.write(updatedJSON);
		}
	}
}
