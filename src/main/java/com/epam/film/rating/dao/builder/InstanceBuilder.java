package com.epam.film.rating.dao.builder;

import com.epam.film.rating.dao.impl.FilmDAOImpl;
import com.epam.film.rating.dao.impl.ReviewDAOImpl;
import com.epam.film.rating.dao.impl.UserDAOImpl;
import com.epam.film.rating.entity.film.*;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.entity.user.Role;
import com.epam.film.rating.entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstanceBuilder {


//    public static User buildUser(ResultSet resultSet) throws SQLException {
//        int id = resultSet.getInt(UserDAOImpl.ID);
//        String login = resultSet.getString(UserDAOImpl.LOGIN);
//        String password = resultSet.getString(UserDAOImpl.PASSWORD);
//        String nickname = resultSet.getString(UserDAOImpl.NICKNAME);
//        String name = resultSet.getString(UserDAOImpl.NAME);
//        String surname = resultSet.getString(UserDAOImpl.SURNAME);
//        String phoneNumber = resultSet.getString(UserDAOImpl.PHONE_NUMBER);
//        String eMail = resultSet.getString(UserDAOImpl.E_MAIL);
//        boolean isBanned = resultSet.getBoolean(UserDAOImpl.IS_BANNED);
//        double rating = resultSet.getDouble(UserDAOImpl.RATING);
//        String avatarImage = resultSet.getString(UserDAOImpl.AVATAR_IMAGE);
//        Role role = Role.getRole(resultSet.getInt(UserDAOImpl.ROLE_ID));
//        Status status = Status.getStatus((resultSet.getInt(UserDAOImpl.STATUS_ID)));
//
//        return new User(id, login, password, nickname, name, surname,
//                phoneNumber, eMail, isBanned, rating, avatarImage, role, status);
//    }

    public static User buildUser2(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(UserDAOImpl.ID);
        String login = resultSet.getString(UserDAOImpl.LOGIN);
        String password = resultSet.getString(UserDAOImpl.PASSWORD);
        String nickname = resultSet.getString(UserDAOImpl.NICKNAME);
        String name = resultSet.getString(UserDAOImpl.NAME);
        String surname = resultSet.getString(UserDAOImpl.SURNAME);
        String phoneNumber = resultSet.getString(UserDAOImpl.PHONE_NUMBER);
        String eMail = resultSet.getString(UserDAOImpl.E_MAIL);
        boolean isBanned = resultSet.getBoolean(UserDAOImpl.IS_BANNED);
        double rating = resultSet.getDouble(UserDAOImpl.RATING);
        String avatarImage = resultSet.getString(UserDAOImpl.AVATAR_IMAGE);
        Role role = Role.getRole(resultSet.getInt(UserDAOImpl.ROLE_ID));
//        Status status = Status.getStatus((resultSet.getInt(UserDAOImpl.STATUS_ID)));

        String status2 = resultSet.getString(UserDAOImpl.STATUS);
        System.out.println("Hello " + role);
//        System.out.println("Hello " + status);
        System.out.println("Hello " + status2);

        return new User(id, login, password, nickname, name, surname,
                phoneNumber, eMail, isBanned, rating, avatarImage, role, status2);
    }

//    public static Review buildReview(ResultSet resultSet, List<ReviewApproval> reviewApprovals, int userId, int filmId) throws SQLException {
//        int id = resultSet.getInt(ReviewDAOImpl.ID);
//        String review = resultSet.getString(ReviewDAOImpl.REVIEW);
//        int mark = resultSet.getInt(ReviewDAOImpl.MARK);
//        int likesAmount = resultSet.getInt(ReviewDAOImpl.LIKES_AMOUNT);
//        int dislikesAmount = resultSet.getInt(ReviewDAOImpl.DISLIKES_AMOUNT);
//        return new Review(id, review, mark, likesAmount, dislikesAmount, filmId, userId, reviewApprovals);
//    }

    public static Review buildReview(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ReviewDAOImpl.ID);
        String review = resultSet.getString(ReviewDAOImpl.REVIEW);
        int mark = resultSet.getInt(ReviewDAOImpl.MARK);
        int likesAmount = resultSet.getInt(ReviewDAOImpl.LIKES_AMOUNT);
        int dislikesAmount = resultSet.getInt(ReviewDAOImpl.DISLIKES_AMOUNT);
        return new Review(id, review, mark, likesAmount, dislikesAmount);
    }

//    public ReviewApproval buildReviewApproval(ResultSet resultSet) throws SQLException {
//        boolean isLiked = resultSet.getBoolean(ReviewDAOImpl.IS_LIKED);
//        return new ReviewApproval(isLiked);
//    }

//    public static Trailer buildTrailer(ResultSet resultSet) throws SQLException {
//        int id = resultSet.getInt(UserDAOImpl.ID);
//        String trailerImage = resultSet.getString(UserDAOImpl.TRAILER_VIDEO);
//        return new Trailer(id, trailerImage);
//    }
//
//    public static Poster buildPoster(ResultSet resultSet) throws SQLException {
//        int id = resultSet.getInt(UserDAOImpl.ID);
//        String posterImage = resultSet.getString(UserDAOImpl.POSTER_IMAGE);
//        return new Poster(id, posterImage);
//    }
            //TODO
//    public static Genre buildGenre(ResultSet resultSet) throws SQLException {
//        Genre genre = new Genre();
//
////        genre.setId(resultSet.getInt(FilmDAOImpl.ID));
//        genre.setGenre(resultSet.getString(FilmDAOImpl.GENRE));
//
//        return genre;
//    }

//    public static String buildGenre(ResultSet resultSet) throws SQLException {
//
//        return resultSet.getString(FilmDAOImpl.GENRE);
//    }
            //TODO
//    public static CountryOfOrigin buildCountryOfOrigin(ResultSet resultSet) throws SQLException {
//        CountryOfOrigin country = new CountryOfOrigin();
//
//        country.setId(resultSet.getInt(FilmDAOImpl.ID));
//        country.setCountryOfOrigin(resultSet.getString(FilmDAOImpl.COUNTRY_OF_ORIGIN));
//
//        return country;
//    }

//    public static Film buildFilm(ResultSet resultSet, List<Trailer> trailers, List<Poster> posters,
//                                 List<Genre> genres, List<CountryOfOrigin> countryOfOrigin) throws SQLException {
//
//        int id = resultSet.getInt(FilmDAOImpl.ID);
//        int productionYear = resultSet.getInt(FilmDAOImpl.PRODUCTION_YEAR);
//        String name = resultSet.getString(FilmDAOImpl.NAME);
//        String description = resultSet.getString(FilmDAOImpl.DESCRIPTION);
//        double filmRating = resultSet.getDouble(FilmDAOImpl.FILM_RATING);
//        int reviewAmount = resultSet.getInt(FilmDAOImpl.REVIEW_AMOUNT);
//        Type type = Type.getType(resultSet.getInt(FilmDAOImpl.TYPE_ID));
//        AgeRating ageRating = AgeRating.getAgeRating(resultSet.getInt(FilmDAOImpl.AGE_RATING));
//
//        return new Film(id, productionYear, name, description, filmRating, reviewAmount,
//                type, ageRating, genres, countryOfOrigin, posters, trailers);
//    }

//    public static Film buildFilm(ResultSet resultSet) throws SQLException {
//
//        int id = resultSet.getInt(FilmDAOImpl.ID);
//        int productionYear = resultSet.getInt(FilmDAOImpl.PRODUCTION_YEAR);
//        String name = resultSet.getString(FilmDAOImpl.NAME);
//        String description = resultSet.getString(FilmDAOImpl.DESCRIPTION);
//        double filmRating = resultSet.getDouble(FilmDAOImpl.FILM_RATING);
//        int reviewAmount = resultSet.getInt(FilmDAOImpl.REVIEW_AMOUNT);
//        Type type = Type.getType(resultSet.getInt(FilmDAOImpl.TYPE_ID));
//        AgeRating ageRating = AgeRating.getAgeRating(resultSet.getInt(FilmDAOImpl.AGE_RATING));
//
//        return new Film(id, productionYear, name, description, filmRating, reviewAmount,
//                type, ageRating);
//    }

//    public static Film buildFilm(ResultSet resultSet) throws SQLException {
//
//        int id = resultSet.getInt(FilmDAOImpl.ID);
//        int productionYear = resultSet.getInt(FilmDAOImpl.PRODUCTION_YEAR);
//        String name = resultSet.getString(FilmDAOImpl.NAME);
//        String description = resultSet.getString(FilmDAOImpl.DESCRIPTION);
//        double filmRating = resultSet.getDouble(FilmDAOImpl.FILM_RATING);
//        int reviewAmount = resultSet.getInt(FilmDAOImpl.REVIEW_AMOUNT);
//
//        return new Film(id, productionYear, name, description, filmRating, reviewAmount);
//    }

    public static Film buildFilm(ResultSet resultSet) throws SQLException {

        Film film = new Film();

        film.setId(resultSet.getInt(FilmDAOImpl.ID));
        film.setProductionYear(resultSet.getInt(FilmDAOImpl.PRODUCTION_YEAR));
        film.setName(resultSet.getString(FilmDAOImpl.NAME));
        film.setDescription(resultSet.getString(FilmDAOImpl.DESCRIPTION));
        film.setFilmRating(resultSet.getDouble(FilmDAOImpl.FILM_RATING));
        film.setReviewAmount(resultSet.getInt(FilmDAOImpl.REVIEW_AMOUNT));
        film.setAgeRating(resultSet.getString(FilmDAOImpl.AGE_RATING));
        film.setType(resultSet.getString(FilmDAOImpl.TYPE));

        return film;
    }






}
