package com.example.medic.Presentation.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.medic.DI.ServiceLocator;
import com.example.medic.Domain.Model.User;
import com.example.medic.MainActivity;
import com.example.medic.Presentation.ViewModel.AuthViewModel;
import com.example.medic.R;
import com.example.medic.databinding.AuthFragmentBinding;
import com.google.android.gms.common.SignInButton;

public class AuthFragment extends Fragment {
    AuthFragmentBinding binding;
    AuthViewModel mViewModel;

    int RC_SIGN_IN = 6;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        ServiceLocator.getInstance().getGoogleLogic().auth.init(getActivity());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AuthFragmentBinding.inflate(getLayoutInflater(), container, false);

        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(binding.editTextTextEmailAddress.getText().toString(),
                        binding.editTextTextPassword.getText().toString());
            }
        });

        binding.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth(binding.editTextTextEmailAddress.getText().toString(),
                        binding.editTextTextPassword.getText().toString());
            }
        });

        setHasOptionsMenu(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ServiceLocator.getInstance().getGoogleLogic().auth.requestCodeCheck(requestCode, RC_SIGN_IN, data);

        ServiceLocator.getInstance().getGoogleLogic().auth.getLastAuthInfo((MainActivity)getActivity());

        Navigation.findNavController(((MainActivity) getActivity()).mBinding.navHostFragment).popBackStack();
    }

    public void auth(String email, String password){
        if (email.length() != 0 && password.length() != 0){
            mViewModel.auth(email, password, getActivity());
        }
    }

    public void register(String email, String password){
        if (email.length() != 0 && password.length() != 0){
            mViewModel.register(email, password, getActivity());
        }
    }
}


