<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>All User list</title>
</head>
<body>
<form action = "list">
		

   <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of People</h2></caption>
            <tr>
				<th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Address</th>
                <th>Password</th>
                <th>Role</th>
                <th>Phone Number</th>
                <th>Credit Card Number</th>

            </tr>
            <c:forEach var="user" items="${listClient}">
                <tr style="text-align:center">
                    <td><c:out value="${user.Email}" /></td>
                    <td><c:out value="${user.FirstName}" /></td>
                    <td><c:out value="${user.LastName}" /></td>
                    <td><c:out value= "${user.address_street_num} ${user.address_street} ${user.address_city} ${user.address_state} ${user.address_zip_code}" /></td>
                    <td><c:out value="${user.Password}" /></td>
                    <td><c:out value="${user.Role}" /></td>
                    <td><c:out value="${user.PhoneNumber}"/></td>
                    <td><c:out value="${user.CreditCardInfo}" /></td>
                </tr>
            </c:forEach>
        </table>
        	</form>
    </div>   
</body>
</html>