package com.example.medic.Presentation.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.medic.DI.ServiceLocator;
import com.example.medic.Domain.Model.Operations.PostOperations;
import com.example.medic.Domain.Model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AddPostViewModel extends ViewModel {
    public LiveData<Post> addPost(String title, String body, String tags, List<String> images){
        String localDateTime = LocalDateTime.now().toString();

        Post post = PostOperations.addPost(title,
                body,
                localDateTime,
                ServiceLocator.getInstance().getUser(),
                tags,
                images.stream().filter(Objects::nonNull).collect(Collectors.toList()));

        return ServiceLocator.getInstance().getRepository().addPost(post);
    }

    public LiveData<String> getCorrectedText(String untreatedText){
        return ServiceLocator.getInstance().getProfanityChecker().getCorrectedBody(untreatedText);
    }
}