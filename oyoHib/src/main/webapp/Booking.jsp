<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <form method="get" action="Booking.jsp">
  <table>
  	<center>
  	
  	    Enter Room Id :
  	    <input type="text" name ="roomId" value ="${param.RoomStatus}"/><br/><br/>
  		Enter CustName :
  		<input type="text" name="custName" /><br/><br/>
  		Enter City :
  		<input type="text" name="city" /><br/><br/>
  		Enter ChkInDate :
  		<input type="date" name="chkInDate" /><br/><br/>
  		Enter ChkOutDate :
  		<input type="date" name="chkOutDate" /><br/><br/>
  		<input type="submit" value="Book" />
  		<a href="Billing.jsp">Make Billing</a>
  	</center>
  	</table>
  </form>
  <c:set var="name" value="${param.roomId}" />
  <c:out value="${name}"></c:out>
    <c:out value= "${param.chkOutDate}"></c:out>
  	<c:if test="${param.city != null}">
  	<c:out value="${name}"></c:out>
    <jsp:useBean id="beanDao" class="com.infinite.oyoHib.RoomDAO"/>
	<jsp:useBean id="beanBook" class="com.infinite.oyoHib.Booking" />
	
		<jsp:setProperty property="roomId" name="beanBook" />
		<jsp:setProperty property="custName" name="beanBook" />
		<jsp:setProperty property="city" name="beanBook" />
		
		<fmt:parseDate value="${param.chkInDate}" pattern="yyyy-MM-dd" var="cdate1"/>
		<c:set var="sqlDate1" value="${beanDao.convertDate(cdate1)}"/>
		
		<fmt:parseDate value="${param.chkOutDate}" pattern="yyyy-MM-dd" var="cdate2"/>
		<c:set var="sqlDate2" value="${beanDao.convertDate(cdate2)}"/>
		
		<jsp:setProperty property="chkInDate" name="beanBook" value="${sqlDate1}"/>
		<jsp:setProperty property="chkOutDate" name="beanBook" value="${sqlDate2}"/>
		
		<c:out value="${beanDao.bookingRoom(beanBook)}"></c:out>
		
		 <br/><br/>
	</c:if>
	<c:out value= "${name}"></c:out>

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