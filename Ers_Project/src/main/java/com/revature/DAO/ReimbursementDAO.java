package com.revature.DAO;

import java.util.ArrayList;

import com.revature.models.Reimbursement;

public interface ReimbursementDAO {

	ArrayList<Reimbursement> getAllTickets();
	
	boolean addNewTicket(double amount, String description, int statusId, int typeId, int userId);
	boolean updateTicketStatus(int userId, int statusId, int ticketId);
	
	ArrayList<Reimbursement> getTicketStatus(int statusId);
	ArrayList<Reimbursement> getUserTickets(int userId);
}
