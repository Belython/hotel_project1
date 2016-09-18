<!DOCTYPE html>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="../error/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css"/>--%>
<html>
<head>
    <title>Результаты поиска</title>
</head>
<body>
<%@include file="../../../../assets/pages/inputs/header.jsp"%>
<form name="billsForm" method="POST" action="controller">
    <input type="hidden" name="command" value="payBill"/>
    Счета<br/>
    <c:forEach var="bill" items="${billList}">
        <c:forEach var="room" items="${bill.bookedRoomList}">
            Тип номера ${room.roomType.name}
            Номер номера ${room.roomNumber}
            Статус ${bill.roomTypeStatus}
        </c:forEach>
        <c:if test="${bill.roomTypeStatus == 'notPaid'}">
            <input type="hidden" name="billToPay" value="${bill.roomTypeId}"/>
            <input type="submit" value="Оплатить"/>
        </c:if><br/>
    </c:forEach>
</form>
</body>
</html>
