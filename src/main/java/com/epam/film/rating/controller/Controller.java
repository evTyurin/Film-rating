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

        ///////////////////////////

//        System.out.println("1");
//
//        request.getSession(true).setAttribute("local", request.getParameter("local"));
//        System.out.println("2");
//        request.getRequestDispatcher("index.jsp").forward(request, response);
//        System.out.println("3");
        //////////////////////////////////

//        String login = request.getParameter("login");
//        String password = request.getParameter("password");


//        String login = request.getParameter("login");
//        String password = request.getParameter("password");
//        String nickname = request.getParameter("nickname");
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        String phoneNumber = request.getParameter("phoneNumber");
//        String eMail = request.getParameter("eMail");
//
//        User user = new User();
//        user.setLogin(login);
//        user.setPassword(password);
//        user.setNickname(nickname);
//        user.setName(name);
//        user.setSurname(surname);
//        user.setPhoneNumber(phoneNumber);
//        user.seteMail(eMail);
//
//        DAOFactory instance = DAOFactory.getInstance();
//        UserDAO ud = instance.getUserDAO();
//
//        try {
//            if(ud.add(user) != 1) {
//                response.getWriter().println("You've sucsessfully log in");
//            } else {
//                response.getWriter().println("You've NOT sucsessfully log in");
//            }
//        } catch (SQLException | InterruptedException e) {
//            e.printStackTrace();
//        }



//        System.out.println("Hello");
//        UserDAOImpl ud = new UserDAOImpl();
//        try {
//            List<User> list = ud.getAll();
//            for (User u : list) {
//                response.getWriter().println(u.getName());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        ConnectionPool connectable = ConnectionPool.getInstance();
//
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        try {
//            List<User> users = new ArrayList<>();
//
//            connection = connectable.getConnection();
//            preparedStatement = connection.prepareStatement("select * from user;");
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                users.add(InstanceBuilder.buildUser(resultSet));
//            }
//            for (User u : users) {
//                response.getWriter().println(u.getName());
//            }
//
//        }catch (SQLException  sqlE) {
//            sqlE.getStackTrace();
//        } finally {
//            try {
//                resultSet.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//            try {
//                preparedStatement.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }





//        DAOFactory instance = DAOFactory.getInstance();
//        UserDAO ud = instance.getUserDAO();
//
//        try {
//            if(ud.logIn(login, password)) {
//                response.getWriter().println("You've sucsessfully log in");
//            } else {
//                response.getWriter().println("You've NOT sucsessfully log in");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


    }

    public void process (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("command");

        Command command = provider.getCommand(commandName);

        command.execute(request, response);
    }
}
