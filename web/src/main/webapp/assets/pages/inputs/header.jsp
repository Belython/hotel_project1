<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div roomTypeId="header">
    <a href="controller?command=goToMain">${goToMain}</a>
    <div roomTypeId="uesrForm">
        <ul class="userCenterNav">
            <li class="userCenterLanguage"></li>
            <c:choose>
                <c:when test="${empty client}">
                    <li class="userCenterLoginField">
                        <form roomTypeId="login" name="loginForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="login"/>
                            <input type="hidden" name="currentPagePath" value="/assets/pages/index.jsp"/>
                            <h1>Форма входа</h1>
                            <fieldset roomTypeId="inputs">
                                <input roomTypeId="username" type="text" name="login" placeholder="Логин" autofocus required>
                                <input roomTypeId="password" type="password" name="password" placeholder="Пароль" required>
                            </fieldset>
                            <fieldset roomTypeId="actions">
                                <input type="submit" roomTypeId="submit" value="ВОЙТИ">
                                <a href="">Забыли пароль?</a>
                                <a href="controller?command=gotoregistration">${register}reg</a>
                            </fieldset>
                                ${errorLoginOrPassword}<br/>
                        </form>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>Добро пожаловать ${client.firstName}</li>
                    <li>
                        <c:if test="${client.role eq 'admin'}">
                            <p>Вы зашли как администратор</p>
                            <a href="controller?command=goToAdminPage">Пошалим?</a>
                        </c:if>
                    </li>
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
    <div roomTypeId="locale">
        <form name="langForm" method="POST" action="controller">
            <input type="hidden" name="command" value="setLocale"/>
            <label for="currentLocale">Локаль</label>
            <select roomTypeId="currentLocale" name="locale">
                <c:forEach var="localeElement" items="${applicationScope.get('localeList')}">
                    <c:choose>
                        <c:when test="${sessionScope.get('locale') eq localeElement}">
                            <option value=${localeElement} selected="selected">${localeElement}</option>
                        </c:when>
                        <c:otherwise>
                            <option value=${localeElement}>${localeElement}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <input type="submit" value="Ввод">
        </form>
    </div>
    ${operationMessage}
</div>

