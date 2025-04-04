package com.mathrix;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class Menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        findViewById(R.id.play).setOnClickListener(view -> {
            Intent intent = new Intent(Menu.this, PlayOrLearn.class);
            startActivity(intent);
        });

        showWelcomeDialog();
    }

    private void showWelcomeDialog() {
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        boolean isFirstLaunch = prefs.getBoolean("isFirstLaunch", true);

        if (isFirstLaunch) {
            FragmentManager fm = getSupportFragmentManager();
            WelcomeDialog welcomeDialog = new WelcomeDialog();
            welcomeDialog.show(fm, "welcomeDialog");

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("isFirstLaunch", false);
            editor.apply();
        }
    }
}
