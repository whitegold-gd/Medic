package com.example.medic.Presentation.Repository.Room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.lifecycle.LiveData;

import com.example.medic.Presentation.Repository.Room.DTO.PostDTO;

import java.util.List;

@Dao
public interface PostDAO {
    @Insert
    void addPost(PostDTO post);

    @Delete
    void deletePost(PostDTO post);

    @Query("SELECT * FROM post")
    LiveData<List<PostDTO>> getAllPosts();
}
