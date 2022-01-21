package com.epam.film.rating.service;

import com.epam.film.rating.entity.ReviewDTO;
import com.epam.film.rating.entity.film.Film;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.entity.review.ReviewApproval;
import com.epam.film.rating.entity.user.User;

import java.sql.SQLException;
import java.util.List;

public interface Service {

    List<Review> getReviewById(int filmId, int userId) throws SQLException;

    List<ReviewDTO> getReviewsByFilmId(int filmId) throws SQLException;

    List<Film> getFilmsByParameters(String year, String age_rating, String film_type, String genres[]) throws SQLException;

    int getLikesAmountById(int reviewId) throws SQLException;

    ReviewApproval getReviewApprovalById(int userId, int reviewId) throws SQLException, InterruptedException;

    Film getFilmById(int id) throws SQLException;

    boolean updateReviewApprovalLike(boolean isLiked, int userId, int reviewId) throws SQLException, InterruptedException;

    boolean updateLikesAmountById(int likesAmount, int reviewId) throws SQLException, InterruptedException;

    boolean addReviewApproval (int userId, int reviewId, boolean isLiked, boolean isDisliked) throws SQLException, InterruptedException;

    int getDislikesAmountById(int reviewId) throws SQLException;

    boolean updateReviewApprovalDislike(boolean isDisliked, int userId, int reviewId) throws SQLException, InterruptedException;

    boolean updateDislikesAmountById(int dislikesAmount, int reviewId) throws SQLException, InterruptedException;

    boolean addReview(Review review, int filmId) throws SQLException, InterruptedException;

    User login(String login, String password) throws SQLException;
}
