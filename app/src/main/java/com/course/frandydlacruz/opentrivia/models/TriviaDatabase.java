package com.course.frandydlacruz.opentrivia.models;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

import com.course.frandydlacruz.opentrivia.interfaces.ScoreDao;

@Database(entities = UserScore.class, version = 4)
public abstract class TriviaDatabase extends RoomDatabase {

    public abstract ScoreDao UserScoreDao();
    public static final String DBNAME = "TriviaDB";

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
