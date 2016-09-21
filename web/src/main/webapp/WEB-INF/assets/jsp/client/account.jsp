<!DOCTYPE html>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="../error/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css"/>
<html>
<head>
    <title>${account_account}</title>
</head>
<body>
<%@include file="../user/header/header.jsp"%>
<form name="billsForm" method="POST" action="controller">
    <input type="hidden" name="command" value="payBill"/>
    ${account_billList}
    <br/>
    <c:forEach var="bill" items="${billList}">
        <c:forEach var="room" items="${bill.bookedRoomList}">
            Тип номера ${room.roomType.roomTypeName}
            Номер номера ${room.roomNumber}
            Статус ${bill.roomTypeStatus}
        </c:forEach>
        <c:if test="${bill.roomTypeStatus == 'notPaid'}">
            <input type="hidden" name="billToPay" value="${bill.roomTypeId}"/>
            <input type="submit" value="Оплатить"/>
        </c:if><br/>
    </c:forEach>
</form>

<form class="accountForm" name="billsForm" method="POST" action="controller">
    <input type="hidden" name="command" value="payBill"/>
    <input type="hidden" name="isAjaxRequest" value="false"/>
    <table class="billList">
        <thead>
            <tr>
                <th>${account_country}</th>
                <th>${account_city}</th>
                <th>${account_hotelName}</th>
                <th>${account_roomTypeName}</th>
                <th>${account_roomTypeMaxPersons}</th>
                <th>${account_totalPersons}</th>
                <th>${account_roomsAmount}</th>
                <th>${account_checkInDate}</th>
                <th>${account_checkOutDate}</th>
                <th>${account_paymentAmount}</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="bill" items="${billDtoList}">
                <input type="hidden" name="billId" value="${bill.billId}">
                <c:forEach var="room" items="${bill.bookedRoomList}">
                    <c:set var="hotel" value="${room.roomHotel}"/>
                    <c:set var="location" value="${hotel.hotelLocation}"/>
                    <c:set var="roomType" value="${room.roomType}"/>
                    <tr>
                        <td>${location.country}</td>
                        <td>${location.city}</td>
                        <td>${hotel.hotelName}</td>
                        <td>${roomType.roomTypeName}</td>
                        <td>${roomType.maxPersons}</td>
                        <td>${room.maxPersons}</td>
                        <td>${}</td>
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
                </c:forEach>
            </c:forEach>
        </tbody>
    </table>
    <input type="submit" value="Добавить все">
    <button class="addRowBtn" type="button">Добавить строку</button>
</form>


</body>
</html>
