package com.app;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLProtocolException;

import org.jsoup.HttpStatusException;

import com.dao.DAOImpl;
import com.dao.DAOInterface;

public class DocumentStorage {
	DAOInterface dao;
	
	public DocumentStorage(DAOInterface dao) {
		this.dao = dao;
	}
	
	public void getAndStore(int refid, ArrayList<String> links, int depth) throws IOException {
		if(depth > 0) {
			System.out.println("Links at depth " + depth + ": " + links.size());
			for (String url : links) {
				if(!dao.linkExists(url)) {
					try {
						DocumentScraper ds = new DocumentScraper(url);
						String content = ds.getFirstParagraph();
						if (content != null) {
							content = content.toLowerCase();
							dao.addLink(url, content, refid);
							int id = dao.getId(url);
							System.out.println("Added link: " + url);
							ArrayList<String> subLinks = ds.getLinksFromFirstParagraph();
							getAndStore(id, subLinks, depth - 1);
						}
						else {
							System.err.println("Content null: " + url);
						}
					}
					catch (HttpStatusException e) {
						System.err.println("Could not connect: " + url);
					}
					catch (SocketTimeoutException e) {
						System.err.println("Timed out: " + url);
					}
					catch (SSLHandshakeException e) {
						System.err.println("SSL Exception: " + url);
					}
					catch (UnknownHostException e) {
						System.err.println("Unknown Host: " + url);
					}
					catch (SSLProtocolException e) {
						System.err.println("SSL Protocol Exception: " + url);
					}
				}
				else {
					System.out.println("Already in database: " + url);
				}
			}
		}
	}
	
}
