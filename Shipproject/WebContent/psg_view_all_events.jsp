
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Events Info</title>
    <link rel="stylesheet" href="style.css">
  </head>
    <div class="regHeader">
    <div class="logo"><h1><a href="<c:url value='/psg_homepage.jsp' />">Events Information</a></h1></div>
    <div class="menu_nav">
    </div>
  </div>
<body>
<input name="errMsg"  value="<c:out value='${errorMsgs}'/>" class="errorPane">
     <div class="mainbar"><div class="submb"></div></div>
      
 <form action="<c:url value='eventController?action=listSpecificEvent' />" method="post">         
       <table border="1" class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTable20">Event Name</th>
			    <th class="myTable35">Event Date</th> 
		 		<th class="myTable20">Start time</th>
				<th class="myTable30">Duration</th> 
				<th class="myTable30">Location</th> 
			    <th class="myTable30">Available Seats</th>
			</tr>

 		<c:forEach items="${andyEVENTS}" var="event" varStatus="status">
			<tr class="myTableRow">
		<%-- 	<td class="myTable20 "><c:out value="${event.idevents}" /></td> --%>
			<td class="myTable35 "><c:out value="${event.eventname}" /></td>
	   		 <td class="myTable20 "><c:out value="${event.date}" /></td>
			<td class="myTable30 "><c:out value="${event.time}" /></td>
			<td class="myTable30 "><c:out value="${event.duration}" /></td>
			<td class="myTable30 "><c:out value="${event.location}" /></td>
			<td class="myTable30"><c:out value="${event.estCap}"/></td>
			<c:url value='/../eventController' var="itemDetailsURL">
			<c:param name="action" value="psg_listSpecificEvent"/>
			<c:param name="id" value="${event.idcreate}"/>
			</c:url>   		
            <td> <a href="<c:url value='${itemDetailsURL}' />">View Details</a></td>
           <%--    <td> <a href="<c:url value='/CompanyController?action=listSpecificCompany&id=${item.idcompany}' />">Reserve Event</a></td>  --%>
			</tr>
		</c:forEach>
 </table>
<!-- <input name="ListSelectedCompanyButton" type="submit" value="Back ">  -->
 </form>
 <form name="logout" action="<c:url value='/userController?logout'/>" method="post">
 <input name="action" value="logout" type="hidden">
    <input type="submit" value="logout" />
</form>
</body>
</html>