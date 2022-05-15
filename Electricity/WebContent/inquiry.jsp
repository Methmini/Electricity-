<%@page import="model.Inquiry" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inquiry Management</title>
<link rel="stylesheet" href="Views/bootstrap.css">

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Inquiry Management</h1>
				<form id="formItem" name="formItem">

					Name: <input id="name" name="name" type="text" 
						class="form-control form-control-sm"> <br>

					Address: <input id="address" name="address" type="text" 
						class="form-control form-control-sm"> <br> 

					Phone Number:<input id="phoneNb" name="phoneNb" type="text"
						class="form-control form-control-sm"> <br> 

					Date: <input id="date" name="date" type="text"
						class="form-control form-control-sm"> <br> 

					Comment: <input id="comment" name="comment" type="text" 
						class="form-control form-control-sm"> <br>

						<input id="btnSave"
						name="btnSave" type="button" value="Save" class="btn btn-primary"> <input type="hidden" id="hidInquiryIDSave" name="hidInquiryIDSave"
						value="">
				</form>
				<br>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divInquiryGrid">
				<%
				Inquiry inquiryObj = new Inquiry();
 					out.print(inquiryObj.readInquiries());
 					%>
				
	
				</div>
			</div>
		</div>
	</div>
</body>
<script src="Components/jquery.min.js" type="text/javascript"></script>
<script src="Components/inquiry.js" type="text/javascript"></script>
</html>