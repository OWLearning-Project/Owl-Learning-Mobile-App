package com.owllearning.mobile.data.model.response;

import com.owllearning.mobile.data.model.enumeration.TypeRessource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RessourceResponse {
    private int id;
    private String nom;
    private String url;
    private TypeRessource type;
}
