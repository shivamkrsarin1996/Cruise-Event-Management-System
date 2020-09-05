<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
<link rel="stylesheet" href="style.css">
</head>
<body class="regbody">
<header class="regHeader">
<h1>Registration</h1>
</header>
<div class="bodyreg">
<h4>Enter your Information</h4>
<form name="userForm" action="<c:url value='/userController?registerUser'/>" method="post">
<table>
<tr>
 <td> First Name: </td>
 <td>
 <input name="firstname" value="<c:out value='${user.first_name}'/>">
 </td>
 </tr>
 <tr>
 <td>Last Name</td>
 <td><input name="lastname" value="<c:out value='${user.last_name}'/>"></td>
 </tr>
 <tr>
 <td> User Name: </td>
 <td>
 <input name="username" value="<c:out value='${user.username}'/>">
 </td>
 </tr>
 <tr>
 <td>Password</td>
 <td><input name="password" type="password" value="<c:out value='${user.password}'/>"></td>
 </tr>
 <tr>
 <td>Confirm Password</td>
 <td><input name="cpassword" type="password" value="<c:out value='${user.password}'/>"></td>
 </tr>
 <tr>
 <td>Email</td>
 <td><input name="email" value="<c:out value='${user.email}'/>"></td>
 </tr>
 <tr>
 <td>Phone</td>
 <td><input name="phone" value="<c:out value='${user.phone}'/>"></td>
 </tr>
 <tr>
 <td>Room Number</td>
 <td><input name="roomNumber" value="<c:out value='${user.room_number}'/>"></td>
 </tr>
 <tr>
 <td>Deck Number</td>
 <td><input name="deckNumber" value="<c:out value='${user.deck_number}'/>"></td>
 </tr>
 <tr>
 <td>Membership Type </td>
 <td><input name="memtype" type="radio" value="none">None<input name="memtype" type="radio" value="standard">Standard<input name="memtype" type="radio" value="superior">Superior<input name="memtype" type="radio" value="premium">Premium
 </td>
 </tr>
</table>
<input name="action" value="registerUser" type="hidden">
    <input type="submit" value="Submit">
</form>
</div>

</body>
</html>