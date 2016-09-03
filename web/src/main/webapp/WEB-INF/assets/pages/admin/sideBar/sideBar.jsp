<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="../error/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form name="adminMainForm" method="POST" action="controller">
    <input type="radio" name="command" value="getHotels"> Получить список отелей
    <input type="radio" name="command" value="getUsers" > Получить список пользователей
    <input type="radio" name="command" value="getBills" > Получить список счетов
    <input type="submit" placeholder="ВВОД">
</form>

