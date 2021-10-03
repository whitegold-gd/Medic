package com.example.medic.Presentation.Repository;

import android.app.Application;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.medic.Presentation.Repository.Room.DAO.PostDAO;
import com.example.medic.Presentation.Repository.Room.DTO.PostDTO;
import com.example.medic.Domain.Model.Post;

import java.util.List;
import java.util.function.Predicate;

public class PostRepository implements RepositoryTasks {
    private PostDAO postDAO;
    private LiveData<List<PostDTO>> posts;

    public PostRepository(Application application){
        PostRoomDatabase database = PostRoomDatabase.getDatabase(application);
        postDAO = database.postDAO();
        posts = postDAO.getAllPosts();
    }

    @Override
    public LiveData<List<PostDTO>> getAllPosts() {
        return posts;
    }

    @Override
    public void addPost(Post post) {
        PostDTO dto = PostDTO.convertFromPost(post);

        PostRoomDatabase.databaseWriteExecutor.execute(() -> {
            postDAO.addPost(dto);
        });
    }

    @Override
    public MutableLiveData<PostDTO> findPost(String id, LifecycleOwner owner) {
        MutableLiveData<PostDTO> specificPost = new MutableLiveData<>();

        posts.observe(owner, new Observer<List<PostDTO>>() {
            @Override
            public void onChanged(List<PostDTO> listParties) {
                specificPost.setValue(listParties.stream()
                        .filter(new Predicate<PostDTO>() {
                            @Override
                            public boolean test(PostDTO postDTO) {
                                return id.equals(postDTO.getId());
                            }
                        })
                        .findAny()
                        .orElse(null));
            }
        });
        return specificPost;
    }
}
