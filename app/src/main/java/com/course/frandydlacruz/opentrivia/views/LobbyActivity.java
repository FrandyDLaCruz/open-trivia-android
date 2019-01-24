package com.course.frandydlacruz.opentrivia.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.course.frandydlacruz.opentrivia.R;

public class LobbyActivity extends AppCompatActivity {

    private Button newGame;
    private Button score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        newGame = findViewById(R.id.btn_new_game);
        score = findViewById(R.id.btn_score);

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LobbyActivity.this, MainActivity.class));
            }
        });

        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LobbyActivity.this, ScoreActivity.class));
            }
        });
    }
}
