package com.owllearning.mobile.data.model.response;

import java.security.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurResponse
{
    private int id;
    private String nom;
    private String prenom;
    private String pseudo;
    private String email;
    private String role;
    private String dateInscription;
    private String derniereActivite;
}
