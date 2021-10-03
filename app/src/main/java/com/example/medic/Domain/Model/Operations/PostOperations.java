package com.example.medic.Domain.Model.Operations;

import com.example.medic.Domain.Model.Post;
import com.example.medic.Domain.Model.User;

import java.time.LocalDateTime;
import java.util.List;

public class PostOperations {
    public static Post addPost(String title, String text,
                               LocalDateTime time, User user,
                               String tags, List<String> images){
        Post post = new Post();

        post.setTitle(title);
        post.setBody(text);
        post.setTags(tags);
        post.setUser(user);
        post.setDate(time);
        post.setImages(images);

        return post;
    }
}
