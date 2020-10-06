<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Passenger Info</title>
    <link rel="stylesheet" href="style.css">
  </head>
  <div class="regHeader">
    <div class="logo"><h1><a href="<c:url value='/psg_homepage.jsp'  />">Passenger Information</a></h1></div>
    <div class="menu_nav">
    </div>
  </div>					
  <body>
    <div class="bodyreg">
    <input name="errMsg"  value="<c:out value='${andy2.cpasswordError}'/>" class="error">
    <table>
      <tr>
       <td>
             <table border="1" class="myTable"> 
        <tr>
        <td> Passenger ID: </td>
        <td> <c:out value="${loginU.id_user}" /> </td>
        </tr>

        <tr>
        <td> Passenger's First Name: </td>
        <td> <c:out value="${loginU.first_name}"/> </td>
        </tr>

		 <tr>
        <td> Passenger's Last Name: </td>
        <td> <c:out value="${loginU.last_name}"/> </td>
        </tr>
        <tr>
        <td> Phone Number: </td>
        <td> <c:out value="${loginU.phone}" /> </td>
        </tr>

        <tr>
        <td> Email: </td>
        <td> <c:out value="${loginU.email}" /> </td>
        </tr>
		 <tr>
        <td> Room Number: </td>
        <td> <c:out value="${loginU.room_number}" /> </td>
        </tr>
        <tr>
        <td> Deck Number: </td>
        <td> <c:out value="${loginU.deck_number}" /> </td>
        </tr>
        <tr>
        <td> Membership Type: </td>
        <td> <c:out value="${loginU.memtype}" /> </td>
        </tr>
        
        <tr>
        </tr>
        </table>
    </td>
    </tr>
    </table>
    <form action="<c:url value='/psg_updateinfo.jsp'/>" >
    <input type="submit" value="Update profile" />
</form>
    <form name="logout" action="<c:url value='/userController?logout'/>" method="post">
 <input name="action" value="logout" type="hidden">
    <input type="submit" value="logout" />
</form> 
    </div>
  </body>
</html>