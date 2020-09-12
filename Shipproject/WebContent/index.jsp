<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<h1>Ship project</h1>
<input name="errMsg"  value="<c:out value='${errorMs.usernameError}'/>" class="errorPane">
<form name="loginForm" action="<c:url value='/userController?login'/>" method="post">
<table>
<tr>
<td> User Name: </td>
 <td>
 <input name="username" value="<c:out value='${user.username}'/>"></td>
 
 </tr>
 <tr>
 <td>Password</td>
 <td><input name="password" value="<c:out value='${user.password}'/>"></td>
</tr>
 </table>
<input name="action" value="login" type="hidden">
    <input type="submit" value="Submit">
    <input type="button" onclick="location.href='register.jsp';" value="Register" />
</form>
</html>