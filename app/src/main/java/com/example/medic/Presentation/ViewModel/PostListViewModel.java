package com.example.medic.Presentation.ViewModel;

import androidx.lifecycle.LiveData;

import com.example.medic.Domain.Model.Post;
import com.example.medic.Presentation.Repository.Repository;

import java.util.List;

public class PostListViewModel {
    public LiveData<List<Post>> getPostList(){
        return Repository.getInstance().getAllPosts();
    }

    //delete
}
