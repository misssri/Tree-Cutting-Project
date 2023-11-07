<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>Raise a Quote Request</title>
    <style>
body {

  font-family: Arial, Helvetica, sans-serif;
  background-color: white;
}

* {
  box-sizing: border-box;
}

/* Add padding to containers */
.container {
  width:50%;
  padding: 3px;
  background-color: white;
}

/* Full-width input fields */
input[type=text] {
  width: 100%;
  padding: 5px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

input[type=text]:focus {
  background-color: #ddd;
  outline: none;
}

/* Overwrite default styles of hr */
th,td {
  border: 1px solid #f1f1f1;
  margin-bottom: 10px;
}

/* Set a style for the submit button */
input[type="submit"] {
  background-color: blue;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

input[type="submit"] {
  opacity: 1;
  
}

/* Add a blue text color to links */
a {
  background-color: dodgerblue;
}


a {
  background-color: #f1f1f1;
  text-align: center;
}
    </style>
</head>
 

<body>
	<div align="center">
	<h1>Raise a Request for Tree Care</h1>
		<p> ${errorOne } </p>
		<p> ${errorTwo } </p>
		<form action="qrequest">
			<table border="1" cellpadding="5">
				<tr>
					<th>Email Address: </th>
					<td align="center" colspan="3">
						<input type="text" name="Email" size="45"  value="example@gmail.com" onfocus="this.value=''">
					</td>
				</tr>
				
				<tr>
					<th>Tree Size: </th>
					<td align="center" colspan="3">
						<input type="text" name="Size" size="45" value="Size" onfocus="this.value=''">
					</td>
				</tr>
				
				<tr>
					<th>Tree Height: </th>
					<td align="center" colspan="3"> 
						<input type="text" name="Height" size="45" value="Height" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Tree Location: </th>
					<td align="center" colspan="3">
						<input type="text" name="Location" size="45" value="Location" onfocus="this.value=''">
					</td>
				
				</tr>
				<tr>
				<tr>
					<th>Tree Distance to House: </th>
					<td align="center" colspan="3">
						<input type="text" name="DistanceToHouse" size="45" value="Distance" onfocus="this.value=''">
					</td>
	
				</tr>
				<tr>
					<th>Additional Comments: </th>
					<td align="center" colspan="3">
						<input type="text" name="Note" size="45" value="Note" onfocus="this.value=''">
					</td>
	
				</tr>
				
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Request"/>
					</td>
				</tr>
			</table>
			<a href="login.jsp" target="_self">Return to Login Page</a>
		</form>
	</div>
</body>