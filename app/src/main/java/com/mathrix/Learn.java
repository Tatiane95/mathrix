package com.mathrix;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class Learn extends AppCompatActivity {

    private static final String PREFS_NAME = "LearnPrefs";
    private static final String KEY_GUIDE_SHOWN = "guideShown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean guideShown = prefs.getBoolean(KEY_GUIDE_SHOWN, false);

        if (!guideShown) {
            showModal(
                    getString(R.string.what_is_matriz),
                    getString(R.string.guide_content),
                    false,
                    "matriz",
                    false
            );
            prefs.edit().putBoolean(KEY_GUIDE_SHOWN, true).apply();
        }

        LinearLayout scoreButton = findViewById(R.id.score_button);
        LinearLayout conclusionButton = findViewById(R.id.conclusion_button);
        LinearLayout solutionButton = findViewById(R.id.solution_button);

        scoreButton.setOnClickListener(v -> showModal(getString(R.string.addition), getString(R.string.addition_content), true, "addition", true));
        conclusionButton.setOnClickListener(v -> showModal(getString(R.string.subtraction), getString(R.string.subtraction_content), true, "subtraction", true));
        solutionButton.setOnClickListener(v -> showModal(getString(R.string.multiplication), getString(R.string.multiplication_content), true, "multiplication", true));

        findViewById(R.id.go_back).setOnClickListener(v -> finish());
    }

    private void showModal(String title, String content, boolean showBackArrow, String category, boolean isFirstPart) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        LearnModalFragment modalFragment = LearnModalFragment.newInstance(title, content, showBackArrow, isFirstPart, category);
        modalFragment.show(fragmentManager, "LearnModal");
    }
}
