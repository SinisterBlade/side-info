<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
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
	<title>Error!</title>
	<meta charset="utf-8">
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

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">


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
<body id="error">
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="homepage.html">Orange Juice!</a>
			</div>
		</div>
	</nav>

	<div class="jumbotron text-center">
		<h1><i class="fa fa-frown-o"></i> OOPS!</h1>
		<p>Looks like something went wrong!</p>
	</div>

	<div id="error" class="container-fluid bg-grey">
		<div class="row">
			<div class="col-sm-12">
				<h2>What Happened?</h2>
				<h4><%= request.getAttribute("error") %></h4>	
			</div>
		</div>
	</div>

	<footer class="container-fluid text-center">
		<a href="#error" title="To Top">
			<span class="glyphicon glyphicon-chevron-up"></span>
		</a>
	</footer>

	<script>
		$(document).ready(function(){
		  // Add smooth scrolling to all links in navbar + footer link
		  $("footer a[href='#error']").on('click', function(event) {

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