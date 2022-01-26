package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.impl.ReviewDAOImpl;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.service.Service;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LeaveReview implements Command {

    public final String REVIEW_TEXT = "reviewText";
    public final String FILL_MARK = "filmMark";
    public final String FILM_ID = "filmId";
    public final String USER_ID = "userId";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String reviewText = request.getParameter(REVIEW_TEXT).trim();
        int filmMark = Integer.parseInt(request.getParameter(FILL_MARK));

        HttpSession session = request.getSession();
        int userId = (Integer)session.getAttribute(USER_ID);

        String filmId = getFilmIdFromCookie(request, FILM_ID);

        Review review = new Review();
        review.setReview(reviewText);
        review.setMark(filmMark);
        review.setUserId(userId);

        String result = null;
        try {
            ServiceFactory instance = ServiceFactory.getInstance();
            Service service = instance.getService();
            if(service.addReview(review, Integer.parseInt(filmId))) {
                result = "success";
            } else {
                result = "not success";
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        response.setContentType("text/plain");
        response.getWriter().write(result);
    }

    private String getFilmIdFromCookie(HttpServletRequest request, String parameter) {
        Cookie[] cookies = request.getCookies();

        String parameterValue = null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(parameter)) {
                parameterValue = cookie.getValue();
                break;
            }
        }
        return parameterValue;
    }
}