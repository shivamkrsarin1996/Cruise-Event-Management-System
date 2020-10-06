<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
</head>
<div class="regHeader">
    <div class="logo"><h1><a href="<c:url value='/psg_homepage.jsp' />">Events Information</a></h1></div>
    <div class="menu_nav">
    </div>
  </div>
<body>
<h4>Enter your Information</h4>
<input name="errMsg"  value="<c:out value='${Msgs.errorMsg}'/>" class="errorPane">
<form name="dateForm" action="<c:url value='/eventController?eventtypeSearch'/>" method="post">
<table>
<tr>
 <td> Date(YYYY-MM-dd): </td>
 <td>
 <input name="date" type="date" value="<c:out value='${dateeventandy.date}'/>">
 </td>
 </tr>
 <tr>
 <td>Time(HH:mm)</td>
 <td><input name="time" type="time" value="<c:out value='${dateeventandy.time}'/>"></td>
 </tr>
 <tr>
 <td>Event Type</td>
 <td><select name="type">
  <option value="Show">Show</option>
  <option value="Athletic">Athletic</option>
</select></td>
 </tr>
 </table>
 <input name="action" value="eventtypeSearch" type="hidden">
    <input type="submit" value="Submit">
 </form>
</body>
</html>