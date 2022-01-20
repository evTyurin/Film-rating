<%@ page import="com.epam.film.rating.entity.film.Film" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--%>
    <script src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
    <script src="js/app-ajax.js" type="text/javascript"></script>
</head>
<body>

<%--<form>--%>
<%--    Enter Your Name: <input type="text" id="userName" />--%>
<%--</form>--%>
<%--<br>--%>
<%--<br>--%>

<%--<strong>Ajax Response</strong>:--%>
<%--<div id="ajaxGetUserServletResponse"></div>--%>







<%--<script>--%>
<%--    window.onload = function () {--%>
<%--        document.getElementById('showButton').onclick = function () {--%>
<%--            if(document.getElementById('form1').style.display == 'none') {--%>
<%--                document.getElementById('form1').style.display = 'block'--%>
<%--            } else {--%>
<%--                document.getElementById('form1').style.display = 'none'--%>
<%--            }--%>
<%--        };--%>

<%--    };--%>
<%--</script>--%>

<%--<script>--%>
<%--    $('#form').submit(function ()--%>
<%--    {--%>
<%--        $.ajax({--%>
<%--            type: "post",--%>
<%--            url: "books", //this is my servlet--%>
<%--            data: $(this).serialize()--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>


<h3>Description of film</h3>



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
        </div>
</tr>
<br>

<c:if test="${permission eq true}" var="testcif">
    <%--    <button type="submit" name="makereview" value="true" scope="page">Show film</button><br/>--%>
    <div id="review">
        <input type="button" value="Оставить отзыв" id='show_review_form' />
        <form style="display: none" id="review_form">
            Mark: <select name="user_profile_color_2" id="mark" required="required">
            <option value=""></option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select>

            Review :<input type="text"  id="review_text"><br>

<%--            <p type="submit" name="film" value="${film.id}">шИД = ${film.id}</p>--%>
            <c:set var="filmId" scope="request" value="${film.id}" />

            <input type="button" value="Оставить отзыв" id="submit_review" />
        </form>
    </div>
    <br>
</c:if>


<c:if test="${makereview eq true}" var="testcif">
    <c:out value="Hello"/><br/>
</c:if>

<%--<div id="review">--%>
<%--    <input type="button" value="Оставить отзыв" id='show_review_form' />--%>
<%--    <form style="display: none" id="review_form">--%>
<%--        Mark: <select name="user_profile_color_2" id="mark" required="required">--%>
<%--        <option value=""></option>--%>
<%--        <option value="1">1</option>--%>
<%--        <option value="2">2</option>--%>
<%--        <option value="3">3</option>--%>
<%--        <option value="4">4</option>--%>
<%--        <option value="5">5</option>--%>
<%--    </select>--%>

<%--        Review :<input type="text"  id="review_text"><br>--%>
<%--        <input type="button" value="Оставить отзыв" id="submit_review" />--%>
<%--    </form>--%>
<%--</div>--%>
<br>

<strong>Ajax Response</strong>:
<strong id="ajaxGetUserServletResponse"></strong>

<%--<input type="button" value="Вставить" id='showButton' />--%>
<%--<form id="form1" style="display: none;">--%>
<%--    <p>Review <input type="text" name="review"></p>--%>
<%--    <p>Password <input type="text" name="password" required></p>--%>
<%--    <select name="user_profile_color_2" required="required">--%>
<%--        <option value="">Выберите значение</option>--%>
<%--        <option value="1">1</option>--%>
<%--        <option value="2">2</option>--%>
<%--        <option value="3">3</option>--%>
<%--        <option value="4">4</option>--%>
<%--        <option value="5">5</option>--%>
<%--    </select>--%>
<%--</form>--%>
<br>
<br>
<br>
<%--<form id="form" action="books" method="post">--%>

<%--    <input type="submit" name="submit" value="Purchase">--%>
<%--    <input type="button" value="Call Servlet" name="Call Servlet" id="call"/>--%>
<%--</form>--%>


<%--style="display: none;"--%>


    <c:forEach items="${reviews}" var="review" varStatus="status">
        <div class="c" id="${status.index}">
            <tr>
                <td>${review.id}</td>
                <td>${review.review}</td>

                <sup class="n" >${review.likesAmount}</sup>
<%--                <c:if test="${review.id eq 1}" var="testcif">--%>
<%--                <button class="n" onclick="addLike(this)" type="submit" name="id" value="${review.id}" id="plus" style="background-color: bisque">+</button>--%>
<%--                </c:if>--%>

                <button class="n" onclick="updateLike(this)" type="submit" name="id" value="${review.id}" id="plus">+</button>

                <button class="n" onclick="updateDislike(this)" type="submit" name="id" value="${review.id}" id="minus">-</button>

                <sup class="n" >${review.dislikesAmount}</sup>
            </tr>
        </div>
    </c:forEach>



<br>
<br>
<br>
<%--<form method="post"><br><br>--%>

<%--    <p>Review <input type="text" name="review"></p>--%>
<%--    <select name="user_mark" required="required">--%>
<%--        <option value="">Выберите значение</option>--%>
<%--        <option value="1">1</option>--%>
<%--        <option value="2">2</option>--%>
<%--        <option value="3">3</option>--%>
<%--        <option value="4">4</option>--%>
<%--        <option value="5">5</option>--%>
<%--    </select>--%>

<%--    First name :<input type="text" name="fname" id="fname"/><br><br>--%>
<%--    Last name :<input type="text" name="lname" id="lname"/><br><br>--%>
<%--    Email :<input type="text" name="email" id="city_name" /><br><br>--%>
<%--    User name :<input type="text" name="email" id="email"/><br><br>--%>

<%--    First name :<input type="text" name="fname" id="fname"/><br><br>--%>
<%--    Last name :<input type="text" name="lname" id="lname"/><br><br>--%>
<%--    Email :<input type="text" name="email" id="city_name" /><br><br>--%>
<%--    User name :<input type="text" name="email" id="email"/><br><br>--%>
<%--    <input type="submit" value="submit" id="save_data"/>--%>
<%--</form>--%>


</body>
</html>
