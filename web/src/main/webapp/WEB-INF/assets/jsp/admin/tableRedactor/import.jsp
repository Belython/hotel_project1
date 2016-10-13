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
        <c:when test="${fieldName.contains('Id')}">
            <input type="hidden" name="${fieldName}" value="${entity[fieldName]}">
        </c:when>
        <c:otherwise>
            <td>
                <c:if test="${fieldType eq 'freePrimitive'}">
                    <c:if test="${not fieldName.contains('Id')}">
                        <input type="text" name="${fieldName}" value="${entity[fieldName]}">
                    </c:if>
                </c:if>
                <c:if test="${fieldType eq 'primitive'}">
                    <select name="${fieldName}">
                        <c:forEach var="fieldValue" items="${descriptor.allowedValues}">
                            <c:choose>
                                <c:when test="${fieldValue eq entity[fieldName]}">
                                    <option value="${fieldValue}" selected="selected">${fieldValue}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${fieldValue}">${fieldValue}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </c:if>
                <c:if test="${fieldType eq 'entity'}">
                    <jsp:param name="descriptor" value="${fieldMap.get(fieldName)}"/>
                    <jsp:include page="import.jsp" flush="true"/>
                </c:if>
            </td>
        </c:otherwise>
    </c:choose>
</c:forEach>