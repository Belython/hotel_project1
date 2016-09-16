<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="../error/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css"/>
<html>
<head>
    <title>Поиск номеров</title>
</head>
<body>
<%@include file="../../../../assets/pages/inputs/header.jsp" %>

<form name="searchForm" method="POST" action="controller">
    <input type="hidden" name="command" value="makeBill"/>
    <c:forEach var="roomType" items="${selectedHotel.roomTypeList}">
        <label for="${roomType.id}">${roomType.name}${selectedHotel.roomTypesCount.get(roomType)}</label>
        <select id="${roomType.roomTypeId}" name="${roomType.name}">
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
    </c:forEach>
    <input type="submit" value="Заказать">
    ${par}
    ${operationMessage}
    ${errorUserExists} <br/>
</form>


</body>
</html>
