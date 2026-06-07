package com.owllearning.mobile.data.api;

import com.owllearning.mobile.data.model.request.UtilisateurConnexionRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthClient {
    @POST("api/authentification/connexion")
    Call<String> connexion(@Body UtilisateurConnexionRequest request);
}
