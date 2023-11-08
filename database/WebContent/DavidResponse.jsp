<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Respond to Request Made by Client</title>
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
	<h1>Respond to Client Request to Cut Tree</h1>
		<p> ${errorOne } </p>
		<p> ${errorTwo } </p>
		<form action="dresponse" method="post">
		<p>For Customer Request ID: ${RequestID}</p>
			<table border="1" cellpadding="5">
				<tr>
					<th>Price Suggested: </th>
					<td align="center" colspan="3">
						<input type="text" name="PriceSuggested" size="45"  value=" " onfocus="this.value=''">
					</td>
				</tr>
				
				<tr>
					<th>Time Window Suggested: </th>
					<td align="center" colspan="3">
						<input type="text" name="TimeWindowSuggested" size="45" value=" " onfocus="this.value=''">
					</td>
				</tr>
				
				<tr>
					<th>Note: </th>
					<td align="center" colspan="3"> 
						<input type="text" name="Note" size="45" value=" " onfocus="this.value=''">
					</td>
				</tr>
				
				
				
				<tr>
					<td align="center" colspan="5">
					<input type="hidden" name="RequestID" value="${RequestID}">
						<input type="submit" value="Response"/>
					</td>
				</tr>
			</table></br>
			<a href="ListQuote.jsp" target="_self">All Client Quotes</a>
		</form>
	</div>
</body>
</html>