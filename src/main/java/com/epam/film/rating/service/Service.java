package com.epam.film.rating.service;

import com.epam.film.rating.entity.user.User;

import java.sql.SQLException;

public interface Service {
//    boolean logIn(String login, String password) throws SQLException;

    User login(String login, String password) throws SQLException;
}
