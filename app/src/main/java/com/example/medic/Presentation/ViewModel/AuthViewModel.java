package com.example.medic.Presentation.ViewModel;

import android.app.Activity;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.medic.DI.ServiceLocator;
import com.example.medic.Domain.Model.User;
import com.example.medic.MainActivity;

public class AuthViewModel extends ViewModel {
    public void auth(String email, String password, LifecycleOwner owner) {
        ServiceLocator.getInstance().getRepository().findUser(email, owner).observe(owner, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                user.setPassword(password);
                ServiceLocator.getInstance().getRepository().auth(user).observe(owner, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        if (s != null){
                            ServiceLocator.getInstance().setUser(user);
                            System.out.println("Email = " + ServiceLocator.getInstance().getUser().getEmail());
                            System.out.println("Role = " + ServiceLocator.getInstance().getUser().getRole());
                            Navigation.findNavController(((MainActivity) owner).mBinding.navHostFragment)
                                    .popBackStack();
                        }
                    }
                });
            }
        });
    }

    public void register(String email, String password, LifecycleOwner owner) {
        User user = new User(email, password);

        ServiceLocator.getInstance().getRepository().register(user).observe(owner, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    ServiceLocator.getInstance().setUser(user);
                    System.out.println("Email = " + ServiceLocator.getInstance().getUser().getEmail());
                    System.out.println("Role = " + ServiceLocator.getInstance().getUser().getRole());
                    Navigation.findNavController(((MainActivity) owner).mBinding.navHostFragment)
                            .popBackStack();
                }
            }
        });
    }
}