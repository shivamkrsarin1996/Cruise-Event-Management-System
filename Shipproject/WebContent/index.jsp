<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Ship project</h1>
<input name="passwordError"  value="<c:out value='${errorMsgs.passwordError}'/>" class="errorMsg">
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
 <button>login</button>
 <button>Register</button>
</body>
</html>