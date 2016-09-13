<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form roomTypeName="adminMainForm" method="POST" action="controller">
    <input type="radio" roomTypeName="command" value="getHotels"> Получить список отелей
    <select roomTypeName="searchParameter">
        <option value="allHotels">Все отели</option>
        <option value="hotelCountry">Страна</option>
        <option value="hotelCity">Город</option>
    </select>
    <input type="text" roomTypeName="searchParameterValue" placeholder="Введите значение"/>
    <br/>
    <input type="radio" roomTypeName="command" value="getUsers" > Получить список пользователей
    <br/>
    <input type="radio" roomTypeName="command" value="getBills" > Получить список счетов
    <br/>
    <input type="radio" roomTypeName="command" value="getRooms" > Получить список номеров
    <br/>
    <input type="submit" placeholder="ВВОД">
</form>

