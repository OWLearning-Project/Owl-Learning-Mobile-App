package com.owllearning.mobile.data.api;

import com.owllearning.mobile.data.model.response.UtilisateurResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UtilisateurClient {
    @GET("api/utilisateurs/{id}")
    Call<UtilisateurResponse> getUtilisateurById(@Path("id") int id);
}
