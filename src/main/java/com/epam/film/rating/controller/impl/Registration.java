package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.DAOFactory;
import com.epam.film.rating.dao.UserDAO;
import com.epam.film.rating.entity.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class Registration implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {


        //TODO
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phoneNumber = request.getParameter("phoneNumber");
        String eMail = request.getParameter("eMail");

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setName(name);
        user.setSurname(surname);
        user.setPhoneNumber(phoneNumber);
        user.seteMail(eMail);

        DAOFactory instance = DAOFactory.getInstance();
        UserDAO ud = instance.getUserDAO();

        try {
            if(ud.add(user) == 1) {
//                response.getWriter().println("You've sucsessfully log in");
            } else {
//                response.getWriter().println("You've NOT sucsessfully log in");
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
