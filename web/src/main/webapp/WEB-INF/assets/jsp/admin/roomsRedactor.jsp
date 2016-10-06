<!DOCTYPE html>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<html>
<head>
    <title>${roomsRedactor_roomsRedactor}</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/scripts/admin.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/scripts/tablesorter/tablesorter/jquery.tablesorter.js"></script>
</head>
<body>
<%@include file="../user/header/header.jsp"%>
<%@include file="sideBar/sideBar.jsp"%>

<h1>${roomsRedactor_newRooms}</h1>

<p id="operationMessage"></p>

<form class="redactorForm" name="addRoomsForm" method="POST" action="controller">
    <input type="hidden" name="command" value="alterRooms"/>
    <input type="hidden" name="subCommand" value="addNew"/>
    <input type="hidden" name="isAjaxRequest" value="false"/>
    <table class="newEntity" border="2px">
        <thead>
        <tr>
            <th>${roomsRedactor_roomHotel}</th>
            <th>${roomsRedactor_roomType}</th>
            <th>${roomsRedactor_roomNumber}</th>
            <%--<th>bookedDates</th>--%>
            <th>${roomsRedactor_roomStatus}</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <input type="hidden" name="roomId" value="-1">
            <td>
                <select name="hotelId">
                    <c:forEach var="hotel" items="${hotelList}">
                        <c:set var="hotelOption" value="
                            ${roomsRedactor_country}: ${hotel.hotelLocation.country},
                            ${roomsRedactor_city}: ${hotel.hotelLocation.city},
                            ${roomsRedactor_hotelName}: ${hotel.hotelName}"
                        />
                        <option value="${hotel.hotelId}">
                            ${hotelOption}
                        </option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select name="roomTypeId">
                    <c:forEach var="roomType" items="${roomTypeList}">
                        <option value="${roomType.roomTypeId}">
                            ${roomsRedactor_roomTypeName}: ${roomType.roomTypeName},
                            ${roomsRedactor_maxPersons}: ${roomType.maxPersons}.
                            ${roomsRedactor_roomPricePerNight}: ${roomType.pricePerNight}
                        </option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="text" name="roomNumber"></td>
            <%--<td><input type="text" name="bookingStartDate"></td>--%>
            <%--<td><input type="text" name="bookingEndDate"></td>--%>
            <td>
                <select name="roomStatus">
                    <c:forEach var="status" items="${statusList}">
                        <option value="${status}">${status}</option>
                    </c:forEach>
                </select>
            </td>
            <td><button class="addEntityBtn" type="button">${roomsRedactor_createRoom}</button></td>
            <td><button class="removeRowBtn" type="button">${roomsRedactor_removeRoom}</button></td>
        </tr>
        </tbody>
    </table>
    <button class="addRowBtn" type="button">${roomsRedactor_addRoom}</button>
    <input type="submit" value="Добавить все">
</form>

<form name="sortForm" method="POST" action="controller">
    <input type="hidden" name="command" value="sortRooms"/>
    <select name="sortingOption">
        <option value="hotelName">${roomsRedactor_hotelName}</option>
        <option value="hotelCountry">${roomsRedactor_country}</option>
        <option value="hotelCity">${roomsRedactor_city}</option>
        <option value="roomTypeName">${roomsRedactor_roomTypeName}</option>
        <option value="roomNumber">${roomsRedactor_roomNumber}</option>
        <%--<option value="bookingStartDate">Дата начала бронирования</option>--%>
        <%--<option value="bookingEndDate">Дата окончания бронирования</option>--%>
    </select>
    <select name="sortingDirection">
        <option value="ascending">${roomsRedactor_ascending}</option>
        <option value="descending">${roomsRedactor_descending}</option>
    </select>
    <input type="submit" value="${roomsRedactor_sort}">
</form>

<%--<form name="sortForm" method="POST" action="controller">--%>
    <%--<input type="hidden" name="command" value="sort"/>--%>
    <%--<select name="sortingOption">--%>
        <%--<c:set var="optionValues" value="${optionMap.keySet()}"/>--%>
        <%--<c:forEach var="option" items="${optionValues}">--%>
            <%--<option value=${option}>${optionMap.get(option)}</option>--%>
        <%--</c:forEach>--%>
    <%--</select>--%>
    <%--<select name="sortingDirection">--%>
        <%--<option value="ascending">${sorter_ascending}</option>--%>
        <%--<option value="descending">${sorter_descending}</option>--%>
    <%--</select>--%>
    <%--<input type="submit" value="${sorter_sort}">--%>
<%--</form>--%>


<form class="redactorForm" name="alterRoomsForm" method="POST" action="controller">
    <input type="hidden" name="command" value="alterRooms"/>
    <input type="hidden" name="subCommand" value="changeExisting"/>
    <input type="hidden" name="isAjaxRequest" value="false"/>
    <table id="mt" class="existingEntities  tablesorter" border="2px">
        <thead>
        <tr>
            <th>${roomsRedactor_country}</th>
            <th>${roomsRedactor_city}</th>
            <th>${roomsRedactor_hotelName}</th>
            <th>${roomsRedactor_roomTypeName}</th>
            <th>${roomsRedactor_maxPersons}</th>
            <th>${roomsRedactor_pricePerNight}</th>
            <th>${roomsRedactor_facilities}</th>
            <th>${roomsRedactor_roomNumber}</th>
            <%--<th>bookedDates</th>--%>
            <th>${roomsRedactor_roomStatus}</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="room" items="${roomDtoList}">
            <c:set var="dataMap" value="${entityMap.get(room.roomId)}"/>
            <tr>
                <input type="hidden" name="roomId" value="${room.roomId}">
                <c:set var="fieldSet" value="${dataMap.keySet()}"/>
                <c:forEach var="field" items="${fieldSet}">
                    <c:set var="data" value="${dataMap.get(field)}"/>
                    <td>
                        <c:choose>
                            <c:when test="${data.size() eq 0}">
                                <input type="text" name="${field}" value="${room[field]}">
                            </c:when>
                            <c:when test="${data.size() > 0}">
                                <select name="${field}">
                                    <c:forEach var="fieldValue" items="${dataMap.get(field)}">
                                        <c:choose>
                                            <c:when test="${fieldValue eq room[field]}">
                                                <option value="${fieldValue}" selected="selected">${fieldValue}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${fieldValue}">${fieldValue}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </c:when>
                        </c:choose>
                    </td>
                </c:forEach>
                <td><button class="alterEntityBtn" type="button">${roomsRedactor_alterRoom}</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="submit" value="${roomsRedactor_alterAllRooms}">
</form>

</body>
</html>
