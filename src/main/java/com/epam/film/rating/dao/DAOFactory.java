package com.epam.film.rating.dao;

import com.epam.film.rating.dao.impl.FilmDAOImpl;
import com.epam.film.rating.dao.impl.ReviewDAOImpl;
import com.epam.film.rating.dao.impl.UserDAOImpl;

public class DAOFactory {
    private final static DAOFactory instance = new DAOFactory();
    private final UserDAO userDAO;
    private final FilmDAO filmDAO;
    private final ReviewDAO reviewDAO;

    private DAOFactory(){
        userDAO = new UserDAOImpl();
        filmDAO = new FilmDAOImpl();
        reviewDAO = new ReviewDAOImpl();
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public FilmDAO getFilmDAO() {
        return filmDAO;
    }

    public ReviewDAO getReviewDAO() {
        return reviewDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}