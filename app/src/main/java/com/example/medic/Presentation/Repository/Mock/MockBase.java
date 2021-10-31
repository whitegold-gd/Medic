package com.example.medic.Presentation.Repository.Mock;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.medic.Domain.Model.Post;
import com.example.medic.Domain.Model.User;
import com.example.medic.Presentation.Repository.RepositoryTasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MockBase implements RepositoryTasks {
    MutableLiveData<List<Post>> data;
    List<Post> posts;

    public MockBase(){
        posts = new ArrayList<Post>();

        Post post1 = new Post();
        post1.setUser(new User("Nikita", "Ostapeko"));
        post1.setTitle("Введение 1");
        post1.setBody("Повседневная практика показывает, что сложившаяся структура организации " +
                "позволяет выполнять важные задания по разработке модели развития." +
                "\n" +
                "Равным образом сложившаяся структура организации в значительной степени " +
                "обуславливает создание форм развития. Равным образом рамки и место " +
                "обучения кадров позволяет выполнять важные задания по разработке " +
                "существенных финансовых и административных условий." +
                "\n" +
                "Повседневная практика показывает, что сложившаяся структура организации " +
                "представляет собой интересный эксперимент проверки дальнейших " +
                "направлений развития. Товарищи! постоянное " +
                "информационно-пропагандистское " +
                "обеспечение нашей деятельности позволяет выполнять важные задания по " +
                "разработке существенных финансовых и административных условий.");
        post1.setTags("Tags6, tags7, tags8, tags9");
        post1.setDate(LocalDateTime.of(2002, 7, 25, 7,0,0));
        posts.add(post1);

        Post post2 = new Post();
        post1.setUser(new User("Nikita", "Ostapeko"));
        post1.setTitle("Введение 2");
        post1.setBody("Повседневная практика показывает, что сложившаяся структура организации " +
                "позволяет выполнять важные задания по разработке модели развития." +
                "\n" +
                "Равным образом сложившаяся структура организации в значительной степени " +
                "обуславливает создание форм развития. Равным образом рамки и место " +
                "обучения кадров позволяет выполнять важные задания по разработке " +
                "существенных финансовых и административных условий." +
                "\n" +
                "Повседневная практика показывает, что сложившаяся структура организации " +
                "представляет собой интересный эксперимент проверки дальнейших " +
                "направлений развития. Товарищи! постоянное " +
                "информационно-пропагандистское " +
                "обеспечение нашей деятельности позволяет выполнять важные задания по " +
                "разработке существенных финансовых и административных условий.");
        post1.setTags("Tags1 tags2, tags3, tags4");
        post2.setDate(LocalDateTime.of(2002, 7, 25, 8,0,0));
        posts.add(post2);

        Post post3 = new Post();
        post3.setUser(new User("Nikita", "Ostapeko"));
        post3.setTitle("Введение 1");
        post3.setBody("Повседневная практика показывает, что сложившаяся структура организации " +
                "позволяет выполнять важные задания по разработке модели развития." +
                "\n" +
                "Равным образом сложившаяся структура организации в значительной степени " +
                "обуславливает создание форм развития. Равным образом рамки и место " +
                "обучения кадров позволяет выполнять важные задания по разработке " +
                "существенных финансовых и административных условий." +
                "\n" +
                "Повседневная практика показывает, что сложившаяся структура организации " +
                "представляет собой интересный эксперимент проверки дальнейших " +
                "направлений развития. Товарищи! постоянное " +
                "информационно-пропагандистское " +
                "обеспечение нашей деятельности позволяет выполнять важные задания по " +
                "разработке существенных финансовых и административных условий.");
        post3.setTags("Tags2, tags1, tags4, tags3");
        post3.setDate(LocalDateTime.of(2002, 7, 25, 9,0,0));
        posts.add(post3);

        data = new MutableLiveData<>(posts);
    }

    @Override
    public void addPost(Post post){
        posts.add(post);

        data.setValue(posts);
    }

    @Override
    public void deletePost(Post post) {

    }

    @Override
    public <T extends User> LiveData<T> findUser(String email, LifecycleOwner owner) {
        return null;
    }

    @Override
    public <T extends User> LiveData<T> findUser(String email, String password, LifecycleOwner owner) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public MutableLiveData<Post> findPost(String id, LifecycleOwner owner) {
        MutableLiveData<Post> specificPost = new MutableLiveData<>();

        data.observe(owner, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                specificPost.setValue(posts.stream()
                        .filter(new Predicate<Post>() {
                            @Override
                            public boolean test(Post post) {
                                    return id.equals(post.getId());
                                }
                            })
                        .findAny()
                        .orElse(null));
            }
        });

        return specificPost;
    }

    public MutableLiveData<List<Post>> getAllPosts() {
        return data;
    }
}
