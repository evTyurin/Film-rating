package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.impl.UserDAOImpl;
import com.epam.film.rating.entity.user.Role;
import com.epam.film.rating.entity.user.User;
import com.epam.film.rating.service.Service;
import com.epam.film.rating.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class Login implements Command {

    public final String parameterLogin = "login";
    public final String parameterPassword = "password";
    public final String adminPageURL = "/WEB-INF/jsp/adminmainpage.jsp";
    public final String userPageURL = "/WEB-INF/jsp/usermainpage.jsp";
    public final String mainPageURL = "/WEB-INF/jsp/mainpage.jsp";

    //TODO is it correct?

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter(parameterLogin);
        String password = request.getParameter(parameterPassword);

        ServiceFactory instance = ServiceFactory.getInstance();
        Service service = instance.getService();

        try {
            User user = service.login(login, password);

            if(user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user); //TODO maybe cookie?

                if (user.getRole().equals(Role.USER)) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher(userPageURL);
                    dispatcher.forward(request, response);

                } else if (user.getRole().equals(Role.ADMINISTRATOR)) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher(adminPageURL);
                    dispatcher.forward(request, response);
                }
            } else {
                //TODO some incorrect input message
                RequestDispatcher dispatcher = request.getRequestDispatcher(mainPageURL);
                dispatcher.forward(request, response);
            }
        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
