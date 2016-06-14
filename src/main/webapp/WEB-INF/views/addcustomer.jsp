<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>New Customer Form</title>
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
    <div class="well lead">New Customer Form</div>
    <form:form method="POST" modelAttribute="customer" class="form-horizontal">
        <form:input type="hidden" path="customerID" id="id"/>
         
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="name">Name</label>
                <div class="col-md-7">
                    <form:input type="text" path="customerName" id="name" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="customerName" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
 
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="contactInformation">Contact</label>
                <div class="col-md-7">
                    <form:input type="text" path="contactInformation" id="contactInformation" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="contactInformation" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
 
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="address">Address</label>
                <div class="col-md-7">
                     <form:input type="text" path="address" id="address" class="form-control input-sm" />
                     <div class="has-error">
                         <form:errors path="address" class="help-inline"/>
                     </div>  
                </div>
            </div>
        </div>
 
        <div class="row">
            <div class="form-actions floatRight">
                        <input type="submit" value="Add" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/customers' />">Cancel</a>
            </div>
        </div>
    </form:form>
    </div>
</body>
</html>