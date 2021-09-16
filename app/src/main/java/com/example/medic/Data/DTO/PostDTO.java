package com.example.medic.Data.DTO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.medic.Domain.Model.Post;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity(tableName = "post")
public class PostDTO extends Post {
    @PrimaryKey
    @NotNull
    @ColumnInfo
    public int id;
    @ColumnInfo
    public String title;
    @ColumnInfo
    public String body;
    @ColumnInfo
    public String tags;
    @ColumnInfo
    public String nameOfAuthor;
    @ColumnInfo
    public String date;

    @Override
    public String getNameOfAuthor() {
        return new Gson().fromJson(this.nameOfAuthor, String.class);
    }
    @Override
    public void setNameOfAuthor(String nameOfAuthor) {
        super.setNameOfAuthor(nameOfAuthor);
        this.nameOfAuthor = new Gson().toJson(nameOfAuthor);
    }
    @Override
    public LocalDateTime getDate() {
        if (this.date != null) {
            return LocalDateTime.parse(this.date, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        } else {
            return null;
        }
    }
    @Override
    public void setDate(LocalDateTime date) {
        super.setDate(date);
        this.date = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
