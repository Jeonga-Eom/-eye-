package com.example.eye.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import java.util.Calendar;

public class NotificationJobFireBaseService extends JobService {
    @Override
    public boolean onStartJob(JobParameters job) {
        SharedPreferences sh_Pref;

        Log.d("NotificationJobService", "onStartJob");
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 111, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        /**
         * Intent 플래그
         *    FLAG_ONE_SHOT : 한번만 사용하고 다음에 이 PendingIntent가 불려지면 Fail을 함
         *    FLAG_NO_CREATE : PendingIntent를 생성하지 않음. PendingIntent가 실행중인것을 체크를 함
         *    FLAG_CANCEL_CURRENT : 실행중인 PendingIntent가 있다면 기존 인텐트를 취소하고 새로만듬
         *    FLAG_UPDATE_CURRENT : 실행중인 PendingIntent가 있다면  Extra Data만 교체함
         */
        sh_Pref = this.getSharedPreferences("Time", MODE_PRIVATE);
        long finish = sh_Pref.getLong("Millis", 0) + 8 * 60 * 60 * 1000;

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(finish);
        long current = System.currentTimeMillis();

        if (c.getTimeInMillis() >= current) {
            long interval = c.getTimeInMillis() - current;
            Log.i("setting", (c.getTimeInMillis() / (1000 * 60 * 60)) % 24 + ":" + (c.getTimeInMillis() % (1000 * 60)) % 60);
            Log.i("current",  (current / (1000 * 60 * 60)) % 24 + ":" + (current% (1000 * 60)) % 60);
            //Toast.makeText(this, interval / (1000 * 60 * 60) + ":" + interval % (1000 * 60 * 60) / (1000 * 60), Toast.LENGTH_LONG).show();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + interval, pendingIntent); //10초뒤 알람
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                manager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + interval, pendingIntent);
            } else {
                manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + interval, pendingIntent);
            }
        }
        /**
         * AlarmType
         *    RTC_WAKEUP : 대기모드에서도 알람이 작동함을 의미함
         *    RTC : 대기모드에선 알람을 작동안함
         */

        return false; // Answers the question: "Is there still work going on?"
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false; // Answers the question: "Should this job be retried?"
    }
}
