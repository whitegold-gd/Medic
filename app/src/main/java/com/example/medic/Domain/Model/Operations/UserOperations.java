package com.example.medic.Domain.Model.Operations;

import com.example.medic.Domain.Model.User;

public class UserOperations {
    public static User addUser(String firstName, String secondName,
                               String email, String password){
        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(secondName);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }
}
