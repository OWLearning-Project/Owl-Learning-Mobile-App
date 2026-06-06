package com.owllearning.mobile.data.model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurConnexionRequest {
    private String email;
    private String motDePasse;
}
