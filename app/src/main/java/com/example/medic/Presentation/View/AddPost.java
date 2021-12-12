package com.example.medic.Presentation.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.example.medic.Domain.Model.Post;
import com.example.medic.MainActivity;
import com.example.medic.Presentation.View.Adapters.ImageSliderAdapter;
import com.example.medic.Presentation.ViewModel.AddPostViewModel;
import com.example.medic.databinding.AddPostFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class AddPost extends Fragment {
    int operationChecker = 0;

    String correctTitle;
    String correctBody;
    String correctTags;

    List<String> images = new ArrayList<>();

    AddPostViewModel addPostViewModel;
    AddPostFragmentBinding mBinding;

    public static AddPost newInstance(){
        return new AddPost();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addPostViewModel = new ViewModelProvider(this).get(AddPostViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = AddPostFragmentBinding.inflate(getLayoutInflater(), container, false);

        mBinding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });

        mBinding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBinding.title.getText().length() == 0 || mBinding.body.getText().length() == 0 || mBinding.tags.getText().length() == 0) {
                    Toast.makeText(getContext(), "Заполните все поля", Toast.LENGTH_LONG).show();
                } else {
                    operationChecker = 0;
                    addPostViewModel.getCorrectedText(mBinding.title.getText().toString())
                            .observe(getViewLifecycleOwner(), new Observer<String>() {
                                @Override
                                public void onChanged(String s) {
                                    if (operationChecker == 2){
                                        correctTitle = s;
                                        addPostViewModel.addPost(
                                                correctTitle,
                                                correctBody,
                                                correctTags,
                                                images
                                        ).observe(getActivity(), new Observer<Post>() {
                                            @Override
                                            public void onChanged(Post post) {
                                                Navigation.findNavController(v).popBackStack();
                                            }
                                        });
                                        operationChecker = 0;
                                    } else {
                                        correctTitle = s;
                                        operationChecker++;
                                    }
                                }
                            });
                    addPostViewModel.getCorrectedText(mBinding.body.getText().toString())
                            .observe(getViewLifecycleOwner(), new Observer<String>() {
                                @Override
                                public void onChanged(String s) {
                                    if (operationChecker == 2){
                                        correctBody = s;
                                        addPostViewModel.addPost(
                                                correctTitle,
                                                correctBody,
                                                correctTags,
                                                images
                                        ).observe(getActivity(), new Observer<Post>() {
                                            @Override
                                            public void onChanged(Post post) {
                                                Navigation.findNavController(v).popBackStack();
                                            }
                                        });
                                        operationChecker = 0;
                                    } else {
                                        correctBody = s;
                                        operationChecker++;
                                    }
                                }
                            });
                    addPostViewModel.getCorrectedText(mBinding.tags.getText().toString())
                            .observe(getViewLifecycleOwner(), new Observer<String>() {
                                @Override
                                public void onChanged(String s) {
                                    if (operationChecker == 2){
                                        correctTags = s;
                                        addPostViewModel.addPost(
                                                correctTitle,
                                                correctBody,
                                                correctTags,
                                                images
                                        ).observe(getActivity(), new Observer<Post>() {
                                            @Override
                                            public void onChanged(Post post) {
                                                Navigation.findNavController(v).popBackStack();
                                            }
                                        });
                                        operationChecker = 0;
                                    } else {
                                        correctTags = s;
                                        operationChecker++;
                                    }
                                }
                            });
                }
            }
        });

        mBinding.imagesLayout.setOnClickListener((View v) -> {
            if (mBinding.imageSlider.getVisibility() == View.GONE) {
                mBinding.imageSlider.setVisibility(View.VISIBLE);
                //mBinding.imageDropdownArrow.setImageResource(R.drawable.arrow_up);
            } else {
                mBinding.imageSlider.setVisibility(View.GONE);
                //mBinding.imageDropdownArrow.setImageResource(R.drawable.arrow_down);
            }
        });

        mBinding.imageSlider.setAdapter(new ImageSliderAdapter(images, true, ((MainActivity) requireActivity())));
        mBinding.imageSlider.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding = null;
        addPostViewModel = null;
    }
}
