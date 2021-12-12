package com.example.medic.Presentation.Repository;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medic.Domain.Model.Post;
import com.example.medic.Domain.Model.User;

import java.util.List;

public interface RepositoryTasks {
    LiveData<String> auth(User user);
    LiveData<String> register(User user);

    <T extends Post> LiveData<List<T>> getAllPosts();
    void addPost(Post post);
    void deletePost(Post post);
    <T extends Post> MutableLiveData<T> findPost(String id, LifecycleOwner owner);

    <T extends User> LiveData<T> findUser(String email, LifecycleOwner owner);
    <T extends User> LiveData<T> findUser(String email, String password, LifecycleOwner owner);
    void addUser(User user);
    void updateUser(User user);
}
