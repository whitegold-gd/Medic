package com.example.medic.Presentation.View;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.example.medic.MainActivity;
import com.example.medic.Presentation.View.Adapters.ImageSliderAdapter;
import com.example.medic.Presentation.ViewModel.AddPostViewModel;
import com.example.medic.Presentation.ViewModel.PostListViewModel;
import com.example.medic.R;
import com.example.medic.databinding.AddPostFragmentBinding;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddPost extends Fragment {

    TextView title;
    TextView body;
    TextView tags;

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
                title = mBinding.title;
                body = mBinding.body;
                tags = mBinding.tags;

                if (title.getText().length() == 0 || body.getText().length() == 0 || tags.getText().length() == 0) {
                    Toast.makeText(getContext(), "Заполните все поля", Toast.LENGTH_LONG).show();
                } else {
                    addPostViewModel.addPost(
                            title.getText().toString(),
                            body.getText().toString(),
                            tags.getText().toString(),
                            images
                    );
                    Navigation.findNavController(v).popBackStack();
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
