<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/css/app.css" />" rel="stylesheet">
<title>Add Product Confirmation Page</title>
</head>
<body>
	<div class="generic-container">
		<div class="alert alert-success lead">${success}</div>
		<span class="well floatRight"> Go to <a href="<c:url value='/products' />">Product List</a>
		</span>
	</div>
</body>

</html>