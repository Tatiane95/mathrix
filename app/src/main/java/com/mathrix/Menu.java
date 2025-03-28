package com.mathrix;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        @SuppressLint("WrongViewCast") ImageButton barraMenu = findViewById(R.id.imageView);

        barraMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu.this, "Menu clicado!", Toast.LENGTH_SHORT).show();


            }
        });
    }
}
