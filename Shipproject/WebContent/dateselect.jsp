<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<h4>Enter your Information</h4>
<input name="errMsg"  value="<c:out value='${Msgs.errorMsg}'/>" class="errorPane">
<form name="dateForm" action="<c:url value='/eventController?eventSearch'/>" method="post">
<table>
<tr>
 <td> Date(yyyy-mm-dd): </td>
 <td>
 <input name="date" type="date" value="<c:out value='${dateevent.date}'/>">
 </td>
 </tr>
 <tr>
 <td>Time(hh:mm:ss)</td>
 <td><input name="time" type="time" value="<c:out value='${dateevent.time}'/>"></td>
 </tr>
 </table>
 <input name="action" value="eventSearch" type="hidden">
    <input type="submit" value="Submit">
 </form>
</body>
</html>