package com.course.frandydlacruz.opentrivia.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.course.frandydlacruz.opentrivia.R;

public class ScoreViewHolder extends RecyclerView.ViewHolder {

    private TextView tvUser;
    private TextView tvScore;

    public ScoreViewHolder(@NonNull View itemView) {
        super(itemView);
        tvUser = itemView.findViewById(R.id.tvUser);
        tvScore = itemView.findViewById(R.id.tvScore);
    }

    public void bind(String user, int score) {
        tvUser.setText(user);
        tvScore.setText(String.valueOf((score)));
    }
}
