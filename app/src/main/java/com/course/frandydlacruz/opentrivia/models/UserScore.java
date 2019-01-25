package com.course.frandydlacruz.opentrivia.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class UserScore {
    @NonNull
    @PrimaryKey
    public String name;

    public int score;
}
