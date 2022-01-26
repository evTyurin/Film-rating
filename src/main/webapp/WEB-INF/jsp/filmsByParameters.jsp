<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<br>
<br>
<br>
<br>
<h3 align="center">List of films</h3>
<table>
    <tr>
        <div >
            <td>ID</td>
            <td>Film name</td>
            <td>Production year</td>
            <td>Genre</td>
            <td>Description</td>
            <td></td>
        </div>
    </tr>
        <c:forEach items="${films}" var="film" varStatus="status">
            <tr>
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
            </tr>
        </c:forEach>
</table>

    <br>
    <br>

<div align="center">
    <c:forEach items="${pageNumbers}" var="page" varStatus="status">
        <a href="Controller?${URL}&pageNumber=${page}">${page}</a>
    </c:forEach>
</div>


</body>
</html>
