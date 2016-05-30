package com.exception;

/**
 * Exception to be thrown when database query cannot be executed
 * @author Rajat
 *
 */
public class DatabaseException extends Exception {
	@Override
	public String getMessage() {
		return "The internal database is facing some issues.";
	}
}
