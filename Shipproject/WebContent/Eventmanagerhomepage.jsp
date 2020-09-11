<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome manager</h1>


<ul>
<li><a href="<c:url value='/Evenmanagercreateevent.jsp' />"><span>Create Events</span></a> </li>

<li><a href="<c:url value='/eventController?action=eventmanagereventlist' />"><span>View Events</span></a> </li>
</ul>  

</body>
</html>