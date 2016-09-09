<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="../error/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<html>
<head>
    <title>Режим администратора</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script type="text/javascript" src="../../../../assets/scripts/admin.js"></script>
</head>
<body>
<%@include file="../../../../assets/pages/inputs/header.jsp"%>
<%@include file="sideBar/sideBar.jsp"%>
<form name="sortForm" method="POST" action="controller">
    <input type="hidden" name="command" value="sortHotelsTable"/>
    <select name="sortingOption">
        <option value="hotelName">Название отеля</option>
        <option value="hotelCountry">Страна</option>
        <option value="hotelCity">Город</option>
    </select>
    <select name="sortingDirection">
        <option value="ascending">По возрастанию</option>
        <option value="descending">По убыванию</option>
    </select>
    <input type="submit" value="sort">
</form>

<form name="newEntityForm" method="POST" action="controller">
    <input type="hidden" name="command" value="addNewEntity"/>
    <c:set var="entityFields" value="${fieldValuesMap.keySet()}"/>
    <c:forEach var="entityField" items="${entityFields}">
        <c:set var="fieldValues" value="${fieldValuesMap.get(entityField)}"/>
        ${entityField}
        <select name="${entityField}">
            <c:forEach var="fieldValue" items="${fieldValues}">
                <option value="${fieldValue}">${fieldValue}</option>
            </c:forEach>
        </select>
    </c:forEach>
</form>

<form name="hotelForm" method="POST" action="controller">
    <input type="hidden" name="command" value="alterHotels"/>
    <input type="hidden" name="isAjaxRequest" value="false"/>
    <table>
        <tr>
            <th>country</th>
            <th>city</th>
            <th>name</th>
            <th>discount</th>
            <th>status</th>
        </tr>
        <c:forEach var="hotel" items="${hotelsList}">
            <c:set var="id" value="${hotel.id}"/>
            <tr>
                <input type="hidden" name="hotelId" placeholder="${hotel.id}" value="${hotel.id}">
                <td><input type="text" name="hotelCountry" placeholder="${hotel.country}" value="${hotel.country}"></td>
                <td><input type="text" name="hotelCity" placeholder="${hotel.city}" value="${hotel.city}"></td>
                <td><input type="text" name="hotelName" placeholder="${hotel.name}" value="${hotel.name}"></td>
                <td><input type="text" name="hotelDiscount" placeholder="${hotel.discount}" value="${hotel.discount}"></td>
                <td><input type="text" name="hotelStatus" placeholder="${hotel.status}" value="${hotel.status}"></td>
                <td><button class="submitBtn" type="button">Засслать</button></td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="SubmitAll">
</form>
<button id="addBtn" type="button" name="butt"> Добавить отель </button>
</body>
</html>
