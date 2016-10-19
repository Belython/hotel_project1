<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="descriptor" value="${descriptor}"/>
<c:set var="entity" value="${descriptor.owner}"/>
<c:set var="fieldMap" value="${descriptor.fieldMap}"/>
<c:set var="fieldNameSet" value="${fieldMap.keySet()}"/>
<c:forEach var="fieldName" items="${fieldNameSet}">
    <c:set var="fieldDescriptor" value="${fieldMap.get(fieldName)}"/>
    <c:set var="fieldType" value="${fieldDescriptor.fieldType}"/>
    <c:choose>
        <c:when test="${fieldName.contains('Id') && fieldType.equals('freePrimitive')}">
            <input type="hidden" name="${fieldName}" value="${entity[fieldName]}">
        </c:when>
        <c:otherwise>
            <c:if test="${fieldType.equals('freePrimitive')}">
                <td>
                    <input type="text" name="${fieldName}" value="${entity[fieldName]}">
                </td>
            </c:if>
            <c:if test="${fieldType.equals('primitive')}">
                <td>
                    <select name="${fieldName}">
                        <c:forEach var="fieldValue" items="${fieldDescriptor.allowedValues}">
                            <c:choose>
                                <c:when test="${fieldValue.equals(entity[fieldName])}">
                                    <option value="${fieldValue}" selected="selected">${fieldValue}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${fieldValue}">${fieldValue}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
            </c:if>
            <c:if test="${fieldType.equals('entity')}">
                <c:set var="descriptor" value="${fieldDescriptor}" scope="request"/>
                <jsp:include page="/WEB-INF/assets/jsp/admin/tableRedactor/alterImport.jsp" flush="true"/>
            </c:if>
        </c:otherwise>
    </c:choose>
</c:forEach>