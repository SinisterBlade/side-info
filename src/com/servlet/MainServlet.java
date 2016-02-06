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

import com.app.Cluster;
import com.app.DocumentScraper;
import com.app.DocumentStorage;
import com.app.GoogleLinkRetriever;
import com.app.VectorSpaceCreator;
import com.bean.KDocument;
import com.dao.ConnectionManager;
import com.dao.DAOImpl;
import com.dao.DAOInterface;
import com.factory.KDocFactory;

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
		ArrayList<String> vectors = (ArrayList<String>) new VectorSpaceCreator(query).getVectorSpace();
		KDocFactory factory = new KDocFactory(dao, vectors);
		ArrayList<KDocument> docList = factory.getKDocs();
		/*Cluster cluster = new Cluster(1, vectors);
		for (KDocument kd : docList) {
			System.out.println("ID: " + kd.getId());
			System.out.println("TFIDF: " + kd.getTfIdf());
			cluster.addDocument(kd);
		}
		cluster.calculateCentroid();*/
		
		
	}
	
	@Override
	public void init() throws ServletException {
		ConnectionManager cm = (ConnectionManager)getServletContext().getAttribute("connection");
		Connection con = cm.getConnection();
		System.out.println(con);
		dao = new DAOImpl(con);
	}
}
