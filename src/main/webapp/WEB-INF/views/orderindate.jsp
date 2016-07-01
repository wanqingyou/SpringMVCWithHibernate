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
				<span class="lead">Search By Date </span>
			</div>

			<form name="custForm" action="custSearch" method="post">
				<table>
					<tr>
						<td style="text-align: right;"><strong>Order Date:</strong></td>
						<td><input type="date" name="orderDate" autocomplete="off" value="<c:out value="${orderDate}"/>"></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input type="submit" value="Search"></td>
					</tr>
				</table>
			</form>

			<c:choose>
				<c:when test="${noData eq true}">
					<h2>Sorry, no records found.</h2>
				</c:when>
				<c:when test="${not empty searchResults}">
					<table class="results">
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Contact</th>
							<th>Address</th>
							<th>Order Date</th>
						</tr>
						<c:forEach var="cust" items="${searchResults}">
							<tr>
								<td><c:out value="${cust.customerID}" /></td>
								<td><c:out value="${cust.customerName}" /></td>
								<td><c:out value="${cust.contactInformation}" /></td>
								<td><c:out value="${cust.address}" /></td>
								<td><c:out value="${orderDate}" />
							</tr>
						</c:forEach>
					</table>

				</c:when>
			</c:choose>

		</div>
		<span class="well floatRight"> Go to <a href="<c:url value='/customers' />">Customers List</a>
		</span>
	</div>

</body>
</html>