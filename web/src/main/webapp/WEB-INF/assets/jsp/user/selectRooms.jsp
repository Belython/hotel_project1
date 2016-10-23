<!DOCTYPE html>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"/>
<html>
<head>
    <title>${selectRoom_selectRoom}</title>
</head>
<body>
<%@include file="header/header.jsp" %>

<form name="selectRoomsForm" method="POST" action="controller">
    <input type="hidden" name="command" value="makeBill"/>
    <input type="hidden" name="isAjaxRequest" value="false"/>
    <table class="roomList" border="2px">
        <thead>
        <tr>
            <th>${selectRoom_roomTypeName}</th>
            <th>Макс. человек</th>
            <th>Цена</th>
            <th>${selectRoom_available}</th>
            <th>${selectRoom_quantity}</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="roomTypeMap" value="${selectedGlobalHotel.roomTypesCount}"/>
        <c:set var="roomTypeSet" value="${roomTypeMap.keySet()}"/>
        <c:forEach var="roomType" items="${roomTypeSet}">
            <tr>
                <td>${roomType.roomTypeName}</td>
                <td>${roomType.maxPersons}</td>
                <td>${roomType.pricePerNight}</td>
                <td>${roomTypeMap.get(roomType)}</td>
                <td>
                    <select name="${roomType.roomTypeName}">
                        <c:forEach var="i" begin="0" end="20">
                            <option value="${i}">${i}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="submit" value="${selectRoom_submitRooms}">

</form>

</body>
</html>
