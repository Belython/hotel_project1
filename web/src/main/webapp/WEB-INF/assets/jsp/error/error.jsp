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
    <title>${error_error}Ошибка</title>
</head>
<body>
${error_sorry}Извините, но в данный момент сервис не доступен: <br/>
${errorDatabase} <br/>
<a href="controller?command=goToMain">${error_goToMain}Перейти на главную страницу</a>
</body>
</html>