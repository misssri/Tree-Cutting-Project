<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Easy Client</title>
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
     
    <sql:query var="listeasyclient"   dataSource="${myDS}">
      SELECT qr.ClientID,c.FirstName, c.LastName
FROM QuoteRequest qr
JOIN Client c ON qr.ClientID = c.ClientID
WHERE qr.LatestNegotiationID = 1 and qr.Status = 'accepted';
 </sql:query>
     <div class="topnav">
<a class="active" href="DavidDashboard.jsp">Home</a>
  <a href="ListQuote.jsp">Show Initial Requests</a>
  <a href="ListNegotiations.jsp">Respond to Negotiations</a>
  <a href="dotherquotes.jsp">All Quotes</a>
   <a href="dbilllist.jsp">View Pending Bills</a>
  <a href="dbillnegotiate.jsp">View Bill Negotiations</a>
 <a href="login.jsp" class="split">Logout</a>
			</div>
			<div class="bg-image"></div>
<div class="bg-text">
    <div align="center">
        <table border="1" cellpadding="5">
           <h2>Clients Who easily accepts prices</h2>
            <tr>
            <th>S.No</th>
                <th>Client Name</th>
                
            </tr>
            <c:forEach var="user" items="${listeasyclient.rows}">
                <tr>
                <td><%= serialNumber %></td>
                    <td><c:out value="${user.FirstName}" /> <c:out value="${user.LastName}" /></td>
                    
                    
                    
                    
                    
                
                </tr>
                <% serialNumber++; %>
            </c:forEach>
        </table>
    </div></div>
</body>
</html>
