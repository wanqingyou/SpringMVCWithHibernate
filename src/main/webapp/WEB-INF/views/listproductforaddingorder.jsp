<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add Order</title>
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
    
        <div class="panel panel-default">
              <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">Choose items to be added for the new order of <c:out value="${customerName}"/>.</span></div>
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