<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Клиенты</title>
</head>
<body>
<table border="1">
    <tr bgcolor="#CCCCCC">
        <td align="center"><strong>Фамилия</strong></td>
        <td align="center"><strong>Имя</strong></td>
    </tr>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td><c:out value="${ user.lastName }"/></td>
            <td><c:out value="${ user.firstName }"/></td>
        </tr>
    </c:forEach>
</table>
<a href="controller?commandName=backadmin">Вернуться обратно</a>
<a href="controller?commandName=logout">Выйти из системы</a>
</body>
</html>