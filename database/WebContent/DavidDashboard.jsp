<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
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
  width: 80%;
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
.standard-button {
  font-size: 16px; /* Set the font size */
  width: 300px; /* Set the width */
  height: 40px; /* Set the height */
}
.standard-button {
  font-size: 16px;
  padding: 10px 20px;
  background-color: #27ae60; /* Green color */
  color: #ffffff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.standard-button:hover {
  background-color: #219653; /* Darker green on hover */
}

.glitter{
  font-size: 50px;
  text-align: center;
  position: relative;
  color: #ffd700; /* Gold color */
  animation: glitter-animation-gold 2s infinite;
}

@keyframes glitter-animation-gold {
  0% { color: #ffec80; } /* Lighter gold */
  25% { color: #ffd700; } /* Gold */
  50% { color: #ffec80; } /* Lighter gold */
  75% { color: #ffd700; } /* Gold */
  100% { color: #ffec80; } /* Lighter gold */
}


</style>
</head>
<body>
<div class="topnav">
<a class="active" href="DavidDashboard.jsp">Home</a>
  <a href="ListQuote.jsp">Show Initial Requests</a>
  <a href="ListNegotiations.jsp">Respond to Negotiations</a>
  <a href="dotherquotes.jsp">All Quotes</a>
  <a href="dbilllist.jsp">View Pending Bills</a>
  <a href="dbillneglist.jsp">View Bill Negotiations</a>
   <a href="login.jsp" class="split">Logout</a>
			</div>
			<div class="bg-image"></div>
<div class="bg-text">
  <h1 class="glitter" style="font-size:50px">Welcome David!!</h1>
 <form action="clistdresponse"><input type="submit" class="standard-button"  style="font-size:20px" value="View Big clients"/></form></br>
 <form action="EasyClients.jsp"><input type="submit" class="standard-button" style="font-size:20px" value="View Easy Clients"/></form></br>
  <form action="HighestTreeCut.jsp"><input type="submit" class="standard-button" style="font-size:20px" value="View Highest Tree Cut"/></form></br>
 <form action="HighestTreeCut.jsp"><input type="submit" class="standard-button" style="font-size:20px" value="View Prospective clients"/></form></br>
<form action="OverdueBills.jsp"> <input type="submit" class="standard-button" style="font-size:20px" value="View Overdue bills"/></form></br>
<form action="HighestTreeCut.jsp"> <input type="submit" class="standard-button" style="font-size:20px" value="View Bad clients"/></form></br>
<form action="GoodClient.jsp"> <input type="submit" class="standard-button" style="font-size:20px" value="View Good Clients"/></form></br>
 <input type="submit" class="standard-button"  style="font-size:20px" value="Show Statistics"/>

 
</div>
</body>
</html>
