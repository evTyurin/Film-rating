package com.epam.film.rating.dao.impl;

import com.epam.film.rating.connectionpool.ConnectionPool;
import com.epam.film.rating.dao.ReviewDAO;
import com.epam.film.rating.dao.builder.InstanceBuilder;
import com.epam.film.rating.entity.ReviewDTO;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.entity.review.ReviewApproval;
import com.epam.film.rating.entity.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReviewDAOImpl implements ReviewDAO {
    public static final String ID = "id";
    public static final String REVIEW = "review";
    public static final String MARK = "mark";
    public static final String LIKES_AMOUNT = "likes_amount";
    public static final String DISLIKES_AMOUNT = "dislikes_amount";
    public static final String IS_LIKED = "is_liked";
    public static final String IS_DISLIKED = "is_disliked";

    ConnectionPool connectable = ConnectionPool.getInstance();

    public ReviewDAOImpl() {};

    public static String GET_REVIEWS_BY_ID = "select review.id, review.review, review.mark, review.likes_amount, review.dislikes_amount from review WHERE review.film_id=? AND review.users_id=?;";

    public static String GET_REVIEWS_BY_FILM_ID = "select review.id, review.review, review.mark, review.likes_amount, review.dislikes_amount, user.nickname, user.rating, user.avatar_image, user_status.status FROM user JOIN review ON user.id=review.users_id JOIN user_status ON user_status.id=user.user_status_id WHERE review.film_id=?;";

    public static String GET_REVIEWS = "select * from review;";
    public static String ADD_REVIEW = "insert into review (review, mark, film_id, users_id) values(?, ?, ?, ?);";

    public static String GET_REVIEW_DISLIKE_AMOUNT_BY_ID = "SELECT review.dislikes_amount from review WHERE review.id=?;";
    public static String GET_REVIEW_LIKE_AMOUNT_BY_ID = "SELECT review.likes_amount from review WHERE review.id=?;";

    public static String UPDATE_LIKES_AMOUNT_BY_ID = "update review set review.likes_amount=? where review.id=?;";
    public static String UPDATE_DISLIKES_AMOUNT_BY_ID = "update review set review.dislikes_amount=? where review.id=?;";

//    public static String GET_REVIEW_APPROVAL = "SELECT user_review_approval.is_liked FROM user_review_approval WHERE user_review_approval.users_id=? AND user_review_approval.review_id=?;";
    public static String GET_REVIEW_APPROVAL = "SELECT user_review_approval.is_liked, user_review_approval.is_disliked FROM user_review_approval WHERE user_review_approval.users_id=? AND user_review_approval.review_id=?;";

    public static String ADD_REVIEW_APPROVAL = "insert into user_review_approval (users_id, review_id, is_liked, is_disliked) values(?, ?, ?, ?);";

    public static String UPDATE_REVIEW_APPROVAL_LIKE = "update user_review_approval set user_review_approval.is_liked=? WHERE user_review_approval.users_id=? AND user_review_approval.review_id=?;";
    public static String UPDATE_REVIEW_APPROVAL_DISLIKE = "update user_review_approval set user_review_approval.is_disliked=? WHERE user_review_approval.users_id=? AND user_review_approval.review_id=?;";

    public List<Review> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<Review> users = new ArrayList<>();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_REVIEWS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(InstanceBuilder.buildReview(resultSet));
            }
            return users;
        }catch (SQLException  sqlE) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    public boolean updateDislikesAmountById(int dislikesAmount, int reviewId) throws SQLException, InterruptedException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_DISLIKES_AMOUNT_BY_ID);
            preparedStatement.setInt(1, dislikesAmount);
            preparedStatement.setInt(2, reviewId);
            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            //TODO
        } finally {
            connectable.closeConnection(preparedStatement, connection);
        }
        return false;
    }

    public ReviewApproval getReviewApprovalById(int userId, int reviewId) throws SQLException, InterruptedException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_REVIEW_APPROVAL);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, reviewId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return InstanceBuilder.buildReviewApproval(resultSet);
            }
        } catch (SQLException e) {
            //TODO
        } finally {
            connectable.closeConnection(preparedStatement, connection);
        }
        return null; //TODO
    }

    public ReviewApproval getReviewApprovalLikeById(int userId, int reviewId) throws SQLException, InterruptedException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_REVIEW_APPROVAL);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, reviewId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return InstanceBuilder.buildReviewApproval(resultSet);
            }
        } catch (SQLException e) {
            //TODO
        } finally {
            connectable.closeConnection(preparedStatement, connection);
        }
        return null; //TODO
    }

    public boolean addReviewApproval (int userId, int reviewId, boolean isLiked, boolean isDisliked) throws SQLException, InterruptedException {
        Connection connection = connectable.getConnection();
        PreparedStatement pr = null;

        try{
            pr = connection.prepareStatement(ADD_REVIEW_APPROVAL);

            pr.setInt(1, userId);
            pr.setInt(2, reviewId);
            pr.setBoolean(3, isLiked);
            pr.setBoolean(4, isDisliked);

            if(pr.executeUpdate() == 1) {
                return true;
            };
        } catch (SQLException | NullPointerException e) {
            //TODO
        } finally {
            connectable.closeConnection(pr, connection);
        }
        return false;
    }

    public boolean updateReviewApprovalLike(boolean isLiked, int userId, int reviewId) throws SQLException, InterruptedException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_REVIEW_APPROVAL_LIKE);
            preparedStatement.setBoolean(1, isLiked);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, reviewId);
            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            //TODO
        } finally {
            connectable.closeConnection(preparedStatement, connection);
        }
        return false;
    }

    public boolean updateReviewApprovalDislike(boolean isDisliked, int userId, int reviewId) throws SQLException, InterruptedException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_REVIEW_APPROVAL_DISLIKE);
            preparedStatement.setBoolean(1, isDisliked);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, reviewId);
            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            //TODO
        } finally {
            connectable.closeConnection(preparedStatement, connection);
        }
        return false;
    }

    public boolean updateLikesAmountById(int likesAmount, int reviewId) throws SQLException, InterruptedException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_LIKES_AMOUNT_BY_ID);
            preparedStatement.setInt(1, likesAmount);
            preparedStatement.setInt(2, reviewId);
            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            //TODO
        } finally {
            connectable.closeConnection(preparedStatement, connection);
        }
        return false;
    }

    public List<Review> getReviewById(int filmId, int userId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<Review> reviews = new ArrayList<>();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_REVIEWS_BY_ID);
            preparedStatement.setInt(1, filmId);
            preparedStatement.setInt(2, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reviews.add(InstanceBuilder.buildReview(resultSet));
            }
            return reviews;
        }catch (SQLException  sqlE) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    public int getDislikesAmountById(int reviewId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_REVIEW_DISLIKE_AMOUNT_BY_ID);
            preparedStatement.setInt(1, reviewId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt(ReviewDAOImpl.DISLIKES_AMOUNT);
            }

            return -1;
        }catch (SQLException  sqlE) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    public int getLikesAmountById(int reviewId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_REVIEW_LIKE_AMOUNT_BY_ID);
            preparedStatement.setInt(1, reviewId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt(ReviewDAOImpl.LIKES_AMOUNT);
            }

            return -1;
        }catch (SQLException  sqlE) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    public boolean add (Review review, int filmId) throws SQLException, InterruptedException {
        Connection connection = connectable.getConnection();
        PreparedStatement pr = null;

        try{
            pr = connection.prepareStatement(ADD_REVIEW);

            pr.setString(1, review.getReview());
            pr.setInt(2, review.getMark());
            pr.setInt(3, filmId);
            pr.setInt(4, review.getUserId());

            if(pr.executeUpdate() == 1) {
                return true;
            };
        } catch (SQLException | NullPointerException e) {
            //TODO
        } finally {
            connectable.closeConnection(pr, connection);
        }
        return false;

    }

    public List<ReviewDTO> getReviewsByFilmId(int filmId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<ReviewDTO> reviews = new ArrayList<>();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_REVIEWS_BY_FILM_ID);
            preparedStatement.setInt(1, filmId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("inside");
                reviews.add(InstanceBuilder.buildReviewDTO(resultSet));
                System.out.println("olso inside");
            }
            if (reviews.isEmpty()) { //TODO is it correct?
                reviews = Collections.EMPTY_LIST;
            }
            return reviews;
        }catch (SQLException  e) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }
}
