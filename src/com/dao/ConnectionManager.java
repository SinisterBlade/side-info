package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.exception.CannotInitializeDatabaseException;

public class ConnectionManager {
	String driver, url, username, password;
	
	public ConnectionManager(String driver, String url, String username, String password) {
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
	}

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
