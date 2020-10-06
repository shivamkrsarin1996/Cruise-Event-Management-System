<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Specific Event</title>
<link rel="stylesheet" href="style.css">
</head>
   
    
<body>
   <div><h1><a href="<c:url value='/corodinatoreventlist.jsp' />">Event Coordinator</a></h1></div>
    <h2> Selected Event</h2>
    <input name="errMsg"  value="<c:out value='${success.errorMsg}'/>" class="error">

         <table border="1" class="myTable"> 
    <tr>
    <td> EventName: </td>
    <td> <c:out value="${EVENTScor.eventname}" /> </td>
    </tr>

    <tr>
    <td> Location: </td>
    <td> <c:out value="${EVENTScor.location}"/> </td>
    </tr>

    <tr>
    <td> Capacity: </td>
    <td> <c:out value="${EVENTScor.capacity}" /> </td>
    </tr>
    <tr>
    <td> Estimated attendees: </td>
    <td> <c:out value="${EVENTScor.estCap}" /> </td>
    </tr>

    <tr>
    <td> Duration: </td>
    <td> <c:out value="${EVENTScor.duration}" /> </td>
    </tr>

   <tr>
    <td> Type: </td>
    <td> <c:out value="${EVENTScor.type}" /> </td>
    </tr>
    
     <tr>
    <td> Date: </td>
    <td> <c:out value="${EVENTScor.date}" /> </td>
    </tr>
    
     <tr>
    <td> Time: </td>
    <td> <c:out value="${EVENTScor.time}" /> </td>
    </tr>
    <tr>
    <td> Coordinator First name </td>
    <td> <c:out value="${cordinatorcor.first_name}" /> </td>
    </tr>
    <tr>
    <td> Coordinator Last name </td>
    <td> <c:out value="${cordinatorcor.last_name}" /> </td>
    </tr>
    
    <tr>
    </tr>
    </table>
    <div class=lin>
    
 <form name="logout" action="<c:url value='/userController?logout'/>" method="post">
 <input name="action" value="logout" type="hidden">
    <input type="submit" value="logout" />
</form> 
</div>
</body>
</html>