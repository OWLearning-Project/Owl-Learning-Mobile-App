package com.owllearning.mobile.data.model.request;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurConnexionRequest {
    @SerializedName("email")
    private String email;
    @SerializedName("motDePasse")
    private String motDePasse;
}
