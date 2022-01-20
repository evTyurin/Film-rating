package com.epam.film.rating.controller;

import com.epam.film.rating.connectionpool.ConnectionPool;
import com.epam.film.rating.dao.DAOFactory;
import com.epam.film.rating.dao.UserDAO;
import com.epam.film.rating.dao.builder.InstanceBuilder;
import com.epam.film.rating.dao.impl.UserDAOImpl;
import com.epam.film.rating.entity.user.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {
    private final CommandProvider provider = new CommandProvider();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    public void process (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("command");

        Command command = provider.getCommand(commandName);

        command.execute(request, response);
    }
}
