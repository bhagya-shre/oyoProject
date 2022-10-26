<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="roomBean" class="com.infinite.oyoHib.RoomDAO"/>
<form action="ShowRoom.jsp">
<table border='3'>
		<tr>
		 <th>RoomId </th>
		 <th> Type</th>
		 <th>Status</th>
		 <th>CostPerDay</th>
	    </tr>
	  <c:forEach var="room" items="${roomBean.showRoom( roomid)}">
	  	<tr>
	  		<td><c:out value="${room.roomid }"/> </td>
	  		<td><c:out value="${room.type}"/> </td>
	  		<td><c:out value="${room.status}"/> </td>
	  		<td><c:out value="${room.costPerDay}"/> </td>
	  		
	  	</tr>
	  </c:forEach>
	 </table>
</form>
</body>

</html>