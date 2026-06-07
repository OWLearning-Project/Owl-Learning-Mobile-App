package com.owllearning.mobile.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.owllearning.mobile.R;
import com.owllearning.mobile.data.model.response.CoursResponse;

import java.util.List;

public class CoursAdapter extends RecyclerView.Adapter<CoursAdapter.CoursViewHolder>{

    private List<CoursResponse> listeCours;
    private OnCoursClickListener listener;

    public interface OnCoursClickListener {
        void onCoursClick(CoursResponse cours);
    }

    public CoursAdapter(List<CoursResponse> listeCours, OnCoursClickListener listener) {
        this.listeCours = listeCours;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CoursViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cours, parent, false);
        return new CoursViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursViewHolder holder, int position) {
        CoursResponse cours = listeCours.get(position);

        holder.textTitre.setText(cours.getTitre());
        holder.textCreateur.setText("Créé par : " + cours.getCreateur().getPrenom() + " " + cours.getCreateur().getNom());
        holder.textDifficulte.setText(cours.getDifficulte().getLabel());


        if(cours.getCategories() != null && !cours.getCategories().isEmpty()) {
            StringBuilder texteCategories = new StringBuilder();
            for (int i = 0; i < cours.getCategories().size(); i++) {
                texteCategories.append(cours.getCategories().get(i).getLabel());

                if (i < cours.getCategories().size() - 1) {
                    texteCategories.append(", ");
                }
            }
            holder.textCategories.setText(texteCategories.toString());
        } else {
            holder.textCategories.setText("Aucune catégorie");
        }

        holder.itemView.setOnClickListener(v -> {
            if(listener != null) {
                listener.onCoursClick(cours);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listeCours == null)
            return 0;
        return listeCours.size();
    }

    public static class CoursViewHolder extends RecyclerView.ViewHolder {
        TextView textTitre;
        TextView textCreateur;
        TextView textDifficulte;
        TextView textCategories;

        public CoursViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitre = itemView.findViewById(R.id.text_titre_cours);
            textCreateur = itemView.findViewById(R.id.text_createur_cours);
            textDifficulte = itemView.findViewById(R.id.text_difficulte_cours);
            textCategories = itemView.findViewById(R.id.text_categories_cours);
        }
    }
}
