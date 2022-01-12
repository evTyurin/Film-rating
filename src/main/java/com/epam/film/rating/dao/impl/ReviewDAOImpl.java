package com.epam.film.rating.dao.impl;

import com.epam.film.rating.connectionpool.ConnectionPool;
import com.epam.film.rating.dao.ReviewDAO;
import com.epam.film.rating.dao.builder.InstanceBuilder;
import com.epam.film.rating.entity.review.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAOImpl implements ReviewDAO {
    public static final String ID = "id";
    public static final String REVIEW = "review";
    public static final String MARK = "mark";
    public static final String LIKES_AMOUNT = "likesAmount";
    public static final String DISLIKES_AMOUNT = "dislikesAmount";
    public static final String IS_LIKED = "is_liked";

    ConnectionPool connectable = ConnectionPool.getInstance();

    public ReviewDAOImpl() {};

    public static String GET_REVIEWS_BY_ID = "select review.id, review.review, review.mark, review.likes_amount, review.dislikes_amount from review WHERE review.film_id=? AND review.users_id=?;";

    public static String GET_REVIEWS = "select * from review;";
    public static String ADD_REVIEW = "insert into review values (review, mark, film_id, users_id) values(?, ?, ?, ?);";

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

    public int add (Review review) throws SQLException, InterruptedException {
        Connection connection = connectable.getConnection();
        PreparedStatement pr = null;

        try{
            pr = connection.prepareStatement(ADD_REVIEW);

            pr.setString(1, review.getReview());
            pr.setInt(2, review.getMark());
//            pr.setInt(3, review.getFilmId());
//            pr.setInt(5, review.getUserId());

            pr.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            //TODO
        } finally {
            connectable.closeConnection(pr, connection);
        }
        return 1;

    }



}
