package com.owllearning.mobile.data.api;

import com.owllearning.mobile.data.model.Request.UtilisateurConnexionRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class AuthClient {
    private final AuthApi api;

    public AuthClient() {
        this.api = ClientApi.getClient().create(AuthApi.class);
    }

    public AuthApi getApi() { return api; }
}
