
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form name="EventForm" action="<c:url value='/eventController?Eventmanagercreateevent'/>" method="post">

<table>
<tr>
 <td> Event Name: </td>
 <td>
 <input name="eventname" value="<c:out value='${events.eventname}'/>">
 
 </td> 
 </tr>
 
 <tr>
 <td>Location</td>
 <td><input name="location" value="<c:out value='${events.location}'/>"></td>
 </tr>
 <tr>
 <td>capacity</td>
 <td>
 <input name="capacity" value="<c:out value='${events.capacity}'/>"></td>
 </tr>
 <tr>
 <td>Duration</td>
 <td><input name="duration" value="<c:out value='${events.duration}'/>"></td>
 
 </tr>

 <tr>
 <td> Type</td>
 <td><select name="type">
 
  <option value="bowling1">Bowling1</option>
  <option value="bowling2">Bowling2</option>
  <option value="movie1">Movie1</option>
  <option value="movie2">Movie2</option>
  <option value="extreme zipline">Extreme zipline</option>
  <option value="skycourse ropes">Skycourse ropes</option>
  <option value="ice skating">Ice skating</option>
  <option value="go karting">Go karting</option>
  <option value="broadway show">Broadway show</option>
  <option value="planetarium">Planetarium</option>
  
</select>
</td>
 </tr>
 </table>
<input name="action" value="createevent" type="hidden">
    <input type="submit" value="Submit">
</form>




</body>
</html>