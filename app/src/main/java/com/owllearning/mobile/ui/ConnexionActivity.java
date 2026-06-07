package com.owllearning.mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.owllearning.mobile.R;
import com.owllearning.mobile.data.repository.AuthRepository;
import com.owllearning.mobile.utils.SessionManager;
import com.owllearning.mobile.viewmodel.ConnexionViewModel;

public class ConnexionActivity extends AppCompatActivity {

    private ConnexionViewModel viewModel;

    private EditText editEmail;
    private EditText editMotDePasse;
    private Button btnConnexion;
    private ProgressBar progressBar;
    private Button btnCreerCompte;
    private TextView textViewErreur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_connexion);

        initialiserLayout();
        initialiserViewModel();

        viewModel.getChargement().observe(this, enChargement -> {
            if(enChargement) {
                progressBar.setVisibility(View.VISIBLE);
                btnConnexion.setEnabled(false);
            } else {
                progressBar.setVisibility(View.GONE);
                btnConnexion.setEnabled(true);
            }
        });

        viewModel.getToken().observe(this, token -> {
            if(token != null) {
                SessionManager sessionManager = new SessionManager(this);
                sessionManager.saveToken(token);

                Toast.makeText(this, "Connecté avec succès !", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        viewModel.getErreur().observe(this, messageErreur -> {
            if(messageErreur != null) {
                textViewErreur.setText(messageErreur);
                textViewErreur.setVisibility(View.VISIBLE);
            } else {
                textViewErreur.setVisibility(View.GONE);
            }
        });

        btnConnexion.setOnClickListener(v -> seConnecter());

        btnCreerCompte.setOnClickListener(v -> versCreerCompte());
    }

    public void initialiserLayout() {
        editEmail = findViewById(R.id.edit_email);
        editMotDePasse = findViewById(R.id.edit_motdepasse);
        btnConnexion = findViewById(R.id.btn_connexion);
        progressBar = findViewById(R.id.progress_bar);
        btnCreerCompte = findViewById(R.id.btn_creer_compte);
        textViewErreur = findViewById(R.id.textView_erreur_connexion);
    }

    public void initialiserViewModel() {
        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new ConnexionViewModel(new AuthRepository(ConnexionActivity.this));
            }
        };
        viewModel = new ViewModelProvider(this, factory).get(ConnexionViewModel.class);
    }

    public void seConnecter() {
        String email = editEmail.getText().toString().trim();
        String mdp = editMotDePasse.getText().toString().trim();

        if (email.isEmpty() || mdp.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
        } else {
            viewModel.seConnecter(email, mdp);
        }
    }

    public void versCreerCompte() {
        Intent intent = new Intent(ConnexionActivity.this, InscriptionActivity.class);
        startActivity(intent);
    }

}