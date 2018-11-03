package com.revature;

import java.util.List;

import com.revature.DAO.ReimbursementDAOImpl;
import com.revature.DAO.UsersDAOImpl;
import com.revature.models.Reimbursement;
import com.revature.models.Users;

public class MainDriver {


	
	public static void main(String[] args) {
		UsersDAOImpl usrDao = new UsersDAOImpl();
		ReimbursementDAOImpl reimDAO = new ReimbursementDAOImpl();
		
		Reimbursement reim = new Reimbursement();
		Users user = new Users();
		

		reim.setAmount(1000);
		reim.setDesc("I am broken.");
		reim.setAuthor_id(1);
		reim.setStatus_id(1);
		reim.setType_id(1);
		//boolean wasAdded = reimDAO.addNewTicket(reim.getAmount(), reim.getDesc(), reim.getStatus_id(), reim.getType_id());
		List<Reimbursement> reimbs = reimDAO.getAllTickets();
		
		for(Reimbursement reimb : reimbs) {
			System.out.println(reimb);
		}
		
//		user.setUsername("Gingerbread");
//		user.setPassword("Man");
//		user.setEmail("Ginger@gmail.com");
//		user.setFirstname("Ginger");
//		user.setLastname("Bread");
//		user.setRole_id(1);
//		
//		usrDao.addUser(user);
		

	}
}
