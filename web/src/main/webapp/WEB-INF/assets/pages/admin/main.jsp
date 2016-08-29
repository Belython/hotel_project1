<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Добро пожаловать</title>
</head>
<body>
<h2>${user.firstName} ${user.lastName}</h2>
<h3>Вы вошли в систему как администратор</h3>
<h4>Выберите операцию:</h4>
<a href="controller?commandName=users">Показать список клиентов</a> <br/>
<a href="controller?commandName=operations">Показать список всех операций</a> <br/>
<a href="controller?commandName=gotounblock">Разблокировать счет клиента</a> <br/>
<a href="controller?commandName=logout">Выйти из системы</a> <br/>
</body>
</html>