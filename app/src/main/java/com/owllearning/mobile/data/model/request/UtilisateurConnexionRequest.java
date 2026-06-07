package com.owllearning.mobile.data.model.request;

import com.google.gson.annotations.SerializedName;


public class UtilisateurConnexionRequest {
    @SerializedName("email")
    private String email;
    @SerializedName("motDePasse")
    private String motDePasse;

    public UtilisateurConnexionRequest(){}
    public UtilisateurConnexionRequest(String email, String motDePasse) {
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
