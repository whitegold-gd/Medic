package com.example.medic.Presentation.Repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.medic.Presentation.Repository.Room.DAO.PostDAO;
import com.example.medic.Presentation.Repository.Room.DTO.PostDTO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {PostDTO.class}, version = 1, exportSchema = false)
public abstract class PostRoomDatabase extends RoomDatabase {
    public abstract PostDAO postDAO();

    private static volatile PostRoomDatabase instance;
    private static final int NUMBER_OF_THREADS = 3;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PostRoomDatabase getDatabase(Context context){
        if (instance == null){
            synchronized (PostRoomDatabase.class){
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        PostRoomDatabase.class, "posts_database")
                        .allowMainThreadQueries().build();
            }
        }
        return instance;
    }
}
