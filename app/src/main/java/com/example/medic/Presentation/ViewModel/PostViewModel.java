package com.example.medic.Presentation.ViewModel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.medic.DI.ServiceLocator;
import com.example.medic.Domain.Model.Post;
import com.example.medic.MainActivity;

public class PostViewModel extends ViewModel {
    private Post post;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void deletePost(Post post, LifecycleOwner activity){
        ServiceLocator.getInstance().getRepository().deletePost(post).observe(activity, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Navigation.findNavController(((MainActivity) activity).mBinding.navHostFragment)
                        .popBackStack();
            }
        });
    }
}
