package com.epam.film.rating.entity.user;

import java.util.Objects;

public class User {
    private int id;
    private String login;
    private String password;
    private String nickname;
    private String name;
    private String surname;
    private String phoneNumber;
    private String eMail;
    private boolean isBanned;
    private double rating;
    private String avatarImage;
    private Role role;
    private String status;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && isBanned() == user.isBanned() && Double.compare(user.getRating(), getRating()) == 0 && getLogin().equals(user.getLogin()) && getPassword().equals(user.getPassword()) && getNickname().equals(user.getNickname()) && getName().equals(user.getName()) && getSurname().equals(user.getSurname()) && getPhoneNumber().equals(user.getPhoneNumber()) && geteMail().equals(user.geteMail()) && getAvatarImage().equals(user.getAvatarImage()) && getRole() == user.getRole() && getStatus().equals(user.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getPassword(), getNickname(), getName(), getSurname(), getPhoneNumber(), geteMail(), isBanned(), getRating(), getAvatarImage(), getRole(), getStatus());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", eMail='" + eMail + '\'' +
                ", isBanned=" + isBanned +
                ", rating=" + rating +
                ", avatarImage='" + avatarImage + '\'' +
                ", role=" + role +
                ", status='" + status + '\'' +
                '}';
    }
}
