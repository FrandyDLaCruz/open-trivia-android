package com.course.frandydlacruz.opentrivia.views;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.course.frandydlacruz.opentrivia.JobSchedule;
import com.course.frandydlacruz.opentrivia.JobScheduleCreator;
import com.course.frandydlacruz.opentrivia.R;
import com.course.frandydlacruz.opentrivia.TriviaApplication;
import com.course.frandydlacruz.opentrivia.interfaces.OnSaveListener;
import com.course.frandydlacruz.opentrivia.interfaces.ScoreDao;
import com.course.frandydlacruz.opentrivia.models.Result;
import com.course.frandydlacruz.opentrivia.models.UserScore;
import com.evernote.android.job.JobManager;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Question extends AppCompatActivity implements OnSaveListener {

    private TextView tvCategory;
    private TextView tvDifficulty;
    private TextView tvQuestion;
    private TextView tvAnswer;
    private Button btnTrue;
    private Button btnFalse;
    public UserScore userScore = new UserScore();
    public AsyncTask<UserScore, Void, Void> task;

    //ToDo  Remember each 2 min. that the game is there to play

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
    protected void onPause() {
        super.onPause();
        JobManager.create(this).addJobCreator(new JobScheduleCreator());
        JobSchedule.schedulePeriodic();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.lifes).setTitle(getResources().getString(R.string.main_menu_title_life) + TriviaApplication.lifes);
        return true;
    }

    public void validateAnswer(String answer, String button) {
        if(answer.equals(button)) {
            tvAnswer.setText("CORRECT!!");
            tvAnswer.setVisibility(View.VISIBLE);
            TriviaApplication.strikes++;
            Toast.makeText(Question.this, "You have answered " + TriviaApplication.strikes + " correct answers", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Question.this, MainActivity.class));
        }
        else {
            tvAnswer.setText("INCORRECT!!");
            tvAnswer.setVisibility(View.VISIBLE);
            TriviaApplication.lifes--;

            if(TriviaApplication.lifes == 0) {
                gameOver();
            }
            else {
                startActivity(new Intent(Question.this, MainActivity.class));
            }
        }
    }

    public void gameOver() {
        Toast.makeText(Question.this, "Game Over, you answered " + TriviaApplication.strikes + " correct answers", Toast.LENGTH_LONG).show();

        DialogUser dialogUser = new DialogUser();
        dialogUser.show(getSupportFragmentManager(), "Save Data");

        userScore.score = TriviaApplication.strikes;

        task = new AsyncTask<UserScore, Void, Void>() {
            @Override
            protected Void doInBackground(UserScore... userScores) {
                ScoreDao userScoreDao = ((TriviaApplication) getApplicationContext()).getTriviaDB().UserScoreDao();
                userScoreDao.insertUserScore(userScores[0]);
                return null;
            }
        };

        TriviaApplication.strikes = 0;
        TriviaApplication.lifes = 3;
    }

    @Override
    public void sendInput(String userName) {
        userScore.name = userName;
    }
}
