package com.example.medic.Presentation.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medic.Domain.Model.Post;

import java.util.List;

public interface RepositoryTasks {
    <T extends Post> LiveData<List<T>> getAllPosts();
    <T extends Post> void addPost(T post);
}
