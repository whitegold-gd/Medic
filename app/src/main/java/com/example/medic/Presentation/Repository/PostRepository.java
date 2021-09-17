package com.example.medic.Presentation.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medic.Presentation.Repository.Room.DAO.PostDAO;
import com.example.medic.Presentation.Repository.Room.DTO.PostDTO;
import com.example.medic.Domain.Model.Post;

import java.util.List;

public class PostRepository implements RepositoryTasks{
    private PostDAO postDAO;
    private LiveData<List<PostDTO>> posts = new MutableLiveData<>();

    PostRepository(Application application){
        PostRoomDatabase database = PostRoomDatabase.getDatabase(application);
        postDAO = database.postDAO();
        posts = postDAO.getAllPosts();
    }

    @Override
    public LiveData<List<PostDTO>> getAllPosts() {
        return posts;
    }

    @Override
    public <T extends Post> void addPost(T post) {
        PostRoomDatabase.databaseWriteExecutor.execute(() -> {
            postDAO.addPost((PostDTO) post);
        });
    }
}
