<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/payments.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6"> 
<h1>Payment Management</h1>
<form id="formItem" name="formItem">
 Bill Number: 
 <input id="billno" name="billno" type="text" 
 class="form-control form-control-sm">
 <br> Customer Name: 
 <input id="name" name="name" type="text" 
 class="form-control form-control-sm">
 <br> Bill Amount: 
 <input id="amount" name="amount" type="text" 
 class="form-control form-control-sm">
 <br> Customer Contact: 
 <input id="contact" name="contact" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidIDSave" name="hidIDSave" value="">
 
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">

</div>
</div> </div> </div> 

</body>
</html>