package com.example.medic.DI;

import android.app.Application;

import com.example.medic.Presentation.Repository.Mock.MockBase;
import com.example.medic.Presentation.Repository.Network.ProfanityLofic.ProfanityChecker;
import com.example.medic.Presentation.Repository.PostRepository;
import com.example.medic.Presentation.Repository.RepositoryTasks;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceLocator {
    static ServiceLocator instance;

    private ServiceLocator(){};
    private RepositoryTasks repositoryTasks;
    private ProfanityChecker profanityChecker;

    static public ServiceLocator getInstance(){
        if (instance == null){
            instance = new ServiceLocator();
        }
        return instance;
    }

    public void init(Application application){
        if (repositoryTasks == null)
        {
            repositoryTasks = new PostRepository(application);
        }
    }

    public RepositoryTasks getRepository(){
        if (repositoryTasks == null){
            repositoryTasks = new MockBase();
        }
        return repositoryTasks;
    }

    public ProfanityChecker getProfanityChecker(){
        if (profanityChecker == null){
            profanityChecker = new ProfanityChecker();
        }
        return profanityChecker;
    }

    private Gson gson;

    public Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(
                            LocalDateTime.class,
                            new JsonDeserializer<LocalDateTime>() {
                                @Override
                                public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                                    return LocalDateTime.parse(
                                            json.getAsString(),
                                            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
                                    );
                                }
                            }
                    )
                    .registerTypeAdapter(
                            LocalDateTime.class,
                            (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) -> new JsonPrimitive(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(src))
                    )
                    .create();
        }
        return gson;
    }
}
