package com.mathrix;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class PlayOrLearn extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_or_learn);

        findViewById(R.id.go_back).setOnClickListener(view -> finish());

        findViewById(R.id.button_play).setOnClickListener(view -> {
            Intent intent = new Intent(PlayOrLearn.this, Game.class);
            startActivity(intent);
        });

        findViewById(R.id.button_learn).setOnClickListener(view -> {
            Intent intent = new Intent(PlayOrLearn.this, Learn.class);
            startActivity(intent);
        });
    }
}
