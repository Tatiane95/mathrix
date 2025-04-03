package com.mathrix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Menu2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu2);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Aqui, utilizamos o ID do botão correto, que é imageButton5 no seu XML
        ImageButton barraMenu = findViewById(R.id.imageButton5);

        barraMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Exibe uma mensagem Toast para confirmar o clique
                Toast.makeText(Menu2.this, "Menu clicado!", Toast.LENGTH_SHORT).show();

                // Agora, direciona para a Activity Menu3
                Intent intent = new Intent(Menu2.this, Menu3.class); // Troque para Menu3.class
                startActivity(intent);
            }
        });
    }
}
