<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="Controller?command=findFilmsByParameters" method="post">
    <h3>Select film parameters</h3>
    <input type="hidden" name="command" value="show">
    <p>production year <input type="number" name="year" value="production year"/></p>
    <p>
        <input type="checkbox" name="age_rating" value="R6">6+
        <input type="checkbox" name="age_rating" value="R12"/>12+
        <input type="checkbox" name="age_rating" value="R14"/>14+
        <input type="checkbox" name="age_rating" value="R16"/>16+
        <input type="checkbox" name="age_rating" value="R18"/>18+
    </p>

    <p>
        <input type="checkbox" name="type" value="film"/>Film
        <input type="checkbox" name="type" value="serial"/>Serial
    </p>

    <p>
        <input type="checkbox" name="genre" value="drama"/>drama
        <input type="checkbox" name="genre" value="action"/>action
        <input type="checkbox" name="genre" value="sci-fi"/>sci-fi
        <input type="checkbox" name="genre" value="thriller"/>thriller
    </p>

    <p><input type="submit" value="press"/>
</form>

</body>
</html>
