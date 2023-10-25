<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<a href="login.jsp"target ="_self" > logout</a><br><br> 


    <div align="center">
    <h1>List all clients</h1>
        <table border="1" cellpadding="6">
            <caption><h2>List of clients</h2></caption>
            <tr>
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Address</th>
                <th>Password</th>
                <th>role</th>
                <th>Phone number</th>
                <th>Credit Card Number</th>
            </tr>
            <c:forEach var="client" items="${getClient}">
                <tr style="text-align:center">
                    <td>"${client.Email}" </td>
                    <td>"${client.FirstName}"</td>
                    <td>"${client.LastName}"</td>
                    <td>"${client.address_street_num} ${client.address_street} ${client.address_city} ${client.address_state} ${client.address_zip_code}"</td>
                    <td>"${client.Password}"</td>
                    <td>"${client.Role}"</td>
                    <td>"${client.PhoneNumber}"</td>
                    <td>"${client.CreditcardInfo}"</td>
                 </tr>
            </c:forEach>
          </table>
	</div>
<body>

</body>
</html>