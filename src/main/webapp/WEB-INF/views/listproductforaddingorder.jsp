<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Order</title>
<link href="<c:url value="/resources/css/app.css" />" rel="stylesheet">
</head>

<body>
	<div class="generic-container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">Choose items to be added for the new order of <c:out value="${customerName}" />.
				</span>
			</div>
			<input type="hidden" value="${customerId }">
			<table class="results" id="itemTable">
				<thead>
					<tr>
						<th>Product ID</th>
						<th>Name</th>
						<th>Unit Price</th>
						<th width="100">Add</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listItems}" var="product">
						<tr>
							<td>${product.productId}</td>
							<td>${product.name}</td>
							<td>${product.unitPrice}</td>
							<td><a href="<c:url value='/orders/add/${customerId}/${product.productId}' />">Add To Cart</a></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row">
			<div class="form-actions floatRight">
				<a href="<c:url value='/orderItemList/${customerId}' />">Check out</a> or <a href="<c:url value='/customers' />">Cancel</a>
			</div>
		</div>
	</div>
</body>
</html>