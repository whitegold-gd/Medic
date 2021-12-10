package com.example.medic.Presentation.Repository;

import android.app.Application;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medic.Domain.Model.Post;
import com.example.medic.Domain.Model.User;
import com.example.medic.Presentation.Repository.Network.MedicServer.MedicServerAPI;
import com.example.medic.Presentation.Repository.Room.DAO.PostDAO;
import com.example.medic.Presentation.Repository.Room.DAO.UserDAO;
import com.example.medic.Presentation.Repository.Room.DTO.PostDTO;
import com.example.medic.Presentation.Repository.Room.DTO.UserDTO;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostRepository implements RepositoryTasks {
    private static final String HOST = "http://10.0.2.2:8080";

    private PostDAO postDAO;
    private UserDAO userDAO;
    private LiveData<List<PostDTO>> posts;
    private MedicServerAPI api;

    public PostRepository(Application application){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(MedicServerAPI.class);

        PostRoomDatabase database = PostRoomDatabase.getDatabase(application);
        postDAO = database.postDAO();
        userDAO = database.userDAO();
        posts = postDAO.getAllPosts();
    }

    @Override
    public MutableLiveData<List<Post>> getAllPosts() {
        MutableLiveData<List<Post>> allPosts = new MutableLiveData<>();

        api.getAllPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                allPosts.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                t.printStackTrace();
                //allPosts.setValue(posts);
            }
        });

        return allPosts;
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

        api.deletePostById(UUID.fromString(post.getId())).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    System.out.println(response);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                /*PostRoomDatabase.databaseWriteExecutor.execute(() -> {
                    postDAO.deletePost(dto);
                });*/
            }
        });
    }

    @Override
    public MutableLiveData<PostDTO> findPost(String id, LifecycleOwner owner) {
        MutableLiveData<PostDTO> specificPost = new MutableLiveData<>();

        api.getPostById(UUID.fromString(id)).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    specificPost.setValue((PostDTO)response.body());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                t.printStackTrace();
                /*posts.observe(owner, new Observer<List<PostDTO>>() {
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
                });*/
            }
        });
        return specificPost;
    }

    @Override
    public LiveData<UserDTO> findUser(String email, LifecycleOwner owner) {
        MutableLiveData<UserDTO> answer = new MutableLiveData<>();

        api.getInfoByEmail(email).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                answer.setValue((UserDTO) response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });

        /*userDAO.getUserByEmail(email).observe(owner, new Observer<UserDTO>() {
            @Override
            public void onChanged(UserDTO userDTO) {
                answer.setValue(userDTO);
            }
        });*/
        return answer;
    }

    @Override
    public LiveData<UserDTO> findUser(String email, String password, LifecycleOwner owner) {
        MutableLiveData<UserDTO> answer = new MutableLiveData<>();

        api.getInfoByEmail(email).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                answer.setValue((UserDTO) response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });

        /*userDAO.getUserByEmailAndPassword(email, password).observe(owner, new Observer<UserDTO>() {
            @Override
            public void onChanged(UserDTO userDTO) {
                answer.setValue(userDTO);
            }
        });*/
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
