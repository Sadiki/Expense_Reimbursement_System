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

@WebServlet("/new_ticket")
public class AddNewTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users user = new Users();
		ReimbursementService reimbService = new ReimbursementService();
		ObjectMapper mapper = new ObjectMapper();
		String[] newTicketInfo = mapper.readValue(request.getInputStream(), String[].class);
		String amount = newTicketInfo[0];
		String description = newTicketInfo[1];
		String status = newTicketInfo[2];
		String type = newTicketInfo[3];
		String userId = newTicketInfo[4];
		for (int i = 0; i < newTicketInfo.length; i++) {
			System.out.println(newTicketInfo[i]);
		}
		boolean isCreated = reimbService.addNewTicket(Double.parseDouble(amount), description, Integer.parseInt(status),
				Integer.parseInt(type), Integer.parseInt(userId));
		if (isCreated == true) {
			System.out.println("New Ticket Created");
			PrintWriter pw = response.getWriter();
			response.setContentType("application/json");
			String reimbJSON = mapper.writeValueAsString(isCreated);
			pw.write(reimbJSON);
		}
	}

}