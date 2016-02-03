package com.dao;

public interface DAOInterface {
	public void addLink(String url, String content, int refId);
	public void clearTable();
	public boolean linkExists(String link);
	public int getId(String url);
}
