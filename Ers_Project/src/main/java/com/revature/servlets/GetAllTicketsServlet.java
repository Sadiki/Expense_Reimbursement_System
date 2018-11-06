package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
@WebServlet("/all_tickets")
public class GetAllTicketsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	ReimbursementService reimbService = new ReimbursementService();
	List<Reimbursement> allTickets = new ArrayList<Reimbursement>();
	
	ObjectMapper mapper = new ObjectMapper();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		allTickets = reimbService.getAllTickets();
		
		String allTicketsJSON = "";
		
		// Create a printwriter to send back the information
		PrintWriter pw = response.getWriter();
		response.setContentType("application/JSON");
		
		for(Reimbursement tic: allTickets) {
			allTicketsJSON += "*" + mapper.writeValueAsString(tic);
		}
		System.out.println(allTicketsJSON);
		pw.write(allTicketsJSON);
	}
	
}
