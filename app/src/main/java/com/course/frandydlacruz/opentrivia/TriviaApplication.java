package com.course.frandydlacruz.opentrivia;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.course.frandydlacruz.opentrivia.models.TriviaDatabase;

public class TriviaApplication extends Application {
    public TriviaDatabase db;
    public static int lifes = 1;
    public static int strikes = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        // when upgrading versions, kill the original tables by using fallbackToDestructiveMigration()
        db = Room.databaseBuilder(TriviaApplication.this, TriviaDatabase.class, TriviaDatabase.DBNAME).fallbackToDestructiveMigration().build();
    }

    public TriviaDatabase getTriviaDB() {
        return db;
    }
}
