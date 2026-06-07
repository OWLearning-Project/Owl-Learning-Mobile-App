package com.owllearning.mobile.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.owllearning.mobile.R;
import com.owllearning.mobile.data.repository.UtilisateurRepository;
import com.owllearning.mobile.utils.SessionManager;
import com.owllearning.mobile.viewmodel.ProfilViewModel;


public class ProfilFragment extends Fragment {

    private boolean enCoursModification = false;
    private TextView titreNom;
    private TextInputEditText editNom;
    private TextInputEditText editPrenom;
    private TextInputEditText editEmail;
    private TextInputEditText editPseudo;
    private TextView dateInscription;
    private TextView derniereActivite;
    private Button btnDeconnexion;
    private ProfilViewModel viewModel;
    private ProgressBar progressBar;
    private SessionManager sessionManager;

    private Button btnModifier;

    public ProfilFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sessionManager = new SessionManager(requireContext());

        initialiserLayout(view);

        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new ProfilViewModel(new UtilisateurRepository(requireContext()));
            }
        };
        viewModel = new ViewModelProvider(this, factory).get(ProfilViewModel.class);

        viewModel.getChargement().observe(getViewLifecycleOwner(), enChargement -> {
            if (enChargement) {
                progressBar.setVisibility(View.VISIBLE);
                btnModifier.setEnabled(false);
            } else {
                progressBar.setVisibility(View.GONE);
                btnModifier.setEnabled(true);
            }
        });

        viewModel.getErreur().observe(getViewLifecycleOwner(), messageErreur -> {
            if(messageErreur != null) {
                Toast.makeText(requireContext(), messageErreur, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getProfil().observe(getViewLifecycleOwner(), utilisateurResponse -> {
            if (utilisateurResponse != null) {
                titreNom.setText(utilisateurResponse.getPrenom() + " " + utilisateurResponse.getNom());

                editNom.setText(utilisateurResponse.getNom());
                editPrenom.setText(utilisateurResponse.getPrenom());
                editEmail.setText(utilisateurResponse.getEmail());
                editPseudo.setText(utilisateurResponse.getPseudo());

                dateInscription.setText("Inscription : " + formaterDate(utilisateurResponse.getDateInscription()));
                derniereActivite.setText("Dernière activité : " + formaterDate(utilisateurResponse.getDerniereActivite()));
            }
        });

        btnDeconnexion.setOnClickListener(v -> seDeconnecter());

        viewModel.chargerProfil(2);
    }

    public void initialiserLayout(View view) {
        titreNom = view.findViewById(R.id.titre_nom);
        editNom = view.findViewById(R.id.edit_nom);
        editPrenom = view.findViewById(R.id.edit_prenom);
        editEmail = view.findViewById(R.id.edit_email);
        editPseudo = view.findViewById(R.id.edit_pseudo);
        btnDeconnexion = view.findViewById(R.id.btn_deconnexion);
        btnModifier = view.findViewById(R.id.btn_modifier_profil);
        progressBar = view.findViewById(R.id.progress_bar);
        dateInscription = view.findViewById(R.id.text_date_inscription);
        derniereActivite = view.findViewById(R.id.text_derniere_activite);
    }

    public void seDeconnecter() {
        sessionManager.clearSession();
        Toast.makeText(requireContext(), "Déconnecté avec succès", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(requireActivity(), ConnexionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public String formaterDate(String dateBrute) {
        if (dateBrute == null || dateBrute.isEmpty()) {
            return "Inconnue";
        }
        String annee = dateBrute.substring(0, 4);
        String mois = dateBrute.substring(5, 7);
        String jour = dateBrute.substring(8, 10);
        return jour + "/" + mois + "/" + annee;
    }
}