package com.example.medic.Presentation.View;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.medic.DI.ServiceLocator;
import com.example.medic.Presentation.ViewModel.AuthViewModel;
import com.example.medic.databinding.AuthFragmentBinding;

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

    /*@Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AuthFragmentBinding.inflate(getLayoutInflater(), container, false);

        if (ServiceLocator.getInstance().getUser().getRole() == User.Role.Guest){
            binding.signInButton.setVisibility(View.VISIBLE);
            binding.signOutButton.setVisibility(View.GONE);
            binding.signInButton.setSize(SignInButton.SIZE_STANDARD);
            binding.signInButton.setOnClickListener((view) -> auth());
        } else {
            binding.signInButton.setVisibility(View.GONE);
            binding.signOutButton.setVisibility(View.VISIBLE);
            binding.signOutButton.setOnClickListener((view) -> signOut());
        }

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

    public void auth(){
        Intent signInIntent = ServiceLocator.getInstance().getGoogleLogic().auth.getIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void signOut(){
        ServiceLocator.getInstance().getGoogleLogic().auth.signOut((MainActivity)getActivity());
        Navigation.findNavController(((MainActivity) getActivity()).mBinding.navHostFragment).popBackStack();
    }*/
}


