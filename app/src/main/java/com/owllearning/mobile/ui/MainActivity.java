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
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.owllearning.mobile.R;
import com.owllearning.mobile.utils.SessionManager;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_menu);

        if (savedInstanceState == null){
            remplacerFragment(new MesCoursFragment());
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if(itemId == R.id.nav_mes_cours) {
                remplacerFragment(new MesCoursFragment());
                return true;
            }
            else if (itemId == R.id.nav_catalogue) {
                remplacerFragment(new CatalogueFragment());
                return true;
            }
            else if (itemId == R.id.nav_messagerie) {
                remplacerFragment(new MessagerieFragment());
                return true;
            }
            else if (itemId == R.id.nav_profil) {
                remplacerFragment(new ProfilFragment());
                return true;
            }

            return false;
        });
    }

    private void remplacerFragment (Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_accueil, fragment)
                .commit();
    }
}