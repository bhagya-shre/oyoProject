<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<form action="Billing.jsp">
	<center>
	<h2>Billing</h2>
		<input type="text" name="roomId" />
		<input type="submit" value="Check Out"></center>
	</form>
	<c:out value="${param.roomId != null }"></c:out>
	<c:if test="${param.roomId != null }">
		<jsp:useBean id="beanDao"  class="com.infinite.oyoHib.RoomDAO" />
		<c:out value="${beanDao.billing(param.roomId)}" />
	</c:if>
	<jsp:useBean id="roomBean" class="com.infinite.oyoHib.RoomDAO"/>
<form action="ShowRoom.jsp">

<table border='3'>
		<tr>
		 <th>Bid </th>
		 <th> bookId</th>
		 <th>roomId</th>
		 <th>noOfDays</th>
		 <th>Amount</th>
	    </tr>
	  <c:forEach var="bill" items="${roomBean.showBill( bid)}">
	  	<tr>
	  		<td><c:out value="${bill.bid }"/> </td>
	  		<td><c:out value="${bill.bookId}"/> </td>
	  		<td><c:out value="${bill.roomId}"/> </td>
	  		<td><c:out value="${bill.noOfDays}"/> </td>
	  		<td><c:out value="${bill.billAmt}"/> </td>
	  	</tr>
	  </c:forEach>
	 </table>
</form>
</body>
