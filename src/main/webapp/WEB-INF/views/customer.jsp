<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customers List</title>
<link href="<c:url value="/resources/css/app.css" />" rel="stylesheet">
</head>

<body>
	<div class="generic-container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">List of Customers </span>
			</div>
			<table class="results">
				<thead>
					<tr>
						<th>Customer ID</th>
						<th>Name</th>
						<th>Contact</th>
						<th>Address</th>
						<th width="100"></th>
						<th width="100"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listCustomers}" var="customer">
						<tr>
							<td>${customer.customerID}</td>
							<td>${customer.customerName}</td>
							<td>${customer.contactInformation}</td>
							<td>${customer.address}</td>
							<td><a href="<c:url value='/get-order-details/${customer.customerID}' />" class="btn btn-success custom-width">Order Details</a></td>
							<td><a href="<c:url value='/get-item-to-add/${customer.customerID}' />" class="btn btn-success custom-width">Add Order</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="well">
			<a href="<c:url value='/customer/add' />">Add New Customer</a>&nbsp;&nbsp;&nbsp; <a href="<c:url value='/order/list-in-date' />">List
				Orders In Date</a>
		</div>
		<span class="well floatRight"> Go to <a href="<c:url value='/' />">Management Menu</a>
		</span>
	</div>
</body>
</html>