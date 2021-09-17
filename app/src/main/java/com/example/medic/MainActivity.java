package com.example.medic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.medic.Presentation.Repository.Repository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository.init(getApplication());
    }
}