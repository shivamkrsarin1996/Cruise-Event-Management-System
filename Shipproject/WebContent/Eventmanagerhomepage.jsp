<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Event Manager - Home page</title>
</head>
<body>
 
<h1>Welcome event manager</h1>


<ul>
<!--<li><a href="<c:url value='/eventController?action=redirectCreatepage' />"><span>Create Events</span></a> </li>-->
<li><a href="<c:url value='/eventController?action=redirectPagedatetime'/>"><span>View Event Summary</span></a></li>
<li><a href="<c:url value='/eventController?action=eventmanagereventlist' />"><span>View All Events</span></a> </li>
</ul>
 <form name="logout" action="<c:url value='/userController?logout'/>" method="post">
 <input name="action" value="logout" type="hidden">
    <input type="submit" value="logout" />
</form> 

</body>
</html>