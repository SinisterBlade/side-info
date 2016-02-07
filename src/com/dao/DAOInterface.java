package com.dao;

import java.sql.ResultSet;

public interface DAOInterface {
	public void addLink(String url, String content, int refId);
	public void clearTable();
	public boolean linkExists(String link);
	public int getId(String url);
	public ResultSet getAllContent();
	public int getFrequency(String term);
	public int countDocuments();
	public String getURL(int id);
}
