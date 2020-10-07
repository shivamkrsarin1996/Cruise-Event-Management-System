<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Event Information</title>
    <link rel="stylesheet" href="style.css">
  </head>
  <div class="regHeader">
      <div class="logo"><h1><a href="<c:url value='/psg_reserved_events.jsp' />">Event Details</a></h1></div>
  <div class="menu_nav">
    </div>
  </div>					
  <body>
    <div class="bodyreg">
   
  <table>
      <tr>
       <td>
    <table border="1" class="myTable"> 
  <tr>
    <td> Event Name: </td>
    <td> <c:out value="${EVENTS.eventname}" /> </td>
    </tr>

	<tr>
    <td>Event Date: </td>
    <td> <c:out value="${EVENTS.date}" /> </td>
    </tr>
    
    <tr>
    <td>Start Time: </td>
    <td> <c:out value="${EVENTS.time}" /> </td>
    </tr>
   
	<tr>
    <td> Duration: </td>
    <td> <c:out value="${EVENTS.duration}" /> </td>
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
    <td> Number of attendees: </td>
    <td> <c:out value="${EVENTS.estCap}" /> </td>
    </tr>


   <tr>
    <td> Type: </td>
    <td> <c:out value="${EVENTS.type}" /> </td>
    </tr>
    <tr>
    <td> Coordinator First name </td>
    <td> <c:out value="${cordinator.first_name}" /> </td>
    </tr>
    <tr>
    <td> Coordinator Last name </td>
    <td> <c:out value="${cordinator.last_name}" /> </td>
    </tr>
    

    

        </table>
    </td>
    </tr>	
    </table>  
  <!--   <input name="ReserveEventButton" type="submit" value="Reserve Event">	-->
    </div>

    <form name="logout" action="<c:url value='/userController?logout'/>" method="post">
 <input name="action" value="logout" type="hidden">
    <input type="submit" value="logout" />
</form>
  </body>
</html>