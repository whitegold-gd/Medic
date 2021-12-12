package com.example.medic.Presentation.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.medic.DI.ServiceLocator;
import com.example.medic.Domain.Model.User;

public class AuthViewModel extends ViewModel {
    public void auth(String login, String password) {
        ServiceLocator.getInstance().getRepository().auth(new User(login, password));
    }

    public void register(String login, String password) {
        ServiceLocator.getInstance().getRepository().register(new User(login, password));
    }
}