package com.example.medic.Domain.Model;

import java.util.UUID;

public class UserAccountInfo {
    private UUID id;

    private String login;

    private String password;

    private Role role;

    private User user;

    public UserAccountInfo() {
    }

    public UserAccountInfo(String login, String password, Role role, User user) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.user = user;
    }

    public enum Role {
        Administrator,
        Moderator,
        User,
        Guest
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
