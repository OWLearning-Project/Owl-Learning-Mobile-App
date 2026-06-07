package com.owllearning.mobile.data.repository;

import android.content.Context;

import com.owllearning.mobile.data.api.ClientApi;
import com.owllearning.mobile.data.api.UtilisateurClient;
import com.owllearning.mobile.data.model.response.UtilisateurResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UtilisateurRepository {
    public UtilisateurClient utilisateurClient;

    public UtilisateurRepository(Context context) {
        utilisateurClient = ClientApi.getClient(context).create(UtilisateurClient.class);
    }

    public interface ProfilCallback {
        void onSuccess(UtilisateurResponse utilisateurResponse);
        void onError(String message);
    }

    public void getProfilUtilisateur(int idUtilisateur, ProfilCallback callback) {
        utilisateurClient.getUtilisateurById(idUtilisateur).enqueue(new Callback<UtilisateurResponse>() {
            @Override
            public void onResponse(Call<UtilisateurResponse> call, Response<UtilisateurResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Erreur serveur : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<UtilisateurResponse> call, Throwable t) {
                callback.onError("Problème réseau : " + t.getMessage());
            }
        });
    }
}
