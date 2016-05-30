package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.exception.CannotInitializeDatabaseException;

/**
 * Class to manage database connection
 * @author Rajat
 *
 */
public class ConnectionManager {
	String driver, url, username, password;
	
	/**
	 * 
	 * @param driver Database driver
	 * @param url Database URL
	 * @param username Database User Name
	 * @param password Database Password
	 */
	public ConnectionManager(String driver, String url, String username, String password) {
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * 
	 * @return SQL Database Connection
	 * @throws CannotInitializeDatabaseException
	 */
	public Connection getConnection() throws CannotInitializeDatabaseException {
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, username, password);
			return con;
		} catch(SQLException e) {
			throw new CannotInitializeDatabaseException();
		} catch(ClassNotFoundException e) {
			throw new CannotInitializeDatabaseException();
		}
	}
}
