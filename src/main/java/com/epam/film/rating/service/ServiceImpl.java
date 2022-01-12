package com.epam.film.rating.service;

import com.epam.film.rating.dao.DAOFactory;
import com.epam.film.rating.dao.UserDAO;
import com.epam.film.rating.entity.user.User;

import java.sql.SQLException;

public class ServiceImpl implements Service{

//    @Override
//    public boolean logIn(String login, String password) throws SQLException {
//        DAOFactory factory = DAOFactory.getInstance();
//        UserDAO userDAO = factory.getUserDAO();
//        return userDAO.logIn(login,password);
//    }

    @Override
    public User login(String login, String password) throws SQLException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        return userDAO.login(login, password);
    }

}
