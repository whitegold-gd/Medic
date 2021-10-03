package com.example.medic.Presentation.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.DI.ServiceLocator;
import com.example.medic.Domain.Model.Post;
import com.example.medic.MainActivity;
import com.example.medic.Presentation.View.Adapters.ImageSliderAdapter;
import com.example.medic.Presentation.ViewModel.PostViewModel;
import com.example.medic.R;
import com.example.medic.databinding.AddPostFragmentBinding;
import com.example.medic.databinding.PostFragmentBinding;

import java.time.format.DateTimeFormatter;

public class PostFragment extends Fragment {

    private PostViewModel mViewModel;
    private PostFragmentBinding mBinding;

    public static PostFragment newInstance() {
        return new PostFragment();
    }

    //TODO:
    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            mViewModel = new ViewModelProvider(this).get(PostViewModel.class);

        if (getArguments() != null) {
            mViewModel.setPost(
                    ServiceLocator.getInstance().getGson().fromJson(getArguments().getString("Post"), Post.class)
            );
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = PostFragmentBinding.inflate(getLayoutInflater(), container, false);

        if (mViewModel.getPost() != null) {
            mBinding.fab.setOnClickListener((View v) -> {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "https://rf.medic_post/" + mViewModel.getPost().getId());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            });

            mBinding.imageSlider.setAdapter(new ImageSliderAdapter(mViewModel.getPost().getImages(),
                    false, ((MainActivity) requireActivity())));
            mBinding.postTitle.setText(mViewModel.getPost().getTitle());
            mBinding.postBody.setText(mViewModel.getPost().getBody());
            mBinding.postTags.setText(mViewModel.getPost().getTags());
        }

        return mBinding.getRoot();
    }

}
