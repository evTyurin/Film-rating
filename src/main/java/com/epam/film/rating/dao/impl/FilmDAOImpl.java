package com.epam.film.rating.dao.impl;

import com.epam.film.rating.connectionpool.ConnectionPool;
import com.epam.film.rating.dao.FilmDAO;
import com.epam.film.rating.dao.builder.InstanceBuilder;
import com.epam.film.rating.entity.film.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilmDAOImpl implements FilmDAO {

    public static final String ID = "id";
    public static final String PRODUCTION_YEAR = "production_year";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String FILM_RATING = "film_rating";
    public static final String REVIEW_AMOUNT = "review_amount";
//    public static final String AGE_RATING = "age_rating_id";
//    public static final String TYPE_ID = "type_id";
    public static final String AGE_RATING = "age_rating";
    public static final String TYPE = "type";

    public static final String GENRE = "genre";

    public static final String COUNTRY_OF_ORIGIN = "production_country";

    public static final String TRAILER_VIDEO = "trailer_video";
    public static final String POSTER_IMAGE = "poster_image";



    public static String GET_POSTERS_BY_ID = "select poster_image from film_poster where film_id=?;";
    public static String GET_TRAILERS_BY_ID = "select trailer_video from film_trailer where film_id=?;";
    public static String GET_GENRES_BY_ID = "SELECT genre.genre FROM genre JOIN film_genre ON genre.id=film_genre.genre_id WHERE film_genre.film_id=?;";
    public static String GET_COUNTRY_OF_ORIGIN_BY_ID = "SELECT country_of_origin.production_country FROM country_of_origin JOIN film_country_of_origin ON country_of_origin.id=film_country_of_origin.country_of_origin_id WHERE film_country_of_origin.film_id=?;";

    public static String GET_FILMS = "select * from film;";

    public static String ADD_POSTER = "insert into film_poster values (poster_image, film_id) values(?, ?) where film_id=?;";
    public static String ADD_TRAILER = "insert into film_trailer values (trailer_video, film_id) values(?, ?) where film_id=?;";
    public static String ADD_FILM = "insert into film values (production_year, name, description, type_id, age_rating) values(?, ?, ?, ?, ?);";


    public static String GET_FILM_BY_ID = "SELECT film.id, film.production_year, film.name, film.description, film.film_rating, film.review_amount, film_age_rating.age_rating, film_type.type FROM film JOIN film_age_rating ON film.age_rating_id=film_age_rating.id JOIN film_type ON film.type_id=film_type.id WHERE film.id=?";



    ConnectionPool connectable = ConnectionPool.getInstance();

    public FilmDAOImpl() {}


    public String createSQL (String year, String age_rating, String film_type, String genres[]) {
        StringBuilder filmParameters = new StringBuilder();

        filmParameters.append("SELECT film.id, film.production_year, film.name, film.description, film.film_rating, film.review_amount, film_age_rating.age_rating, film_type.type FROM film JOIN film_age_rating ON film.age_rating_id=film_age_rating.id JOIN film_type ON film.type_id=film_type.id WHERE 1=1");


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

        System.out.println(filmParameters.toString());
        return filmParameters.toString();
    }

    public List<Film> getFilmsByParameters(String year, String age_rating, String film_type, String genres[]) throws SQLException {
        int id;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = createSQL(year, age_rating, film_type, genres);
        try {
            List<Film> films= new ArrayList<>();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("nice");
                films.add(InstanceBuilder.buildFilm(resultSet));
            }

            for (Film film: films ) {
                id = film.getId();

                film.setGenre(getGenresById(id));
                film.setPoster(getPostersById(id));
                film.setCountryOfOrigin(getCountriesOfOriginById(id));
                film.setTrailer(getTrailersById(id));
            }
            return films;
        }catch (SQLException  e) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }


    public List<Film> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<Film> users = new ArrayList<>();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_FILMS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(InstanceBuilder.buildFilm(resultSet));
            }
            return users;
        }catch (SQLException  e) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    public List<String> getPostersById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<String> posters = new ArrayList<>();
            connection = connectable.getConnection();

            preparedStatement = connection.prepareStatement(GET_POSTERS_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                posters.add(resultSet.getString(FilmDAOImpl.POSTER_IMAGE));
            }
            if (posters.isEmpty()) { //TODO is it correct?
                posters = Collections.EMPTY_LIST;
            }
            return posters;
        }catch (SQLException  e) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    public List<String> getTrailersById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<String> trailers = new ArrayList<>();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_TRAILERS_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                trailers.add(resultSet.getString(FilmDAOImpl.TRAILER_VIDEO));
            }
            if (trailers.isEmpty()) { //TODO is it correct?
                trailers = Collections.EMPTY_LIST;
            }
            return trailers;
        }catch (SQLException  e) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    public List<String> getGenresById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<String> genres = new ArrayList<>();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_GENRES_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                genres.add(resultSet.getString(FilmDAOImpl.GENRE));
            }
            return genres;
        }catch (SQLException  e) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    public List<String> getCountriesOfOriginById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<String> countriesOfOrigin = new ArrayList<>();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_COUNTRY_OF_ORIGIN_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                countriesOfOrigin.add(resultSet.getString(FilmDAOImpl.COUNTRY_OF_ORIGIN));
            }
            if (countriesOfOrigin.isEmpty()) { //TODO is it correct?
                countriesOfOrigin = Collections.EMPTY_LIST;
            }
            return countriesOfOrigin;
        }catch (SQLException  e) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public Film getFilmById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Film film = new Film();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_FILM_BY_ID);

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                film = InstanceBuilder.buildFilm(resultSet);
            }
            return film;
        }catch (SQLException  e) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }


    @Override
    public int addTrailer (String trailerPath, int filmId) throws SQLException, InterruptedException {

        Connection connection = connectable.getConnection();
        PreparedStatement pr = null;
        try{
            pr = connection.prepareStatement(ADD_TRAILER);
            pr.setString(1, trailerPath);
            pr.setInt(2, filmId);
            pr.executeUpdate();
        } catch (SQLException e) {
            //TODO
        } finally {
            connectable.closeConnection(pr, connection);
        }
        return 1;
    }

    public int addPoster(String posterPath, int filmId) throws SQLException, InterruptedException {
        Connection connection = connectable.getConnection();
        PreparedStatement pr = null;
        try{
            pr = connection.prepareStatement(ADD_POSTER);
            pr.setString(1, posterPath);
            pr.setInt(2, filmId);
            pr.executeUpdate();
        } catch (SQLException e) {
            //TODO
        } finally {
            connectable.closeConnection(pr, connection);
        }
        return 1;
    }

    public int addFilm (Film film) throws SQLException, InterruptedException {
        Connection connection = connectable.getConnection();
        PreparedStatement pr = null;

        try{
            pr = connection.prepareStatement(ADD_FILM);

            pr.setInt(1, film.getProductionYear());
            pr.setString(2, film.getName());
            pr.setString(3, film.getDescription());
//            pr.setInt(4, film.getType().getId());
//            pr.setInt(5, film.getAgeRating().getId());

            pr.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            //TODO
        } finally {
            connectable.closeConnection(pr, connection);
        }
        return 1;

    }
}
