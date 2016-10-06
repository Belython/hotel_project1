<!DOCTYPE html>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tcal/tcal.css"/>

<html>
<head>
    <title>${index_booking}</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/scripts/script.js"></script>

    <c:choose>
        <c:when test="${locale eq 'ru_RU'}">
            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/scripts/tcal/tcal_ru.js"></script>
        </c:when>
         <c:when test="${locale eq 'en_US'}">
            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/scripts/tcal/tcal_en.js"></script>
        </c:when>
    </c:choose>

</head>
<body>

<%@include file="header/header.jsp" %>
${index_searchParameters}
<form name="findForm" method="POST" action="controller">
    <input type="hidden" name="command" value="selectHotel"/>
    <label for="country">${index_country}</label>
    <select id="country" name="hotelCountry">
        <c:forEach var="country" items="${supportedCountries}">
            <option value="${country}">${country}</option>
        </c:forEach>
    </select>
    <label for="city">${index_city}</label>
    <select id="city" name="hotelCity">
        <c:forEach var="city" items="${supportedCities}">
            <option value="${city}">${city}</option>
        </c:forEach>
    </select>
    <label for="hotel">${index_hotel}</label>
    <select id="hotel" name="hotelName">
        <c:forEach var="hotel" items="${supportedHotels}">
            <option value="${hotel}">${hotel}</option>
        </c:forEach>
        <option value="allHotels">${index_allHotels}</option>
    </select>
    <br/>
    <label for="roomsAmount">${index_roomsAmount}</label>
    <select id="roomsAmount" name="totalRooms">
        <option value="1" selected="selected">1</option>
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
    <label for="personsAmount">${index_personsAmount}</label>
    <select id="personsAmount" name="totalPersons">
        <option value="1" selected="selected">1</option>
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
    <br/>
    <label for="checkInDate">${index_checkInDate}</label>
    <input id="checkInDate" class="tcal" type="text" name="checkInDate" value=""/>
    <label for="checkOutDate">${index_checkOutDate}</label>
    <input id="checkOutDate" class="tcal" type="text" name="checkOutDate" value=""/>
    <input type="submit" value="${index_searchRooms}">
</form>

</body>

</html>