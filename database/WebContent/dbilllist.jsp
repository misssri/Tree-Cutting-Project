<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Quotes</title>
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
  height: 100%; 
  
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
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 2;
  width: 90%;
  padding: 20px;
  text-align: center;
}
body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}


.topnav {
  overflow: hidden;
  background-color: #333;
}

.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.active {
  background-color: #04AA6D;
  color: white;
}
.topnav a.split {
  float: right;
  background-color: #04AA6D;
  color: white;
}
.red-button {
  background-color: #f44336; /* Red */
  color: white;
  padding: 10px;
  width: 75px; /* Set the width */
  height: 40px;
}

.blue-button {
  background-color: #008CBA; /* Blue */
  color: white;
  padding: 10px;
   width: 75px; /* Set the width */
  height: 40px;
}
</style>
</head>
<body>
<%
    
    // Counter for serial number
    int serialNumber = 1;
%>
    <sql:setDataSource
        var="myDS"
        driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/treecutting"
        user="root" password="Vishnupriya2"
    />
     
    <sql:query var="listBills"   dataSource="${myDS}">
       SELECT b.*,c.FirstName,c.LastName FROM Bill b
JOIN WorkOrder w on b.OrderID=w.OrderID
JOIN QuoteRequest q on w.RequestID = q.RequestID
JOIN Client c on q.ClientID = c.ClientID
WHERE b.Status="pending" or b.Status="in progress";
 </sql:query>
     <div class="topnav">
<a class="active" href="DavidDashboard.jsp">Home</a>
  <a href="ListQuote.jsp">Show Initial Requests</a>
  <a href="ListNegotiations.jsp">Respond to Negotiations</a>
  <a href="dotherquotes.jsp">All Quotes</a>
  <a href="dbilllist.jsp">View Pending Bills</a>
  <a href="dbillneglist.jsp">View Bills Under Negotiation</a>
   <a href="login.jsp" class="split">Logout</a>
			</div>
			<div class="bg-image"></div>
<div class="bg-text">
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of All Pending Bills</h2></caption>
            <tr>
            <th>S.No</th>
                <th>Client Name</th>
                <th>Amount</th>
                <th>Bill Date</th>
                <th>Due Date</th>
                
                <th>Respond</th>
            </tr>
            <c:forEach var="user" items="${listBills.rows}">
                <tr>
                <td><%= serialNumber %></td>
                    <td><c:out value="${user.FirstName}" /> <c:out value="${user.LastName}" /></td>
                    
                    <td><c:out value="${user.Amount}" /></td>
                    <td><c:out value="${user.BillDate}" /></td>
                    <td><c:out value="${user.DueDate}" /></td>
                   
                    
                    
                    
                    <td>
                           	<form action="bdNegotiate" method="post">
                    	
                    		<input type="hidden" name="RequestID" value="${user.BillID}">
                    		<input type="submit" class="blue-button" value="Negotiate"/>
                    	</form></td>
                </tr>
                <% serialNumber++; %>
            </c:forEach>
        </table>
    </div></div>
</body>
</html>
