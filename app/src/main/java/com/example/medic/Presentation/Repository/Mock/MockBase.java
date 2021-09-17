package com.example.medic.Presentation.Repository.Mock;

import androidx.lifecycle.MutableLiveData;

import com.example.medic.Presentation.Repository.RepositoryTasks;
import com.example.medic.Domain.Model.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockBase implements RepositoryTasks {
    MutableLiveData<List<Post>> data;
    List<Post> posts;

    public MockBase(){
        posts = new ArrayList<Post>();

        Post post1 = new Post("Введение 1",
                "Повседневная практика показывает, что сложившаяся структура организации " +
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
                        "разработке существенных финансовых и административных условий.",
                "Tags6, tags7, tags8, tags9",
                "User1234");
        post1.setDate(LocalDateTime.of(2002, 7, 25, 7,0,0));
        posts.add(post1);

        Post post2 = new Post("Введение 2",
                "Повседневная практика показывает, что сложившаяся структура организации " +
                        "позволяет выполнять важные задания по разработке модели развития." +
                        "\n" +
                        "Равным образом сложившаяся структура организации в значительной " +
                        "степени обуславливает создание форм развития. Равным образом рамки " +
                        "и место обучения кадров позволяет выполнять важные задания по " +
                        "разработке существенных финансовых и административных условий." +
                        "\n" +
                        "Повседневная практика показывает, что сложившаяся структура " +
                        "организации представляет собой интересный эксперимент проверки " +
                        "дальнейших направлений развития. Товарищи! постоянное " +
                        "информационно-пропагандистское обеспечение нашей деятельности позволяет " +
                        "выполнять важные задания по разработке существенных финансовых и " +
                        "административных условий.",
                "Tags, tags1, tags2, tags3",
                "User5678");
        post2.setDate(LocalDateTime.of(2002, 7, 25, 8,0,0));
        posts.add(post2);

        Post post3 = new Post("Введение 3",
                "Повседневная практика показывает, что сложившаяся структура организации " +
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
                        "информационно-пропагандистское обеспечение нашей деятельности позволяет " +
                        "выполнять важные задания по разработке существенных финансовых и " +
                        "административных условий.",
                "Tags, tags1, tags2, tags3",
                "User2741");
        post3.setDate(LocalDateTime.of(2002, 7, 25, 9,0,0));
        posts.add(post3);

        data = new MutableLiveData<>(posts);
    }

    @Override
    public <T extends Post> void addPost(T post){
        posts.add(post);

        data.setValue(posts);
    }

    public MutableLiveData<List<Post>> getAllPosts() {
        return data;
    }
}
