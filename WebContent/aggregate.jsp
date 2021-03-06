<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<!-- ****** faviconit.com favicons ****** -->
	<link rel="shortcut icon" href="assets/icon/orange-juice.ico">
	<link rel="icon" sizes="16x16 32x32 64x64" href="assets/icon/orange-juice.ico">
	<link rel="icon" type="image/png" sizes="196x196" href="assets/icon/orange-juice-192.png">
	<link rel="icon" type="image/png" sizes="160x160" href="assets/icon/orange-juice-160.png">
	<link rel="icon" type="image/png" sizes="96x96" href="assets/icon/orange-juice-96.png">
	<link rel="icon" type="image/png" sizes="64x64" href="assets/icon/orange-juice-64.png">
	<link rel="icon" type="image/png" sizes="32x32" href="assets/icon/orange-juice-32.png">
	<link rel="icon" type="image/png" sizes="16x16" href="assets/icon/orange-juice-16.png">
	<link rel="apple-touch-icon" href="assets/icon/orange-juice-57.png">
	<link rel="apple-touch-icon" sizes="114x114" href="assets/icon/orange-juice-114.png">
	<link rel="apple-touch-icon" sizes="72x72" href="assets/icon/orange-juice-72.png">
	<link rel="apple-touch-icon" sizes="144x144" href="assets/icon/orange-juice-144.png">
	<link rel="apple-touch-icon" sizes="60x60" href="assets/icon/orange-juice-60.png">
	<link rel="apple-touch-icon" sizes="120x120" href="assets/icon/orange-juice-120.png">
	<link rel="apple-touch-icon" sizes="76x76" href="assets/icon/orange-juice-76.png">
	<link rel="apple-touch-icon" sizes="152x152" href="assets/icon/orange-juice-152.png">
	<link rel="apple-touch-icon" sizes="180x180" href="assets/icon/orange-juice-180.png">
	<meta name="msapplication-TileColor" content="#FFFFFF">
	<meta name="msapplication-TileImage" content="assets/icon/orange-juice-144.png">
	<meta name="msapplication-config" content="assets/icon/browserconfig.xml">
	<!-- ****** faviconit.com favicons ****** -->
	<title>Aggregate Information</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link href="http://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">

	<link href="http://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="assets/bootstrap-3.3.6-dist/css/bootstrap.min.css">

	<!-- Optional theme -->
	<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous"> -->

	<!-- jQuery -->
	<script src="assets/jquery-1.12.4.min.js"></script>

	<!-- Latest compiled and minified JavaScript -->
	<script src="assets/bootstrap-3.3.6-dist/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

	<link rel="stylesheet" href="assets/font-awesome-4.6.3/css/font-awesome.min.css">


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

		@media screen and (max-width: 768px) {
			.col-sm-4 {
				text-align: center;
				margin: 25px 0;
			}
		}

		.navbar {
			font-family: Montserrat, sans-serif;
			margin-bottom: 0;
			background-color: #f4511e;
			z-index: 10;
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

	</style>

</head>
<body id="aggregate">
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
					<li><a href="#aggregate">AGGREGATE</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="jumbotron text-center">
		<h1><i class="fa fa-file-text-o"></i> Aggregate Information</h1>
		<p>This page shows you the combined text of a particular cluster!</p>
	</div>

	<div id="aggregate" class="container-fluid bg-grey">
		<div class="row">
			<div class="col-sm-12">
				<h2>Contents</h2>
				<h4 class="text-justify"><%= request.getAttribute("text") %></h4>	
			</div>
		</div>
	</div>

	<footer class="container-fluid text-center">
		<a href="#aggregate" title="To Top">
			<span class="glyphicon glyphicon-chevron-up"></span>
		</a>
	</footer>

	<script>
		$(document).ready(function(){
		  // Add smooth scrolling to all links in navbar + footer link
		  $(".navbar-collapse a, footer a[href='#aggregate']").on('click', function(event) {

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