<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<tr>
    <td>${loop.index + 1}</td>
    <c:set var="descriptor" value="${descriptor}" scope="request"/>
    <jsp:include page="/WEB-INF/assets/jsp/admin/tableRedactor/newImport.jsp" flush="true"/>
    <td>
        <button class="addEntityBtn" type="button">${tableRedactor_create}</button>
    </td>
    <td>
        <button class="removeRowBtn" type="button">${tableRedactor_remove}</button>
    </td>
</tr>