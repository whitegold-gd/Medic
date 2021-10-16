package com.example.medic.Presentation.Repository.Network.ProfanityLofic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfanityChecker {
    private PurgoMalumAPI api;
    private final String API_KEY = "29bb57a21fmsh971770ea1c6d2a3p14da05jsn492b8dd6c6b5";
    private final String API_HOST = "community-purgomalum.p.rapidapi.com";

    public ProfanityChecker() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://community-purgomalum.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.api = retrofit.create(PurgoMalumAPI.class);
    }

    static class ProfanityRequest{
        String value;

        public ProfanityRequest(String value) {
            this.value = value;
        }
    }

    static class ProfanityResponse{
        String result;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }

    public LiveData<String> getCorrectedBody(String body){
        MutableLiveData<String> correctedText = new MutableLiveData<>();

        Call<ProfanityResponse> call = api.listAddresses(body, API_HOST, API_KEY);
        call.enqueue(new Callback<ProfanityResponse>() {
            @Override
            public void onResponse(Call<ProfanityResponse> call, Response<ProfanityResponse> response) {
                if (response.isSuccessful() && response.body()!= null){
                    correctedText.setValue(response.body().result);
                }
            }

            @Override
            public void onFailure(Call<ProfanityResponse> call, Throwable t) {

            }
        });
        return correctedText;
    }
}
