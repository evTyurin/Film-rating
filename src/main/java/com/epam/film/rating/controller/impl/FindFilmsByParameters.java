package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.impl.FilmDAOImpl;
import com.epam.film.rating.entity.film.Film;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FindFilmsByParameters implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            StringBuilder filmParameters = new StringBuilder();

            filmParameters.append("SELECT film.id, film.production_year, film.name, film.description, film.film_rating, film.review_amount, film_age_rating.age_rating, film_type.type FROM film JOIN film_age_rating ON film.age_rating_id=film_age_rating.id JOIN film_type ON film.type_id=film_type.id WHERE 1=1");

            if(request.getParameter("command") != null) {

                String year = request.getParameter("year");
                String age_rating = request.getParameter("age_rating");
                String film_type = request.getParameter("type");
                String genres[] = request.getParameterValues("genre");

                if (year != null && year != "") {
                    System.out.println("year = " + year);
                    filmParameters.append(" AND film.production_year = '").append(year).append("'");
                }

                if (age_rating != null) {
                    filmParameters.append(" AND film_age_rating.age_rating = '").append(age_rating).append("'");
                }

                if (film_type != null) {
                    filmParameters.append(" AND film_type.type = '").append(film_type).append("'");
                }

                if (genres != null) {
                    filmParameters.append(" AND film.id IN (SELECT film_genre.film_id FROM film_genre JOIN genre ON film_genre.genre_id=genre.id WHERE genre.genre in(");
                    for (int i = 0; i < genres.length; i++) {
                        filmParameters.append("'").append(genres[i]).append("'");
                        if (genres.length - i > 1) {
                            filmParameters.append(", ");
                        }
                    }
                    filmParameters.append(") GROUP BY film_genre.film_id HAVING count(*) ='");
                    filmParameters.append(genres.length);
                    filmParameters.append("')");
                }
                filmParameters.append(";");

                FilmDAOImpl filmDAO = new FilmDAOImpl();
                List<Film> films = new ArrayList<>();
                films = filmDAO.trygetAll(filmParameters.toString());

                for (Film film: films ) {
                    System.out.println("film name = " + film.getName());
                    int id = film.getId();

                    film.setGenre(filmDAO.getGenresById(id));
//TODO
//                    film.setPoster(filmDAO.getPostersById(id));

                    film.setCountryOfOrigin(filmDAO.getCountriesOfOriginById(id));
//TODO
//                    film.setTrailer(filmDAO.getTrailersById(id));
                }

                List<String> firstNames = new ArrayList<>();
                firstNames.add("first");
                request.setAttribute("firstNames", firstNames);

//                HttpSession session = request.getSession();
//                session.setAttribute("films", films);

                request.setAttribute("films", films);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/filmlist.jsp");
        dispatcher.forward(request, response);
    }
}
