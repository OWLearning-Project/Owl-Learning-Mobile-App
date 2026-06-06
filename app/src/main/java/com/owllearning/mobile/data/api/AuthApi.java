package com.owllearning.mobile.data.api;

import com.owllearning.mobile.data.model.Request.UtilisateurConnexionRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("authentification/connexion")
    Call<String> connexion(@Body UtilisateurConnexionRequest request);
}
