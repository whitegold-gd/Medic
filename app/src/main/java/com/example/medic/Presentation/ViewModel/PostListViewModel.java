package com.example.medic.Presentation.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.medic.Domain.Model.Post;
import com.example.medic.Presentation.Repository.Repository;

import java.util.List;

public class PostListViewModel extends ViewModel {
    public LiveData<List<Post>> getPostList(){
        return Repository.getInstance().getAllPosts();
    }

    //delete
}
