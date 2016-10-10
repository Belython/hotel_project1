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
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/scripts/tablesorter/tablesorter/jquery.tablesorter.js"></script>
</head>
<body>
<%@include file="../user/header/header.jsp"%>
<%@include file="sideBar/sideBar.jsp"%>

<%--<h1>${roomsRedactor_newRooms}</h1>--%>

<p id="operationMessage"></p>

<form class="newEntityForm entityForm" name="newEntityForm" method="POST" action="controller">
    <input type="hidden" name="command" value="alterRooms"/>
    <input type="hidden" name="subCommand" value="addNew"/>
    <input type="hidden" name="isAjaxRequest" value="false"/>
    <table class="newEntity" border="2px">
        <thead>
            <tr>
                <c:forEach var="colName" items="${colNameList}">
                    <th>${colName}</th>
                </c:forEach>
            </tr>
        </thead>
        <tbody>
        <c:set var="entitySet" value="${entityMap.keySet()}"/>
        <c:set var="entity" value="${entitySet.iterator().next()}"/>
            <c:set var="dataMap" value="${entityMap.get(entity)}"/>
            <tr>
                <c:set var="fieldSet" value="${dataMap.keySet()}"/>
                <c:forEach var="field" items="${fieldSet}" begin="1">
                    <c:set var="data" value="${dataMap.get(field)}"/>
                    <c:choose>
                        <c:when test="${field.contains('Id')}">
                            <input type="hidden" name="${field}" value="${entity[field]}">
                        </c:when>
                        <c:otherwise>
                            <td>
                                <c:if test="${data.size() eq 0}">
                                    <c:if test="${not field.contains('Id')}">
                                        <input type="text" name="${field}">
                                    </c:if>
                                </c:if>
                                <c:if test="${data.size() > 0}">
                                    <select name="${field}">
                                        <c:forEach var="fieldValue" items="${dataMap.get(field)}">
                                            <c:choose>
                                                <c:when test="${fieldValue eq entity[field]}">
                                                    <option value="${fieldValue}" selected="selected">${fieldValue}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${fieldValue}">${fieldValue}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </c:if>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <td><button class="addEntityBtn" type="button">${tableRedactor_create}</button></td>
                <td><button class="removeRowBtn" type="button">${tableRedactor_remove}</button></td>
            </tr>
        </tbody>
    </table>
    <button class="addRowBtn" type="button">${tableRedactor_add}</button>
    <input type="submit" value="${tableRedactor_addAll}">
</form>

<form class="alterEntityForm entityForm" name="alterEntityForm" method="POST" action="controller">
    <input type="hidden" name="command" value="alterRooms"/>
    <input type="hidden" name="subCommand" value="changeExisting"/>
    <input type="hidden" name="isAjaxRequest" value="false"/>
    <table id="mt" class="existingEntities  tablesorter" border="2px">
        <thead>
            <tr>
                <c:forEach var="colName" items="${colNameList}">
                    <th>${colName}</th>
                </c:forEach>
            </tr>
        </thead>
        <tbody>
        <c:set var="entitySet" value="${entityMap.keySet()}"/>
        <c:forEach var="entity" items="${entitySet}">
            <c:set var="dataMap" value="${entityMap.get(entity)}"/>
            <tr>
                <c:set var="fieldSet" value="${dataMap.keySet()}"/>
                <c:forEach var="field" items="${fieldSet}">
                    <c:set var="data" value="${dataMap.get(field)}"/>
                    <c:choose>
                        <c:when test="${field.contains('Id')}">
                            <input type="hidden" name="${field}" value="${entity[field]}">
                        </c:when>
                        <c:otherwise>
                            <td>
                                <c:if test="${data.size() eq 0}">
                                    <c:if test="${not field.contains('Id')}">
                                        <input type="text" name="${field}" value="${entity[field]}">
                                    </c:if>
                                </c:if>
                                <c:if test="${data.size() > 0}">
                                    <select name="${field}">
                                        <c:forEach var="fieldValue" items="${dataMap.get(field)}">
                                            <c:choose>
                                                <c:when test="${fieldValue eq entity[field]}">
                                                    <option value="${fieldValue}" selected="selected">${fieldValue}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${fieldValue}">${fieldValue}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </c:if>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <td><button class="alterEntityBtn" type="button">${tableRedactor_alter}</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="submit" value="${tableRedactor_alterAll}">
</form>

</body>
</html>
