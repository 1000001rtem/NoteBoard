<%--
  Created by IntelliJ IDEA.
  User: Artem Eremin
  Date: 04.01.2019
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>


<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>

    <style><%@include file="/WEB-INF/packages/loginStyles.css"%></style>
    <title>Document</title>
</head>
<body>
<div class="vladmaxi-top">
    <a href="/" target="_blank">Главная</a>
    <span class="right">
            <a href="/">
                <strong>Вернуться на главную</strong>
            </a>
        </span>
    <div class="clr"></div>
</div>

<form action="/login" method="post" class="login">
    <p>
        <label for="username">Логин:</label>
        <input type="text" name="username" id="username" value="name@example.com">
    </p>

    <p>
        <label for="password">Пароль:</label>
        <input type="password" name="password" id="password" value="4815162342">
    </p>

    <p class="login-submit">
        <button type="submit" class="login-button">Войти</button>
    </p>

    <p class="forgot-password"><a href="index.html">Забыл пароль?</a></p>
</form>
</body>
</html>
