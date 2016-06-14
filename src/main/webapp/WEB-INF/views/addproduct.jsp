<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Management Form</title>
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

.form-control {
	font-size: 12px !important;
}

.floatRight {
	float: right;
	margin-right: 18px;
}

.has-error {
	color: red;
}

.generic-container {
	position: fixed;
	width: 40%;
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
		<c:choose>
			<c:when test="${edit}">
				<div class="well lead">Update Product Form</div>
			</c:when>
			<c:otherwise>
				<div class="well lead">Add Product Form</div>
			</c:otherwise>
		</c:choose>
		<form:form method="POST" modelAttribute="product" class="form-horizontal">
			<form:input type="hidden" path="productId" id="id" />

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="name">Name</label>
					<div class="col-md-7">
						<form:input type="text" path="name" id="name" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="name" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="unitPrice">Unit Price</label>
					<div class="col-md-7">
						<form:input type="text" path="unitPrice" id="unitPrice" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="unitPrice" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update" class="btn btn-primary btn-sm" /> or <a href="<c:url value='/products' />">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Add" class="btn btn-primary btn-sm" /> or <a href="<c:url value='/products' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>