<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form name="adminMainForm" method="POST" action="controller">
    <input type="radio" name="command" value="redactHotels"> ${sideBar_redactHotels}
    <br/>
    <input type="radio" name="command" value="redactLocations" > ${sideBar_redactLocations}
    <br/>
    <input type="radio" name="command" value="redactUsers" > ${sideBar_redactUsers}
    <br/>
    <input type="radio" name="command" value="redactRoomTypes" > ${sideBar_redactRoomTypes}
    <br/>
    <input type="radio" name="command" value="redactRooms" > ${sideBar_redactRooms}
    <br/>
    <input type="radio" name="command" value="redactBills" > ${sideBar_redactBills}
    <br/>
    <input type="submit" value="${sideBar_submit}">
</form>

