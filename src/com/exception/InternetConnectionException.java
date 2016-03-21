package com.exception;

public class InternetConnectionException extends Exception {
	@Override
	public String getMessage() {
		return "Could not connect to the internet.";
	}
}
