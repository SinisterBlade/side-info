package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.Cluster;
import com.bean.KDocument;
import com.dao.ConnectionManager;
import com.dao.DAOImpl;
import com.dao.DAOInterface;
import com.exception.CannotInitializeDatabaseException;

public class AggregateServlet extends HttpServlet {
	
	DAOInterface dao;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("error", "Please use page navigation instead of manually entering the URL!");
		RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int clusterID = Integer.parseInt(req.getParameter("clusterid"));
		ArrayList<Cluster> clusters = (ArrayList<Cluster>) req.getSession().getAttribute("clusters");
		StringBuilder text = new StringBuilder();
		Cluster selectedCluster = null;
		for (Cluster c : clusters) {
			if(c.getId() == clusterID){
				selectedCluster = c;
				break;
			}
		}
		for(KDocument doc : selectedCluster.getDocuments()) {
			text.append("<p><p>");
			text.append(dao.getContent(doc.getId()));
			text.append("</p></p>");
		}
		req.setAttribute("text", text.toString());
		RequestDispatcher rd = req.getRequestDispatcher("aggregate.jsp");
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
