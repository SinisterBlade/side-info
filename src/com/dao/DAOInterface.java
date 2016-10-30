package com.dao;

import java.sql.ResultSet;

import com.exception.DatabaseException;

/**
 * Interface that describes all database operations required by the application
 * @author Rajat
 *
 */
public interface DAOInterface {
	
	/**
	 * Add contents of a web document to the database
	 * @param url URL of the web document
	 * @param content Text content of the web page
	 * @param refId Reference ID of the web page from where the URL was obtained
	 */
	public void addLink(String url, String content, int refId);
	
	/**
	 * Truncate all contents of the database
	 * @throws DatabaseException
	 */
	public void clearTable() throws DatabaseException;
	
	/**
	 * Check whether a web document already exists in the database
	 * @param link URL of web document to be checked
	 * @return true if the document exists in the database
	 */
	public boolean linkExists(String link);
	
	/**
	 *
	 * @param url URL of web document
	 * @return ID of web document
	 */
	public int getId(String url);
	
	/**
	 * Gets ID and content of the web page
	 * @return ResultSet with ID and Content
	 */
	public ResultSet getAllContent();
	
	/**
	 * 
	 * @param term Term whose frequency is to be determined
	 * @return Number of documents containing specified term
	 */
	public int getFrequency(String term);
	
	/**
	 * 
	 * @return Total number of documents
	 */
	public int countDocuments();
	
	/**
	 * 
	 * @param id ID of web document
	 * @return URL of web document
	 */
	public String getURL(int id);
	
	public String getContent(int id);
}
