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

<li><a href="<c:url value='/eventController?action=eventassignedtlist' />"><span>View Assigned Events</span></a> </li>

<li><a href="<c:url value='/Logout.jsp' />"><span>Log out</span></a> </li>
</ul>  

</body>
</html>