package com.example.medic.Presentation.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.DI.ServiceLocator;
import com.example.medic.Domain.Model.Post;
import com.example.medic.Domain.Model.User;
import com.example.medic.MainActivity;
import com.example.medic.Presentation.View.Adapters.PostListAdapter;
import com.example.medic.Presentation.ViewModel.PostListViewModel;
import com.example.medic.R;

import java.util.List;

public class PostList extends Fragment {
    View postListView;
    PostListViewModel postListViewModel;
    Boolean userVerified = false;

    Menu mainMenu;

    int RC_SIGN_IN = 6;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        postListView = inflater.inflate(R.layout.post_list_fragment, null, false);

        setHasOptionsMenu(true);

        RecyclerView recyclerView = postListView.findViewById(R.id.postListRecycler);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));

        postListViewModel = new ViewModelProvider(this).get(PostListViewModel.class);

        postListViewModel.getPostList().observe(getViewLifecycleOwner(), (List<Post> postList) -> {
            recyclerView.setAdapter(new PostListAdapter(postList, (MainActivity) requireActivity()));
        });

        if (!ServiceLocator.getInstance().getUser().getRole().equals(User.Role.Guest)) {
            postListView.findViewById(R.id.buttonPanel).setVisibility(View.VISIBLE);
        } else {
            postListView.findViewById(R.id.buttonPanel).setVisibility(View.GONE);
        }

        postListView.findViewById(R.id.buttonPanel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_postList_to_addPost);
            }
        });

        return postListView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        this.mainMenu = menu;
        if (ServiceLocator.getInstance().getUser().getRole() != User.Role.Guest) mainMenu.findItem(R.id.miProfile)
                .setIcon(R.drawable.ic_baseline_assignment_return_24);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miProfile: {
                if (ServiceLocator.getInstance().getUser().getRole() == User.Role.Guest){
                    auth();
                    authWithLoginAndPassword();
                } else {
                    signOut();
                }
                break;
            }
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ServiceLocator.getInstance().getGoogleLogic().auth
                .requestCodeCheck(requestCode, RC_SIGN_IN, data)){
            /*mainMenu.findItem(R.id.miProfile).setIcon(R.drawable.ic_baseline_assignment_return_24);
            postListView.findViewById(R.id.buttonPanel).setVisibility(View.VISIBLE);*/
            userVerified = true;

            ServiceLocator.getInstance().getGoogleLogic()
                    .auth.getLastAuthInfo((MainActivity)getActivity());
        }
    }

    public void auth(){
        Intent signInIntent = ServiceLocator.getInstance().getGoogleLogic().auth.getIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void authWithLoginAndPassword(){
        Navigation.findNavController(postListView).navigate(R.id.action_postList_to_authFragment);
    }

    public void signOut(){
        ServiceLocator.getInstance().getGoogleLogic().auth.signOut((MainActivity)getActivity());
        mainMenu.findItem(R.id.miProfile).setIcon(R.drawable.ic_baseline_account_box_24);
        postListView.findViewById(R.id.buttonPanel).setVisibility(View.GONE);
        userVerified = false;
    }
}


