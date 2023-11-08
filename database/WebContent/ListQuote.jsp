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
</head>
<body>
    <sql:setDataSource
        var="myDS"
        driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/treecutting"
        user="root" password="Vishnupriya2"
    />
     
    <sql:query var="listQuotes"   dataSource="${myDS}">
        SELECT * FROM QuoteRequest q,Tree t where q.RequestID = t.RequestID and NegotiatedBy = "Client" ;
    </sql:query>
     
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of All Quotes from Clients</h2></caption>
            <tr>
                <th>RequestID</th>
                <th>ClientID</th>
                <th>Note</th>
                <th>Status</th>
                <th>LatestNegotiationID</th>
                <th>TreeID</th>
                <th>Picture1</th>
                <th>Picture2</th>
                <th>Picture3</th>
                <th>Size</th>
                <th>Height</th>
                <th>Location</th>
                <th>DistanceToHouse</th>
                <th>Respond</th>
            </tr>
            <c:forEach var="user" items="${listQuotes.rows}">
                <tr>
                    <td><c:out value="${user.RequestID}" /></td>
                    <td><c:out value="${user.ClientID}" /></td>
                    <td><c:out value="${user.Note}" /></td>
                    <td><c:out value="${user.Status}" /></td>
                    <td><c:out value="${user.LatestNegotiationID}" /></td>
                    <td><c:out value="${user.TreeID}" /></td>
                    <td><c:out value="${user.Picture1}" /></td>
                    <td><c:out value="${user.Picture2}" /></td>
                    <td><c:out value="${user.Picture3}" /></td>
                    <td><c:out value="${user.Size}" /></td>
                    <td><c:out value="${user.Height}" /></td>
                    <td><c:out value="${user.Location}" /></td>
                    <td><c:out value="${user.DistanceToHouse}" /></td>
                    <td>
                   <form action="Accept"><input type="submit" value="Accept"/></form></br>
                    	<form action="Reject"><input type="submit" value="Reject"/></form></br>
                    	<form action="Negotiate" method="post">
                    		<input type="hidden" name="RequestID" value="${user.RequestID}">
                    		<input type="submit" value="Negotiate"/>
                    	</form></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
