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
            <c:forEach var="client" items="${listClient}">
                <tr style="text-align:center">
                    <td><c:out value="${clients.Email}" /></td>
                    <td><c:out value="${clients.FirstName}" /></td>
                    <td><c:out value="${clients.LastName}" /></td>
                    <td><c:out value="${clients.Password}" /></td>
                    <td><c:out value= "${clients.address_street_num} ${clients.address_street} ${clients.address_city} ${clients.address_state} ${clients.address_zip_code}" /></td>
                    <td><c:out value="${clients.Role}" /></td>
                    <td><c:out value="${clients.PhoneNumber}"/></td>
                    <td><c:out value="${clients.CreditCardInfo}" /></td>
                    </tr>
            </c:forEach>
        </table>
	</div>
	</div>

</body>
</html>