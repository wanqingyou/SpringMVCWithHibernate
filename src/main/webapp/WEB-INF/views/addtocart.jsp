<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/css/app.css" />" rel="stylesheet">
<title>Add to Shopping Cart</title>
</head>

<body>
	<div class="generic-container">
		<div class="well lead">Add Item Form</div>
		<form:form method="POST" modelAttribute="productTo" class="form-horizontal">
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
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="contactInformation">Quantity</label>
					<div class="col-md-7">
						<form:input type="number" path="quantity" id="quantity" min="1" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="unitPrice" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-actions floatRight">
					<input type="submit" value="Add" class="btn btn-primary btn-sm" /> or <a href="<c:url value='/customers' />">Cancel</a>

				</div>
			</div>
		</form:form>
	</div>
</body>
</html>