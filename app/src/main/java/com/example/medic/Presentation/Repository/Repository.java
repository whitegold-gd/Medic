package com.example.medic.Presentation.Repository;

import android.app.Application;

import com.example.medic.Presentation.Repository.Mock.MockBase;

public class Repository {
    static RepositoryTasks repositoryTasks;

    static public void init(Application application){
        if (repositoryTasks == null)
        {
            repositoryTasks = new PostRepository(application);
        }
    }

    static public RepositoryTasks getInstance(){
        if (repositoryTasks == null){
            repositoryTasks = new MockBase();
        }
        return repositoryTasks;
    }
}
