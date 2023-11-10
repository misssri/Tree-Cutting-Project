<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page</title>
</head>
<body>

<div align = "center">
	
	<form action = "initialize">
		<input type = "submit" value = "Initialize the Database"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

<h1>List all clients</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of clients</h2></caption>
            <tr>
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Password</th>
                <th>Address</th>
                <th>Password</th>
                <th>Role</th>
                <th>Phone number</th>
                <th>Credit card info</th>
            </tr>
            <c:forEach var="client" items="${listClients}">
                <tr style="text-align:center">
                    <td><c:out value="${client.Email}" /></td>
                    <td><c:out value="${client.FirstName}" /></td>
                    <td><c:out value="${client.LastName}" /></td>
                    <td><c:out value="${client.Password}" /></td>
                    <td><c:out value= "${client.address_street_num} ${client.address_street} ${client.address_city} ${client.address_state} ${client.address_zip_code}" /></td>
                    <td><c:out value="${client.Role}" /></td>
                    <td><c:out value="${client.PhoneNumber}"/></td>
                    <td><c:out value="${client.CreditCardInfo}" /></td>
                    </tr>
            </c:forEach>
        </table>
	</div>
	
</div>

</body>
</html>