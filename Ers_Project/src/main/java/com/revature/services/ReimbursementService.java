package com.revature.services;

import java.util.ArrayList;

import com.revature.DAO.ReimbursementDAOImpl;
import com.revature.models.Reimbursement;

public class ReimbursementService {
	private ReimbursementDAOImpl reimDAO = new ReimbursementDAOImpl();
	
	public ArrayList<Reimbursement> getAllTickets(){
		return reimDAO.getAllTickets();
	}
	
	public boolean addNewTicket(double amount, String description, int statusId, int typeId, int userId) {
		return reimDAO.addNewTicket(amount, description, statusId, typeId, userId);
	}
	
	public boolean updateStatus(int reimbId, int resolver, int newStatusId) {
		return reimDAO.updateTicketStatus(reimbId, resolver, newStatusId);
	}
	
	public ArrayList<Reimbursement> getTicketStatus(int statusId){
		return reimDAO.getTicketStatus(statusId);
	}
	
	public ArrayList<Reimbursement> getUserTickets(int authorId){
		return reimDAO.getUserTickets(authorId);
	}
	
}
