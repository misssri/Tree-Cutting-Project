<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>Registration</title>
    <style>
         body, html {
  height: 100%;
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

* {
  box-sizing: border-box;
}

.bg-image {
  /* The image used */
  background-image: url("https://t4.ftcdn.net/jpg/03/52/73/59/360_F_352735994_4FpchPEOdipty9TvI4WWjjI1xgJdB5m6.jpg");
  
  /* Add the blur effect */
  filter: blur(8px);
  -webkit-filter: blur(8px);
  
  /* Full height */
  height: 200%; 
  
  /* Center and scale the image nicely */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  background-attachment: fixed;

  
}

/* Position text in the middle of the page/image */
.bg-text {
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0, 0.4); /* Black w/opacity/see-through */
  color: white;
  font-weight: bold;
  border: 3px solid #f1f1f1;
  position: absolute;
  top: 90%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 2;
  width: 80%;
  padding: 20px;
  text-align: center;
}
body {

  font-family: Arial, Helvetica, sans-serif;
  background-color: white;
}

* {
  box-sizing: border-box;
  box-width: 70%;
}

/* Add padding to containers */
.container {
  width: 40%;
  padding: 3px;
  background-color: white;
}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 5px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
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

<div class="bg-image"></div>
<div class="bg-text">
<h2>Register to Avail Services</h2>
	<div align="center">
	
		<p> ${errorOne } </p>
		<p> ${errorTwo } </p>
		<form action="register">
			<table border="1" cellpadding="5">
				<tr>
					<th>Username: </th>
					<td align="center" colspan="3">
						<input type="text" name="Email" size="45"  value="example@gmail.com" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>First Name: </th>
					<td align="center" colspan="3">
						<input type="text" name="FirstName" size="45" value="FirstName" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Last Name: </th>
					<td align="center" colspan="3">
						<input type="text" name="LastName" size="45" value="LastName" onfocus="this.value=''">
					</td>
				</tr>
				
				<tr>
					<th>Password: </th>
					<td align="center" colspan="3"> 
						<input type="password" name="Password" size="45" value="password" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Password Confirmation: </th>
					<td align="center" colspan="3">
						<input type="password" name="confirmation" size="45" value="password" onfocus="this.value=''">
					</td>
				
				</tr>
				<tr>
				<tr>
					<th>Street Number: </th>
					<td align="center" colspan="3">
						<input type="text" name="address_street_num" size="45" value="Eg: 23567" onfocus="this.value=''">
					</td>
	
				</tr>
				<tr>
					<th>Street Name: </th>
					<td align="center" colspan="3">
						<input type="text" name="address_street_name" size="45" value="Eg: 123 Main st" onfocus="this.value=''">
					</td>
	
				</tr>
				<tr>
					<th>City: </th>
					<td align="center" colspan="3">
						<input type="text" name="address_city" size="45" value="Eg: Detroit" onfocus="this.value=''">
					</td>
	
				</tr>
				<tr>
					<th>State: </th>
					<td align="center" colspan="3">
						<input type="text" name="address_state" size="45" value="Eg:MI" onfocus="this.value=''">
					</td>
	
				</tr>
				<tr>
					<th> Zip code:</th>
					<td align="center" colspan="3">
						<input type="text" name="address_zipcode" size="45" value="Eg:12345" onfocus="this.value=''">
					</td>
	
				</tr>
				<tr>
					<th>Role: </th>
					<td align="center" colspan="3">
						<input type="text" name="Role" size="45" value="role" onfocus="this.value=''">
					</td>
	
				</tr>
				<tr>
					<th>Phone number: </th>
					<td align="center" colspan="3">
						<input type="text" name="phonenumber" size="45" value="Eg:12345678901" onfocus="this.value=''">
					</td>
	
				</tr>
				<tr>
					<th>Credit Card number: </th>
					<td align="center" colspan="3">
						<input type="text" name="CreditCardInfo" size="45" value="XXXX-XXXX-XXXX-XXXX" onfocus="this.value=''">
					</td>
	
				</tr>
					<td align="center" colspan="5">
						<input type="submit" value="Register"/>
					</td>
				</tr>
			</table></br>
			<a href="login.jsp" target="_self">Return to Login Page</a>
		</form>
	</div></div>
</body>