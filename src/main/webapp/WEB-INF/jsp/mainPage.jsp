<%@ page language="java" contentType ="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="local" var="loc" />

    <fmt:message bundle="${loc}" key="local.message" var="message" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />

</head>
<body>
<h2>Hello username</h2>

<c:if test="${empty user}" var="testcif">
    <form action="Controller?command=goToLoginPage" method="post">
        <input type="submit" value="Log in" />
    </form>
    <form action="Controller?command=goToRegistrationPage" method="post">
        <input type="submit" value="Registration" />
    </form>
    <br/>
</c:if>

<c:if test="${not empty user}" var="testcif">
    <c:out value="Hello ${user.name }"/>
</c:if>

</body>
</html>
