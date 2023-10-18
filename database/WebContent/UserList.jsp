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
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>