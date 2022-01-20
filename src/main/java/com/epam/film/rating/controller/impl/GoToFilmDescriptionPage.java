package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.ReviewDAO;
import com.epam.film.rating.dao.impl.FilmDAOImpl;
import com.epam.film.rating.dao.impl.ReviewDAOImpl;
import com.epam.film.rating.entity.ReviewDTO;
import com.epam.film.rating.entity.film.Film;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.entity.user.User;
import com.epam.film.rating.service.Service;
import com.epam.film.rating.service.ServiceFactory;

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


public class GoToFilmDescriptionPage implements Command {
    public final String currentURL = "/WEB-INF/jsp/filmDescription.jsp";
    public final String parameterId = "id";
    public final String URL = "URL";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    //TODO refactoring
        try{
            ServiceFactory instance = ServiceFactory.getInstance();
            Service service = instance.getService();

            int filmId = Integer.parseInt(request.getParameter(parameterId));
            Film film = service.getFilmById(filmId);

            HttpSession session = request.getSession();
            session.setAttribute(URL, currentURL);
            request.setAttribute("film", film);

            int userId = (Integer)session.getAttribute("userId");

            List<Review> reviews = service.getReviewById(filmId, userId);
            if (reviews.isEmpty()) {
                request.setAttribute("permission", "true");
            } else {
                request.setAttribute("permission", "false");
            }

            request.setAttribute("filmId", filmId);
            Cookie cookie = new Cookie("filmId", Integer.toString(filmId));
            response.addCookie(cookie);

            List<ReviewDTO> ReviewsDTO = service.getReviewsByFilmId(filmId);

            request.setAttribute("reviews", ReviewsDTO);

            RequestDispatcher dispatcher = request.getRequestDispatcher(currentURL);
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}