package com.revature.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static ConnectionFactory cf;
	
	private static boolean build = true;
	
	private ConnectionFactory() {
		build = false;
	}
	
	public static synchronized ConnectionFactory getInstance() {
		return (build) ? cf = new ConnectionFactory(): cf;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		
		try {
			// Load the properties file keys/values into the properties object
			prop.load(new FileReader("C:\\Users\\Sudoku\\Expense_Reimbursement_System\\Ers_Project\\src\\main\\resources\\application.properties"));
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Get a connection from the DriverManger
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("usr"), prop.getProperty("pw"));
		} catch( ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		return conn;
	}
}
