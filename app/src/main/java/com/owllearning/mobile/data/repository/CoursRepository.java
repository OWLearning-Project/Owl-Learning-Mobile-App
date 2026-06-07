package com.owllearning.mobile.data.repository;

import android.content.Context;
import android.util.Log;

import com.owllearning.mobile.data.api.ClientApi;
import com.owllearning.mobile.data.api.CoursClient;
import com.owllearning.mobile.data.model.response.CoursResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoursRepository {

    private final CoursClient coursClient;

    public CoursRepository(Context context){
        coursClient = ClientApi.getClient(context).create(CoursClient.class);
    }

    public interface CoursCallback {
        void onSuccess(List<CoursResponse> listeCours);
        void onError(String message);
    }

    public void getListeCoursPublies(CoursCallback callback) {
        coursClient.getTousLesCoursPublies().enqueue(new Callback<List<CoursResponse>>() {

            @Override
            public void onResponse(Call<List<CoursResponse>> call, Response<List<CoursResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Erreur serveur : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<CoursResponse>> call, Throwable t) {
                callback.onError("Problème réseau : " + t.getMessage());
            }
        });
    }
}