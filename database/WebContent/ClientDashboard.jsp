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

</style>
</head>
<body>
<div class="topnav">
<a class="active" href="ClientDashboard.jsp">Home</a>
  <a href="quoteRequest.jsp">Raise a Request</a>
   <a href="login.jsp" class="split">Logout</a>
			</div>
			<div class="bg-image"></div>

<div class="bg-text">
  
  <h1 style="font-size:50px">Welcome!!</h1>

<form action="clistdresponse"><input type="hidden" name="ClientID" value="${ClientID}"><input type="submit"  style="font-size:20px" value="Show Responses from David"/></form></br>
<form action="clistalldresponse"><input type="hidden" name="ClientID" value="${ClientID}"><input type="submit"  style="font-size:20px" value="Show Rejections from David"/></form></br>
<form action="cbsee"><input type="hidden" name="ClientID" value="${ClientID}"><input type="submit"  style="font-size:20px" value="Show Bills to Pay"/></form></br>
       
</div>

			
</body>
</html>