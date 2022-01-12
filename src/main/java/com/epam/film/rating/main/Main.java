package com.epam.film.rating.main;

import com.epam.film.rating.dao.builder.InstanceBuilder;
import com.epam.film.rating.dao.impl.UserDAOImpl;
import com.epam.film.rating.entity.film.Film;
import com.epam.film.rating.entity.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) throws ClassNotFoundException, SQLException {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            List<User> users = new ArrayList<>();
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/filmRating", "root", "ETmysql21@");
//            preparedStatement = connection.prepareStatement("select * from user;");
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                users.add(InstanceBuilder.buildUser(resultSet));
//            }
//            for (User u : users) {
//                System.out.println(u.getName());
//            }
//
//        }catch (SQLException  sqlE) {
//            throw new SQLException();
//        }


//
//
//
//
//        System.out.println("Hello");
//                UserDAOImpl ud = new UserDAOImpl();
//        try {
//            List<User> list = ud.getAll();
//            for (User u : list) {
//                System.out.println(u.geteMail());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


    }
}
