<%@page import="com.dao.DAOInterface"%>
<%@page import="com.bean.KDocument"%>
<%@page import="com.app.Cluster"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Results for "<%= request.getParameter("query") %>"</title>
</head>
<body>
<h1>Results</h1>
<% 
	DAOInterface dao = (DAOInterface) request.getAttribute("dao");
	ArrayList<Cluster> clusters = (ArrayList<Cluster>) request.getAttribute("clusters");
	for(Cluster c : clusters) {
%>
		<h2><%= "Cluster " + c.getId() + ":" %></h2>
<%
		ArrayList<KDocument> documents = c.getDocuments();
%>
		<ul>
<%
		for(KDocument doc : documents) {
			String url = dao.getURL(doc.getId());
%>
				<li><a href="<%= url %>"><%= url %></a>
<%
		}
%>
		</ul>
<%
	}
%>
</body>
</html>