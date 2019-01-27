package com.course.frandydlacruz.opentrivia.Jobs;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.course.frandydlacruz.opentrivia.R;
import com.course.frandydlacruz.opentrivia.views.LobbyActivity;
import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

import java.util.concurrent.TimeUnit;

public class JobSchedule extends Job /*JobService*/ {

    static final String TAG = "show_notification_job_tag";

    @NonNull
    @Override
    protected Result onRunJob(@NonNull Params params) {

//        if(!Helper.isAppRunning(getContext(), "com.course.frandydlacruz.opentrivia")) {

            Intent notificationIntent = new Intent(getContext(), LobbyActivity.class);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, notificationIntent, 0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "default")
                    .setSmallIcon(R.drawable.ic_favorite_heart_button)
                    .setContentTitle("Hey! Open trivia is waiting for you")
                    .setContentText("Long time without playing Open Trivia, come and play!")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
            notificationManager.notify(0, builder.build());
//        }

        return Result.SUCCESS;
    }

    public static void schedulePeriodic() {
        new JobRequest.Builder(JobSchedule.TAG)
                .setExact(TimeUnit.SECONDS.toMillis(10))
                .setUpdateCurrent(true)
                .build()
                .schedule();
    }

//    @Override
//    public boolean onStartJob(JobParameters params) {
//
//        if(!Helper.isAppRunning(JobSchedule.this, "com.course.frandydlacruz.opentrivia")) {
//
//            Intent notificationIntent = new Intent(this, LobbyActivity.class);
//            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default")
//                    .setSmallIcon(R.drawable.ic_favorite_heart_button)
//                    .setContentTitle("Hey! Open trivia is waiting for you")
//                    .setContentText("Long time without playing Open Trivia, come and play!")
//                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                    .setContentIntent(pendingIntent)
//                    .setAutoCancel(true);
//
//            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//            notificationManager.notify(0, builder.build());
//        }
//        return false;
//    }
//
//    @Override
//    public boolean onStopJob(JobParameters params) {
//        return true;
//    }
}
