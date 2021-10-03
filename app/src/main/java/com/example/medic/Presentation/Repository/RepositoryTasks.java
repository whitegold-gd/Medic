package com.example.medic.Presentation.Repository;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medic.Domain.Model.Post;

import java.util.List;

public interface RepositoryTasks {
    <T extends Post> LiveData<List<T>> getAllPosts();
    void addPost(Post post);
    <T extends Post> MutableLiveData<T> findPost(String id, LifecycleOwner owner);
}
