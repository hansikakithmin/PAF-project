package com.paymentclient;
import java.sql.*; 

public class payment {
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3325/PAF", "root", ""); 
	 } 
	 catch (Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 return con; 
	 } 
	
	
	
	public String readItems() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting  to the database for reading."; 
	 } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Bill Number</th><th>Customer Name</th><th>Bill Amount</th>"+"<th>Customer Contact</th><th>Update</th><th>Remove</th></tr>"; 
	 String query = "select * from payments"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String Id = Integer.toString(rs.getInt("Id")); 
	 String billno = rs.getString("billno"); 
	 String name = rs.getString("name"); 
	 String amount = Double.toString(rs.getDouble("amount")); 
	 String contact = rs.getString("contact"); 
	 // Add into the html table
	output += "<tr><td><input id='hidPaymentIDUpdate' name='hidPaymentIDUpdate' type='hidden' value='" +Id+ "'>" + billno + "</td>"; 
	 output += "<td>" + name + "</td>"; 
	 output += "<td>" + amount + "</td>"; 
	 output += "<td>" + contact + "</td>"; 
	 // buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger'  data-itemid='"+ Id + "'>" + "</td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
	public String insertItem(String billno, String name, String amount, String contact){ 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting  to the database for inserting."; 
	 } 
	 // create a prepared statement
	 String query = " insert into payments (`Id`,`billno`,`name`,`amount`,`contact`)"+ " values (?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, billno); 
	 preparedStmt.setString(3, name); 
	 preparedStmt.setDouble(4, Double.parseDouble(amount)); 
	 preparedStmt.setString(5, contact); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newPayments = readItems(); 
	 output = "{\"status\":\"success\", \"data\": \"" + newPayments + "\"}"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "{\"status\":\"error\", \"data\":  \"Error while inserting the item.\"}"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
	public String updateItem(String Id, String billno, String name,String amount, String contact){ 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting to the database for updating."; 
	 } 
	 // create a prepared statement
	 String query = "UPDATE payments SET billno=?,name=?,amount=?,contact=? WHERE Id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setString(1, billno); 
	 preparedStmt.setString(2, name); 
	 preparedStmt.setDouble(3, Double.parseDouble(amount)); 
	 preparedStmt.setString(4, contact); 
	 preparedStmt.setInt(5, Integer.parseInt(Id)); 
	// execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newPayments = readItems(); 
	 output = "{\"status\":\"success\", \"data\": \"" + newPayments + "\"}"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "{\"status\":\"error\", \"data\":  \"Error while updating the item.\"}"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
	public String deleteItem(String Id) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting  to the database for deleting."; 
	 } 
	 // create a prepared statement
	 String query = "delete from payments where Id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(Id)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newPayments = readItems(); 
	 output = "{\"status\":\"success\", \"data\": \"" + newPayments + "\"}"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	}






