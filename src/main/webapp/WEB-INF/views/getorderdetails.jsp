<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get Order Details By Customer</title>
<link href="<c:url value="/resources/css/app.css" />" rel="stylesheet">
</head>
<body>
	<div class="generic-container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">Order List of Customers <c:out value="${customerName}"></c:out></span> <input type="hidden" value="${customerId}">
			</div>
			<table class="results">
				<thead>
					<tr>
						<th>Order ID</th>
						<th>Order Date</th>
						<th>Order Amount</th>
						<th width="100"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listOrders}" var="order">
						<tr>
							<td>${order.orderID}</td>
							<td>${order.orderDate}</td>
							<td>${order.amount}</td>
							<td><a href="<c:url value='/get-order-items/${customerId}/${order.orderID}' />" class="btn btn-success custom-width">Order
									Items</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<span class="well floatRight"> Go to <a href="<c:url value='/customers' />">Customers List</a>
		</span>
	</div>

</body>
</html>