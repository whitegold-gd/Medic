package com.example.medic.Presentation.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.medic.DI.ServiceLocator;
import com.example.medic.Domain.Model.Post;
import com.example.medic.MainActivity;
import com.example.medic.Presentation.View.Adapters.ImageSliderAdapter;
import com.example.medic.Presentation.ViewModel.PostViewModel;
import com.example.medic.R;
import com.example.medic.databinding.PostFragmentBinding;

public class PostFragment extends Fragment {

    private PostViewModel mViewModel;
    private PostFragmentBinding mBinding;
    private Boolean deleteButtonBoolean;

    private Post currentPost;

    public static PostFragment newInstance() {
        return new PostFragment();
    }

    //TODO:
    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            mViewModel = new ViewModelProvider(this).get(PostViewModel.class);

        if (getArguments() != null) {
            currentPost = ServiceLocator.getInstance().getGson()
                    .fromJson(getArguments().getString("Post"), Post.class);
            mViewModel.setPost(currentPost);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = PostFragmentBinding.inflate(getLayoutInflater(), container, false);

        setHasOptionsMenu(true);

        deleteButtonBoolean = false;

        Log.w("TAG123", ServiceLocator.getInstance().getUser().getRole().toString());

        switch (ServiceLocator.getInstance().getUser().getRole()) {
            case Administrator:
                deleteButtonBoolean = true;
                sendIntentLogic();
                break;
            case Moderator:
                deleteButtonBoolean = true;
                mBinding.fab.setVisibility(View.GONE);
                break;
            case User:
                deleteButtonBoolean = false;
                mBinding.fab.setVisibility(View.VISIBLE);
                sendIntentLogic();
                break;
            case Guest:
                mBinding.fab.setVisibility(View.GONE);
                deleteButtonBoolean = false;
                break;
        }

        if (mViewModel.getPost() != null) {
            mBinding.imageSlider.setAdapter(new ImageSliderAdapter(mViewModel.getPost().getImages(),
                    false, ((MainActivity) requireActivity())));
            mBinding.postTitle.setText(mViewModel.getPost().getTitle());
            mBinding.postBody.setText(mViewModel.getPost().getBody());
            mBinding.postTags.setText(mViewModel.getPost().getTags());
        }

        return mBinding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.post_fragment, menu);
        MenuItem item = menu.findItem(R.id.miDelete);
        item.setVisible(deleteButtonBoolean);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miDelete: {
                mViewModel.deletePost(currentPost, getActivity());
                break;
            }
        }
        return true;
    }

    public void sendIntentLogic(){
        mBinding.fab.setOnClickListener((View v) -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "https://rf.medic_post/" + mViewModel.getPost().getId());
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });
    }
}
