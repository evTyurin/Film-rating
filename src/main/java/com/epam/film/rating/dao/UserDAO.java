package com.epam.film.rating.dao;

import com.epam.film.rating.entity.user.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    int add(User user) throws SQLException, InterruptedException;

    User login(String login, String password) throws SQLException;

    User trygetByLoginAndPassword(String sql) throws SQLException;

    public List<User> getAll() throws SQLException;
}
