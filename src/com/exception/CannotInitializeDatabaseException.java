package com.exception;

/**
 * Exception that is thrown when database cannot be initialised or is not found
 * @author Rajat
 *
 */
public class CannotInitializeDatabaseException extends Exception {
	@Override
	public String getMessage() {
		return "The database could not be configured properly.";
	}
}
