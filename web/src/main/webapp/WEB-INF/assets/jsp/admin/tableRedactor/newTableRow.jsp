<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<tr>
    <td></td>
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