package com.mathrix;

import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Game extends AppCompatActivity {

    private TextView matrixAText, matrixBText, operationSymbol, scoreText;
    private ImageView heart1, heart2, heart3;
    private LinearLayout[] optionLayouts;
    private TextView[] optionTexts;
    private int[][] matrixA;
    private int[][] matrixB;
    private int[][] correctResult;
    private String currentOperation;
    private int score = 0;
    private int lives = 3;
    private final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        matrixAText = findViewById(R.id.matrix_a);
        matrixBText = findViewById(R.id.matrix_b);
        operationSymbol = findViewById(R.id.operation_symbol);
        scoreText = findViewById(R.id.score);
        heart1 = findViewById(R.id.heart1);
        heart2 = findViewById(R.id.heart2);
        heart3 = findViewById(R.id.heart3);

        optionLayouts = new LinearLayout[]{
                findViewById(R.id.option1),
                findViewById(R.id.option2),
                findViewById(R.id.option3),
                findViewById(R.id.option4)
        };

        optionTexts = new TextView[]{
                findViewById(R.id.option1_text),
                findViewById(R.id.option2_text),
                findViewById(R.id.option3_text),
                findViewById(R.id.option4_text)
        };

        for (LinearLayout layout : optionLayouts) {
            layout.setOnClickListener(this::handleAnswer);
        }

        updateUI();
        generateQuestion();
    }

    private void generateQuestion() {
        matrixA = generateMatrix();
        matrixB = generateMatrix();
        currentOperation = pickRandomOperation();

        switch (currentOperation) {
            case "Adição":
                correctResult = addMatrices(matrixA, matrixB);
                operationSymbol.setText(getString(R.string.addition_symbol));
                break;
            case "Subtração":
                correctResult = subtractMatrices(matrixA, matrixB);
                operationSymbol.setText(getString(R.string.subtraction_symbol));
                break;
            case "Multiplicação":
                correctResult = multiplyMatrices(matrixA, matrixB);
                operationSymbol.setText(getString(R.string.multiplication_symbol));
                break;
        }

        matrixAText.setText(formatMatrix(matrixA));
        matrixBText.setText(formatMatrix(matrixB));

        int correctIndex = random.nextInt(4);
        for (int i = 0; i < 4; i++) {
            if (i == correctIndex) {
                optionTexts[i].setText(formatMatrix(correctResult));
            } else {
                optionTexts[i].setText(formatMatrix(generateMatrix()));
            }
        }
    }

    private void handleAnswer(View view) {
        int selectedIndex = -1;

        for (int i = 0; i < optionLayouts.length; i++) {
            if (view.getId() == optionLayouts[i].getId()) {
                selectedIndex = i;
                break;
            }
        }

        if (selectedIndex == -1) return;

        String selectedText = optionTexts[selectedIndex].getText().toString();
        if (selectedText.equals(formatMatrix(correctResult))) {
            score += 100;
            Toast.makeText(this, "Correto!", Toast.LENGTH_SHORT).show();
        } else {
            lives--;
            Toast.makeText(this, "Errado!", Toast.LENGTH_SHORT).show();
        }

        updateUI();

        if (lives <= 0) {
            Toast.makeText(this, "Fim de jogo! Pontuação final: " + score, Toast.LENGTH_LONG).show();
            finish();
        } else {
            generateQuestion();
        }
    }

    private void updateUI() {
        scoreText.setText("Pontuação:\n" + score);
        heart1.setVisibility(lives >= 1 ? View.VISIBLE : View.INVISIBLE);
        heart2.setVisibility(lives >= 2 ? View.VISIBLE : View.INVISIBLE);
        heart3.setVisibility(lives >= 3 ? View.VISIBLE : View.INVISIBLE);
    }

    private int[][] generateMatrix() {
        return new int[][]{
                {random.nextInt(10), random.nextInt(10)},
                {random.nextInt(10), random.nextInt(10)}
        };
    }

    private String pickRandomOperation() {
        String[] ops = {
                getString(R.string.addition),
                getString(R.string.subtraction),
                getString(R.string.multiplication)
        };
        return ops[random.nextInt(3)];
    }

    private int[][] addMatrices(int[][] a, int[][] b) {
        return new int[][]{
                {a[0][0] + b[0][0], a[0][1] + b[0][1]},
                {a[1][0] + b[1][0], a[1][1] + b[1][1]}
        };
    }

    private int[][] subtractMatrices(int[][] a, int[][] b) {
        return new int[][]{
                {a[0][0] - b[0][0], a[0][1] - b[0][1]},
                {a[1][0] - b[1][0], a[1][1] - b[1][1]}
        };
    }

    private int[][] multiplyMatrices(int[][] a, int[][] b) {
        return new int[][]{
                {
                        a[0][0] * b[0][0] + a[0][1] * b[1][0],
                        a[0][0] * b[0][1] + a[0][1] * b[1][1]
                },
                {
                        a[1][0] * b[0][0] + a[1][1] * b[1][0],
                        a[1][0] * b[0][1] + a[1][1] * b[1][1]
                }
        };
    }

    private String formatMatrix(int[][] matrix) {
        int maxLenCol1 = Math.max(String.valueOf(matrix[0][0]).length(), String.valueOf(matrix[1][0]).length());
        int maxLenCol2 = Math.max(String.valueOf(matrix[0][1]).length(), String.valueOf(matrix[1][1]).length());

        String row1 = String.format("|%" + maxLenCol1 + "d %" + maxLenCol2 + "d|", matrix[0][0], matrix[0][1]);
        String row2 = String.format("|%" + maxLenCol1 + "d %" + maxLenCol2 + "d|", matrix[1][0], matrix[1][1]);

        return row1 + "\n" + row2;
    }
}
