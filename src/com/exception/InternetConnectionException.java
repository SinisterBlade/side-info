package com.exception;

/**
 * Exception is thrown when the application cannot connect to the internet
 * @author Rajat
 *
 */
public class InternetConnectionException extends Exception {
	@Override
	public String getMessage() {
		return "Could not connect to the internet.";
	}
}
