package com.owllearning.mobile.data.model.response;

import com.owllearning.mobile.data.model.enumeration.Categorie;
import com.owllearning.mobile.data.model.enumeration.Difficulte;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursResponse {
    private int id;
    private String titre;
    private String description;
    private String dateCreation;
    private boolean estPrive;
    private boolean estPublie;
    private Difficulte difficulte;
    private List<Categorie> categories;
    private UtilisateurResponse createur;
    private List<ChapitreResponse> chapitres;
    private List<UtilisateurResponse> eleves;
}
