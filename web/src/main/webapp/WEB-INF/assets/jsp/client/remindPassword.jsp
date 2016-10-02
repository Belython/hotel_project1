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
    <title>${remindPassword_forgotPassword}</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/scripts/script.js"></script>
</head>
<body>
<%@include file="../user/header/header.jsp"%>

<form name="remindPasswordForm" method="POST" action="controller">
    <input type="hidden" name="command" value="remindPassword">
    <label for="emailInput">${remindPassword_enterEmail}</label>
    <input id="emailInput" type="text" name="email">
    <input type="submit" value="${remindPassword_remindPassword}">
</form>

</body>
</html>
