package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.Cluster;
import com.app.DocumentStorage;
import com.app.GoogleLinkRetriever;
import com.app.KMeans;
import com.app.Ranker;
import com.app.VectorSpaceCreator;
import com.bean.KDocument;
import com.dao.ConnectionManager;
import com.dao.DAOImpl;
import com.dao.DAOInterface;
import com.exception.CannotInitializeDatabaseException;
import com.exception.DatabaseException;
import com.factory.KDocFactory;

public class MainServlet extends HttpServlet {
	
	DAOInterface dao;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In Main Servlet");
		String query = req.getParameter("query");
		req.setAttribute("dao", dao);
		try {
			dao.clearTable();
		} catch (DatabaseException e) {
			req.setAttribute("error", e.getMessage());
			RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
			rd.forward(req, resp);
			return;
		}
		GoogleLinkRetriever retriever = new GoogleLinkRetriever();
		ArrayList<String> links;
		try {
			links = retriever.getLinks(query);
		} catch (Exception e) {
			req.setAttribute("error", e.getMessage());
			RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
			rd.forward(req, resp);
			return;
		}
		DocumentStorage dStorage = new DocumentStorage(dao);
		dStorage.getAndStore(0, links, 3);
		ArrayList<String> vectors = (ArrayList<String>) new VectorSpaceCreator(query).getVectorSpace();
		KDocFactory factory = new KDocFactory(dao, vectors);
		ArrayList<KDocument> docList = factory.getKDocs();
		KMeans km = new KMeans(vectors.size(), docList, vectors);
		ArrayList<Cluster> clusters = km.startKMeaning();
		System.out.println("Clustering Finished!");
		Ranker.rankClusters(clusters);
		Ranker.removeEmpty(clusters);
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
	public void init() {
		ConnectionManager cm = (ConnectionManager)getServletContext().getAttribute("connection");
		Connection con = null;
		try {
			con = cm.getConnection();
		} catch (CannotInitializeDatabaseException e) {
			e.printStackTrace();
		}
		System.out.println(con);
		dao = new DAOImpl(con);
	}
}
