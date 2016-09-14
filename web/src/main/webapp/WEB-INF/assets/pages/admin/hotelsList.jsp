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

<%--<form name="newEntityForm" method="POST" action="controller">--%>
    <%--<input type="hidden" name="command" value="addNewEntity"/>--%>
    <%--<c:set var="entityFields" value="${fieldValuesMap.keySet()}"/>--%>
    <%--<c:forEach var="entityField" items="${entityFields}">--%>
        <%--<c:set var="fieldValues" value="${fieldValuesMap.get(entityField)}"/>--%>
        <%--${entityField}--%>
        <%--<select name="${entityField}">--%>
            <%--<c:forEach var="fieldValue" items="${fieldValues}">--%>
                <%--<option value="${fieldValue}">${fieldValue}</option>--%>
            <%--</c:forEach>--%>
        <%--</select>--%>
    <%--</c:forEach>--%>
<%--</form>--%>

<form name="hotelForm" method="POST" action="controller">
    <input type="hidden" name="command" value="alterHotels"/>
    <input type="hidden" name="isAjaxRequest" value="false"/>
    <table>
        <tr>
            <th>country</th>
            <th>city</th>
            <th>name</th>
            <th>roomTypeStatus</th>
        </tr>
        <c:forEach var="roomHotel" items="${hotelList}">
            <c:set var="roomTypeId" value="${roomHotel.roomTypeId}"/>
            <tr>

                <%--<c:set var="fieldValues" value="${fieldValuesMap.get(hotelCountry)}"/>--%>
                <%--<select name="${entityField}">--%>
                    <%--<c:forEach var="fieldValue" items="${fieldValues}">--%>
                        <%--<option value="${fieldValue}">${fieldValue}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>

                <input type="hidden" name="hotelId" placeholder="${roomHotel.roomTypeId}" value="${roomHotel.roomTypeId}">
                <c:set var="hotelLocation" value="${roomHotel.hotelLocation}"/>
                <%--<td><input type="text" name="hotelCountry" placeholder="${roomHotel.country}" value="${roomHotel.country}"></td>--%>
                <td>
                    <c:set var="fieldValues" value="${fieldValuesMap.get('hotelCountry')}"/>
                    <select name="hotelCountry">
                        <c:forEach var="fieldValue" items="${fieldValues}">
                            <c:choose>
                                <c:when test="${fieldValue eq hotelLocation.country}">
                                    <option value="${fieldValue}" selected="selected">${fieldValue}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${fieldValue}">${fieldValue}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
                <td><input type="text" name="hotelCity" placeholder="${hotelLocation.city}" value="${hotelLocation.city}"></td>
                <td><input type="text" name="hotelName" placeholder="${roomHotel.name}" value="${roomHotel.name}"></td>
                <td><input type="text" name="hotelStatus" placeholder="${roomHotel.roomTypeStatus}" value="${roomHotel.roomTypeStatus}"></td>
                <td><button class="submitBtn" type="button">Засслать</button></td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="SubmitAll">
</form>
<button roomTypeId="addBtn" type="button" name="butt"> Добавить отель </button>
</body>
</html>
