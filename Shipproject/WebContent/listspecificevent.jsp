<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Specific Event</title>
<link rel="stylesheet" href="style.css">
</head>
   
    
<body>
    <div><h1><a href="<c:url value='/eventmanagereventlist.jsp' />">Event Manager</a></h1></div>
    <h2> Selected Event</h2>
    <input name="errMsg"  value="<c:out value='${success.errorMsg}'/>" class="error">

         <table border="1" class="myTable"> 
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
    <div class=lin>
    <form action="<c:url value='/EventModify.jsp'/>">
    <input type="submit" value="Modify" />
</form>
<form action="<c:url value='/eventController?redirectAssignCor'/>" method="post">
<input name="action" value="redirectAssignCor" type="hidden">
    <input type="submit" value="Assign New Cordinator" />
</form>
 <form name="logout" action="<c:url value='/userController?logout'/>" method="post">
 <input name="action" value="logout" type="hidden">
    <input type="submit" value="logout" />
</form> 
</div>
</body>
</html>