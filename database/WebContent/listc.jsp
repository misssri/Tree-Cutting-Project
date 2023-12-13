<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page</title>
</head>
<body>
 <sql:setDataSource
        var="myDS"
        driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/treecutting"
        user="root" password="Vishnupriya2"
    />
     
    <sql:query var="listClient"   dataSource="${myDS}">
       SELECT * from Client;
 </sql:query>
<div align = "center">
	
	
<h1>List all users</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Address</th>
                <th>Password</th>
                <th>Phone Number</th>
                <th>Role</th>
                
            </tr>
            <c:forEach var="clients" items="${listClient.rows}">
                <tr style="text-align:center">
                    <td><c:out value="${clients.Email}" /></td>
                    <td><c:out value="${clients.FirstName}" /></td>
                    <td><c:out value="${clients.LastName}" /></td>
                    
                    <td><c:out value="${clients.address_street_num}" />,<c:out value="${clients.address_street}" />,<c:out value="${clients.address_city}" />,<c:out value="${clients.address_state}" /></td>
                    <td><c:out value="${clients.Password}" /></td>
                    <td><c:out value="${clients.PhoneNumber}" /></td>
                    <td><c:out value="${clients.Role}" /></td>
                    
            </c:forEach>
        </table>
	</div>
	</div>

</body>
</html>