package com.owllearning.mobile.data.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapitreResponse {
    private int id;
    private String titre;
    private String description;
    private List<RessourceResponse> ressources;
}
