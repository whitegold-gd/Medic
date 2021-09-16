package com.example.medic.Domain.Model;

import java.time.LocalDateTime;

public class Post {
    private String title;
    private String body;
    private String tags;
    private String nameOfAuthor;
    private String date;

    public Post(){
    }

    public Post(String title, String body, String tags, String nameOfAuthor){
        this.title = title;
        this.body = body;
        this.tags = tags;
        this.nameOfAuthor = nameOfAuthor;
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

    public void setNameOfAuthor(String nameOfAuthor) {
        this.nameOfAuthor = nameOfAuthor;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getNameOfAuthor() {
        return nameOfAuthor;
    }

    public LocalDateTime getDate() {
        return date;
    }

}
