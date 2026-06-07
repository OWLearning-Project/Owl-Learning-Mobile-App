package com.owllearning.mobile.data.repository;

import android.content.Context;

import com.owllearning.mobile.data.api.AuthClient;
import com.owllearning.mobile.data.api.ClientApi;
import com.owllearning.mobile.data.model.request.UtilisateurConnexionRequest;


import retrofit2.Callback;

public class AuthRepository {
    private final AuthClient authClient;

    public AuthRepository(Context context) {
        this.authClient = ClientApi.getClient(context).create(AuthClient.class);
    }

    public void connexion(String email, String motDePasse, Callback<String> callback) {
        UtilisateurConnexionRequest request = new UtilisateurConnexionRequest(email, motDePasse);
        authClient.connexion(request).enqueue(callback);
    }
}
