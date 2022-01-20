package com.epam.film.rating.dao;

import com.epam.film.rating.entity.film.Film;

import java.sql.SQLException;
import java.util.List;

public interface FilmDAO {
    int addTrailer (String trailerPath, int filmId) throws SQLException, InterruptedException;

    List<Film> getFilmsByParameters(String year, String age_rating, String film_type, String genres[]) throws SQLException;

    Film getFilmById(int id) throws SQLException;
}