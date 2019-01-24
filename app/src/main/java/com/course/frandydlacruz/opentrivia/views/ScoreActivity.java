package com.course.frandydlacruz.opentrivia.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.course.frandydlacruz.opentrivia.R;

public class ScoreActivity extends AppCompatActivity {

    private RecyclerView rvPlayerScore;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        rvPlayerScore = findViewById(R.id.rv_player_score);
        rvPlayerScore.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        rvPlayerScore.setLayoutManager(layoutManager);
    }
}
