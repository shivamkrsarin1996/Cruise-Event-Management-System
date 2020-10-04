<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Event Coordinator</title>
</head>
<body>
<h1>Welcome Event Coordinator....!!</h1>
<ul>

<li><a href="<c:url value='/eventController?action=eventassignedtlist&id=${loginU.id_user}' />"><span>View Assigned Events</span></a> </li>
<li><a href="<c:url value='/eventController?action=eventcorlist' />"><span>View Events</span></a> </li>
</ul>  
 <form name="logout" action="<c:url value='/userController?logout'/>" method="post">
 <input name="action" value="logout" type="hidden">
    <input type="submit" value="logout" />
</form> 
</body>
</html>