<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.List"%>

<title>Spring MVC</title>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu Page</title>
<style type="text/css">
		body {
			font-family: helvetica, arial, verdana;
			font-size: 16pt;
		}
		
		.generic-container {
		  position:fixed;
		  width:40%;
		  margin-left: 20px;
		  margin-top: 20px;
		  margin-bottom: 20px;
		  padding: 20px;
		  background-color: #EAE7E7;
		  border: 1px solid #ddd;
		  border-radius: 4px;
		  box-shadow: 0 0 30px black;
		}
		
		.custom-width {
		    width: 80px !important;
		}
	</style>
</head>
<body>
	<div class="generic-container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">Management Menu</span>
			</div>
			<a href="customers">Manage Customers/Orders</a><br/>
			<a href="products">Manage Products</a>
		</div>
	</div>

</body>
</html>