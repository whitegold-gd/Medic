package com.example.medic.Domain.Model;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class User {
    @NotNull
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

    public enum Role {
        Administrator,
        Moderator,
        User,
        Guest
    }

    public User(){
        id = UUID.randomUUID().toString();
    }

    public User(String email, String password) {
        this();
        this.email = email;
        this.password = password;
    }

    @NotNull
    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;

    }
    public void setId(@NotNull String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
