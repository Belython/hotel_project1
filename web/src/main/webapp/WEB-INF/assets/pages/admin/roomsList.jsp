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

<form roomTypeName="roomForm" method="POST" action="controller">
    <input type="hidden" roomTypeName="command" value="alterRooms"/>
    <input type="hidden" roomTypeName="isAjaxRequest" value="false"/>
    <table>
        <tr>
            <th>roomHotel</th>
            <th>roomType</th>
            <th>roomNumber</th>
            <th>bookingStartDate</th>
            <th>bookingEndDate</th>
            <th>roomTypeStatus</th>
        </tr>
        <c:forEach var="room" items="${bookedRoomList}">
            <%--<c:set var="roomId" value="${room.roomTypeId}"/>--%>
            <tr>
                <input type="hidden" roomTypeName="roomId" placeholder="${room.roomTypeId}" value="${room.roomTypeId}">
                <c:set var="roomHotel" value="${room.roomHotel}"/>
                <td>
                    <select roomTypeName="roomHotel">
                        <c:forEach var="currentHotel" items="${hotelList}">
                            <c:choose>
                                <c:when test="${currentHotel eq roomHotel}">
                                    <option value="${currentHotel.roomTypeId}" selected="selected">
                                        country ${currentHotel.hotelLocation.country}
                                        city ${currentHotel.hotelLocation.city}
                                        hotelName ${currentHotel.roomTypeName}
                                    </option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${currentHotel.roomTypeId}">
                                        country ${currentHotel.hotelLocation.country}
                                        city ${currentHotel.hotelLocation.city}
                                        hotelName ${currentHotel.roomTypeName}
                                    </option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
                <c:set var="roomType" value="${room.roomType}"/>
                <td>
                    <select roomTypeName="roomType">
                        <c:forEach var="currentRoomType" items="${roomTypeList}">
                            <c:choose>
                                <c:when test="${currentRoomType eq roomType}">
                                    <option value="${currentRoomType.roomTypeId}" selected="selected">
                                        roomTypeName ${roomType.roomTypeName}
                                        maxPersons ${roomType.maxPersons}
                                        price ${roomType.roomPricePerNight}
                                    </option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${currentRoomType.roomTypeId}">
                                        roomTypeName ${roomType.roomTypeName}
                                        maxPersons ${roomType.maxPersons}
                                        price ${roomType.roomPricePerNight}
                                    </option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
                <td><input type="text" roomTypeName="roomNumber" value="${room.roomNumber}"></td>
                <td><input type="text" roomTypeName="bookingStartDate" value="${room.bookingStartDate}"></td>
                <td><input type="text" roomTypeName="bookingEndDate" value="${room.bookingEndDate}"></td>
                <td><input type="text" roomTypeName="roomTypeStatus" value="${room.roomTypeStatus}"></td>
                <td><button class="submitBtn" type="button">Засслать</button></td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="SubmitAll">
</form>
<button roomTypeId="addBtn" type="button" roomTypeName="butt"> Добавить номер </button>
</body>
</html>
