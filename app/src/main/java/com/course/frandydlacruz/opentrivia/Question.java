package com.course.frandydlacruz.opentrivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.course.frandydlacruz.opentrivia.entities.Result;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Question extends AppCompatActivity {

    private TextView tvCategory;
    private TextView tvDifficulty;
    private TextView tvQuestion;
    private TextView tvAnswer;
    private Button btnTrue;
    private Button btnFalse;

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
            tvAnswer.setText(result.getCorrectAnswer());

            btnTrue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(result.getCorrectAnswer().equals("True")) {
                        tvAnswer.setText("CORRECT!!");
                        tvAnswer.setVisibility(View.VISIBLE);
                    }
                    else {
                        tvAnswer.setText("INCORRECT!!");
                        tvAnswer.setVisibility(View.VISIBLE);
                    }
                }
            });

            btnFalse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(result.getCorrectAnswer().equals("False")) {
                        tvAnswer.setText("CORRECT!!");
                        tvAnswer.setVisibility(View.VISIBLE);
                    }
                    else {
                        tvAnswer.setText("INCORRECT!!");
                        tvAnswer.setVisibility(View.VISIBLE);
                    }
                }
            });
        }


    }
}
