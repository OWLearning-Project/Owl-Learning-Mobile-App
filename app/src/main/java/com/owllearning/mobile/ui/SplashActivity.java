package com.owllearning.mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.owllearning.mobile.R;
import com.owllearning.mobile.utils.SessionManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logoSplash = findViewById(R.id.logo_splash);
        logoSplash.setAlpha(0f);
        logoSplash.setTranslationY(50f);
        logoSplash.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(1500)
                .withEndAction(() -> {
                    SessionManager sessionManager = new SessionManager(this);
                    if (sessionManager.isLoggedIn()) {
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(this, ConnexionActivity.class);
                        startActivity(intent);
                    }
                    finish();
                });
    }
}