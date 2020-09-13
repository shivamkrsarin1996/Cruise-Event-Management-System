<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Specific Event</title>
</head>
   
    
<body>
    <div><h1><a href="<c:url value='/Eventmanagerhomepage.jsp' />">Event Manager</a></h1></div>
    <h2> Selected Event</h2>
    

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
    </tr>
    </table>

</body>
</html>