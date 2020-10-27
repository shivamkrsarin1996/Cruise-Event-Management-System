
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Event Creation</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

<div><h1><a href="<c:url value='/Eventmanagerhomepage.jsp' />">Event Manager</a></h1></div>
<h2>Create Event</h2>
<input name="errMsg"  value="<c:out value='${createMsgs.errorMsg}'/>" class="errorPane">
<form name="EventForm" action="<c:url value='/eventController?Eventmanagercreateevent'/>" method="post">

<table>
<tr>
 <td> Event Name: </td>
 <td><select name="eventid">
 <c:forEach items="${simpleEventList}" var="item" varStatus="status">
 <option value="<c:out value="${item.id_event}" />"><c:out value="${item.eventname}" />(<c:out value="${item.type}" />)</option>
 </c:forEach>
 </select>
 </td>
 <td><!--<input name="erroreventname"  value="<c:out value='${createMsgs.eventname}'/>" class="errorMsg">--> </td> 
 </tr>
 
 <tr>
 <td>Date(YYYY-MM-dd)</td>
 <td><input name="date" type="date" value="<c:out value='${create.date}'/>"></td>
 <td><input name="errordate"  value="<c:out value='${createMsgs.date}'/>" class="errorMsg"></td>
 </tr>
 <tr>
 <td>Time(HH:mm)</td>
 <td>
 <input name="time" type="time" value="<c:out value='${create.time}'/>"></td>
 <td><input name="errortime"  value="<c:out value='${createMsgs.time}'/>" class="errorMsg"></td>
 </tr>
 
 <tr>
 <td>Estimated Attendees</td>
 <td><input name="estCap" value="<c:out value='${create.estCap}'/>"></td>
 <td><input name="errorestCap"  value="<c:out value='${createMsgs.estCap}'/>" class="errorMsg"></td>
 </tr>

 <tr>
 <td> Event Coordinator</td>
 <td><select name="coordinatorid">
 <c:forEach items="${CordinatorList}" var="item" varStatus="status">
 <option value="<c:out value="${item.id_user}" />"><c:out value="${item.first_name}" /> <c:out value="${item.last_name}" /></option>
 </c:forEach>
</select>
</td>
<td><input name="errorestcoordinatorid"  value="<c:out value='${createMsgs.manager}'/>" class="errorMsg"></td>
 </tr>
 </table>
<input name="action" value="Eventmanagercreateevent" type="hidden">
    <input type="submit" value="Submit">
</form>
 <form name="logout" action="<c:url value='/userController?logout'/>" method="post">
 <input name="action" value="logout" type="hidden">
    <input type="submit" value="logout" />
</form> 




</body>
</html>