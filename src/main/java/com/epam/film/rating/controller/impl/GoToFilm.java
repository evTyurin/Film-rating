package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.impl.ReviewDAOImpl;
import com.epam.film.rating.entity.film.Film;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.entity.user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GoToFilm implements Command {
    public final String currentURL = "/WEB-INF/jsp/filmdescription.jsp";
    public final String parameterId = "id";
    public final String URL = "URL";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


        int filmId = Integer.parseInt(request.getParameter(parameterId));
        System.out.println(filmId);

        HttpSession session = request.getSession();
        session.setAttribute(URL, currentURL);

        User user = (User) session.getAttribute("user");

        try{

            ReviewDAOImpl reviewDAO = new ReviewDAOImpl();
            List<Review> reviews = reviewDAO.getReviewById(filmId, user.getId());
            if (reviews.isEmpty()) {
                request.setAttribute("permission", "true");
            } else {
                request.setAttribute("permission", "false");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(currentURL);
        dispatcher.forward(request, response);
    }
}