<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Passenger Info</title>
    <link rel="stylesheet" href="style.css">
  </head>
  <div class="regHeader">
    <div class="logo"><h1><a href="<c:url value='/psg_homepage.jsp'  />">Passenger Information</a></h1></div>
    <div class="menu_nav">
    </div>
  </div>					
<body>
<div class="bodyreg">
<input name="errMsg"  value="<c:out value='${andy.cpasswordError}'/>" class="errorPane">
<form name="updateProfile" action="userController?action=updateProfile" method="post">
<table>
<tr>
<td>
    <table>
     	<tr>
        <td> Passenger User Name: </td>
        <td><c:out value='${loginU.username}'/></td>
        </tr>

        <tr>
        <td> Passenger's Password: </td>
        <td> 
       <input name="password" value="<c:out value='${loginU.password}'/>">
        </td>
         <td><input name="passwordError"  value="<c:out value='${andy.passwordError}'/>" class="errorMsg"> </td>
        </tr>
         <tr>
        <td> Passenger's First Name: </td>
        <td> 
       <input name="firstname" value="<c:out value='${loginU.first_name}'/>">
        </td>
         <td><input name="first_nameError"  value="<c:out value='${andy.first_nameError}'/>" class="errorMsg"> </td>
        </tr>

		 <tr>
        <td> Passenger's Last Name: </td>
         <td><input name="lastname" value="<c:out value='${loginU.last_name}'/>"></td>
         <td><input name="last_nameError"  value="<c:out value='${andy.last_nameError}'/>" class="errorMsg"> </td>
        </tr>
        <tr>
        <td> Phone Number: </td>
        <td><input name="phone" value="<c:out value='${loginU.phone}'/>"></td>
 <td><input name="phoneError"  value="<c:out value='${andy.phoneError}'/>" class="errorMsg"> </td>
        </tr>

        <tr>
        <td> Email: </td>
        <td><input name="email" value="<c:out value='${loginU.email}'/>"></td>
 <td><input name="emailError"  value="<c:out value='${andy.emailError}'/>" class="errorMsg"> </td>
        </tr>
		 <tr>
        <td> Room Number: </td>
        <td><input  name="roomNumber" value="<c:out value='${loginU.room_number}'/>"></td>
 <td><input name="room_numberError"  value="<c:out value='${andy.room_numberError}'/>" class="errorMsg"> </td>
        </tr>
        <tr>
        <td> Deck Number: </td>
         <td><input  name="deckNumber" value="<c:out value='${loginU.deck_number}'/>"></td>
 <td><input name="deck_numberError"  value="<c:out value='${andy.deck_numberError}'/>" class="errorMsg"> </td>
        </tr>
        <tr>
        <td> Current Membership Type: </td>
        <td> <c:out value="${loginU.memtype}" /> </td>
        </tr>
        <tr>
        <td>Change Membership Type</td>
        <td><select name="memtype">
        <option value="<c:out value="${loginU.memtype}"/>">No change</option>
        <option value="none">None</option>
        <option value="standard">Standard</option>
        <option value="superior">Superior</option>
        <option value="premium">Premium</option>
        </select></td>
        </tr>
        
        <tr>
        </tr>
        </table>
    </td>
    </tr>
    </table>
   
   <input name="action" value="updateProfile" type="hidden">
    <input type="submit" value="Save" onclick="return confirm('Are you sure to save changes?')">
</form>
   
    <form name="logout" action="<c:url value='/userController?logout'/>" method="post">
 <input name="action" value="logout" type="hidden">
    <input type="submit" value="logout" />
</form> 
    </div>
  </body>
</html>