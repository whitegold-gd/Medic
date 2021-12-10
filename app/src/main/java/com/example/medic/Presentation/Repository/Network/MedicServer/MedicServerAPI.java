package com.example.medic.Presentation.Repository.Network.MedicServer;

import com.example.medic.Domain.Model.Post;
import com.example.medic.Domain.Model.User;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MedicServerAPI {
    @GET("/user/byId")
    @Headers({"Accept: application/json"})
    Call<User> getInfoById(@Query("id") UUID id);

    @GET("/user/byEmail")
    @Headers({"Accept: application/json"})
    Call<User> getInfoByEmail(@Query("email") String email);

    /*@POST("/user/byToken")
    @Headers({"Accept: application/json"})
    Call<UserPOJO> getInfoByToken(@Body Authentication authentication);*/

    @GET("/post/all")
    @Headers({"Accept: application/json"})
    Call<List<Post>> getAllPosts();

    @POST("/post/add")
    @Headers({"Accept: application/json"})
    Call<Post> addNewPostToList(@Query("post") Post post);

    @DELETE("/post/delete")
    @Headers({"Accept: application/json"})
    Call<Void> deletePostById(@Query("id") UUID id);

    @GET("/post/byId")
    @Headers({"Accept: application/json"})
    Call<Post> getPostById(@Query("id") UUID id);

    @GET("/post/share")
    @Headers({"Accept: application/json"})
    Call<String> getLinkToShare(@Query("id") UUID id);
}
