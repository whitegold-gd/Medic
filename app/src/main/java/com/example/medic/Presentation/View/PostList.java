package com.example.medic.Presentation.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.Domain.Model.Post;
import com.example.medic.MainActivity;
import com.example.medic.Presentation.View.Adapters.PostListAdapter;
import com.example.medic.Presentation.ViewModel.PostListViewModel;
import com.example.medic.R;

import java.util.List;

public class PostList extends Fragment {
    View postListView;
    PostListViewModel postListViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        postListView = inflater.inflate(R.layout.post_list_fragment, null, false);

        RecyclerView recyclerView = postListView.findViewById(R.id.postListRecycler);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));

        postListViewModel = new ViewModelProvider(this).get(PostListViewModel.class);

        postListViewModel.getPostList().observe(getViewLifecycleOwner(), (List<Post> postList) -> {
            recyclerView.setAdapter(new PostListAdapter(postList, (MainActivity) requireActivity()));
        });

        postListView.findViewById(R.id.buttonPanel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_postList_to_addPost);
            }
        });

        return postListView;
    }
}


