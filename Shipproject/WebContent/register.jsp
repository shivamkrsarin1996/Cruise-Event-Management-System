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
<h1><a href="<c:url value='/'  />">Registration</a></h1>
</header>
<div class="bodyreg">
<h4>Enter your Information</h4>
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" class="errorPane">
<form name="userForm" action="<c:url value='/userController?registerUser'/>" method="post">
<table>
<tr>
 <td> First Name: </td>
 <td>
 <input name="firstname" value="<c:out value='${user.first_name}'/>">
 </td>
 <td><input name="first_nameError"  value="<c:out value='${errorMsgs.first_nameError}'/>" class="errorMsg"> </td>
 
 </tr>
 <tr>
 <td>Last Name</td>
 <td><input name="lastname" value="<c:out value='${user.last_name}'/>"></td>
 <td><input name="last_nameError"  value="<c:out value='${errorMsgs.last_nameError}'/>" class="errorMsg"> </td>
 </tr>
 <tr>
 <td> User Name: </td>
 <td>
 <input name="username" value="<c:out value='${user.username}'/>"></td>
 <td><input name="usernameError"  value="<c:out value='${errorMsgs.usernameError}'/>" class="errorMsg"> </td>
 </tr>
 <tr>
 <td>Password</td>
 <td><input name="password" value="<c:out value='${user.password}'/>"></td>
 <td><input name="passwordError"  value="<c:out value='${errorMsgs.passwordError}'/>" class="errorMsg"> </td>
 </tr>
 <tr>
 <td>Confirm Password</td>
 <td><input name="cpassword" value="<c:out value='${user.cpassword}'/>"></td>
 <td><input name="cpasswordError"  value="<c:out value='${errorMsgs.cpasswordError}'/>" class="errorMsg"> </td>
 
 </tr>
 <tr>
 <td>Email</td>
 <td><input name="email" value="<c:out value='${user.email}'/>"></td>
 <td><input name="emailError"  value="<c:out value='${errorMsgs.emailError}'/>" class="errorMsg"> </td>
 </tr>
 <tr>
 <td>Phone</td>
 <td><input name="phone" value="<c:out value='${user.phone}'/>"></td>
 <td><input name="phoneError"  value="<c:out value='${errorMsgs.phoneError}'/>" class="errorMsg"> </td>
 </tr>
 <tr>
 <td>Room Number</td>
 <td><input name="roomNumber" value="<c:out value='${user.room_number}'/>"></td>
 <td><input name="room_numberError"  value="<c:out value='${errorMsgs.room_numberError}'/>" class="errorMsg"> </td>
 </tr>
 <tr>
 <td>Deck Number</td>
 <td><input name="deckNumber" value="<c:out value='${user.deck_number}'/>"></td>
 <td><input name="deck_numberError"  value="<c:out value='${errorMsgs.deck_numberError}'/>" class="errorMsg"> </td>
 </tr>
 <tr>
 <td>Membership Type</td>
 <td><select name="memtype">
  <option value="none">None</option>
  <option value="standard">Standard</option>
  <option value="superior">Superior</option>
  <option value="premium">Premium</option>
</select></td>
 </tr>
</table>
<input name="action" value="registerUser" type="hidden">
    <input type="submit" value="Submit">
</form>
</div>

</body>
</html>