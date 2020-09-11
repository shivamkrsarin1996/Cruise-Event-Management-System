
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>insert title</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<link href="mystyle.css" rel="stylesheet" type="text/css" />

<body>
   

      <div class="mainbar"><div class="submb"></div></div>
    
 <form action="<c:url value='eventController?action=listspecificevent' />" method="post">         
       <table border="1" class="myTable"> 
			<tr class="myTableRow"> 
			    <th class="myTable20">Select Event</th>
				<th class="myTable20">EventName</th>
				<th class="myTable20">Location</th>
				<th class="myTable35">Date</th> 
				<th class="myTable20">Time</th>
				<th class="myTable30">Action</th> 
			</tr>

 			<c:forEach items="${EVENTS}" var="item" varStatus="status">
			<tr class="myTableRow">
			<td class="myTableRadio"><input type="radio" id="radioCompany${status.count}" name="radioCompany" value="<c:out value="${status.count}" />"></td> 	
			<td class="myTable20 "><c:out value="${item.eventname}" /></td>
			<td class="myTable20 "><c:out value="${item.location}" /></td>
			<td class="myTable35 "><c:out value="${item.date}" /></td>
			<td class="myTable20 "><c:out value="${item.time}" /></td>
            <td> <a href="<c:url value='/eventController?action=listSpecificevent&id=${item.idcreate}' />">View</a></td>
			</tr>
		   </c:forEach>
 </table>
<input name="ListSelectedCompanyButton" type="submit" value="View selected">
 </form>
</body>
</html>