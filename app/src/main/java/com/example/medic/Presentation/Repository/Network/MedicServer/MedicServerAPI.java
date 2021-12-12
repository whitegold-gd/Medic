package com.example.medic.Presentation.Repository.Network.MedicServer;

import com.example.medic.Domain.Model.Post;
import com.example.medic.Domain.Model.User;
import com.example.medic.Presentation.Repository.Room.DTO.PostDTO;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MedicServerAPI {
    @POST("/auth")
    @Headers({"Accept: application/json"})
    Call<String> auth(@Body User user);

    @POST("/register")
    @Headers({"Accept: application/json"})
    Call<String> register(@Body User user);

    @GET("/user/byId")
    @Headers({"Accept: application/json"})
    Call<User> getInfoById(@Header("Authorization") String token, @Query("id") UUID id);

    @GET("/user/byEmail")
    @Headers({"Accept: application/json"})
    Call<User> getInfoByEmail(@Header("Authorization") String token, @Query("email") String email);

    @GET("/post/all")
    @Headers({"Accept: application/json"})
    Call<List<PostDTO>> getAllPosts();

    @POST("/post/add")
    @Headers({"Accept: application/json"})
    Call<Post> addNewPostToList(@Header("Authorization") String token, @Query("post") Post post);

    @DELETE("/post/delete")
    @Headers({"Accept: application/json"})
    Call<Void> deletePostById(@Header("Authorization") String token, @Query("id") UUID id);

    @GET("/post/byId")
    @Headers({"Accept: application/json"})
    Call<Post> getPostById(@Query("id") UUID id);

    @GET("/post/share")
    @Headers({"Accept: application/json"})
    Call<String> getLinkToShare(@Query("id") UUID id);
}
