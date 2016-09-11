<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="../error/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<form name="roomForm" method="POST" action="controller">
    <input type="hidden" name="command" value="alterRooms"/>
    <input type="hidden" name="isAjaxRequest" value="false"/>
    <table>
        <tr>
            <th>hotel</th>
            <th>roomType</th>
            <th>roomNumber</th>
            <th>bookingStartDate</th>
            <th>bookingEndDate</th>
            <th>status</th>
        </tr>
        <c:forEach var="room" items="${roomList}">
            <%--<c:set var="roomId" value="${room.id}"/>--%>
            <tr>
                <input type="hidden" name="roomId" placeholder="${room.id}" value="${room.id}">
                <c:set var="hotel" value="${room.hotel}"/>
                <td>
                    <select name="roomHotel">
                        <c:forEach var="currentHotel" items="${hotelList}">
                            <c:choose>
                                <c:when test="${currentHotel eq hotel}">
                                    <option value="${currentHotel.id}" selected="selected">
                                        country ${currentHotel.location.country}
                                        city ${currentHotel.location.city}
                                        hotelName ${currentHotel.name}
                                    </option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${currentHotel.id}">
                                        country ${currentHotel.location.country}
                                        city ${currentHotel.location.city}
                                        hotelName ${currentHotel.name}
                                    </option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
                <c:set var="roomType" value="${room.roomType}"/>
                <td>
                    <select name="roomType">
                        <c:forEach var="currentRoomType" items="${roomTypeList}">
                            <c:choose>
                                <c:when test="${currentRoomType eq roomType}">
                                    <option value="${currentRoomType.id}" selected="selected">
                                        name ${roomType.name}
                                        maxPersons ${roomType.maxPersons}
                                        price ${roomType.roomPricePerNight}
                                    </option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${currentRoomType.id}">
                                        name ${roomType.name}
                                        maxPersons ${roomType.maxPersons}
                                        price ${roomType.roomPricePerNight}
                                    </option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
                <td><input type="text" name="roomNumber" value="${room.roomNumber}"></td>
                <td><input type="text" name="bookingStartDate" value="${room.bookingStartDate}"></td>
                <td><input type="text" name="bookingEndDate" value="${room.bookingEndDate}"></td>
                <td><input type="text" name="status" value="${room.status}"></td>
                <td><button class="submitBtn" type="button">Засслать</button></td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="SubmitAll">
</form>
<button id="addBtn" type="button" name="butt"> Добавить номер </button>
</body>
</html>
