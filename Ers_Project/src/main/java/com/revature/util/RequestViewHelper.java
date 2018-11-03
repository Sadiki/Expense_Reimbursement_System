package com.revature.util;

import javax.servlet.http.HttpServletRequest;

public class RequestViewHelper {

	public static String process(HttpServletRequest request) {
		// Uses a switch flow control to determine which partial to return based on what client is requesting from the servlet
		switch(request.getRequestURI()) {
		case "/Ers_Project/welcome_page.view":
			return "partials/welcome_page.html";
		case "/Ers_Project/login.view":
			return "partials/login.html";
		case "/Ers_Project/create_account.view":
			return "partials/create_account.html";
		case "/Ers_Project/admin_login.view":
			return "partials/admin_login.html";
		case "/Ers_Project/user_login.view":
			return "partials/user_login.html";
		case "/Ers_Project/new_ticket.view":
			return "partials/new_ticket.html";
			default: 
				return null;
			
		}
	}
}
