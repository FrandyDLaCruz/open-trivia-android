package com.course.frandydlacruz.opentrivia.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.course.frandydlacruz.opentrivia.models.UserScore;

import java.util.List;

@Dao
public interface ScoreDao {

    @Query("SELECT * FROM UserScore ORDER BY score DESC")
    List<UserScore> getAllUsersScore();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertUserScore(UserScore userScore);
}
