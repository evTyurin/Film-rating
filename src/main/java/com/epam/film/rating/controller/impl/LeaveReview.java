package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.impl.FilmDAOImpl;
import com.epam.film.rating.dao.impl.ReviewDAOImpl;
import com.epam.film.rating.entity.ReviewDTO;
import com.epam.film.rating.entity.film.Film;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.entity.user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LeaveReview implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //TODO refactoring

        String reviewText = request.getParameter("reviewText").trim();
        int filmMark = Integer.parseInt(request.getParameter("filmMark"));

        HttpSession session = request.getSession();

        Cookie[] cookies = request.getCookies();

        String filmId = null;

        for (Cookie aCookie : cookies) {
            String name = aCookie.getName();

            if (name.equals("filmId")) {
                filmId = aCookie.getValue();
                break;
            }
        }
        int fILMid = Integer.parseInt(filmId);

        int userId = (Integer)session.getAttribute("userId");

        Review review = new Review();
        review.setReview(reviewText);
        review.setMark(filmMark);
        review.setUserId(userId);

        try {
            ReviewDAOImpl reviewDAO = new ReviewDAOImpl();
            System.out.println("result = " + reviewDAO.add(review, fILMid));
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }

        String greetings = "Hello " + reviewText;

        response.setContentType("text/plain");
        response.getWriter().write(greetings);
    }
}