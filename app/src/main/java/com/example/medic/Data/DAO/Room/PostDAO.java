package com.example.medic.Data.DAO.Room;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.lifecycle.LiveData;

import com.example.medic.Data.DTO.PostDTO;

import java.util.List;

public interface PostDAO {
    @Insert
    void addPost(PostDTO post);

    @Delete
    void deletePost(PostDTO post);

    @Query("SELECT * FROM post")
    LiveData<List<PostDAO>> getAllPosts();
}
