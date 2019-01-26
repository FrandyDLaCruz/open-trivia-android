package com.course.frandydlacruz.opentrivia;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

public class JobScheduleCreator implements JobCreator {
    @Nullable
    @Override
    public Job create(@NonNull String tag) {
        switch (tag) {
            case JobSchedule.TAG:
                return new JobSchedule();
            default:
                return null;
        }
    }
}
