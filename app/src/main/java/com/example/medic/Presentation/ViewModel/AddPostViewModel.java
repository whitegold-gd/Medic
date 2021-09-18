package com.example.medic.Presentation.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.medic.Presentation.Repository.Repository;
import com.example.medic.Presentation.Repository.Room.DTO.PostDTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class AddPostViewModel extends ViewModel {
    public void addPost(String title, String body, String tags){
        PostDTO postDTO = new PostDTO();
        LocalDateTime localDateTime = LocalDateTime.now();

        postDTO.setTitle(title);
        postDTO.setBody(body);
        postDTO.setTags(tags);
        postDTO.setDate(localDateTime);
        postDTO.setNameOfAuthor("Никита Остапенко");

        Repository.getInstance().addPost(postDTO);
    }
}
