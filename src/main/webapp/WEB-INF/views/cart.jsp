<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>
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
	<form:form method="POST" class="form-horizontal">
		<div class="panel panel-default">
		
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">Order Item List in the Shopping Cart of Customers <c:out value="${customerName}"></c:out></span>
				<input type="hidden" value="${customerId}">
			</div>
			<c:set var="totalAmount" value="${0}"/>
			<c:set var="totalQuantity" value="${0}"/>
			<table class="results">
				<thead>
					<tr>
						<th>Item ID</th>
						<th>Item Name</th>
						<th>Item Amount</th>
						<th>Item Quantity</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${itemList}" var="item">
						<tr>
							<td>${item.productId}</td>
							<td>${item.name}</td>
							<td>${item.unitPrice}</td><c:set var="totalAmount" value="${totalAmount + item.unitPrice*item.quantity}" />
							<td>${item.quantity}</td><c:set var="totalQuantity" value="${totalQuantity + item.quantity}" />
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br/>
			<div class="well">	
	            Quantities: <c:out value="${totalQuantity}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Total: <c:out value="${totalAmount}"></c:out>
	        </div>
	        
		</div>
		<span class="well floatRight">
	        <input type="submit" value="Submit" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/customers' />">Cancel</a>
	    </span>
	  </form:form>
	</div>

</body>
</html>