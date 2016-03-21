package com.exception;

public class DatabaseException extends Exception {
	@Override
	public String getMessage() {
		return "The internal database is facing some issues.";
	}
}
