<!DOCTYPE html>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="../error/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" href="/assets/css/style.css"/>
<html>
<head>
    <title>${selectRoom_selectRoom}</title>
</head>
<body>
<%@include file="../user/header/header.jsp" %>

<form name="selectRoomsForm" method="POST" action="controller">
    <input type="hidden" name="command" value="makeBill"/>
    <table class="hotelList">
        <thead>
        <tr>
            <th>${selectRoom_roomTypeName}</th>
            <th>${selectRoom_available}</th>
            <th>${selectRoom_quantity}</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="roomType" items="${selectedHotelDto.roomTypeList}">
            <tr>
                <td>${roomType.roomTypeName}</td>
                <td>${selectedHotel.roomTypesCount.get(roomType)}</td>
                <td>
                    <select name="${roomType.name}">
                        <option value="0" selected="selected">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                    </select>
                </td>
                <%--<td><button class="selectRoomsBtn" type="button">${selectRoom_submitRooms}</button></td>--%>
                <td><input type="submit">${selectRoom_submitRooms}</input></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</form>

</body>
</html>
