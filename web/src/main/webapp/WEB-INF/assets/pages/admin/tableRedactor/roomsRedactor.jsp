<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>Новые номера</h1>
<form name="addRoomsForm" method="POST" action="controller">
    <input type="hidden" name="command" value="addRooms"/>
    <table>
        <thead>
            <tr>
                <th>roomHotel</th>
                <th>roomType</th>
                <th>roomNumber</th>
                <th>bookingStartDate</th>
                <th>bookingEndDate</th>
                <th>roomTypeStatus</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="room" items="${roomList}">
                <tr>
                    <input type="hidden" name="roomId" placeholder="${room.roomTypeId}" value="${room.roomTypeId}">
                    <c:set var="rHotel" value="${room.roomHotel}"/>
                    <td>
                        <select name="roomHotel">
                            <c:forEach var="currentHotel" items="${hotelList}">
                                <option value="${currentHotel.hotelId}">
                                    country ${currentHotel.hotelLocation.country}
                                    city ${currentHotel.hotelLocation.city}
                                    hotelName ${currentHotel.hotelName}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                    <c:set var="rType" value="${room.roomType}"/>
                    <td>
                        <select name="roomType">
                            <c:forEach var="currentRoomType" items="${roomTypeList}">
                                <option value="${currentRoomType.roomTypeId}">
                                    name ${rType.roomTypeName}
                                    maxPersons ${rType.maxPersons}
                                    price ${rType.roomPricePerNight}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><input type="text" name="roomNumber" value="${room.roomNumber}"></td>
                    <td><input type="text" name="bookingStartDate" value="${room.bookingStartDate}"></td>
                    <td><input type="text" name="bookingEndDate" value="${room.bookingEndDate}"></td>
                    <td><input type="text" name="roomTypeStatus" value="${room.roomStatus}"></td>
                    <td><button class="addBtn" type="button">Добавить номер</button></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <input type="submit" value="Подтвердить">
</form>

<form name="sortRoomsForm" method="POST" action="controller">
    <input type="hidden" name="command" value="sortRooms"/>
    <select name="sortingOption">
        <option value="hotelName">Название отеля</option>
        <option value="hotelCountry">Страна</option>
        <option value="hotelCity">Город</option>
        <option value="name">Тип номера</option>
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

<form name="alterRoomsForm" method="POST" action="controller">
    <input type="hidden" name="command" value="alterRooms"/>
    <input type="hidden" name="isAjaxRequest" value="false"/>
    <table>
        <thead>
            <tr>
                <th>roomHotel</th>
                <th>roomType</th>
                <th>roomNumber</th>
                <th>bookingStartDate</th>
                <th>bookingEndDate</th>
                <th>roomTypeStatus</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="room" items="${roomList}">
                <tr>
                    <input type="hidden" name="roomId" placeholder="${room.roomId}" value="${room.roomId}">
                    <c:set var="rHotel" value="${room.roomHotel}"/>
                    <td>
                        <select name="roomHotel">
                            <c:forEach var="currentHotel" items="${hotelList}">
                                <c:choose>
                                    <c:when test="${currentHotel eq rHotel}">
                                        <option value="${currentHotel.hotelId}" selected="selected">
                                            country ${currentHotel.hotelLocation.country}
                                            city ${currentHotel.hotelLocation.city}
                                            hotelName ${currentHotel.hotelName}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${currentHotel.roomTypeId}">
                                            country ${currentHotel.hotelLocation.country}
                                            city ${currentHotel.hotelLocation.city}
                                            hotelName ${currentHotel.hotelName}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <c:set var="rType" value="${room.roomType}"/>
                    <td>
                        <select name="roomType">
                            <c:forEach var="currentRoomType" items="${roomTypeList}">
                                <c:choose>
                                    <c:when test="${currentRoomType eq rType}">
                                        <option value="${currentRoomType.roomTypeId}" selected="selected">
                                            name ${rType.roomTypeName}
                                            maxPersons ${rType.maxPersons}
                                            price ${rType.roomPricePerNight}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${currentRoomType.roomTypeId}">
                                            name ${rType.roomTypeName}
                                            maxPersons ${rType.maxPersons}
                                            price ${rType.roomPricePerNight}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <td><input type="text" name="roomNumber" value="${room.roomNumber}"></td>
                    <td><input type="text" name="bookingStartDate" value="${room.bookingStartDate}"></td>
                    <td><input type="text" name="bookingEndDate" value="${room.bookingEndDate}"></td>
                    <td><input type="text" name="roomStatus" value="${room.roomStatus}"></td>
                    <td><button class="alterBtn" type="button">Изменить номер</button></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <input type="submit" value="Изменить все">
</form>


