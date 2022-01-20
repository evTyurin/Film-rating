package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.impl.FilmDAOImpl;
import com.epam.film.rating.dao.impl.ReviewDAOImpl;
import com.epam.film.rating.entity.ReviewDTO;
import com.epam.film.rating.entity.film.Film;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.service.Service;
import com.epam.film.rating.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FindFilmsByParameters implements Command {
    public final String parameterYear = "year";
    public final String parameterAgeRating = "age_rating";
    public final String parameterType = "type";
    public final String parameterGenre = "genre";

    public final String currentURL = "/WEB-INF/jsp/filmsByParameters.jsp";

    public final String attributeFilms = "films";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ServiceFactory instance = ServiceFactory.getInstance();
            Service service = instance.getService();

            String year = request.getParameter(parameterYear);
            String age_rating = request.getParameter(parameterAgeRating);
            String film_type = request.getParameter(parameterType);
            String genres[] = request.getParameterValues(parameterGenre);

            List<Film> films = service.getFilmsByParameters(year, age_rating, film_type, genres);
            request.setAttribute(attributeFilms, films);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(currentURL);
        dispatcher.forward(request, response);
    }
}
