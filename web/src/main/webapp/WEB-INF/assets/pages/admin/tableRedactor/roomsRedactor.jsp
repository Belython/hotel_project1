<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>Новые номера</h1>
<form name="newRoomsForm" method="POST" action="controller">
    <input type="hidden" name="command" value="addNewRooms"/>
    <table>
        <thead>
            <tr>
                <th>hotel</th>
                <th>roomType</th>
                <th>roomNumber</th>
                <th>bookingStartDate</th>
                <th>bookingEndDate</th>
                <th>status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="room" items="${roomList}">
                <tr>
                    <input type="hidden" name="roomId" placeholder="${room.id}" value="${room.id}">
                    <c:set var="hotel" value="${room.hotel}"/>
                    <td>
                        <select name="roomHotel">
                            <c:forEach var="currentHotel" items="${hotelList}">
                                <option value="${currentHotel.id}">
                                    country ${currentHotel.location.country}
                                    city ${currentHotel.location.city}
                                    hotelName ${currentHotel.name}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                    <c:set var="roomType" value="${room.roomType}"/>
                    <td>
                        <select name="roomType">
                            <c:forEach var="currentRoomType" items="${roomTypeList}">
                                <option value="${currentRoomType.id}">
                                    name ${roomType.name}
                                    maxPersons ${roomType.maxPersons}
                                    price ${roomType.roomPricePerNight}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><input type="text" name="roomNumber" value="${room.roomNumber}"></td>
                    <td><input type="text" name="bookingStartDate" value="${room.bookingStartDate}"></td>
                    <td><input type="text" name="bookingEndDate" value="${room.bookingEndDate}"></td>
                    <td><input type="text" name="status" value="${room.status}"></td>
                    <td><button class="addBtn" type="button">Добавить номер</button></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</form>

<form name="sortRoomsForm" method="POST" action="controller">
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

<form name="alterRoomsForm" method="POST" action="controller">
    <input type="hidden" name="command" value="alterRooms"/>
    <input type="hidden" name="isAjaxRequest" value="false"/>
    <table>
        <thead>
            <tr>
                <th>hotel</th>
                <th>roomType</th>
                <th>roomNumber</th>
                <th>bookingStartDate</th>
                <th>bookingEndDate</th>
                <th>status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="room" items="${roomList}">
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
                    <td><button class="alterBtn" type="button">Изменить номер</button></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <input type="submit" value="Изменить все">
</form>


