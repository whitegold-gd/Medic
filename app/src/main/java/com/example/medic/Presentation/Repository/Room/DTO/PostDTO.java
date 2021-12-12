package com.example.medic.Presentation.Repository.Room.DTO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.example.medic.Domain.Model.Post;
import com.example.medic.Domain.Model.User;
import com.google.gson.Gson;

import java.util.List;

@Entity(tableName = "post", primaryKeys = {"id"}, ignoredColumns = {"title", "body", "tags", "user", "date", "images"})
public class PostDTO extends Post {
    @ColumnInfo
    public String titleDTO;
    @ColumnInfo
    public String bodyDTO;
    @ColumnInfo
    public String tagsDTO;
    @ColumnInfo
    public String userDTO;
    @ColumnInfo
    public String dateDTO;
    @ColumnInfo
    public String imagesDTO;

    @Override
    public void setUser(User user) {
        super.setUser(user);
        this.userDTO = new Gson().toJson(user);
    }

    @Override
    public void setImages(List<String> images) {
        super.setImages(images);
        this.imagesDTO = new Gson().toJson(images);
    }

    @Override
    public void setDate(String date) {
        this.dateDTO = date;
        super.setDate(date);
    }

    @Override
    public User getUser() {
        if (super.getUser() == null) {
            super.setUser(new Gson().fromJson(this.userDTO, User.class));
        }
        return super.getUser();
    }

    @Override
    public List<String> getImages() {
        if (super.getImages() == null || super.getImages().isEmpty()) {
            super.setImages(new Gson().fromJson(this.imagesDTO, List.class));
        }
        return super.getImages();
    }

    @Override
    public String getDate() {
        if (super.getDate() == null) {
            if (this.dateDTO != null) {
                setDate(dateDTO);
            } else {
                return null;
            }
        }
        return super.getDate();
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
        this.titleDTO = title;
    }

    @Override
    public void setBody(String body) {
        super.setBody(body);
        this.bodyDTO = body;
    }

    @Override
    public void setTags(String tags) {
        super.setTags(tags);
        this.tagsDTO = tags;
    }

    @Override
    public String getTitle() {
        if (super.getTitle() == null) {
            super.setTitle(titleDTO);
        }
        return super.getTitle();
    }

    @Override
    public String getBody() {
        if (super.getBody() == null) {
            super.setBody(bodyDTO);
        }
        return super.getBody();
    }

    @Override
    public String getTags() {
        if (super.getTags() == null) {
            super.setTags(tagsDTO);
        }
        return super.getTags();
    }

    public void setTitleDTO(String titleDTO) {
        this.titleDTO = titleDTO;
        super.setTitle(new Gson().fromJson(this.titleDTO, String.class));
    }

    public void setBodyDTO(String bodyDTO) {
        this.bodyDTO = bodyDTO;
        super.setBody(new Gson().fromJson(this.bodyDTO, String.class));
    }

    public void setTagsDTO(String tagsDTO) {
        this.tagsDTO = tagsDTO;
        super.setTags(new Gson().fromJson(this.tagsDTO, String.class));
    }

    public void setUserDTO(String userDTO) {
        this.userDTO = userDTO;
        super.setUser(new Gson().fromJson(this.userDTO, User.class));
    }

    public void setDateDTO(String dateDTO) {
        this.bodyDTO = bodyDTO;
        super.setDate(dateDTO);
    }

    public void setImagesDTO(String imagesDTO) {
        this.imagesDTO = imagesDTO;
    }

    public String getTitleDTO() {
        return titleDTO;
    }

    public String getBodyDTO() {
        return bodyDTO;
    }

    public String getTagsDTO() {
        return tagsDTO;
    }

    public String getUserDTO() {
        return userDTO;
    }

    public String getDateDTO() {
        return dateDTO;
    }

    public String getImagesDTO() {
        return imagesDTO;
    }

    public static PostDTO convertFromPost(Post post) {
        PostDTO dto = new PostDTO();

        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        dto.setTags(post.getTags());
        dto.setUser(post.getUser());
        dto.setDate(post.getDate());
        dto.setImages(post.getImages());

        return dto;
    }
}
