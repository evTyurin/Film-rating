<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <tr>
        <c:forEach items="${films}" var="film" varStatus="status">
            <div>
                <td>${film.id}</td>
                <td>${film.name}</td>
                <td>${film.productionYear}</td>
                <td>
                    <c:forEach items="${film.genre}" var="genre">
                        ${genre}<br/>
                    </c:forEach>
                </td>
                <td>${film.description}</td>
                <td>
                    <form action="Controller?command=goToFilmDescriptionPage" id="${status.index}" method="post" >
                        <button type="submit" name="id" value="${film.id}">Show film</button>
                    </form>
                </td>
            </div>
        </c:forEach>
    </tr>
</body>
</html>
