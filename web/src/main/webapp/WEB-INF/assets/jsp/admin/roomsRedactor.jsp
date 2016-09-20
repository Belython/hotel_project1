<!DOCTYPE html>
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
    <script type="text/javascript" src="/assets/scripts/admin.js"></script>
</head>
<body>
<%@include file="../user/header/header.jsp"%>
<%@include file="sideBar/sideBar.jsp"%>

<h1>Новые номера</h1>

<p id="operationMessage"></p>

<form class="redactorForm" name="addRoomsForm" method="POST" action="controller">
    <input type="hidden" name="command" value="alterRooms"/>
    <input type="hidden" name="subCommand" value="addNew"/>
    <input type="hidden" name="isAjaxRequest" value="false"/>
    <table class="newEntity">
        <thead>
        <tr>
            <th>roomHotel</th>
            <th>roomType</th>
            <th>roomNumber</th>
            <th>bookingStartDate</th>
            <th>bookingEndDate</th>
            <th>roomStatus</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <input type="hidden" name="roomId" value="-1">
            <td>
                <select name="hotelId">
                    <c:forEach var="hotel" items="${hotelSet}">
                        <c:set var="hotelOption" value="
                            country ${hotel.hotelLocation.country}
                            city ${hotel.hotelLocation.city}
                            hotelName ${hotel.hotelName}"
                        />
                        <option value="${hotel.hotelId}">
                            ${hotelOption}
                        </option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select name="roomTypeId">
                    <c:forEach var="roomType" items="${roomTypeSet}">
                        <option value="${roomType.roomTypeId}">
                            name ${roomType.roomTypeName}
                            maxPersons ${roomType.maxPersons}
                            price ${roomType.roomPricePerNight}
                        </option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="text" name="roomNumber"></td>
            <td><input type="text" name="bookingStartDate"></td>
            <td><input type="text" name="bookingEndDate"></td>
            <td>
                <select name="roomStatus">
                    <c:forEach var="status" items="${statusList}">
                        <option value="${status}">${status}</option>
                    </c:forEach>
                </select>
            </td>
            <td><button class="addEntityBtn" type="button">Добавить номер</button></td>
            <td><button class="removeRowBtn" type="button">Убрать номер</button></td>
        </tr>
        </tbody>
    </table>
    <input type="submit" value="Добавить все">
    <button class="addRowBtn" type="button">Добавить строку</button>
</form>

<form name="sortForm" method="POST" action="controller">
    <input type="hidden" name="command" value="sortRooms"/>
    <select name="sortingOption">
        <option value="hotelName">Название отеля</option>
        <option value="hotelCountry">Страна</option>
        <option value="hotelCity">Город</option>
        <option value="roomTypeName">Тип номера</option>
        <option value="roomNumber">Номер номера</option>
        <option value="bookingStartDate">Дата начала бронирования</option>
        <option value="bookingEndDate">Дата окончания бронирования</option>
    </select>
    <select name="sortingDirection">
        <option value="ascending">По возрастанию</option>
        <option value="descending">По убыванию</option>
    </select>
    <input type="submit" value="Сортировать">
</form>

<form class="redactorForm" name="alterRoomsForm" method="POST" action="controller">
    <input type="hidden" name="command" value="alterRooms"/>
    <input type="hidden" name="subCommand" value="changeExisting"/>
    <input type="hidden" name="isAjaxRequest" value="false"/>
    <table class="existingEntities">
        <thead>
        <tr>
            <th>roomHotel</th>
            <th>roomType</th>
            <th>roomNumber</th>
            <th>bookingStartDate</th>
            <th>bookingEndDate</th>
            <th>roomStatus</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="room" items="${roomDtoList}">
            <tr>
                <input type="hidden" name="roomId" value="${room.roomId}">
                <c:set var="currentHotel" value="${room.roomHotel}"/>
                <td>
                    <select name="hotelId">
                        <c:forEach var="hotel" items="${hotelSet}">
                            <c:choose>
                                <c:when test="${hotel eq currentHotel}">
                                    <option value="${hotel.hotelId}" selected="selected">
                                        country ${hotel.hotelLocation.country}
                                        city ${hotel.hotelLocation.city}
                                        hotelName ${hotel.hotelName}
                                    </option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${hotel.hotelId}">
                                        country ${hotel.hotelLocation.country}
                                        city ${hotel.hotelLocation.city}
                                        hotelName ${hotel.hotelName}
                                    </option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
                <c:set var="currentRoomType" value="${room.roomType}"/>
                <td>
                    <select name="roomTypeId">
                        <c:forEach var="roomType" items="${roomTypeSet}">
                            <c:choose>
                                <c:when test="${roomType eq currentRoomType}">
                                    <option value="${roomType.roomTypeId}" selected="selected">
                                        name ${roomType.roomTypeName}
                                        maxPersons ${roomType.maxPersons}
                                        price ${roomType.roomPricePerNight}
                                    </option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${roomType.roomTypeId}">
                                        name ${roomType.roomTypeName}
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
                    <%--<td><input type="text" name="roomStatus" value="${room.roomStatus}"></td>--%>
                <c:set var="currentRoomStatus" value="${room.roomStatus}"/>
                <td>
                    <select name="roomStatus">
                        <c:forEach var="status" items="${statusList}">
                            <c:choose>
                                <c:when test="${currentRoomStatus eq status}">
                                    <option value="${status}" selected="selected">${status}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${status}">${status}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
                <td><button class="alterEntityBtn" type="button">Изменить номер</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="submit" value="Изменить все">
</form>
</body>
</html>
