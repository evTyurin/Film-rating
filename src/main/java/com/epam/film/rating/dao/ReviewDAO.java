package com.epam.film.rating.dao;

import com.epam.film.rating.entity.ReviewDTO;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.entity.review.ReviewApproval;

import java.sql.SQLException;
import java.util.List;

public interface ReviewDAO {
    List<Review> getReviewById(int filmId, int userId) throws SQLException;

    List<ReviewDTO> getReviewsByFilmId(int filmId) throws SQLException;

    ReviewApproval getReviewApprovalById(int userId, int reviewId) throws SQLException, InterruptedException;

    int getLikesAmountById(int reviewId) throws SQLException;
}
