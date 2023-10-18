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

<h1>List all users</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>Email</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Address</th>
                <th>Password</th>
                <th>Role</th>
                <th>PhoneNumber</th>
                <th>CreditCardInfo</th>
            </tr>
            <c:forEach var="users" items="${listAllUsers}">
                <tr style="text-align:center">
                    <td><c:out value="${users.Email}" /></td>
                    <td><c:out value="${users.FirstName}" /></td>
                    <td><c:out value="${users.LastName}" /></td>
                    <td><c:out value= "${users.address_street_num} ${users.address_street} ${users.address_city} ${users.address_state} ${users.address_zip_code}" /></td>
                    <td><c:out value="${users.Password}" /></td>
                    <td><c:out value="${users.Role}" /></td>
                    <td><c:out value="${users.PhoneNumber}"/></td>
                    <td><c:out value="${users.CreditCardInfo}" /></td>
            </c:forEach>
        </table>
	</div>
	</div>

</body>
</html>