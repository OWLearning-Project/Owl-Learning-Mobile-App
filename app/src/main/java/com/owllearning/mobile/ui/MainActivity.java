package com.owllearning.mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.owllearning.mobile.R;
import com.owllearning.mobile.utils.SessionManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnDeconnexion = findViewById(R.id.btn_deconnexion_test);
        btnDeconnexion.setOnClickListener(view -> {
            SessionManager sessionManager = new SessionManager(this);
            sessionManager.clearSession();

            Toast.makeText(this, "Déconnecté", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, ConnexionActivity.class);
            startActivity(intent);

            finish();
        });
    }
}