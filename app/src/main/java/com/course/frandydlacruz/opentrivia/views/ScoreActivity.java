package com.course.frandydlacruz.opentrivia.views;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.course.frandydlacruz.opentrivia.Adapters.ScoreAdapter;
import com.course.frandydlacruz.opentrivia.R;
import com.course.frandydlacruz.opentrivia.TriviaApplication;
import com.course.frandydlacruz.opentrivia.interfaces.ScoreDao;
import com.course.frandydlacruz.opentrivia.models.UserScore;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ScoreActivity extends AppCompatActivity {

    private RecyclerView rvPlayerScore;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    public AsyncTask<Void, Void, List<UserScore>> task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        rvPlayerScore = findViewById(R.id.rv_player_score);
        rvPlayerScore.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        rvPlayerScore.setLayoutManager(layoutManager);

        try {
            adapter = new ScoreAdapter(get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rvPlayerScore.setAdapter(adapter);
    }

    public List<UserScore> get() throws ExecutionException, InterruptedException {
        task = new AsyncTask<Void, Void, List<UserScore>>() {
            @Override
            protected List<UserScore> doInBackground(Void... voids) {
                ScoreDao userScoreDao = ((TriviaApplication) getApplicationContext()).getTriviaDB().UserScoreDao();
                List<UserScore> scores = userScoreDao.getAllUsersScore();
                return scores;
            }
        };

        return task.execute().get();
    }
}
