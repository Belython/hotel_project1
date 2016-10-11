<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form name="adminMainForm" method="POST" action="controller">
    <input type="radio" name="command" value="goToHotelsRedactor"> ${sideBar_redactHotels}
    <br/>
    <input type="radio" name="command" value="goToLocationsRedactor" > ${sideBar_redactLocations}
    <br/>
    <input type="radio" name="command" value="goToUsersRedactor" > ${sideBar_redactUsers}
    <br/>
    <input type="radio" name="command" value="goToRoomTypesRedactor" > ${sideBar_redactRoomTypes}
    <br/>
    <input type="radio" name="command" value="goToRoomsRedactor" > ${sideBar_redactRooms}
    <br/>
    <input type="radio" name="command" value="goToBillsRedactor" > ${sideBar_redactBills}
    <br/>
    <input type="submit" value="${sideBar_submit}">
</form>

