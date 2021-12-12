package com.example.medic.Domain.Model;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Post {
    @NotNull
    private String id;
    private String title;
    private String body;
    private String tags;
    private User user;
    private String date;
    private List<String> images;
    public Post(){
        id = UUID.randomUUID().toString();
        images = new ArrayList<>();
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(@NotNull String id) {
        this.id = id;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getTags() {
        return tags;
    }

    public User getUser() {
        return user;
    }

    public String getDate() {
        return date;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public List<String> getImages() {
        return images;
    }
}
