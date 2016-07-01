<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products List</title>
<link href="<c:url value="/resources/css/app.css" />" rel="stylesheet">
</head>

<body>
	<div class="generic-container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">List of Products</span>
			</div>
			<table class="results">
				<thead>
					<tr>
						<th>Product ID</th>
						<th>Name</th>
						<th>Unit Price</th>
						<th width="100"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listProducts}" var="product">
						<tr>
							<td>${product.productId}</td>
							<td>${product.name}</td>
							<td>${product.unitPrice}</td>
							<td><a href="<c:url value='/edit-product-details/${product.productId}' />" class="btn btn-success custom-width">Edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="well">
			<a href="<c:url value='/product/add' />">Add New Product</a>
		</div>

		<span class="well floatRight"> Go to <a href="<c:url value='/' />">Management Menu</a>
		</span>
	</div>
</body>
</html>