package com.example.medic.Presentation.Repository;

import android.app.Application;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medic.DI.ServiceLocator;
import com.example.medic.Domain.Model.Post;
import com.example.medic.Domain.Model.User;
import com.example.medic.Presentation.Repository.Network.MedicServer.MedicServerAPI;
import com.example.medic.Presentation.Repository.Room.DAO.PostDAO;
import com.example.medic.Presentation.Repository.Room.DAO.UserDAO;
import com.example.medic.Presentation.Repository.Room.DTO.PostDTO;
import com.example.medic.Presentation.Repository.Room.DTO.UserDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        api = retrofit.create(MedicServerAPI.class);

        PostRoomDatabase database = PostRoomDatabase.getDatabase(application);
        postDAO = database.postDAO();
        userDAO = database.userDAO();
        posts = postDAO.getAllPosts();
    }

    @Override
    public LiveData<String> auth(User user){
        MutableLiveData<String> body = new MutableLiveData<>();

        api.auth(user).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                ServiceLocator.getInstance().setToken(response.body());
                body.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return body;
    }

    @Override
    public LiveData<String> register(User user){
        MutableLiveData<String> body = new MutableLiveData<>();

        api.register(user).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("HHELOOO " + response.body());
                if (response.body() != null){
                    ServiceLocator.getInstance().setToken(response.body());
                    body.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return body;
    }

    @Override
    public LiveData<List<PostDTO>> getAllPosts() {
        MutableLiveData<List<PostDTO>> allPosts = new MutableLiveData<>();

        api.getAllPosts().enqueue(new Callback<List<PostDTO>>() {
            @Override
            public void onResponse(Call<List<PostDTO>> call, Response<List<PostDTO>> response) {
                allPosts.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PostDTO>> call, Throwable t) {
                t.printStackTrace();
                //allPosts.setValue(posts);
            }
        });

        return allPosts;
    }

    @Override
    public LiveData<Post> addPost(Post post) {
        MutableLiveData<Post> postLiveData = new MutableLiveData<>();
        api.addNewPostToList(ServiceLocator.getInstance().getToken(), post)
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        System.out.println("Added post successfully!!!");
                        postLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
        return postLiveData;
    }

    @Override
    public LiveData<Boolean> deletePost(Post post) {
        MutableLiveData<Boolean> mylist = new MutableLiveData<>();

        api.deletePostById(ServiceLocator.getInstance().getToken(), post.getId())
                .enqueue(new Callback<Boolean>(){
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                mylist.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return mylist;
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
    public LiveData<User> findUser(String email, LifecycleOwner owner) {
        MutableLiveData<User> answer = new MutableLiveData<>();

        api.getInfoByEmail(email).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null){
                    System.out.println("User exist and found: " + response.body().getRole());
                    answer.setValue(response.body());
                }
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
