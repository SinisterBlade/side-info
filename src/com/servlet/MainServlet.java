package com.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.Cluster;
import com.app.DocumentScraper;
import com.app.DocumentStorage;
import com.app.GoogleLinkRetriever;
import com.app.KMeans;
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
		req.setAttribute("dao", dao);
		dao.clearTable();
		GoogleLinkRetriever retriever = new GoogleLinkRetriever();
		ArrayList<String> links = retriever.getLinks(query);
		DocumentStorage dStorage = new DocumentStorage(dao);
		dStorage.getAndStore(0, links, 3);
		ArrayList<String> vectors = (ArrayList<String>) new VectorSpaceCreator(query).getVectorSpace();
		KDocFactory factory = new KDocFactory(dao, vectors);
		ArrayList<KDocument> docList = factory.getKDocs();
		KMeans km = new KMeans(vectors.size(), docList, vectors);
		ArrayList<Cluster> clusters = km.startKMeaning();
		System.out.println("Clustering Finished!");
		req.setAttribute("clusters", clusters);
		/*System.out.println("Final clusters:");
		for(Cluster c : clusters) {
			System.out.println("Cluster " + c.getId() + ":");
			ArrayList<KDocument> documents = c.getDocuments();
			for(KDocument doc : documents) {
				System.out.println(dao.getURL(doc.getId()));
			}
		}*/
		
		//req.setAttribute("error", "This error is awesome!");
		RequestDispatcher rd = req.getRequestDispatcher("result.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	public void init() throws ServletException {
		ConnectionManager cm = (ConnectionManager)getServletContext().getAttribute("connection");
		Connection con = cm.getConnection();
		System.out.println(con);
		dao = new DAOImpl(con);
	}
}
