package com.example.medic.Presentation.Repository.Room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.medic.Presentation.Repository.Room.DTO.UserDTO;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void addUser(UserDTO user);

    @Query("SELECT * FROM user WHERE email = :email")
    LiveData<UserDTO> getUserByEmail(String email);

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    LiveData<UserDTO> getUserByEmailAndPassword(String email, String password);

    @Query("SELECT * FROM user")
    LiveData<List<UserDTO>> getAllUsers();

    @Update
    void updateUserInfo(UserDTO user);
}
