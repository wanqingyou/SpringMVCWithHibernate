<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add Order Confirmation Page</title>
    <style type="text/css">
		body {
			font-family: helvetica, arial, verdana;
			font-size: 16pt;
		}
		
		input[type="submit"] {
			background-color: black;
			color: white;
			border: none;
		}
		
		table th {
			background-color: black;
			color: white;
		}
		
		table.results tr:nth-child(even) {
			background: #EEE
		}
		
		table.results tr:nth-child(odd) {
			background: #FFF
		}
		
		.form-control{
			font-size:12px!important;
		}
		
		.floatRight{
			float:right;
			margin-right: 18px;
		}
		
		.has-error{
			color:red;
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
    <div class="alert alert-success lead">
        ${success}
    </div>
     
    <span class="well floatRight">
        Go to <a href="<c:url value='/customers' />">Customer List</a>
    </span>
</div>
</body>
 
</html>