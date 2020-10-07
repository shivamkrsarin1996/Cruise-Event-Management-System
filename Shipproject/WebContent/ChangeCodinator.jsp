<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify Event</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
    <div><h1><a href="<c:url value='/Eventmanagerhomepage.jsp' />">Event Manager</a></h1></div>
    <h2> Modify Event</h2>
    <input name="errMsg"  value="<c:out value='${corMsgs.errorMsg}'/>" class="errorPane">
    <c:url value='/../eventController' var="itemDetailsURL">
			<c:param name="action" value="Eventmanagerassignevent"/>
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
     <tr>
    <td> Estimated attendees: </td>
    <td> <c:out value="${EVENTS.estCap}" /> </td>
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
    <td> <c:out value="${EVENTS.date}" /> </td>
    </tr>
    
     <tr>
    <td> Time: </td>
    <td> <c:out value="${EVENTS.time}" /> </td>
    </tr>
    <tr>
    <td> Current Coordinator First name </td>
    <td> <c:out value="${cordinator.first_name}" /> </td>
    </tr>
    <tr>
    <td> Current Coordinator Last name </td>
    <td> <c:out value="${cordinator.last_name}" /> </td>
    </tr>
    <tr>
 <td> New Event Coordinator</td>
 <td><select name="coordinatorid">
 <c:forEach items="${CordinatorList}" var="item" varStatus="status">
 <option value="<c:out value="${item.id_user}" />"><c:out value="${item.first_name}" /> <c:out value="${item.last_name}" /></option>
 </c:forEach>
</select>
</td>
<td><input name="errorestcoordinatorid"  value="<c:out value='${corMsgs.manager}'/>" class="errorMsg"></td>
 </tr>
    
    <tr>
    </tr>
    </table>
    <input name="action" value="Eventmanagerassignevent" type="hidden">
    <input type="submit" value="Submit">
    </form>
 <form name="logout" action="<c:url value='/userController?logout'/>" method="post">
 <input name="action" value="logout" type="hidden">
    <input type="submit" value="logout" />
</form> 
</body>
</html>