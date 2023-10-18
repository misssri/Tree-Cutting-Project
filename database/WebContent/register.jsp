<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>Registration</title></head>
<body>
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
			</table>
			<a href="login.jsp" target="_self">Return to Login Page</a>
		</form>
	</div>
</body>