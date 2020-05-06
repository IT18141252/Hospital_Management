<%@ page import="model.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Management</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css"> 
	<script src="Components/jquery-3.4.1.min.js"></script> 
	<script src="Components/payments.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>Hospital Registration</h1>
				<form id="form" name="form" method="post" action="Hospital.jsp">  
					Hospital ID:  
					<input id="Id" name="Id" type="text" class="form-control form-control-sm">  
					<br> Hospital Name:  
					<input id="HName" name="HName" type="text" class="form-control form-control-sm">  
					<br> Address:  
					<input id="address" name="address" type="text" class="form-control form-control-sm">  
					<br> Email:  
					<input id="email" name="email" type="text" class="form-control form-control-sm">  
					<br>Telephone:
					<input id="Tphone" name="Tphone" type="text" class="form-control form-control-sm">  
					<br>  
					<input id="btnSubmit" name="btnSubmit" type="button" value="Submit" class="btn btn-primary">  
					<input type="hidden" id="hidPayIDSave" name="hidPayIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success">
					<%
						out.print(session.getAttribute("statusMsg"));
					%>
				</div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="hospitalGrid">
					<%
						Hospital hospitalObj = new Hospital();
						out.print(hospitalObj.readHospital());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>