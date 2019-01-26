package com.course.frandydlacruz.opentrivia.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.course.frandydlacruz.opentrivia.R;
import com.course.frandydlacruz.opentrivia.ViewHolders.ScoreViewHolder;
import com.course.frandydlacruz.opentrivia.models.UserScore;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreViewHolder> {

    private List<UserScore> userScore;

    public ScoreAdapter(List<UserScore> userScore) {
        this.userScore = userScore;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.score_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        boolean shouldAttachToParentInmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentInmediately);

        ScoreViewHolder scoreViewHolder = new ScoreViewHolder(view);

        return scoreViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        holder.bind(userScore.get(position).name, userScore.get(position).score);
    }

    @Override
    public int getItemCount() {
        return userScore.size();
    }
}
