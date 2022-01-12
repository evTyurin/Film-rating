package com.epam.film.rating.entity.user;

public enum Role {
    ADMINISTRATOR (1),
    USER (2);

    int id;

    Role (int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Role getRole (int id) {
        for (Role role: Role.values()) {
            if (role.id == id) {
                return role;
            }
        }
        return null;
    }
}