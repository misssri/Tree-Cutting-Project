<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>Raise a Quote Request</title>
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
  width: 80%;
  padding: 20px;
  text-align: center;
}
body {

  font-family: Arial, Helvetica, sans-serif;
  background-color: white;
}

* {
  box-sizing: border-box;
}

/* Add padding to containers */
.container {
  width:50%;
  padding: 3px;
  background-color: white;
}

/* Full-width input fields */
input[type=text] {
  width: 100%;
  padding: 5px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

input[type=text]:focus {
  background-color: #ddd;
  outline: none;
}

/* Overwrite default styles of hr */
th,td {
  border: 1px solid #f1f1f1;
  margin-bottom: 10px;
}

/* Set a style for the submit button */
input[type="submit"] {
  background-color: blue;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

input[type="submit"] {
  opacity: 1;
  
}

/* Add a blue text color to links */
a {
  background-color: dodgerblue;
}


a {
  background-color: #f1f1f1;
  text-align: center;
}
    </style>
</head>
 

<body>
<div class="bg-image"></div>
<div class="bg-text">
<h2>Raise a Request To Cut Tree</h2>
	<div align="center">
	
		<p> ${errorOne } </p>
		<p> ${errorTwo } </p>
		<form action="qrequest" method="post" enctype="multipart/form-data">
			<table border="1" cellpadding="5">
				<tr>
					<th>Email Address: </th>
					<td align="center" colspan="3">
						<input type="text" name="Email" size="45"  value="example@gmail.com" onfocus="this.value=''">
					</td>
				</tr>
				
				<tr>
					<th>Tree Size: </th>
					<td align="center" colspan="3">
						<input type="text" name="Size" size="45" value="Size" onfocus="this.value=''">
					</td>
				</tr>
				
				<tr>
					<th>Tree Height: </th>
					<td align="center" colspan="3"> 
						<input type="text" name="Height" size="45" value="Height" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Tree Location: </th>
					<td align="center" colspan="3">
						<input type="text" name="Location" size="45" value="Location" onfocus="this.value=''">
					</td>
				
				</tr>
				
				<tr>
					<th>Tree Distance to House: </th>
					<td align="center" colspan="3">
						<input type="text" name="DistanceToHouse" size="45" value="Distance" onfocus="this.value=''">
					</td>
	
				</tr>
				<tr>
					<th>Picture 1: </th>
					<td align="center" colspan="3">
						<input type="file" name="Picture1" accept="image/* onfocus="this.value=''">
					</td>
	
				</tr>
				<tr>
					<th>Picture 2: </th>
					<td align="center" colspan="3">
						<input type="file" name="Picture2" accept="image/* onfocus="this.value=''">
					</td>
	
				</tr>
				<tr>
					<th>Picture 3: </th>
					<td align="center" colspan="3">
						<input type="file" name="Picture3" accept="image/* onfocus="this.value=''">
						
					</td>
	
				</tr>
				<tr>
					<th>Additional Comments: </th>
					<td align="center" colspan="3">
						<input type="text" name="Note" size="45" value="Note" onfocus="this.value=''">
					</td>
	
				</tr>
				
				
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Request"/>
					</td>
				</tr>
			</table></br>
			<a href="ClientDashboard.jsp" target="_self">Return to Home</a>
		</form>
	</div></div>
</body>