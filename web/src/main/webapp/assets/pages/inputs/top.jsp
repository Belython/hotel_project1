<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="top">
    <div id="uesrForm">
        <ul class="userCenterNav">
            <li class="userCenterLanguage"></li>
            <c:choose>
                <c:when test="${empty user}">
                    <li class="userCenterLoginField">
                        <form id="login" name="loginForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="login"/>
                            <input type="hidden" name="currentPagePath" value="/assets/pages/index.jsp"/>
                            <h1>Форма входа</h1>
                            <fieldset id="inputs">
                                <input id="username" type="text" name="login" placeholder="Логин" autofocus required>
                                <input id="password" type="password" name="password" placeholder="Пароль" required>
                            </fieldset>
                            <fieldset id="actions">
                                <input type="submit" id="submit" value="ВОЙТИ">
                                <a href="">Забыли пароль?</a>
                                <a href="controller?command=gotoregistration">Регистрация</a>
                            </fieldset>
                                ${errorLoginOrPassword}<br/>
                        </form>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="userCenterAccount">
                        <a href="controller?command=goToAccount">Личный кабинет</a>
                    </li>
                    <li class="userCenterLogoutField">
                        <a href="controller?command=logout">Выйти из системы</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>

