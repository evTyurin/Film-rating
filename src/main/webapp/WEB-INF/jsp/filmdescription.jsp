<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>Description of film</h3>

<c:if test="${permission eq true}" var="testcif">
    <button type="submit" name="makereview" value="true" scope="page">Show film</button><br/>
</c:if>

<c:if test="${makereview eq true}" var="testcif">
    <c:out value="Hello"/><br/>
</c:if>
</body>
</html>
