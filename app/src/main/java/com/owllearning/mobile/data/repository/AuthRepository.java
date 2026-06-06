package com.owllearning.mobile.data.repository;

import com.owllearning.mobile.data.api.AuthClient;
import com.owllearning.mobile.data.model.Request.UtilisateurConnexionRequest;

import retrofit2.Callback;

public class AuthRepository {
    private final AuthClient authClient;

    public AuthRepository() {
        this.authClient = new AuthClient();
    }

    public void connexion(String email, String motDePasse, Callback<String> callback) {
        UtilisateurConnexionRequest request = new UtilisateurConnexionRequest(email, motDePasse);
        authClient.getApi().connexion(request).enqueue(callback);
    }
}
