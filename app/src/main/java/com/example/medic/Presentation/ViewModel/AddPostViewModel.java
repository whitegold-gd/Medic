package com.example.medic.Presentation.ViewModel;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModel;

import com.example.medic.DI.ServiceLocator;
import com.example.medic.Domain.Model.Operations.PostOperations;
import com.example.medic.Domain.Model.Post;
import com.example.medic.Domain.Model.User;
import com.example.medic.Presentation.Repository.Room.DTO.PostDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AddPostViewModel extends ViewModel {
    public void addPost(String title, String body, String tags, List<String> images){
        LocalDateTime localDateTime = LocalDateTime.now();

        Post post = PostOperations.addPost(title,
                body,
                localDateTime,
                new User("Никита", "Остапенко"),
                tags,
                images.stream().filter(Objects::nonNull).collect(Collectors.toList()));

        Log.i("TAG1", body);

        ServiceLocator.getInstance().getRepository().addPost(post);
    }
}