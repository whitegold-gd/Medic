package com.example.medic.Presentation.Repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.medic.Domain.Model.User;
import com.example.medic.Presentation.Repository.Room.DAO.PostDAO;
import com.example.medic.Presentation.Repository.Room.DAO.UserDAO;
import com.example.medic.Presentation.Repository.Room.DTO.PostDTO;
import com.example.medic.Presentation.Repository.Room.DTO.UserDTO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {PostDTO.class, UserDTO.class}, version = 1, exportSchema = false)
public abstract class PostRoomDatabase extends RoomDatabase {
    public abstract PostDAO postDAO();
    public abstract UserDAO userDAO();

    private static volatile PostRoomDatabase instance;
    private static final int NUMBER_OF_THREADS = 3;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PostRoomDatabase getDatabase(Context context){
        if (instance == null){
            synchronized (PostRoomDatabase.class){
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        PostRoomDatabase.class, "posts_database")
                        .allowMainThreadQueries().addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);

                                databaseWriteExecutor.execute( () -> {
                                            UserDTO admin = new UserDTO();
                                            admin.setEmail("bonnie.terebko@gmail.com");
                                            admin.setPassword("admin");
                                            admin.setRole(User.Role.Administrator);

                                            getDatabase(context).userDAO().addUser(admin);

                                            UserDTO moder = new UserDTO();
                                            moder.setEmail("taleforhelen@gmail.com");
                                            moder.setPassword("moder");
                                            moder.setRole(User.Role.Moderator);

                                            getDatabase(context).userDAO().addUser(moder);
                                        }
                                );
                            }
                        }).build();
            }
        }
        return instance;
    }
}
