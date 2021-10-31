package com.example.medic.Presentation.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.medic.DI.ServiceLocator;
import com.example.medic.Domain.Model.Operations.UserOperations;
import com.example.medic.Domain.Model.User;

public class AuthViewModel extends ViewModel {
    public boolean auth (String token) {
        if (token != null) {
            return true;
        }
        return false;
    }

    public boolean auth (String login, String password) {
        if (login.contains("admin") && password.equals("admin")) {
            return true;
        }
        else if (login.contains("moder") && password.equals("moder")) {
            return true;
        }
        return false;
    }

    public void addUser(String firstName, String secondName, String email, String password){

        User user = UserOperations.addUser(firstName,
                secondName,
                email,
                password);

        ServiceLocator.getInstance().getRepository().addUser(user);
    }
}