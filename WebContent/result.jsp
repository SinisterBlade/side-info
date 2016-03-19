<%@page import="com.dao.DAOInterface"%>
<%@page import="com.bean.KDocument"%>
<%@page import="com.app.Cluster"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" ">
	<!-- ****** faviconit.com favicons ****** -->
	<link rel="shortcut icon" href="assets/icons/orange-slice.ico">
	<link rel="icon" sizes="16x16 32x32 64x64" href="assets/icons/orange-slice.ico">
	<link rel="icon" type="image/png" sizes="196x196" href="assets/icons/orange-slice-192.png">
	<link rel="icon" type="image/png" sizes="160x160" href="assets/icons/orange-slice-160.png">
	<link rel="icon" type="image/png" sizes="96x96" href="assets/icons/orange-slice-96.png">
	<link rel="icon" type="image/png" sizes="64x64" href="assets/icons/orange-slice-64.png">
	<link rel="icon" type="image/png" sizes="32x32" href="assets/icons/orange-slice-32.png">
	<link rel="icon" type="image/png" sizes="16x16" href="assets/icons/orange-slice-16.png">
	<link rel="apple-touch-icon" href="assets/icons/orange-slice-57.png">
	<link rel="apple-touch-icon" sizes="114x114" href="assets/icons/orange-slice-114.png">
	<link rel="apple-touch-icon" sizes="72x72" href="assets/icons/orange-slice-72.png">
	<link rel="apple-touch-icon" sizes="144x144" href="assets/icons/orange-slice-144.png">
	<link rel="apple-touch-icon" sizes="60x60" href="assets/icons/orange-slice-60.png">
	<link rel="apple-touch-icon" sizes="120x120" href="assets/icons/orange-slice-120.png">
	<link rel="apple-touch-icon" sizes="76x76" href="assets/icons/orange-slice-76.png">
	<link rel="apple-touch-icon" sizes="152x152" href="assets/icons/orange-slice-152.png">
	<link rel="apple-touch-icon" sizes="180x180" href="assets/icons/orange-slice-180.png">
	<meta name="msapplication-TileColor" content="#FFFFFF">
	<meta name="msapplication-TileImage" content="assets/icons/orange-slice-144.png">
	<meta name="msapplication-config" content="assets/icons/browserconfig.xml">
	<!-- ****** faviconit.com favicons ****** -->
	<title>Results for "<%= request.getParameter("query") %>"</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link href="http://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">

	<link href="http://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

	<!-- Optional theme -->
	<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous"> -->

	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

	<style type="text/css">

		body {
			font: 400 15px Lato, sans-serif;
			line-height: 1.8;
			color: #818181;
		}

		h2 {
			font-size: 24px;
			text-transform: uppercase;
			color: #303030;
			font-weight: 600;
			margin-bottom: 30px;
		}

		h4 {
			font-size: 19px;
			line-height: 1.375em;
			color: #303030;
			font-weight: 400;
			margin-bottom: 30px;
		}

		.jumbotron {
			font-family: Montserrat, sans-serif;
			background-color: #f4511e; /* Orange */
			color: #ffffff;
			padding: 100px 25px;
		}

		.bg-grey {
			background-color: #f6f6f6;
		}

		.container-fluid {
			padding: 60px 50px;
		}

		.logo {
			color: #f4511e;
			font-size: 200px;
		}

		.icon {
			color: #f4511e;
			letter-spacing: 1em;
			font-size: 1.125em;
		}

		@media screen and (max-width: 768px) {
			.col-sm-4 {
				text-align: center;
				margin: 25px 0;
			}
		}

		.carousel-control.right, .carousel-control.left {
			background-image: none;
			color: #f4511e;
		}

		.carousel-indicators li {
			border-color: #f4511e;
		}

		.carousel-indicators li.active {
			background-color: #f4511e;
		}

		.item h4 {
			font-size: 19px;
			line-height: 1.375em;
			font-weight: 400;
			font-style: italic;
			margin: 70px 0;
		}

		.item span {
			font-style: normal;
		}

		.navbar {
			font-family: Montserrat, sans-serif;
			margin-bottom: 0;
			background-color: #f4511e;
			z-index: 9999;
			border: 0;
			font-size: 12px !important;
			line-height: 1.42857143 !important;
			letter-spacing: 4px;
			border-radius: 0;
		}

		.navbar li a, .navbar .navbar-brand {
			color: #fff !important;
		}

		.navbar-nav li a:hover, .navbar-nav li.active a {
			color: #f4511e !important;
			background-color: #fff !important;
		}

		.navbar-default .navbar-toggle {
			border-color: transparent;
			color: #fff !important;
		}

		.icon-bar {
			color: #fff !important;
			background-color: #fff !important;
		}

		footer .glyphicon {
			font-size: 20px;
			margin-bottom: 20px;
			color: #f4511e;
		}

		.panel {
			border: 1px solid #f4511e;
			border-radius: 0;
			transition: box-shadow 0.5s;
		}

		.panel:hover {
			box-shadow: 5px 0px 40px rgba(0, 0, 0, .2);
		}

		.panel-footer.btn:hover {
			border: 1px solid #f4511e;
			background-color: #fff !important;
			color: #f4511e; 
		}

		.panel-heading {
			color: #fff !important;
			background-color: #f4511e !important;
			padding: 25px;
			border-bottom: 1px solid transparent;
			border-top-left-radius: 0px;
			border-top-right-radius: 0px;
			border-bottom-left-radius: 0px;
			border-bottom-right-radius: 0px;
		}

		.panel-footer {
			background-color: #fff !important;
		}

		.panel-footer h3 {
			font-size: 32px;
		}

		.panel-footer h4 {
			color: #aaa;
			font-size: 14px;
		}

		.panel-footer .btn {
			margin: 15px 0;
			background-color: #f4511e;
			color: #fff;
		}

		.list-group-item {
			word-wrap: break-word;
		}

		.thumb:nth-child(3n+1) {
			clear: both;
		}

	</style>

</head>
<body id="resultPage" data-spy="scroll" data-target=".navbar" data-offset="60">

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>				
				</button>
				<a class="navbar-brand" href="homepage.html">Orange Juice!</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#clusters">CLUSTERS</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="jumbotron text-center">
		<h1>Results</h1>
		<p>For "<%= request.getParameter("query") %>"</p>
	</div>

	<div id="clusters" class="container-fluid">
		<div class="text-center">
			<h2>Clusters</h2>
			<p>Pages in the same cluster are supposed to be similar.</p>
		</div>
		<div class="row">
			<% 
			DAOInterface dao = (DAOInterface) request.getAttribute("dao");
			ArrayList<Cluster> clusters = (ArrayList<Cluster>) request.getAttribute("clusters");
			int counter = 0;
			for(Cluster c : clusters) {
			counter++;
			%>
			<div class="col-lg-4 thumb">
				<div class="panel panel-default text-center">
					<div class="panel-heading" title="Cluster ID: <%= c.getId() %>">
						<h1>Cluster <%= counter %></h1>
					</div>
					<div class="panel-body">
						<%
						ArrayList<KDocument> documents = c.getDocuments();
						%>
						<div class="list-group">
						<%
						for(KDocument doc : documents) {
							String url = dao.getURL(doc.getId());
						%>
							<a href="<%= url %>" class="list-group-item" target="_blank"><%= url %></a>
						<%
						}
						%>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>

	<footer class="container-fluid text-center">
		<a href="#resultPage" title="To Top">
			<span class="glyphicon glyphicon-chevron-up"></span>
		</a>
	</footer>

	<script>
		$(document).ready(function(){
		  // Add smooth scrolling to all links in navbar + footer link
		  $(".navbar-collapse a, footer a[href='#resultPage']").on('click', function(event) {

		    // Prevent default anchor click behavior
		    event.preventDefault();

		    // Store hash
		    var hash = this.hash;

		    // Using jQuery's animate() method to add smooth page scroll
		    // The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
		    $('html, body').animate({
		      scrollTop: $(hash).offset().top
		    }, 900, function(){
		   
		      // Add hash (#) to URL when done scrolling (default click behavior)
		      window.location.hash = hash;
		    });
		  });
		})
	</script>

</body>
</html>