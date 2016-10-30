package com.exception;

/**
 * Exception is thrown when the given query does not find any links
 * @author Rajat
 *
 */
public class NoLinksFoundException extends Exception {
	
	private String query;
	
	public NoLinksFoundException(String query) {
		this.query = query;
	}
	
	@Override
	public String getMessage() {
		return("Your query \"" + query + "\" did not match any documents!");
	}
}
