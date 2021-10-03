package com.example.medic;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.example.medic.DI.ServiceLocator;
import com.example.medic.Domain.Model.Post;
import com.example.medic.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        ServiceLocator.getInstance().init(getApplication());

        Uri income = getIntent().getData();
        if (income != null){
            String[] parts = income.toString().split("/");
            String id = parts[parts.length - 1];
            ServiceLocator.getInstance().getRepository().findPost(id, this).observe(this, new Observer<Post>() {
                @Override
                public void onChanged(Post post) {
                    Bundle bundle = new Bundle();
                    bundle.putString("Post", ServiceLocator.getInstance().getGson().toJson(post));

                    Navigation.findNavController(mBinding.navHostFragment).navigate(
                            R.id.action_postList_to_postFragment, bundle
                    );
                }
            });
        }
    }
}