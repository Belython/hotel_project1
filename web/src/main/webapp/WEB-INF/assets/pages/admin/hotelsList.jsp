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
<form roomTypeName="sortForm" method="POST" action="controller">
    <input type="hidden" roomTypeName="command" value="sortHotelsTable"/>
    <select roomTypeName="sortingOption">
        <option value="hotelName">Название отеля</option>
        <option value="hotelCountry">Страна</option>
        <option value="hotelCity">Город</option>
    </select>
    <select roomTypeName="sortingDirection">
        <option value="ascending">По возрастанию</option>
        <option value="descending">По убыванию</option>
    </select>
    <input type="submit" value="sort">
</form>

<%--<form roomTypeName="newEntityForm" method="POST" action="controller">--%>
    <%--<input type="hidden" roomTypeName="command" value="addNewEntity"/>--%>
    <%--<c:set var="entityFields" value="${fieldValuesMap.keySet()}"/>--%>
    <%--<c:forEach var="entityField" items="${entityFields}">--%>
        <%--<c:set var="fieldValues" value="${fieldValuesMap.get(entityField)}"/>--%>
        <%--${entityField}--%>
        <%--<select roomTypeName="${entityField}">--%>
            <%--<c:forEach var="fieldValue" items="${fieldValues}">--%>
                <%--<option value="${fieldValue}">${fieldValue}</option>--%>
            <%--</c:forEach>--%>
        <%--</select>--%>
    <%--</c:forEach>--%>
<%--</form>--%>

<form roomTypeName="hotelForm" method="POST" action="controller">
    <input type="hidden" roomTypeName="command" value="alterHotels"/>
    <input type="hidden" roomTypeName="isAjaxRequest" value="false"/>
    <table>
        <tr>
            <th>country</th>
            <th>city</th>
            <th>roomTypeName</th>
            <th>roomTypeStatus</th>
        </tr>
        <c:forEach var="roomHotel" items="${hotelList}">
            <c:set var="roomTypeId" value="${roomHotel.roomTypeId}"/>
            <tr>

                <%--<c:set var="fieldValues" value="${fieldValuesMap.get(hotelCountry)}"/>--%>
                <%--<select roomTypeName="${entityField}">--%>
                    <%--<c:forEach var="fieldValue" items="${fieldValues}">--%>
                        <%--<option value="${fieldValue}">${fieldValue}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>

                <input type="hidden" roomTypeName="hotelId" placeholder="${roomHotel.roomTypeId}" value="${roomHotel.roomTypeId}">
                <c:set var="hotelLocation" value="${roomHotel.hotelLocation}"/>
                <%--<td><input type="text" roomTypeName="hotelCountry" placeholder="${roomHotel.country}" value="${roomHotel.country}"></td>--%>
                <td>
                    <c:set var="fieldValues" value="${fieldValuesMap.get('hotelCountry')}"/>
                    <select roomTypeName="hotelCountry">
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
                <td><input type="text" roomTypeName="hotelCity" placeholder="${hotelLocation.city}" value="${hotelLocation.city}"></td>
                <td><input type="text" roomTypeName="hotelName" placeholder="${roomHotel.roomTypeName}" value="${roomHotel.roomTypeName}"></td>
                <td><input type="text" roomTypeName="hotelStatus" placeholder="${roomHotel.roomTypeStatus}" value="${roomHotel.roomTypeStatus}"></td>
                <td><button class="submitBtn" type="button">Засслать</button></td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="SubmitAll">
</form>
<button roomTypeId="addBtn" type="button" roomTypeName="butt"> Добавить отель </button>
</body>
</html>
