<!DOCTYPE html>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="../error/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" href="/assets/css/style.css"/>
<html>
<head>
    <title>${selectHotel_selectHotel}</title>
</head>
<body>
<%@include file="../user/header/header.jsp"%>

<form name="selectHotelForm" method="POST" action="controller">
    <input type="hidden" name="command" value="selectRoom"/>

    <table class="hotelList">
        <thead>
            <tr>
                <th>${selectHotel_country}</th>
                <th>${selectHotel_city}</th>
                <th>${selectHotel_hotelName}</th>
                <th>${selectHotel_roomsAvailable}</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="hotel" items="${hotelDtoList}">
            <%--<input type="hidden" name="hotelId" value="${hotel.hotelId}">--%>
            <c:set var="location" value="${hotel.location}"/>
            <tr>
                <td>${location.country}</td>
                <td>${location.city}</td>
                <td>${hotel.hotelName}</td>
                <td>${hotel.roomsAvailable}</td>
                <td>
                    <%--<input class="selectHotelBtn" type="button" name="hotelId" value="${hotel.hotelId}">${selectHotel_submitHotel}</input>--%>
                    <%--<a href="controller?command=refuseBill&billId=${bill.billId}">${account_refuseBill}</a>--%>
                    <a href="controller?command=selectRoom&hotelId=${hotel.hotelId}">${selectHotel_submitHotel}</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</form>


</body>
</html>
