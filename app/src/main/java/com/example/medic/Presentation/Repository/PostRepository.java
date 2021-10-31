package com.example.medic.Presentation.Repository;

import android.app.Application;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.medic.Domain.Model.Post;
import com.example.medic.Domain.Model.User;
import com.example.medic.Presentation.Repository.Room.DAO.PostDAO;
import com.example.medic.Presentation.Repository.Room.DAO.UserDAO;
import com.example.medic.Presentation.Repository.Room.DTO.PostDTO;
import com.example.medic.Presentation.Repository.Room.DTO.UserDTO;

import java.util.List;
import java.util.function.Predicate;

public class PostRepository implements RepositoryTasks {
    private PostDAO postDAO;
    private UserDAO userDAO;
    private LiveData<List<PostDTO>> posts;

    public PostRepository(Application application){
        PostRoomDatabase database = PostRoomDatabase.getDatabase(application);
        postDAO = database.postDAO();
        userDAO = database.userDAO();
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
    public void deletePost(Post post) {
        PostDTO dto = PostDTO.convertFromPost(post);

        PostRoomDatabase.databaseWriteExecutor.execute(() -> {
            postDAO.deletePost(dto);
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

    @Override
    public LiveData<UserDTO> findUser(String email, LifecycleOwner owner) {
        MutableLiveData<UserDTO> answer = new MutableLiveData<>();
        userDAO.getUserByEmail(email).observe(owner, new Observer<UserDTO>() {
            @Override
            public void onChanged(UserDTO userDTO) {
                answer.setValue(userDTO);
            }
        });
        return answer;
    }

    @Override
    public LiveData<UserDTO> findUser(String email, String password, LifecycleOwner owner) {
        MutableLiveData<UserDTO> answer = new MutableLiveData<>();
        userDAO.getUserByEmailAndPassword(email, password).observe(owner, new Observer<UserDTO>() {
            @Override
            public void onChanged(UserDTO userDTO) {
                answer.setValue(userDTO);
            }
        });
        return answer;
    }

    @Override
    public void addUser(User user) {
        UserDTO dto = UserDTO.convertFromUser(user);
        PostRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDAO.addUser(dto);
            }
        });
    }

    @Override
    public void updateUser(User user) {
        UserDTO dto = UserDTO.convertFromUser(user);
        PostRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDAO.updateUserInfo(dto);
            }
        });
    }
}
