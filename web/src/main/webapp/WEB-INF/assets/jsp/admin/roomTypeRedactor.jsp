<!DOCTYPE html>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<html>
<head>
    <title>${tableRedactor_roomsRedactor}</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/scripts/admin.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/plugins/tablesorter/tablesorter/jquery.tablesorter.min.js"></script>
</head>
    <body>
        <%@include file="../user/header/header.jsp"%>
        <%@include file="sideBar/sideBar.jsp"%>
        <%@include file="tableRedactor/tableRedactor.jsp"%>
    </body>
</html>
