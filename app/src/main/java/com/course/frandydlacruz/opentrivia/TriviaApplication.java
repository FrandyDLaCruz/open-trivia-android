package com.course.frandydlacruz.opentrivia;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.arch.persistence.room.Room;
import android.content.ComponentName;
import android.os.Build;

import com.course.frandydlacruz.opentrivia.models.TriviaDatabase;
import com.evernote.android.job.JobManager;

public class TriviaApplication extends Application {
    public TriviaDatabase db;
    public static int lifes = 3;
    public static int strikes = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
        // when upgrading versions, kill the original tables by using fallbackToDestructiveMigration()
        db = Room.databaseBuilder(TriviaApplication.this, TriviaDatabase.class, TriviaDatabase.DBNAME).fallbackToDestructiveMigration().build();

//        ComponentName componentName = new ComponentName(TriviaApplication.this, JobSchedule.class);
//        JobInfo info = new JobInfo.Builder(1, componentName)
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
////                .setPeriodic(1000)
//                .setMinimumLatency(10000)
//                .setPersisted(false)
//                .build();
//
//        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//        scheduler.schedule(info);

//        JobManager.create(this).addJobCreator(new JobScheduleCreator());
//        JobSchedule.schedulePeriodic();
    }

    public TriviaDatabase getTriviaDB() {
        return db;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("default", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
