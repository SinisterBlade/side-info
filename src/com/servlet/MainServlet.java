package com.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.DocumentScraper;
import com.app.DocumentStorage;
import com.app.GoogleLinkRetriever;
import com.app.VectorSpaceCreator;
import com.dao.ConnectionManager;
import com.dao.DAOImpl;
import com.dao.DAOInterface;

public class MainServlet extends HttpServlet {
	
	DAOInterface dao;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In Main Servlet");
		PrintWriter out = resp.getWriter();
		String query = req.getParameter("query");
		/*dao.clearTable();
		GoogleLinkRetriever retriever = new GoogleLinkRetriever();
		ArrayList<String> links = retriever.getLinks(query);
		DocumentStorage dStorage = new DocumentStorage(dao);
		dStorage.getAndStore(0, links, 3);*/
		//System.out.println(dao.linkExists("https://en.wikipedia.org/wiki/Gemma_Arterton"));
		//System.out.println(dao.getId("https://en.wikipedia.org/wiki/Gemma_Arterton"));
		ArrayList<String> s = (ArrayList<String>) new VectorSpaceCreator(query).getVectorSpace();
		for(String sw : s) {
			System.out.println(sw);
		}
	}
	
	@Override
	public void init() throws ServletException {
		ConnectionManager cm = (ConnectionManager)getServletContext().getAttribute("connection");
		Connection con = cm.getConnection();
		System.out.println(con);
		dao = new DAOImpl(con);
	}
}
