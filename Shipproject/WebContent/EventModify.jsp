<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify Event</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
    <div><h1><a href="<c:url value='/listspecificevent.jsp' />">Event Manager</a></h1></div>
    <h2> Modify Event</h2>
    <input name="errMsg"  value="<c:out value='${ModifyMsgs.errorMsg}'/>" class="errorPane">
    <c:url value='/../eventController' var="itemDetailsURL">
			<c:param name="action" value="Eventmanagermodifyevent"/>
			<c:param name="id" value="${EVENTS.idcreate}"/>
			</c:url>
  <form name="ModifyForm" action="<c:url value='${itemDetailsURL}'/>" method="post">

         <table> 
    <tr>
    <td> EventName: </td>
    <td> <c:out value="${EVENTS.eventname}" /> </td>
    </tr>

    <tr>
    <td> Location: </td>
    <td> <c:out value="${EVENTS.location}"/> </td>
    </tr>

    <tr>
    <td> Capacity: </td>
    <td> <c:out value="${EVENTS.capacity}" /> </td>
    </tr>
    <tr>
    <td> Estimated attendees: </td>
    <td><input name="estCap" value="<c:out value="${EVENTS.estCap}" />"></td>
    <td><input name="errorestCap"  value="<c:out value='${ModifyMsgs.estCap}'/>" class="errorMsg"></td>
    </tr>
    <tr>
    <td> Duration: </td>
    <td> <c:out value="${EVENTS.duration}" /> </td>
    </tr>

   <tr>
    <td> Type: </td>
    <td> <c:out value="${EVENTS.type}" /> </td>
    </tr>
    
     <tr>
    <td> Date: </td>
    <td><input name="date" type="date" value="<c:out value="${EVENTS.date}" />"></td>
 <td><input name="errordate"  value="<c:out value='${ModifyMsgs.date}'/>" class="errorMsg"></td>
 </tr>
    
     <tr>
    <td> Time: </td>
    <td>
 <input name="time" type="time" value="<c:out value="${EVENTS.time}" />"></td>
 <td><input name="errortime"  value="<c:out value='${ModifyMsgs.time}'/>" class="errorMsg"></td>
 </tr>
    <tr>
    <td> Coordinator First name </td>
    <td> <c:out value="${cordinator.first_name}" /> </td>
    </tr>
    <tr>
    <td> Coordinator Last name </td>
    <td> <c:out value="${cordinator.last_name}" /> </td>
    </tr>
    
    <tr>
    </tr>
    </table>
    <input name="action" value="Eventmanagermodifyevent" type="hidden">
    <input type="submit" onclick="return confirm('Are you sure you want to modify this item?');"value="Submit">
    </form>
 <form name="logout" action="<c:url value='/userController?logout'/>" method="post">
 <input name="action" value="logout" type="hidden">
    <input type="submit" value="logout" />
</form> 
</body>
</html>