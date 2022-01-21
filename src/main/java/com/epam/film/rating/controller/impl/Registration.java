package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.DAOFactory;
import com.epam.film.rating.dao.UserDAO;
import com.epam.film.rating.entity.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class Registration implements Command {

    public final String login = "login";
    public final String password = "password";
    public final String nickname = "nickname";
    public final String name = "name";
    public final String surname = "surname";
    public final String phoneNumber = "phoneNumber";
    public final String eMail = "eMail";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        User user = new User();
        user.setLogin(request.getParameter(login));
        user.setPassword(request.getParameter(password));
        user.setNickname(request.getParameter(nickname));
        user.setName(request.getParameter(name));
        user.setSurname(request.getParameter(surname));
        user.setPhoneNumber(request.getParameter(phoneNumber));
        user.seteMail(request.getParameter(eMail));


        //TODO Service factory
        DAOFactory instance = DAOFactory.getInstance();
        UserDAO ud = instance.getUserDAO();

        try {
            if(ud.add(user) == 1) {
                // registration was successful
            } else {
                // registration was unsuccessful
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
