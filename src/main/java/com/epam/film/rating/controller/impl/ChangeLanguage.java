package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLanguage implements Command{
    public final String attributeURL = "URL";
    public final String attributeLocal = "local";
    //TODO is it correct?

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String URL = (String) session.getAttribute(attributeURL);

        request.getSession(true).setAttribute(attributeLocal, request.getParameter(attributeLocal));
        request.getRequestDispatcher(URL).forward(request, response);
    }
}
