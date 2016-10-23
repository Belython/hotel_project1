<!DOCTYPE html>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"/>
<html>
<head>
    <title>${account_account}</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/scripts/script.js"></script>
</head>
<body>
<%@include file="../user/header/header.jsp"%>

<form class="accountForm" name="billsForm" method="POST" action="controller">
    <input type="hidden" name="command" value="payBill"/>
    <input type="hidden" name="isAjaxRequest" value="false"/>
    <table class="billList" border="2px">
        <thead>
            <tr>
                <th>${account_country}</th>
                <th>${account_city}</th>
                <th>${account_hotelName}</th>
                <th>${account_roomTypeName}</th>
                <th>${account_maxPersons}</th>
                <th>${account_roomsAmount}</th>
                <th>${account_totalPersons}</th>
                <th>${account_checkInDate}</th>
                <th>${account_checkOutDate}</th>
                <th>${account_paymentAmount}</th>
                <th>${account_billStatus}</th>
            </tr>
        </thead>
        <%--<tbody>--%>
        <c:forEach var="bill" items="${billList}">
            <%--<input type="hidden" name="billId" value="${bill.billId}">--%>
            <c:set var="hotel" value="${bill.bookedHotel}"/>
            <c:set var="location" value="${hotel.location}"/>
            <c:set var="roomTypeMap" value="${bill.bookedRoomTypeMap}"/>
            <c:set var="roomTypeSet" value="${roomTypeMap.keySet()}"/>
            <c:set var="rtSize" value="${roomTypeSet.size()}"/>
            <tr>
                <td rowspan="${rtSize}">${location.country}</td>
                <td rowspan="${rtSize}">${location.city}</td>
                <td rowspan="${rtSize}">${hotel.hotelName}</td>
                <c:forEach var="roomType" items="${roomTypeSet}" end="0">
                    <td>${roomType.roomTypeName}</td>
                    <td>${roomType.maxPersons}</td>
                    <td>${roomTypeMap.get(roomType)}</td>
                </c:forEach>
                <td rowspan="${rtSize}">${bill.totalPersons}</td>
                <td rowspan="${rtSize}">${bill.checkInDate}</td>
                <td rowspan="${rtSize}">${bill.checkOutDate}</td>
                <td rowspan="${rtSize}">${bill.paymentAmount}</td>
                <td rowspan="${rtSize}">${bill.billStatus}</td>
                <c:if test="${bill.billStatus eq 'notPaid'}">
                    <td rowspan="${rtSize}">
                        <%--<input class="payBillBtn" type="button" name="billId" value="${bill.billId}">${account_payBill}</input>--%>
                        <a href="controller?command=payBill&billToPay=${bill.billId}">${account_payBill}</a>
                    </td>
                    <td rowspan="${rtSize}">
                        <%--<input class="refuseBill" type="button" name="refusedBillId" value="${bill.billId}">${account_refuseBill}</input>--%>
                        <a href="controller?command=refuseBill&billToRefuse=${bill.billId}">${account_refuseBill}</a>
                    </td>
                </c:if>
            </tr>
            <c:forEach var="roomType" items="${roomTypeSet}" begin="1">
                <tr>
                    <td>${roomType.roomTypeName}</td>
                    <td>${roomType.maxPersons}</td>
                    <td>${roomTypeMap.get(roomType)}</td>
                </tr>
            </c:forEach>
        </c:forEach>
        <%--</tbody>--%>
    </table>
</form>


</body>
</html>
