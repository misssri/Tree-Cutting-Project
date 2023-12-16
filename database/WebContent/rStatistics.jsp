<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Statistics</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

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
.chart-container {
           background-color: rgba(255,255,255, 0); 
            
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 90%;
            max-width: 800px;
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            align-items: center;
            flex-wrap: wrap;
        }

        canvas {
            margin-bottom: 20px;
            max-width: 45%;
            width: 120%;
        }
         barChart {
            width: 120%; /* Increased width by 20% */
            height: 120%; /* Increased height by 20% */
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
     
    <sql:query var="listNegotiations"   dataSource="${myDS}">
      SELECT c.ClientID, c.FirstName, c.LastName,
       COUNT(t.TreeID) AS TotalTrees,
       SUM(b.Amount) AS TotalDueAmount,
       SUM(CASE WHEN b.Status = 'accepted' THEN b.Amount ELSE 0 END) AS TotalPaidAmount,
       MAX(b.BillDate) AS WorkDoneDate
FROM Client c
JOIN QuoteRequest q on c.ClientID = q.ClientID
LEFT JOIN WorkOrder wo ON q.RequestID = wo.RequestID
LEFT JOIN Bill b ON wo.OrderID = b.OrderID
LEFT JOIN Tree t ON q.RequestID = t.RequestID
WHERE b.Status = 'accepted' || b.Status = "pending"
GROUP BY c.ClientID, c.FirstName, c.LastName
ORDER BY TotalTrees DESC; </sql:query>
     <div class="topnav">
<a class="active" href="RootDashboard.jsp">Home</a>
 
   <a href="login.jsp" class="split">Logout</a>
			</div>
			<div class="bg-image"></div>
<div class="bg-text">
    <div align="center">
   
        <table border="1" cellpadding="5">
            <caption><h2>Completed Jobs By David, Hurray!</h2></caption>
            <tr>
            	<th>S.No</th>
            	<th>Client Name</th>
                <th>TotalTrees</th>
                <th>TotalDueAmount</th>
                <th>TotalPaidAmount</th>
                
            </tr>
            <c:forEach var="user" items="${listNegotiations.rows}">
                <tr>
                	<td><%= serialNumber %></td>
                	
                	<td><c:out value="${user.FirstName}" /> <c:out value="${user.LastName}" /></td>
                    <td><c:out value="${user.TotalTrees}" /></td>
                   
                    <td><c:out value="${user.TotalDueAmount}" /></td>
                    <td><c:out value="${user.TotalPaidAmount}" /></td>
                  
                    	
                    	
                </tr>
                <% serialNumber++; %>
            </c:forEach>
        </table></br></br>
        <div class="chart-container">
        <%-- Chart Rendering --%>
        <canvas id="barChart" height="150"></canvas>
        <canvas id="pieChart" height="150"></canvas>

        <script>
            // Assuming your SQL data is available in the listNegotiations.rows array

            // Extract required data for charts
            const clientNames = [];
            const totalDueAmounts = [];
            const totalPaidAmounts = [];
            const totalTrees = [];

            // Populate arrays with data for charts
            <c:forEach var="user" items="${listNegotiations.rows}">
                clientNames.push('<c:out value="${user.FirstName}"/> <c:out value="${user.LastName}"/>');
                totalDueAmounts.push(<c:out value="${user.TotalDueAmount}"/>);
                totalPaidAmounts.push(<c:out value="${user.TotalPaidAmount}"/>);
                totalTrees.push(<c:out value="${user.TotalTrees}"/>);
            </c:forEach>

            // Create a bar chart
            const ctxBar = document.getElementById('barChart').getContext('2d');
            const barChart = new Chart(ctxBar, {
                type: 'bar',
                data: {
                    labels: clientNames,
                    datasets: [
                        {
                            label: 'Total Due Amount',
                            backgroundColor: 'rgba(54, 162, 235, 0.5)',
                            borderColor: 'rgba(54, 162, 235, 1)',
                            borderWidth: 1,
                            data: totalDueAmounts
                        },
                        {
                            label: 'Total Paid Amount',
                            backgroundColor: 'rgba(255, 99, 132, 0.5)',
                            borderColor: 'rgba(255, 99, 132, 1)',
                            borderWidth: 1,
                            data: totalPaidAmounts
                        }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    scales: {
                    	x: {
                            ticks: {
                                color: 'white' // Setting x-axis ticks text color to white
                            }
                        },
                        y: {
                            ticks: {
                                color: 'white' // Setting y-axis ticks text color to white
                            }
                        }
                    },
                    plugins: {
                        legend: {
                            labels: {
                                color: 'white' // Setting legend label color to white
                            }
                        }
                    }
                }
            });

            // Create a pie chart
            const ctxPie = document.getElementById('pieChart').getContext('2d');
            const pieChart = new Chart(ctxPie, {
                type: 'pie',
                data: {
                    labels: clientNames,
                    datasets: [
                        {
                            label: 'Total Trees Cut',
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.5)',
                                'rgba(54, 162, 235, 0.5)',
                                // Add more colors if needed
                            ],
                            data: totalTrees
                        }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: {
                        legend: {
                            labels: {
                                color: 'white' // Setting legend label color to white
                            }
                        },
                            title: {
                                display: true,
                                text: 'Total Trees per Client', // Title for the chart
                                color: 'white' // Setting title text color to white
                            }
                        
                    }
                }
            });
        </script>
        </div>
    </div></div>
</body>
</html>
