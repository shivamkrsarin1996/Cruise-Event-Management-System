<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" href="style.css">
	</head>
	<body>
		<header class="regHeader">
			<h1><a href="<c:url value='/' />">Welcome aboard Passenger!!</a></h1>
		</header>
		<ul>	
	          <li><a href="<c:url value='/eventController?action=psg_view_all_events' />"><span>View Events</span></a></li>
	          <br><li><a href="<c:url value='/psg_reserved_events.jsp' />"><span>View my reservations</span></a></li>  
	          <br><li><a href="<c:url value='/psg_info.jsp' />"><span>View profile</span></a></li>  
		</ul>
 <form name="logout" action="<c:url value='/userController?logout'/>" method="post">
 <input name="action" value="logout" type="hidden">
    <input type="submit" value="logout" />
</form> 
		
	</body>
</html>