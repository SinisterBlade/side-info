package com.exception;

public class CannotInitializeDatabaseException extends Exception {
	@Override
	public String getMessage() {
		return "The database could not be configured properly.";
	}
}
