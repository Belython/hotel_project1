<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>Новые номера</h1>
<form roomTypeName="newRoomsForm" method="POST" action="controller">
    <input type="hidden" roomTypeName="command" value="addNewRooms"/>
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
            <c:forEach var="room" items="${bookedRoomList}">
                <tr>
                    <input type="hidden" roomTypeName="roomId" placeholder="${room.roomTypeId}" value="${room.roomTypeId}">
                    <c:set var="roomHotel" value="${room.roomHotel}"/>
                    <td>
                        <select roomTypeName="roomHotel">
                            <c:forEach var="currentHotel" items="${hotelList}">
                                <option value="${currentHotel.roomTypeId}">
                                    country ${currentHotel.hotelLocation.country}
                                    city ${currentHotel.hotelLocation.city}
                                    hotelName ${currentHotel.roomTypeName}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                    <c:set var="roomType" value="${room.roomType}"/>
                    <td>
                        <select roomTypeName="roomType">
                            <c:forEach var="currentRoomType" items="${roomTypeList}">
                                <option value="${currentRoomType.roomTypeId}">
                                    roomTypeName ${roomType.roomTypeName}
                                    maxPersons ${roomType.maxPersons}
                                    price ${roomType.roomPricePerNight}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><input type="text" roomTypeName="roomNumber" value="${room.roomNumber}"></td>
                    <td><input type="text" roomTypeName="bookingStartDate" value="${room.bookingStartDate}"></td>
                    <td><input type="text" roomTypeName="bookingEndDate" value="${room.bookingEndDate}"></td>
                    <td><input type="text" roomTypeName="roomTypeStatus" value="${room.roomTypeStatus}"></td>
                    <td><button class="addBtn" type="button">Добавить номер</button></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</form>

<form roomTypeName="sortRoomsForm" method="POST" action="controller">
    <input type="hidden" roomTypeName="command" value="sortRooms"/>
    <select roomTypeName="sortingOption">
        <option value="hotelName">Название отеля</option>
        <option value="hotelCountry">Страна</option>
        <option value="hotelCity">Город</option>
        <option value="roomTypeName">Тип номера</option>
        <option value="roomNumber">Номер номера</option>
        <option value="bookingStartDate">Дата начала бронирования</option>
        <option value="bookingEndDate">Дата окончания бронирования</option>
    </select>
    <select roomTypeName="sortingDirection">
        <option value="ascending">По возрастанию</option>
        <option value="descending">По убыванию</option>
    </select>
    <input type="submit" value="Сортировать">
</form>

<form roomTypeName="alterRoomsForm" method="POST" action="controller">
    <input type="hidden" roomTypeName="command" value="alterRooms"/>
    <input type="hidden" roomTypeName="isAjaxRequest" value="false"/>
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
            <c:forEach var="room" items="${bookedRoomList}">
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
                    <td><button class="alterBtn" type="button">Изменить номер</button></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <input type="submit" value="Изменить все">
</form>


