package com.epam.film.rating.dao;

import java.sql.SQLException;

public interface FilmDAO {
    int addTrailer (String trailerPath, int filmId) throws SQLException, InterruptedException;
}