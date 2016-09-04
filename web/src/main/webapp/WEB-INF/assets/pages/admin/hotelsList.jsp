<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="../error/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<html>
<head>
    <title>Режим администратора</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="../../../../assets/scripts/admin.js"></script>
</head>
<body>
<%@include file="../../../../assets/pages/inputs/header.jsp"%>
<%@include file="sideBar/sideBar.jsp"%>
<form name="hotelForm" method="POST" action="controller">
    <input type="hidden" name="command" value="applyChanges"/>
    <table>
        <tr>
            <th>Hotel Id</th>
            <th>country</th>
            <th>city</th>
            <th>name</th>
            <th>discount</th>
            <th>status</th>
        </tr>
        <c:forEach var="hotel" items="${hotelsList}">
            <c:set var="id" value="${hotel.id}"/>
            <tr>
                <td><input type="text" name="hotelId" placeholder="${hotel.id}" value="${hotel.id}"></td>
                <td><input type="text" name="hotelCountry" placeholder="${hotel.country}" value="${hotel.country}"></td>
                <td><input type="text" name="hotelCity" placeholder="${hotel.city}" value="${hotel.city}"></td>
                <td><input type="text" name="hotelName" placeholder="${hotel.name}" value="${hotel.name}"></td>
                <td><input type="text" name="hotelDiscount" placeholder="${hotel.discount}" value="${hotel.discount}"></td>
                <td><input type="text" name="hotelStatus" placeholder="${hotel.status}" value="${hotel.status}"></td>
                <td><input type="submit" value="apply"></td>
            </tr>
        </c:forEach>
    </table>
</form>
<button type="button" name="butt" onclick="newHotel()" />
</body>
</html>
