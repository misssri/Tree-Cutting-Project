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
<form action = "listc.jsp">
		<input type = "submit" value = "List"/>
	</form><br><br>
	<form action = "RootDashboard.jsp">
		<input type = "submit" value = "Dashboard"/>
	</form>
	</div>



</body>
</html>