<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="../../WEB-INF/assets/pages/error/error.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<%@include file="inputs/header.jsp"%>
<form roomTypeName="registrationForm" method="POST" action="controller">
    <input type="hidden" roomTypeName="command" value="registration"/>
    Введите ваши данные:<br/>
    <table>
        <tr>
            <td>Имя:</td>
            <td><input type="text" roomTypeName="firstName" value="" size="20"/></td>
        </tr>
        <tr>
            <td>Фамилия:</td>
            <td><input type="text" roomTypeName="lastName" value="" size="20"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" roomTypeName="email" value="" size="20"></td>
        </tr>
        <tr>
            <td>Логин:</td>
            <td><input type="text" roomTypeName="login" value="" size="20"/></td>
        </tr>
        <tr>
            <td>Пароль:</td>
            <td><input type="password" roomTypeName="password" value="" size="20"/></td>
        </tr>
    </table>
    ${operationMessage}
    ${errorUserExists} <br/>
    <input type="submit" value="Зарегистрировать"/>
</form>


</body>
</html>