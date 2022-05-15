function onItemSaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
 }
 
 
$(document).ready(function() 
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validatePaymentForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------

 $("#formItem").submit(); 
}); 
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
 $("#hidIDSave").val($(this).closest("tr").find('#hidPaymentIDUpdate').val()); 
 $("#billno").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#name").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#amount").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#contact").val($(this).closest("tr").find('td:eq(3)').text()); 
}); 
// CLIENT-MODEL================================================================
function validatePaymentForm() 
{ 
// CODE
if ($("#billno").val().trim() == "") 
 { 
 return "Insert Bill Number."; 
 } 
// NAME
if ($("#name").val().trim() == "") 
 { 
 return "Insert Name."; 
 } 
// PRICE-------------------------------
if ($("#amount").val().trim() == "") 
 { 
 return "Insert Amount."; 
 } 
// is numerical value
var tmpPrice = $("#amount").val().trim(); 
if (!$.isNumeric(tmpPrice)) 
 { 
 return "Insert a numerical value for Amount."; 
 } 
// convert to decimal price
 $("#amount").val(parseFloat(tmpPrice).toFixed(2)); 
// DESCRIPTION------------------------
if ($("#contact").val().trim() == "") 
 { 
 return "Insert Contact."; 
 } 
return true; 
}

