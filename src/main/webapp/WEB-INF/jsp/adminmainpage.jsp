<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="Controller?command=show" method="post">
    <h3>Select film parameters</h3>
    <input type="hidden" name="command" value="show">
    <p><input type="number" name="year" value="production year"/>y</p>
    <p><input type="checkbox" name="age_rating" value="R6"/>R6</p>
    <p><input type="checkbox" name="age_rating" value="R12"/>R12</p>
    <p><input type="checkbox" name="age_rating" value="R14"/>R14</p>
    <p><input type="checkbox" name="age_rating" value="R16"/>R16</p>
    <p><input type="checkbox" name="age_rating" value="R18"/>R18</p>

    <p><input type="checkbox" name="type" value="film"/>Film_type</p>
    <p><input type="checkbox" name="type" value="serial"/>Serial_type</p>

    <p><input type="checkbox" name="genre" value="drama"/>drama</p>
    <p><input type="checkbox" name="genre" value="action"/>action</p>

    <p><input type="submit" value="subm"/>
</form>

</body>
</html>
