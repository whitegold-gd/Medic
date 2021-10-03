package com.example.medic.Presentation.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.medic.Domain.Model.Post;

public class PostViewModel extends ViewModel {
    private Post post;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
