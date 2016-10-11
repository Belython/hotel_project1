<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="header">
    <a href="controller?command=goToMain">${header_goToMain}</a>
    <p class="message">${operationMessage}</p>
    <div id="uesrForm">
        <ul class="userCenterNav">
            <li class="userCenterLanguage"></li>
            <c:choose>
                <c:when test="${empty user}">
                    <li class="userCenterLoginField">
                        <form id="login" name="loginForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="login"/>
                            <fieldset id="inputs">
                                <input id="username" type="text" name="login" placeholder="${header_login}" autofocus required>
                                <input id="password" type="password" name="password" placeholder="${header_password}" required>
                            </fieldset>
                            <fieldset id="actions">
                                <input type="submit" id="submit" value="${header_signIn}">
                                <a href="controller?command=goToRemindPassword">${header_forgotPassword}</a>
                                <a href="controller?command=goToRegistration">${header_register}</a>
                            </fieldset>
                            ${errorLoginOrPassword}<br/>
                        </form>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>${header_welcome} ${user.firstName}</li>
                    <li>
                        <c:if test="${user.role eq 'admin'}">
                            <p>${header_administrator}</p>
                            <a href="controller?command=goToAdminPage">${header_goToAdminPage}</a>
                        </c:if>
                    </li>
                    <li class="userCenterAccount">
                        <a href="controller?command=goToAccount">${header_goToAccount}</a>
                    </li>
                    <li class="userCenterLogoutField">
                        <a href="controller?command=logout">${header_signOut}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
    
    <div id="locale">
        <form name="langForm" method="POST" action="controller">
            <input type="hidden" name="command" value="setLocale"/>
            <label for="currentLocale">${header_selectLanguage}</label>
            <select id="currentLocale" name="locale">
                <c:set var="localeSet" value="${localeMap.keySet()}"/>
                <c:forEach var="localeElement" items="${localeSet}">
                    <c:choose>
                        <c:when test="${locale eq localeElement}">
                            <option value="${localeElement}" selected="selected">${localeMap.get(localeElement)}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${localeElement}">${localeMap.get(localeElement)}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <input type="submit" value="${header_submit}">
        </form>
    </div>

    <div id="currency">
        <form name="currencyForm" method="POST" action="controller">
            <input type="hidden" name="command" value="setCurrency"/>
            <label for="currentCurrency">${header_selectCurrency}</label>
            <select id="currentCurrency" name="currency">
                <c:set var="currencySet" value="${currencyMap.keySet()}"/>
                <c:forEach var="currencyElement" items="${currencySet}">
                    <c:choose>
                        <c:when test="${currency eq currencyElement}">
                            <option value="${currencyElement}" selected="selected">${currencyMap.get(currencyElement)}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${currencyElement}">${currencyMap.get(currencyElement)}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <input type="submit" value="${header_submit}">
        </form>
    </div>
</div>

