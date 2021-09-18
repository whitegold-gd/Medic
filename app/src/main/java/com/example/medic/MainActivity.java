package com.example.medic;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medic.Presentation.Repository.Repository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Repository.init(getApplication());
    }
}