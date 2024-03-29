package com.epam.film.rating.controller;

import com.epam.film.rating.controller.impl.*;
import com.epam.film.rating.controller.impl.LeaveReview;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put("login", new Login());
        commands.put("registration", new Registration());
        commands.put("goToLoginPage", new GoToLoginPage());
        commands.put("goToRegistrationPage", new GoToRegistrationPage());
        commands.put("changeLanguage", new ChangeLanguage());
        commands.put("goToMainPage", new GoToMainPage());
        commands.put("findFilmsByParameters", new FindFilmsByParameters());
        commands.put("goToFilmDescriptionPage", new GoToFilmDescriptionPage());
        commands.put("leaveReview", new LeaveReview());
        commands.put("updateLike", new Like());
        commands.put("updateDislike", new Dislike());
    }

    public final Command getCommand(String commandName) {
        Command command = commands.get(commandName);
        return command;
    }
}
