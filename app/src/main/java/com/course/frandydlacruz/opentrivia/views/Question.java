package com.course.frandydlacruz.opentrivia.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.course.frandydlacruz.opentrivia.R;
import com.course.frandydlacruz.opentrivia.entities.Result;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Question extends AppCompatActivity {

    private TextView tvCategory;
    private TextView tvDifficulty;
    private TextView tvQuestion;
    private TextView tvAnswer;
    private Button btnTrue;
    private Button btnFalse;
//    public int lifes;

    //ToDo  Remember each 2 min. that the game is there to play
    //ToDo  Apply 3 lifes, if the lifes are over, show the user that he lose and go back to MainActivity with the 3 lifes again

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        tvCategory = findViewById(R.id.tv_category);
        tvDifficulty = findViewById(R.id.tv_difficulty);
        tvQuestion = findViewById(R.id.tv_question);
        tvAnswer = findViewById(R.id.tv_answer);
        btnTrue = findViewById(R.id.btn_true);
        btnFalse = findViewById(R.id.btn_false);

        Intent intent = getIntent();

        if (intent.hasExtra("RESULT")) {
            final Result result = intent.getParcelableExtra("RESULT");
            String question = null;
            String category = null;

            try {
                question = URLDecoder.decode(result.getQuestion(), "UTF-8");
                category = URLDecoder.decode(result.getCategory(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            tvCategory.setText(category);
            tvDifficulty.setText(result.getDifficulty());
            tvQuestion.setText(question);

            btnTrue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validateAnswer(result.getCorrectAnswer(), "True");
                }
            });

            btnFalse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validateAnswer(result.getCorrectAnswer(), "False");
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.lifes).setTitle(getResources().getString(R.string.main_menu_title_life) + MainActivity.lifes);
        return true;
    }

    public void validateAnswer(String answer, String button) {
        if(answer.equals(button)) {
            tvAnswer.setText("CORRECT!!");
            tvAnswer.setVisibility(View.VISIBLE);
            MainActivity.Strikes++;
            Toast.makeText(Question.this, "You have answered " + MainActivity.Strikes + " correct answers", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Question.this, MainActivity.class));
        }
        else {
            tvAnswer.setText("INCORRECT!!");
            tvAnswer.setVisibility(View.VISIBLE);
            MainActivity.lifes--;
            startActivity(new Intent(Question.this, MainActivity.class));

            if(MainActivity.lifes == 0) {
                Toast.makeText(Question.this, "Game Over, you answered " + MainActivity.Strikes + " correct answers", Toast.LENGTH_LONG).show();
                //ToDo  Save the user name Using SharedPreference
                //ToDo  Save the score of the user Using ROOM
                MainActivity.Strikes = 0;
                MainActivity.lifes = 3;
                startActivity(new Intent(Question.this, LobbyActivity.class));
            }
        }
    }
}
