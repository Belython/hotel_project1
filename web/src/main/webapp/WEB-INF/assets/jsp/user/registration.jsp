<!DOCTYPE html>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"  %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<html>
<head>
    <title>${registration_registration}</title>
</head>
<body>
<%@include file="header/header.jsp"%>
<form name="registrationForm" method="POST" action="controller">
    <input type="hidden" name="command" value="register"/>
    <p>${registration_enterYourData}:</p>
    <br/>
    <table>
        <tr>
            <td>${registration_firstName}:</td>
            <td><input type="text" name="firstName" value="" size="20"/></td>
        </tr>
        <tr>
            <td>${registration_lastName}:</td>
            <td><input type="text" name="lastName" value="" size="20"/></td>
        </tr>
        <tr>
            <td>${registration_email}:</td>
            <td><input type="text" name="email" value="" size="20"></td>
        </tr>
        <tr>
            <td>${registration_login}:</td>
            <td><input type="text" name="login" value="" size="20"/></td>
        </tr>
        <tr>
            <td>${registration_password}:</td>
            <td><input type="password" name="password" value="" size="20"/></td>
        </tr>
    </table>
    <input type="submit" value="${registration_register}"/>
    ${operationMessage}
    ${errorUserExists} <br/>
</form>

</body>
</html>