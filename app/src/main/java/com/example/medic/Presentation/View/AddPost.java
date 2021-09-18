package com.example.medic.Presentation.View;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.medic.Presentation.ViewModel.AddPostViewModel;
import com.example.medic.Presentation.ViewModel.PostListViewModel;
import com.example.medic.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class AddPost extends Fragment {
    View view;
    AddPostViewModel addPostViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addPostViewModel = new ViewModelProvider(this).get(AddPostViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_post_fragment, null, false);

        view.findViewById(R.id.buttonAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*addPostViewModel.addPost(
                        view.findViewById(R.id.title).getText().toString(),
                        view.findViewById(R.id.body).getText().toString(),
                        view.findViewById(R.id.text).getText().toString()
                );*/
                addPostViewModel.addPost("Первый", "A:DBFO:AUBFO:AUBW", "Тег, тег, тег");
            }
        });

        view.findViewById(R.id.buttonAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        view = null;
        addPostViewModel = null;
    }
}
